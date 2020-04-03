package ru;

public class RegisterForm {
    private String name;
    private String login;
    private String phone;

    public RegisterForm(String name, String login, String phone) {
        this.name = name;
        this.login = login;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
