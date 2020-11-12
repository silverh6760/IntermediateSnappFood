package controller;

import model.dao.RestaurantDao;
import model.dto.RestaurantDto;
import model.entity.FoodType;
import model.entity.Restaurant;

import java.util.List;

public class RestaurantService {

    RestaurantDao restaurantDao=new RestaurantDao();


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
