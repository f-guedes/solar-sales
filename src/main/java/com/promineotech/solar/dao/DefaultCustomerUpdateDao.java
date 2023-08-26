package com.promineotech.solar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.solar.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultCustomerUpdateDao implements CustomerUpdateDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Customer updateCustomer(Customer customer, String phone, String address) {
    // @formatter:off
    String sql = "UPDATE "
        + "customers SET phone = :phone, address = :address "
        + "WHERE customer_id = '" + customer.getCustomerId() + "'";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("phone", phone);
    params.put("address", address);

    jdbcTemplate.update(sql, params);
    
    Customer updatedCustomer = fetchCustomer(customer.getCustomerId())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID "
            + customer.getCustomerId() + " was not found"));;
    
    log.info("The phone number and address for customer " + updatedCustomer.getCustomerId()
        + " have been successfully updated in the databse");
    
    return updatedCustomer;
  }


  public Optional<Customer> fetchCustomer(String customerId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }


  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
    @Override
    public Customer extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Customer.builder()
          .customerPK(rs.getLong("customer_pk"))
          .customerId(rs.getString("customer_id"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .phone(rs.getString("phone"))
          .address(rs.getString("address"))
          .build();
      // @formatter:on

    }
  }

}
