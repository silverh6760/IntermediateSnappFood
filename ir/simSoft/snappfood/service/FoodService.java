package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.FoodDao;
import ir.simSoft.snappfood.model.entity.Food;
import ir.simSoft.snappfood.model.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FoodService {

    FoodDao foodDao;
    @Autowired
    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public boolean addFoodToRestaurant(FoodDto foodDto, String nameOfRest) {
        boolean answer=foodDao.addFoodToRestaurant(foodDto,nameOfRest);
        return answer;
    }

    public List<Food> seeMenuOfRestaurant(String nameOfRest) {
        List<Food> foodsOfMenu=foodDao.sseMenuOfRestaurant(nameOfRest);
        return foodsOfMenu;
    }
}
