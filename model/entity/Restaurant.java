package model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer area;
    private Long service;
//    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
//    private Set<Food> foodSet=new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(String name, Integer area, Long service) {
        this.name = name;
        this.area = area;
        this.service = service;
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
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", area=" + area +
                ", service=" + service +
                '}';
    }
}
