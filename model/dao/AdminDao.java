package model.dao;

import model.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class AdminDao {

    public boolean verifyAdmin(String username, String password) {
        Session session=DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q=session.createQuery("select a FROM Admin a");
        List<Admin> admins=q.list();
        for(Admin admin:admins){
            if(admin.getUsername().equals(username) && admin.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public void addNewAdmin(Admin admin) {
        Session session=DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(admin);
        transaction.commit();
        session.close();
    }
}
