package ir.simSoft.snappfood.model.dao;

import ir.simSoft.snappfood.model.entity.Food;
import ir.simSoft.snappfood.model.dto.FoodDto;
import ir.simSoft.snappfood.model.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FoodDao {

    RestaurantDao restaurantDao=new RestaurantDao();

    public boolean addFoodToRestaurant(FoodDto foodDto, String nameOfRest) {
        Restaurant restaurant= restaurantDao.findRestByName(nameOfRest);
        if(restaurant!=null){
            Session session = DatabaseConnection.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query q= session.createQuery("select f from Food f where f.restaurant.id=:id");
            q.setParameter("id",restaurant.getId());
            List<Food> foods =q.list();
            for(Food food:foods){
                if(food.getName().equals(foodDto.getName())){
                    return false;
                }
            }
            Food food=new Food(foodDto.getName(),foodDto.getType(),foodDto.getPrice(),foodDto.getCount(),restaurant);
            session.save(food);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    public List<Food> sseMenuOfRestaurant(String nameOfRest) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q= session.createQuery("select f from Food f join fetch Restaurant r " +
                "on f.restaurant.id = r.id where f.restaurant.name =:nameOfRest");
        q.setParameter("nameOfRest",nameOfRest);
        List<Food> foods =q.list();
        return foods;
    }
}
