package ru.netology;

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

        this.department = setDepartment();

        this.email = setEmail();

        this.password = createPassword();
    }

    public String setDepartment(){
        System.out.println("Введите номер департамента.\n1.Департамент продаж\n2.Разработка\n" +
                "3.Бухгалтерия\n0.Нет департамента");
        String depNumber = sc.next();

        boolean flag = false;
        do{
            switch (depNumber) {
                case "1":
                    department = "Департамент продаж";
                    break;
                case "2":
                    department = "Разработка";
                    break;
                case "3":
                    department = "Бухгалтерия";
                    break;
                case "0":
                    department = "Нет департамента";
                    break;
                default:
                    System.out.println("Некорректный ввод, повторите");
                    flag = true;
            }
        }while (flag);
        return department;
    }


}
