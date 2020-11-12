package controller;

import model.dao.CustomerDao;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public class CustomerService {

    CustomerDao customerDao=new CustomerDao();

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
