package model.entity;

import javax.persistence.Entity;

@Entity
public class Admin extends User{
    private String username;
    private String password;

    public Admin() {

    }

    public Admin(String firstName, String lastName, String postalCode, String username, String password) {
        super(firstName, lastName, postalCode);
        this.username = username;
        this.password = password;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
