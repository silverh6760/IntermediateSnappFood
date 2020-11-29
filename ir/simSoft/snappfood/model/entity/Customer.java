package ir.simSoft.snappfood.model.entity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Entity
public class Customer extends User {
    private String phoneNumber;
    @Temporal(value = TemporalType.DATE)
    private Date registerDate;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String postalCode,
                    String phoneNumber, Date registerDate) {
        super(firstName, lastName, postalCode);
        this.phoneNumber = phoneNumber;
        this.registerDate = registerDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }


}
