package model.dao;

import model.dto.RestaurantDto;
import model.entity.FoodType;
import model.entity.Restaurant;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class RestaurantDao {
    public boolean addNewRestaurant(Restaurant restaurant) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q= session.createQuery("select r from model.entity.Restaurant r");
        List<Restaurant> restaurants =q.list();
        for(Restaurant restaurant1:restaurants){
            if(restaurant1.getName().equals(restaurant.getName()))
                return false;
        }
        session.saveOrUpdate(restaurant);
        transaction.commit();
        session.close();
        return true;
    }

    public Restaurant findRestByName(String nameOfRest) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("select r from model.entity.Restaurant r");
        List<Restaurant> restaurants = q.list();
        for (Restaurant restaurant1 : restaurants) {
            if (restaurant1.getName().equals(nameOfRest)) {
                transaction.commit();
                session.close();
                return restaurant1;
            }
        }
        transaction.commit();
        session.close();
        return null;
    }

    public List<Restaurant> seeRestaurantsFromDB(int area) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("select r from Restaurant r where r.area=:area");
        q.setParameter("area",area);
        List<Restaurant> restaurants = q.list();
        transaction.commit();
        session.close();
        return restaurants;
    }

    public List<Restaurant> searchRestaurantByFoodType(FoodType foodType, int area) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Query q = session.createQuery("select r from Restaurant r join Food f where f.restaurant.id=r.id and " +
                "r.area=:area and f.type=:foodType");
        q.setParameter("area",area);
        q.setParameter("foodType",foodType);
        List<Restaurant> restaurants = q.list();
        session.close();
        return restaurants;
    }

    public List<RestaurantDto> observeRestaurantReport() {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q=session.createQuery("select f.name as nameOfFood,res.name as nameOfRest,max(r.count) as maxOfCount" +
                ",res.area as area,count(distinct r.factorNumber)*res.service as totalService from Restaurant res,Food f,Reservation r " +
                "where res.id=f.restaurant.id and f.id=r.food.id group by res.name");
        q.setResultTransformer(Transformers.aliasToBean(RestaurantDto.class));
        List<RestaurantDto> restaurantDtoList=q.list();
        session.close();
        return restaurantDtoList;
    }
}

