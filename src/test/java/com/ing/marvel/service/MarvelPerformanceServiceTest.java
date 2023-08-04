package com.ing.marvel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.ing.marvel.logic.PerformanceReportGenerator;
import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MarvelPerformanceServiceTest {

  @Mock
  PerformanceReportGenerator performanceReportGeneratorMock;
  @InjectMocks
  MarvelPerformanceService marvelPerformanceService;

  @Test
  void given_successful_mock_returns_expected_results() throws MarvelBusinessException {
    when(performanceReportGeneratorMock.generate()).thenReturn(PerformanceReport.builder().build());

    assertNotNull(marvelPerformanceService.getPerformanceReport());
  }

}