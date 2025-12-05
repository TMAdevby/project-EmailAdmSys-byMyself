package ru.netology;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EMail implements Serializable {
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private transient Scanner sc = new Scanner(System.in);

    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;
    private int mailCapacity = 500;
    private String spare_email;

    public EMail(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.department = selectDepartment();

        this.email = selectEmail();

        this.password = generatePassword(8);
    }

    public String selectDepartment() {

        while (true) {
            System.out.println("Введите номер департамента.\n1.Департамент продаж\n2.Разработка\n" +
                    "3.Бухгалтерия\n0.Нет департамента");
            String depNumber = sc.next();

            switch (depNumber) {
                case "1":
                    return "Департамент продаж";
                case "2":
                    return "Разработка";
                case "3":
                    return "Бухгалтерия";
                case "0":
                    return "Нет департамента";
                default:
                    System.out.println("Некорректный ввод, повторите");
            }
        }
    }

    public String selectEmail() {
        System.out.println("Ваш email :");
        String email = lastName + firstName + "@mail.ru";
        System.out.println(email);
        return email;
    }

    public String generatePassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive");
        }
        String bLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String signs = "!@#$%&";
        String value = bLetters + letters + numbers + signs;

        SecureRandom random = new SecureRandom();
        StringBuilder pass = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char ch = value.charAt(random.nextInt(value.length()));
            pass.append(ch);
        }
        return pass.toString();
    }

    public void showInformation() {
        System.out.println("Информация о работнике:");
        System.out.println("Имя : " + firstName);
        System.out.println("Фамилия : " + lastName);
        System.out.println("Департамент : " + department);
        System.out.println("Email : " + email);
        System.out.println("Пароль : " + password);
        System.out.println("Вместимость почты : " + mailCapacity + " Mb");
        System.out.println("Запасная почта : " + spare_email);
    }

    public void changePassword() {
        while (true) {
            System.out.println("Хотите поменять пароль? Ответьте \"Y/y\" или \"N/n\" ");
            char choice = sc.next().charAt(0);
            switch (Character.toLowerCase(choice)) {
                case 'y':
                    System.out.println("Введите новый пароль. Минимум 6 символов ");
                    String newPassword = sc.next();
                    if (newPassword == null || newPassword.length() < 6) {
                        System.out.println("Пароль слишком короткий, он должен содержать минимум 6 символов");
                        continue;
                    }
                    password = newPassword;
                    System.out.println("Установлен новый пероль: " + password);
                    return;
                case 'n':
                    System.out.println("Вы оставили старый пароль");
                    return;
                default:
                    System.out.println("Ответ не корректен. Введите \"Y/y\" или \"N/n\"");
                    break;
            }
        }
    }

    public void changeEmailCapacity() {
        while (true) {
            System.out.println("Текущая вместимость ящика ровна " + mailCapacity + " Mb");
            System.out.println("Хотите поменять вместимость ящика? Ответьте \"Y/y\" или \"N/n\" ");
            char choice = sc.next().charAt(0);
            switch (Character.toLowerCase(choice)) {
                case 'y':
                    System.out.println("Введите желаемый размер ящика");
                    try {
                        int newCapacity = Integer.parseInt(sc.next());
                        if (newCapacity < 0) {
                            System.out.println("Размер ящика не может быть отридцательным числом");
                            break;
                        } else if (newCapacity > 100_000) {
                            System.out.println("Размер не может быть больше 100 Гб");
                            break;
                        } else {
                            mailCapacity = newCapacity;
                            System.out.println("Установлен новый размер ящика " + newCapacity + " Mb");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Введено не целое число. Попробуйте снова");
                        break;
                    }
                case 'n':
                    System.out.println("Размер ящика остался прежним");
                    break;
                default:
                    System.out.println("Ответ не корректен. Введите \"Y/y\" или \"N/n\"");
                    break;
            }
        }
    }

    public void enterAlternEmail() {
        while (true) {
            System.out.println("Введите альтернативный email при надобности, если он не нужен введите 'N/n' ");
            String altEmail = sc.next();
            if (altEmail.equalsIgnoreCase("n")) {
                System.out.println("Альтернативный email не нужен");
                return;
            }
            if (altEmail.matches(EMAIL_PATTERN)) {
                System.out.println("Альтернативный email : " + altEmail);
                spare_email = altEmail;
                return;
            } else {
                System.out.println("Некорректный email. Повторите ввод");
                break;
            }
        }
    }

    public void writeToFile() {
        Path path = Paths.get("D:\\ITStep\\2025\\Small progects\\emailsFile");
        try {
                Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String line = String.format("%s,%s,%s,%d,%s%n",
                firstName + " " + lastName,
                department,
                email,
                mailCapacity,
                spare_email != null ? spare_email : ""
        );

        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)){
            bufferedWriter.write(line);
            System.out.println("Информация записана");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл",e);
        }
    }

    public void readFromFile() {
        Path path = Paths.get("D:\\ITStep\\2025\\Small progects\\emailsFile");
        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения из файла",e);
        }
    }

    public void serializeToFile(Serializable email) {
        Path path = Paths.get("employee.bin");
        Path parent = path.getParent();
        if (parent != null) {
            try {
                Files.createDirectories(parent);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось создать директорию", e);
            }
        }
        try try (FileOutputStream fos = new FileOutputStream(path.toFile(), true);
                 // Обёртка, чтобы пропустить заголовок при APPEND
                 MyObjectOutputStream oos = new MyObjectOutputStream(fos)) {
            oos.writeObject(email);
            System.out.println("Объект сериализован и добавлен в файл");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EMail> deserializeFromFile() {
        Path path = Paths.get("employee.bin");
        List<EMail> emails = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(
                Files.newInputStream(path))) {
            while(true){
                EMail email = (EMail) objectInputStream.readObject();
                emails.add(email);
                System.out.println(email);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Класс EMail не найден",e);
        } catch (IOException e) {
            if (e instanceof EOFException) {
            } else {
                throw new RuntimeException("Ошибка чтения файла", e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return emails;
    }
}
