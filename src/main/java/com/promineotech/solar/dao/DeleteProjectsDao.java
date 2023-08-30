package com.promineotech.solar.dao;

import java.util.List;
import java.util.Optional;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.entity.Project;

public interface DeleteProjectsDao {

  Optional<Customer> fetchCustomerById(String customerId);
  
  List<Project> fetchProjectsByCustomer (Long customerPk);

  void deleteProjects(List<Project> projectsList);



}