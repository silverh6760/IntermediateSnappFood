package ir.simSoft.snappfood.model.dto;

public class resDto {
    private String name;
    private Integer area;
    private Long service;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "resDto{" +
                "name='" + name + '\'' +
                ", area=" + area +
                ", service=" + service +
                '}';
    }
}
