package com.ing.marvel.web.error;

import static org.junit.jupiter.api.Assertions.*;

import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class MarvelExceptionHandlerTest {

  final MarvelExceptionHandler marvelExceptionHandler = new MarvelExceptionHandler();

  @Test
  void given_an_exception_expected_error_response() {
    final var marvelBusinessException = new MarvelBusinessException("error");

    final ResponseEntity<PerformanceReport> response = marvelExceptionHandler.handleException(marvelBusinessException);

    assertNotNull(response);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,PerformanceReport(endpointStatistics=null),[]>",
        response.toString());
  }

}