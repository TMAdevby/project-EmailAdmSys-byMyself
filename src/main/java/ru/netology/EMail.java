package ru.netology;

import java.security.SecureRandom;
import java.util.Scanner;

public class EMail {

    Scanner sc = new Scanner(System.in);

    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;
    private int mailCapacity = 500;
    private String spare_email;

    public EMail(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

        this.department = selectDepartment();

        this.email = selectEmail();

        this.password = generatePassword(8);
    }

    public String selectDepartment(){

        while(true){
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

    public String generatePassword(int length){
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

    public void showInformation(){
        System.out.println("Информация о работнике:");
        System.out.println("Имя : " + firstName);
        System.out.println("Фамилия : " + lastName);
        System.out.println("Департамент : " + department);
        System.out.println("Email : " + email);
        System.out.println("Пароль : " + password);
        System.out.println("Вместимость почты : " + mailCapacity + " Mb");
        System.out.println("Запасной пароль : " + spare_email);
    }
}
