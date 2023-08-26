package com.promineotech.solar.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.solar.dao.CustomerUpdateDao;
import com.promineotech.solar.entity.Customer;

@Service
public class DefaultCustomerUpdateService implements CustomerUpdateService {
  
  @Autowired
  private CustomerUpdateDao customerUpdateDao;
  
  
  @Override
  public Customer updateCustomer(String customerId, String phoneNumber, String address) {
    
    Customer customer = getCustomer(customerId);
    
    return customerUpdateDao.updateCustomer(customer, phoneNumber, address);
    

     

  }

  /**
   * 
   * @param customerId
   * @return
   */
  private Customer getCustomer(String customerId) {
    return customerUpdateDao.fetchCustomer(customerId)
        .orElseThrow(() -> new NoSuchElementException("Customer with ID "
            + customerId + " was not found"));
  }

}
