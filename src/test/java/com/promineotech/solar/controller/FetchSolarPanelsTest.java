//package com.promineotech.solar.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.LinkedList;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.SqlConfig;
//import com.promineotech.solar.entity.Panel;
//import com.promineotech.solar.entity.PanelManufacturer;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@Sql(scripts = {"classpath:flyway/migrations/V1.0__Solar_Schema.sql",
//    "classpath:flyway/migrations/V1.1__Solar_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))
//
//class FetchSolarPanelsTest {
//
//  @Autowired
//  private TestRestTemplate restTemplate;
//  
//  @LocalServerPort
//  private int serverPort;
//
//  
//  @Test
//  void testThatSolarPanelsAreReturnedWhenAValidManufacturerAndModelAreSupplied() {
//    // Given: a valid manufacturer, model and URI
//    PanelManufacturer manufacturer = PanelManufacturer.FREEDOM_FOREVER;
//    String uri =
//        String.format("http://localhost:%d/panels?manufacturer=%s", serverPort, manufacturer);
//
//    // When: a connection is made to the URI
//
//    ResponseEntity<List<Panel>> response =
//        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//
//    // Then: a success (OK - 200) status code is returned
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//    // And: the actual list returned is equal to the one expected
//    List<Panel> expected = buildExpected();
//    assertThat(response.getBody()).isEqualTo(expected);
//  }
//
//  
//  protected List<Panel> buildExpected() {
//    List<Panel> list = new LinkedList<>();
//
//    // @formatter:off
//    list.add(Panel.builder()
//        .panelManufacturer(PanelManufacturer.FREEDOM_FOREVER)
//        .model("FF-MP-BBB-370")
//        .wattage(370)
//        .isTripleBlack(true)
//        .build());
//    
//    list.add(Panel.builder()
//        .panelManufacturer(PanelManufacturer.FREEDOM_FOREVER)
//        .model("FF-MP-BBB-400")
//        .wattage(400)
//        .isTripleBlack(true)
//        .build());
//    
//    // @formatter: on
//    
//    return list;
//  }
//
//}
