package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.CustomerDao;
import ir.simSoft.snappfood.model.dto.CustomerDto;
import ir.simSoft.snappfood.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomerService {

    CustomerDao customerDao;
    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer checkUser(String phoneNumber) {
        Customer customer=customerDao.checkUser(phoneNumber);
        return customer;
    }

    public void addNewCustomer(Customer customer) {
        customerDao.addNewCustomer(customer);
    }

    public List<CustomerDto> observeCustomerReport() {
        List<CustomerDto> customerDtoList =customerDao.observeCustomerReport();
        return customerDtoList;
    }
}
