package com.promineotech.solar.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.solar.entity.OrderRequest;
import com.promineotech.solar.entity.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Validated
@RequestMapping("/order")
public interface SolarOrderController {

  // @formatter:off
  @Operation(
      summary = "Creates an order for a solar project",
      description = "Creates a project in the database from a JSON.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "The created project is returned.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Project.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "An iten was not found with the input criteria.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "orderRequest", 
              required = false,
              description = "The project as JSON containing customer, system size in KW, "
                  + "panel manufacturer, wattage, inverter and system gross price (i.e. EPC)")
      }
   )
  // @formatter:off

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Project createOrder(@Valid @RequestBody OrderRequest orderRequest);
}
