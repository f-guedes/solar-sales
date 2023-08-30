package com.promineotech.solar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.solar.entity.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/update")
public interface CustomerUpdateController {

  // @formatter:off
  @Operation(
      summary = "Updates a customer's phone number and address.",
      description = "Updates the fields 'phone' and 'address' from the 'customer' table.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "The customer information was successfully updated",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Customer.class))),
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
              name = "Customer, phone and address", 
              required = false,
              description = "The customer Id, new phone number and address for customer to be updated.")
      }
   )
  // @formatter:off

  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Customer updateCustomer(@RequestParam(required = true) String customerId, @RequestParam(required = true)String phoneNumber, @RequestParam(required = true)String address);
}
