package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.FoodDao;
import ir.simSoft.snappfood.model.entity.Food;
import ir.simSoft.snappfood.model.dto.FoodDto;
import ir.simSoft.snappfood.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
@Service
public class FoodService {

   private FoodDao foodDao;
    @Autowired
    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Transactional
    public boolean addFoodToRestaurant(FoodDto foodDto, Restaurant restaurant) {
        Food oneFoodByName = foodDao.findOneFoodByName(restaurant.getId(), foodDto.getName());
        if(oneFoodByName==null){
            Food food=new Food(foodDto.getName(),foodDto.getType(),foodDto.getPrice(),foodDto.getCount(),restaurant);
            foodDao.save(food);
            return true;
        }
        return false;
    }
    @Transactional
    public List<Food> seeMenuOfRestaurant(String nameOfRest) {
        List<Food> foodsOfMenu=foodDao.seeMenuOfRestaurant(nameOfRest);
        return foodsOfMenu;
    }
}
