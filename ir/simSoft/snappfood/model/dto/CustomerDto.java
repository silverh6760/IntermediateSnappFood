package ir.simSoft.snappfood.model.dto;

import java.util.Date;

public class CustomerDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date registerDate;
    private Long totalPurchase;

    public CustomerDto(String firstName, String lastName, String phoneNumber, Date registerDate, Long totalPurchase) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.registerDate = registerDate;
        this.totalPurchase = totalPurchase;
    }

    public CustomerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Long getTotalPurchase() {
        return totalPurchase;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registerDate=" + registerDate +
                ", totalPurchase=" + totalPurchase +
                '}';
    }
}
