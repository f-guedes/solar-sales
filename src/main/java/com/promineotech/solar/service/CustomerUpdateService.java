package com.promineotech.solar.service;

import com.promineotech.solar.entity.Customer;

public interface CustomerUpdateService {

  Customer updateCustomer(String customerName, String phoneNumber, String address);

}
