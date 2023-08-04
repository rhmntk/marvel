package com.ing.marvel.web.controller;

import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.service.MarvelPerformanceService;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarvelController {

  private final MarvelPerformanceService marvelPerformanceService;

  public MarvelController(MarvelPerformanceService marvelPerformanceService) {
    this.marvelPerformanceService = marvelPerformanceService;
  }

  @GetMapping(value = "/endpoint/report", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<PerformanceReport> getEndpointPerformanceReport()
      throws MarvelBusinessException {
    final PerformanceReport performanceReport = marvelPerformanceService.getPerformanceReport();

    return ResponseEntity.ok(performanceReport);
  }

}
