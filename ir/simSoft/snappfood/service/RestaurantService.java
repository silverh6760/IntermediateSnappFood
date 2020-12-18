package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.RestaurantDao;
import ir.simSoft.snappfood.model.dto.RestaurantDto;
import ir.simSoft.snappfood.model.entity.FoodType;
import ir.simSoft.snappfood.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
@Service
public class RestaurantService {

    RestaurantDao restaurantDao;
    @Autowired
    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Transactional
    public Restaurant findRestaurantByName(String name){
        Restaurant restaurant = restaurantDao.findByName(name);
        return restaurant;
    }

    @Transactional
    public boolean addNewRestaurant(Restaurant restaurant) {
       Restaurant restFromDB =restaurantDao.findByName(restaurant.getName());
       if(restFromDB!=null)
           return false;
       else {
           restaurantDao.save(restaurant);
           return true;
       }
    }
    @Transactional
    public List<Restaurant> seeRestaurants(int area) {
        List<Restaurant> restaurants= restaurantDao.seeRestaurantsFromDB(area);
            return restaurants;
    }
    @Transactional
    public List<Restaurant> searchRestaurantByFoodType(FoodType foodType, int area) {
        List<Restaurant> restaurants=restaurantDao.searchRestaurantByFoodType(foodType,area);
        return restaurants;
    }

    @Transactional
    public List<RestaurantDto> observeRestaurantReport() {
        List<RestaurantDto> restaurantDtoList=restaurantDao.observeRestaurantReport();
        return restaurantDtoList;
    }
}
