//package com.promineotech.solar.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.SqlConfig;
//import com.promineotech.solar.entity.InverterManufacturer;
//import com.promineotech.solar.entity.PanelManufacturer;
//import com.promineotech.solar.entity.Project;
//
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@Sql(scripts = {"classpath:flyway/migrations/V1.0__Solar_Schema.sql",
//    "classpath:flyway/migrations/V1.1__Solar_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))
//class CreateProjectTest {
//
//  @Autowired
//  private TestRestTemplate restTemplate;
//
//  @LocalServerPort
//  private int serverPort;
//
//  /**
//   * 
//   */
//  @Test
//  void testCreateOrderReturnsSuccess201() {
//    // Given: an order as JSON
//    String body = createOrderBody();
//    String uri = String.format("http://localhost:%d/orders", serverPort);
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//
//    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
//
//    // When: the order is sent
//    ResponseEntity<Project> response =
//        restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Project.class);
//
//    // Then: a 201 status is returned
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//    // And: the returned order is correct
//    assertThat(response.getBody()).isNotNull();
//
//    Project project = response.getBody();
//    assertThat(project.getSystemSize()).isEqualTo(11);
//    assertThat(project.getPanel().getPanelManufacturer()).isEqualTo(PanelManufacturer.LONGI);
//    assertThat(project.getPanel().getModel()).isEqualTo("LR4-60HPH");
//    assertThat(project.getInverter().getInverterManufacturer()).isEqualTo(InverterManufacturer.SOLAREDGE);
//    assertThat(project.getGrossPrice().toBigInteger()).isEqualTo(32139);
//  }
//
//  /**
//   * 
//   * @return
//   */
//  protected String createOrderBody() {
//    // @formatter:off
//    return "{\n"
//        + "  \"customer\": \"COOPER_ZOE\",\n"
//        + "  \"systemSize\": 11,\n"
//        + "  \"panelManufacturer\": \"LONGI\",\n"
//        + "  \"panelWattage\": 365,\n"
//        + "  \"inverter\": \"SOLAREDGE\",\n"
//        + "  \"grossPrice\": 32139\n"
//        + "}";
//    // @formatter:on
//  }
//
//}
