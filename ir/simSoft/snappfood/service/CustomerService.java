package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.CustomerDao;
import ir.simSoft.snappfood.model.dto.CustomerDto;
import ir.simSoft.snappfood.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerDao customerDao;
    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    public Customer checkUser(String phoneNumber) {
      Customer customer = customerDao.findByPhoneNumber(phoneNumber);
        if(customer!=null)
            return customer;
        else
            return null;
    }

    @Transactional
    public void addNewCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Transactional
    public List<CustomerDto> observeCustomerReport() {
        List<CustomerDto> customerDtoList =customerDao.observeCustomerReport();
        return customerDtoList;
    }
}
