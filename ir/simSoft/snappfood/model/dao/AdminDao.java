package ir.simSoft.snappfood.model.dao;

import ir.simSoft.snappfood.model.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface AdminDao extends CrudRepository<Admin, Integer> {

    Optional<Admin> findByUsernameAndPassword(String username,String password);
//    public boolean verifyAdmin(String username, String password) {
//        Session session=DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q=session.createQuery("select a FROM Admin a");
//        List<Admin> admins=q.list();
//        for(Admin admin:admins){
//            if(admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
//                transaction.commit();
//                session.close();
//                return true;
//            }
//        }
//        transaction.commit();
//        session.close();
//        return false;
//    }

//    public void addNewAdmin(Admin admin) {
//        Session session=DatabaseConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(admin);
//        transaction.commit();
//        session.close();
//    }
}
