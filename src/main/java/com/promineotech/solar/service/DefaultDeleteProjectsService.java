package com.promineotech.solar.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.solar.dao.DeleteProjectsDao;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.entity.Project;

@Service
public class DefaultDeleteProjectsService implements DeleteProjectsService {

  @Autowired
  private DeleteProjectsDao deleteProjectsDao;

  public List<Project> deleteProjectsByCustomer(String customerId) {
    Customer customer = getCustomer(customerId);

    List<Project> projectsList =
        deleteProjectsDao.fetchProjectsByCustomer(customer.getCustomerPK());

    deleteProjectsDao.deleteProjects(projectsList);
    
    return projectsList;
  }



 /**
  * 
  * @param customerId
  * @return
  */
  private Customer getCustomer(String customerId) {
    return deleteProjectsDao.fetchCustomerById(customerId).orElseThrow(
        () -> new NoSuchElementException("Customer with ID " + customerId + " was not found"));
  }

}