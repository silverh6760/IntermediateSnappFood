package controller;

import model.dao.FoodDao;
import model.entity.Food;
import model.dto.FoodDto;

import java.util.List;

public class FoodService {

    FoodDao foodDao=new FoodDao();

    public boolean addFoodToRestaurant(FoodDto foodDto, String nameOfRest) {
        boolean answer=foodDao.addFoodToRestaurant(foodDto,nameOfRest);
        return answer;
    }

    public List<Food> seeMenuOfRestaurant(String nameOfRest) {
        List<Food> foodsOfMenu=foodDao.sseMenuOfRestaurant(nameOfRest);
        return foodsOfMenu;
    }
}
