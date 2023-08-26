package com.promineotech.solar.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.solar.entity.Panel;
import com.promineotech.solar.entity.PanelManufacturer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/panels")
public interface SolarPanelsController {

  // @formatter:off
  @Operation(
      summary = "Returns a list of solar panels",
      description = "Returns a list of panels available by manufacturer",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of solar panels is returned.",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Panel.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No solar panels were found with the input criteria.",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "panel manufacturer", 
              allowEmptyValue = false,
              required = false,
              description = "The panel manufacturer (i.e., 'QCELLS')")
          }
   )

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Panel> fetchPanels(
      @RequestParam(required = true) PanelManufacturer manufacturer);
  // @formatter: on
}
