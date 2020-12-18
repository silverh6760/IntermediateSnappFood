package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.AdminDao;
import ir.simSoft.snappfood.model.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminService {

    AdminDao adminDao;
    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Transactional
    public boolean verifyAdmin(String username, String password) {
        Optional<Admin> admin = adminDao.findByUsernameAndPassword(username, password);
        if(admin.isPresent())
            return true;
        else
            return false;
    }

    @Transactional
    public void addNewAdmin(Admin admin) {
        adminDao.save(admin);
    }
}
