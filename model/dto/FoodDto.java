package model.dto;

import model.entity.FoodType;

public class FoodDto {
    private String name;
    private FoodType type;
    private Long price;
    private Integer count;

    public FoodDto() {
    }

    public FoodDto(String name, FoodType type, Long price, Integer count) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
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

    @Override
    public String toString() {
        return "FoodDto{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
