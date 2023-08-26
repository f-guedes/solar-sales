package com.promineotech.solar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.service.CustomerUpdateService;

@RestController
public class DefaultCustomerUpdateController implements CustomerUpdateController {
  
  @Autowired
  private CustomerUpdateService customerUpdateService;

  @Override
  public Customer updateCustomer(String customerId, String phoneNumber, String address) {
    return customerUpdateService.updateCustomer(customerId, phoneNumber, address);
  }

}
