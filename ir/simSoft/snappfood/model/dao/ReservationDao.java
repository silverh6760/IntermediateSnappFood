package ir.simSoft.snappfood.model.dao;

import ir.simSoft.snappfood.model.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ReservationDao extends CrudRepository<Reservation,Integer> {


    @Query(value = "select max(r.factorNumber) from Reservation r")
    List<Integer> getMaxFactorNumber();
//    public int getMaxFactorNumber() {
//        int factorNumber=0;
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q= session.createQuery("select max(r.factorNumber) from Reservation r");
//        List<Integer> num=q.list();
//        //num.stream().forEach(System.out::println);
//        //System.out.println(num.get(0));
//        if (num.get(0) != null) {
//            factorNumber = (int) num.get(0);
//        }
//        transaction.commit();
//        session.close();
//        return  factorNumber+1;
//    }

//    public void saveOrderToDB(Reservation reservation) {
//        Session session = DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(reservation);
//        transaction.commit();
//        session.close();
//    }
}
