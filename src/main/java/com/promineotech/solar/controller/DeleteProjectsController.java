package com.promineotech.solar.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.solar.entity.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/delete")
public interface DeleteProjectsController {

  // @formatter:off
  @Operation(
      summary = "Deletes projects for a customer",
      description = "Deletes all projects for a customer given a customerId",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The project(s) was(were) successfully deleted.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Project.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "A customer was not found with the input criteria.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "CustomerId", 
              required = false,
              description = "The customer Id for customer whose projects should be deleted.")
      }
   )
  // @formatter:off

  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Project> deleteProjectsByCustomer(@RequestParam(required = true) String customerId);
}
