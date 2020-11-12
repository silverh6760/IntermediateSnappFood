package model.dao;

import model.dto.CustomerDto;
import model.entity.Customer;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public Customer checkUser(String phoneNumber) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q= session.createQuery("select c from Customer c");
        List<Customer> customers =q.list();
        for(Customer customer:customers){
            if(customer.getPhoneNumber().equals(phoneNumber)) {
                transaction.commit();
                session.close();
                return customer;
            }
        }
        transaction.commit();
        session.close();
        return null;
    }

    public void addNewCustomer(Customer customer) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(customer);
        transaction.commit();
        session.close();
    }

    public List<CustomerDto> observeCustomerReport() {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
//        SQLQuery query2=session.createSQLQuery("select customer.phoneNumber , customer.registerDate , sum(food.price*food.count)" +
//                " from customer,food,reservation  where  reservation.customer_id=customer.id " +
//                "and food.id=reservation.food_id group by customer.phoneNumber");
        Query query=session.createQuery("select c.firstName as firstName , c.lastName as lastName , c.phoneNumber as phoneNumber , c.registerDate as registerDate ," +
                "sum(f.price*f.count) as totalPurchase from Customer c ,Food f,Reservation r where" +
                " r.customer.id=c.id and f.id=r.food.id group by c.phoneNumber");
        query.setResultTransformer(Transformers.aliasToBean(CustomerDto.class));

        List<CustomerDto> customerDtoList = query.list();
        transaction.commit();
        session.close();
        return customerDtoList;
    }
}
