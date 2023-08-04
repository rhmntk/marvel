package com.ing.marvel.service;

import com.ing.marvel.logic.PerformanceReportGenerator;
import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MarvelPerformanceService {

  private final PerformanceReportGenerator performanceReportGenerator;

  public MarvelPerformanceService(PerformanceReportGenerator performanceReportGenerator) {
    this.performanceReportGenerator = performanceReportGenerator;
  }

  public PerformanceReport getPerformanceReport() throws MarvelBusinessException {
    log.info("start calling a service to generate performance report");
    return performanceReportGenerator.generate();
  }
}
