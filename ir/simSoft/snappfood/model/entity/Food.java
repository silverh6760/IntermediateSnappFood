package ir.simSoft.snappfood.model.entity;

import javax.persistence.*;

@Entity
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private FoodType type;
    private Long price;
    private Integer count;
    @ManyToOne
    private Restaurant restaurant;

    public Food() {
    }

    public Food(String name, FoodType type, Long price, Integer count, Restaurant restaurant) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
