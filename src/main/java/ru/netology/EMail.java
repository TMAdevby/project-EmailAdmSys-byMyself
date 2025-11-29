package ru.netology;

public class EMail {

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


}
