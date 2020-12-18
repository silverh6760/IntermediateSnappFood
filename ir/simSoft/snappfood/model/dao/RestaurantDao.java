package ir.simSoft.snappfood.model.dao;

import ir.simSoft.snappfood.model.dto.RestaurantDto;
import ir.simSoft.snappfood.model.entity.FoodType;
import ir.simSoft.snappfood.model.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
@org.springframework.stereotype.Repository
public interface RestaurantDao extends CrudRepository<Restaurant,Integer> {

    Restaurant findByName(String name);

    @Query(value = "select new ir.simSoft.snappfood.model.dto.RestaurantDto(f.name ,res.name ,max(r.count) " +
                ",res.area ,count(distinct r.factorNumber)*res.service) " +
            " from Restaurant res,Food f,Reservation r" +
            " where res.id=f.restaurant.id and f.id=r.food.id group by res.name")
    List<RestaurantDto> observeRestaurantReport();


    @Query(value = "select r from Restaurant r where r.area=:area" )
    List<Restaurant> seeRestaurantsFromDB(@Param("area") Integer area);


    @Query("select r from Restaurant r inner join Food f on f.restaurant.id = r.id where " +
                "r.area=:area and f.type=:foodType")
    List<Restaurant> searchRestaurantByFoodType(@Param("foodType") FoodType foodType, @Param("area") Integer area);
//    public boolean addNewRestaurant(Restaurant restaurant) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q= session.createQuery("select r from ir.simSoft.snappfood.model.entity.Restaurant r");
//        List<Restaurant> restaurants =q.list();
//        for(Restaurant restaurant1:restaurants){
//            if(restaurant1.getName().equals(restaurant.getName()))
//                return false;
//        }
//        session.saveOrUpdate(restaurant);
//        transaction.commit();
//        session.close();
//        return true;
//    }

//    public Restaurant findRestByName(String nameOfRest) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q = session.createQuery("select r from ir.simSoft.snappfood.model.entity.Restaurant r");
//        List<Restaurant> restaurants = q.list();
//        for (Restaurant restaurant1 : restaurants) {
//            if (restaurant1.getName().equals(nameOfRest)) {
//                transaction.commit();
//                session.close();
//                return restaurant1;
//            }
//        }
//        transaction.commit();
//        session.close();
//        return null;
//    }

//    public List<Restaurant> seeRestaurantsFromDB(int area) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q = session.createQuery("select r from Restaurant r where r.area=:area");
//        q.setParameter("area",area);
//        List<Restaurant> restaurants = q.list();
//        transaction.commit();
//        session.close();
//        return restaurants;
//    }

//    public List<Restaurant> searchRestaurantByFoodType(FoodType foodType, int area) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Query q = session.createQuery("select r " +
//                "from Restaurant r inner join Food f on f.restaurant.id = r.id where " +
//                "r.area=:area and f.type=:foodType");
//        q.setParameter("area",area);
//        q.setParameter("foodType",foodType);
//        List<Restaurant> restaurants = q.list();
//        session.close();
//        return restaurants;
//    }

//    public List<RestaurantDto> observeRestaurantReport() {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q=session.createQuery("select f.name as nameOfFood,res.name as nameOfRest,max(r.count) as maxOfCount" +
//                ",res.area as area,count(distinct r.factorNumber)*res.service as totalService from Restaurant res,Food f,Reservation r " +
//                "where res.id=f.restaurant.id and f.id=r.food.id group by res.name");
//        q.setResultTransformer(Transformers.aliasToBean(RestaurantDto.class));
//        List<RestaurantDto> restaurantDtoList=q.list();
//        session.close();
//        return restaurantDtoList;
//    }
}

