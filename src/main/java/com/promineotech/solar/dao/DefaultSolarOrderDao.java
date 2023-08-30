package com.promineotech.solar.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.solar.entity.Customer;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;
import com.promineotech.solar.entity.Project;
import lombok.extern.slf4j.Slf4j;
import com.promineotech.solar.entity.Inverter;
import com.promineotech.solar.entity.InverterManufacturer;

@Component
@Slf4j
public class DefaultSolarOrderDao implements SolarOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Project saveProject(Customer customer, Float systemSize, Panel panel, Inverter inverter,
      BigDecimal grossPrice) {
    
    log.info(
        "DAO layer log: Customer = {}, System Size in Kw= {}, Panel = {}, Inverter = {}, Gross Price in $= {}",
        customer.getCustomerId(), systemSize, panel.getPanelManufacturer(), inverter.getInverterManufacturer(), grossPrice);
    
    SqlParams params = generateInsertSql(systemSize, panel, inverter, grossPrice);

    KeyHolder keyholder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyholder);

    Long projectPk = keyholder.getKey().longValue();


    // @formatter:off
    String customerProjectsTableSql = ""
        + "INSERT INTO customer_projects ("
        + "customer_fk, project_fk"
        + ") VALUES ("
        + ":customer_fk, :project_fk)";
    // @formatter:on  

    Map<String, Object> customerProjectsParamsMap = new HashMap<>();
    customerProjectsParamsMap.put("customer_fk", customer.getCustomerPK());
    customerProjectsParamsMap.put("project_fk", projectPk);

    jdbcTemplate.update(customerProjectsTableSql, customerProjectsParamsMap);


    // @formatter:off
    return Project.builder()
        .projectPK(projectPk)
        .systemSize(systemSize)
        .panel(panel)
        .inverter(inverter)
        .grossPrice(grossPrice)
        .build();
    // @formatter:on
  }


  /**
   * 
   * @param customer
   * @param panel
   * @param inverter
   * @return
   */
  private SqlParams generateInsertSql(Float systemSize, Panel panel, Inverter inverter,
      BigDecimal grossPrice) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO projects ("
        + "system_size, panel_fk, inverter_fk, gross_price"
        + ") VALUES ("
        + ":system_size, :panel_fk, :inverter_fk, :gross_price"
        + ")";
    // @formatter:on

    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("system_size", systemSize);
    params.source.addValue("panel_fk", panel.getPanelPK());
    params.source.addValue("inverter_fk", inverter.getInverterPK());
    params.source.addValue("gross_price", grossPrice);

    return params;
  }

  @Override
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


  @Override
  public Optional<Panel> fetchPanel(PanelManufacturer panelManufacturer, int wattage) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM panels "
        + "WHERE panel_manufacturer = :panel_manufacturer AND wattage = :wattage";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("panel_manufacturer", panelManufacturer.toString());
    params.put("wattage", wattage);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new PanelResultSetExtractor()));
  }


  @Override
  public Optional<Inverter> fetchInverter(String inverterManufacturer) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM inverters " 
        + "WHERE inverter_manufacturer = :inverter_manufacturer";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("inverter_manufacturer", inverterManufacturer);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new InverterResultSetExtractor()));
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

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

}
