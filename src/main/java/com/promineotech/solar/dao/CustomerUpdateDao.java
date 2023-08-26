package com.promineotech.solar.dao;

import java.util.Optional;
import com.promineotech.solar.entity.Customer;

public interface CustomerUpdateDao {

  Optional<Customer> fetchCustomer(String customerId);
  Customer updateCustomer(Customer customer, String phone, String address);
}
