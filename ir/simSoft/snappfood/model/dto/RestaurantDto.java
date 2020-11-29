package ir.simSoft.snappfood.model.dto;

public class RestaurantDto {
    private String nameOfFood;
    private String nameOfRest;
    private Integer maxOfCount;
    private Integer area;
    private Long totalService;

    public RestaurantDto() {
    }

    public RestaurantDto(String nameOfFood, String nameOfRest, Integer maxOfCount,
                         Integer area, Long totalService) {
        this.nameOfFood = nameOfFood;
        this.nameOfRest = nameOfRest;
        this.maxOfCount = maxOfCount;
        this.area = area;
        this.totalService = totalService;
    }

    public String getNameOfFood() {
        return nameOfFood;
    }

    public void setNameOfFood(String nameOfFood) {
        this.nameOfFood = nameOfFood;
    }

    public String getNameOfRest() {
        return nameOfRest;
    }

    public void setNameOfRest(String nameOfRest) {
        this.nameOfRest = nameOfRest;
    }

    public Integer getMaxOfCount() {
        return maxOfCount;
    }

    public void setMaxOfCount(Integer maxOfCount) {
        this.maxOfCount = maxOfCount;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getTotalService() {
        return totalService;
    }

    public void setTotalService(Long totalService) {
        this.totalService = totalService;
    }

    @Override
    public String toString() {
        return "RestaurantDto{" +
                "nameOfFood='" + nameOfFood + '\'' +
                ", nameOfRest='" + nameOfRest + '\'' +
                ", area=" + area +
                ", totalService=" + totalService +
                '}';
    }
}
