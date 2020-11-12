package model.dao;

import model.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDao {

    public int getMaxFactorNumber() {
        int factorNumber=0;
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q= session.createQuery("select max(r.factorNumber) from Reservation r");
        List<Integer> num=q.list();
        num.stream().forEach(System.out::println);
        System.out.println(num.get(0));
        if (num.get(0) != null) {
            factorNumber = (int) num.get(0);
        }
        transaction.commit();
        session.close();
        return  factorNumber+1;
    }

    public void saveOrderToDB(Reservation reservation) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(reservation);
        transaction.commit();
        session.close();
    }
}
