package ir.simSoft.snappfood.model.dao;

import ir.simSoft.snappfood.model.dto.CustomerDto;
import ir.simSoft.snappfood.model.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


@org.springframework.stereotype.Repository
public interface CustomerDao extends CrudRepository<Customer,Integer> {

    Customer findByPhoneNumber(String phoneNumber);

    @Query(value = "select new ir.simSoft.snappfood.model.dto.CustomerDto(c.firstName  , c.lastName  , c.phoneNumber , c.registerDate ," +
               "sum(f.price*f.count) ) from Customer c ,Food f,Reservation r where" +
                " r.customer.id=c.id and f.id=r.food.id group by c.phoneNumber")
    List<CustomerDto> observeCustomerReport();
//    public Customer checkUser(String phoneNumber) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q= session.createQuery("select c from Customer c");
//        List<Customer> customers =q.list();
//        for(Customer customer:customers){
//            if(customer.getPhoneNumber().equals(phoneNumber)) {
//                transaction.commit();
//                session.close();
//                return customer;
//            }
//        }
//        transaction.commit();
//        session.close();
//        return null;
//    }

//    public void addNewCustomer(Customer customer) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(customer);
//        transaction.commit();
//        session.close();
//    }

//    public List<CustomerDto> observeCustomerReport() {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction=session.beginTransaction();
//        Query query=session.createQuery("select c.firstName as firstName , c.lastName as lastName , c.phoneNumber as phoneNumber , c.registerDate as registerDate ," +
//                "sum(f.price*f.count) as totalPurchase from Customer c ,Food f,Reservation r where" +
//                " r.customer.id=c.id and f.id=r.food.id group by c.phoneNumber");
//        query.setResultTransformer(Transformers.aliasToBean(CustomerDto.class));
//
//        List<CustomerDto> customerDtoList = query.list();
//        transaction.commit();
//        session.close();
//        return customerDtoList;
//    }
}
