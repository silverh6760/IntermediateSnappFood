package controller;

import model.dao.AdminDao;
import model.entity.Admin;

public class AdminService {
    AdminDao adminDao=new AdminDao();
    public boolean verifyAdmin(String username, String password) {
        boolean answer=adminDao.verifyAdmin(username,password);
        return answer;
    }


    public void addNewAdmin(Admin admin) {
        adminDao.addNewAdmin(admin);
    }
}
