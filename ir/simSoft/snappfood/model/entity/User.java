package ir.simSoft.snappfood.model.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String firstName;
    private String lastName;
    private String postalCode;

    public User() {
    }

    public User(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
