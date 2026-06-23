package model;

public class Admin extends User {

    private String password;

    public Admin() {
        password = "";
    }

    public Admin(int userId, String name, String password) {
        super(userId, name);
        this.password = password;
    }

    public boolean checkPassword(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}