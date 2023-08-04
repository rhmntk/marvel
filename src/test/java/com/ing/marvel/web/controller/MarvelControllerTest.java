package com.ing.marvel.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.service.MarvelPerformanceService;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MarvelControllerTest {

  @Mock
  MarvelPerformanceService marvelPerformanceServiceMock;
  @InjectMocks
  MarvelController marvelController;

  @Test
  void when_calling_endpoint_returns_expected_results() throws MarvelBusinessException {

    when(marvelPerformanceServiceMock.getPerformanceReport()).thenReturn(
        PerformanceReport.builder().build()
    );

    ResponseEntity<PerformanceReport> response = marvelController.getEndpointPerformanceReport();

    assertNotNull(response);
    assertTrue(response.getBody() instanceof PerformanceReport);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}