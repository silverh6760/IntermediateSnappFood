package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.RestaurantDao;
import ir.simSoft.snappfood.model.dto.RestaurantDto;
import ir.simSoft.snappfood.model.entity.FoodType;
import ir.simSoft.snappfood.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RestaurantService {

    RestaurantDao restaurantDao;
    @Autowired
    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public boolean addNewRestaurant(Restaurant restaurant) {
        boolean answer=restaurantDao.addNewRestaurant(restaurant);
        return answer;
    }

    public List<Restaurant> seeRestaurants(int area) {
        List<Restaurant> restaurants=restaurantDao.seeRestaurantsFromDB(area);
            return restaurants;
    }

    public List<Restaurant> searchRestaurantByFoodType(FoodType foodType, int area) {
        List<Restaurant> restaurants=restaurantDao.searchRestaurantByFoodType(foodType,area);
        return restaurants;
    }

    public List<RestaurantDto> observeRestaurantReport() {
        List<RestaurantDto> restaurantDtoList=restaurantDao.observeRestaurantReport();
        return restaurantDtoList;
    }
}
