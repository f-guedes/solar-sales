package com.promineotech.solar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.solar.entity.Project;
import com.promineotech.solar.service.DeleteProjectsService;

@RestController
public class DefaultDeleteProjectsController
    implements DeleteProjectsController {
  
  @Autowired
  private DeleteProjectsService deleteProjectsService;

  @Override
  public List<Project> deleteProjectsByCustomer(String customerId) {
    return deleteProjectsService.deleteProjectsByCustomer(customerId);
  }

}
