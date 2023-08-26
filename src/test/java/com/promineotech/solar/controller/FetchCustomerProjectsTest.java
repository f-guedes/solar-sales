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
//import com.promineotech.solar.entity.Project;
//
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@Sql(
//    scripts = {"classpath:flyway/migrations/V1.0__Solar_Schema.sql",
//        "classpath:flyway/migrations/V1.1__Solar_Data.sql"},
//    config = @SqlConfig(encoding = "utf-8"))
//class FetchCustomerProjectsTest {
//
//  @Autowired
//  private TestRestTemplate restTemplate;
//
//  @LocalServerPort
//  private int serverPort;
//
//  @Test
//  void testThatCorrectProjectsAreReturnedWhenValidCustomerIsSupplied() {
//    // Given: a valid customer and URI
//    String customer = "COOPER_ZOE";
//    String uri = String.format("http://localhost:%d/projects?customer=%s", serverPort, customer);
//
//    // When: a connection is made
//    ResponseEntity<List<Project>> response =
//        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//
//    // Then: A correct list of projects for supplied customer is returned
//    List<Project> expected = createExpectedList();
//    assertThat(response.getBody()).isEqualTo(expected);
//
//    // And: A 200 (Success / OK) status is returned
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//  }
//
//  private List<Project> createExpectedList() {
//    List<Project> expected = new LinkedList<>();
//    
//    return null;
//  }
//
//}
