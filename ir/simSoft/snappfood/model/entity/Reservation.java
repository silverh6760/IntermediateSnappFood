package ir.simSoft.snappfood.model.entity;


import javax.persistence.*;
import java.util.Date;
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer factorNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;
    private Integer count;
    @Temporal(value = TemporalType.DATE)
    private Date date;

    public Reservation() {
    }

    public Reservation(Integer factorNumber, Customer customer, Food food, Integer count, Date date) {
        this.factorNumber = factorNumber;
        this.customer = customer;
        this.food = food;
        this.count = count;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(Integer factorNumber) {
        this.factorNumber = factorNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "factorNumber=" + factorNumber +
                ", customer=" + customer +
                ", food=" + food +
                ", count=" + count +
                ", date=" + date +
                '}';
    }
}
