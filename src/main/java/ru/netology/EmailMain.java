package ru.netology;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EmailMain {
    public static void main(String[] args) {
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
                    "6.Прочитать из файа");

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
            }
        }

    }
}