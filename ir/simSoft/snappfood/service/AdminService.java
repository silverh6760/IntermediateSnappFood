package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.AdminDao;
import ir.simSoft.snappfood.model.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminService {

    AdminDao adminDao;
    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public boolean verifyAdmin(String username, String password) {
        boolean answer=adminDao.verifyAdmin(username,password);
        return answer;
    }


    public void addNewAdmin(Admin admin) {
        adminDao.addNewAdmin(admin);
    }
}
