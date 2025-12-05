package ru.netology;

import java.util.Scanner;

public class EmailMain {
    public static void main(String[] args) {
        System.out.println("Java version: " + System.getProperty("java.version"));
        Scanner sc = new Scanner(System.in);
            System.out.println("Введите имя:");
            String fName = sc.next();
            System.out.println("Введите фамилию:");
            String lName = sc.next();

            EMail email = new EMail(fName, lName);

            while (true) {
                System.out.println("Выберите номер действия:\n" +
                        "1.Показать информацию о работнике\n" +
                        "2.Изменить пароль\n" +
                        "3.Изменить вместимость ящика\n" +
                        "4.Ввести альтернативный email\n" +
                        "5.Записать в файл\n" +
                        "6.Прочитать из файа\n" +
                        "7.Сериализовать в файл\n" +
                        "8.Десериализовать из файла\n" +
                        "0.Выйти"
                );

                String choice = sc.next();

                switch (choice) {
                    case "1":
                        email.showInformation();
                        break;
                    case "2":
                        email.changePassword();
                        break;
                    case "3":
                        email.changeEmailCapacity();
                        break;
                    case "4":
                        email.enterAlternEmail();
                        break;
                    case "5":
                        email.writeToFile();
                        break;
                    case "6":
                        email.readFromFile();
                        break;
                    case "7":
                        email.serializeToFile(email);
                        break;
                    case "8":
                        email.deserializeFromFile();
                        break;
                    case "0":
                        System.out.println("Выход из программы");
                        return;
                    default:
                        System.out.println("Неверный ввод. Повторите");
                        break;
                }
            }

    }
}