package com.promineotech.solar.service;

import java.util.List;
import com.promineotech.solar.entity.Project;

public interface DeleteProjectsService {

  List<Project> deleteProjectsByCustomer(String customerId);

}