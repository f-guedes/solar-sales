package com.promineotech.solar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultSolarPanelsDao implements SolarPanelsDao {

  @Autowired
  NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Panel> fetchPanels(PanelManufacturer panelManufacturer) {
    log.info("DAO layer log: Panel Manufacturer selected = {}", panelManufacturer);

    String sql = "SELECT * FROM panels WHERE panel_manufacturer = :manufacturer";
    
    Map<String, Object> params = new HashMap<>();
    params.put("manufacturer", panelManufacturer.toString());

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Panel mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Panel.builder()
            .panelPK(rs.getLong("panel_pk"))
            .panelManufacturer(PanelManufacturer.valueOf(rs.getString("panel_manufacturer")))
            .model(rs.getString("model"))
            .wattage(rs.getInt("wattage"))
            .isTripleBlack(rs.getBoolean("is_triple_black"))
            .build();
        // @formatter:off
      }});
  }
}
