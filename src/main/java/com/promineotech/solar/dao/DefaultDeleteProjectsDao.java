package com.promineotech.solar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.entity.Inverter;
import com.promineotech.solar.entity.InverterManufacturer;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;
import com.promineotech.solar.entity.Project;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultDeleteProjectsDao implements DeleteProjectsDao {

  @Autowired
  NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Optional<Customer> fetchCustomerById(String customerId) {
    log.info("DAO layer log: Customer submitted to have projects deleted = {}", customerId);

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

  @Override
  public List<Project> fetchProjectsByCustomer(Long customerPk) {

   //@formatter:off
     String sql = "SELECT p.* "
         + "FROM projects p "
         + "INNER JOIN customer_projects cp ON (p.project_pk = cp.project_fk) "
         + "INNER JOIN customers c ON (c.customer_pk = cp.customer_fk) "
         + "WHERE c.customer_pk = :customerId";
       //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customerId", customerPk);


    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
      // @formatter:off
      return Project.builder()
          .projectPK(rs.getLong("project_pk"))
          .systemSize(rs.getFloat("system_size"))
          .panel(fetchPanelForProject(rs.getInt("panel_fk")))
          .inverter(fetchInverterForProject(rs.getInt("inverter_fk")))
          .grossPrice(rs.getBigDecimal("gross_price"))
          .build();
      // @formatter:off
    }

      private Panel fetchPanelForProject(int panelFk) {
        String sql = "SELECT * FROM panels WHERE panel_pk = " + panelFk;
        
        return jdbcTemplate.query(sql, new PanelResultSetExtractor());
      }

      private Inverter fetchInverterForProject(int inverterFk) {
        String sql = "SELECT * FROM inverters WHERE inverter_pk = " + inverterFk;
        return jdbcTemplate.query(sql, new InverterResultSetExtractor());
      }});


  }

  @Override
  public void deleteProjects(List<Project> projectsList) {
    
    Map<String, Object> params = new HashMap<>();
    
    String sql = "DELETE FROM projects WHERE project_pk IN (";
    
    for (int index = 0; index < projectsList.size(); index++) {
      String key = "option_" + index;
      sql += ":" + key + ", ";
      params.put(key, projectsList.get(index).getProjectPK());
    }

    sql = sql.substring(0, sql.length() - 2);
    sql += ")";
    
    jdbcTemplate.update(sql, params);
    
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

  class PanelResultSetExtractor implements ResultSetExtractor<Panel> {
    @Override
    public Panel extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Panel.builder()
          .panelPK(rs.getLong("panel_pk"))
          .panelManufacturer(PanelManufacturer.valueOf(rs.getString("panel_manufacturer")))
          .model(rs.getString("model"))
          .wattage(rs.getInt("wattage"))
          .isTripleBlack(rs.getBoolean("is_triple_black"))
          .build();
      // @formatter:on

    }
  }

  class InverterResultSetExtractor implements ResultSetExtractor<Inverter> {
    @Override
    public Inverter extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Inverter.builder()
          .inverterPK(rs.getLong("inverter_pk"))
          .inverterManufacturer(InverterManufacturer.valueOf(rs.getString("inverter_manufacturer")))
          .build();
      // @formatter:on

    }
  }

}