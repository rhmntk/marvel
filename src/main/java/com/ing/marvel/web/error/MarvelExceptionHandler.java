package com.ing.marvel.web.error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class MarvelExceptionHandler {

  private final PerformanceReport emptyPerformanceReport = PerformanceReport.builder().build();

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(MarvelBusinessException.class)
  public ResponseEntity<PerformanceReport> handleException(
      final MarvelBusinessException exception) {
    log.error("guarantees service exception is triggered. reason: {}", exception.getMessage());
    return new ResponseEntity<>(emptyPerformanceReport, INTERNAL_SERVER_ERROR);
  }

}
