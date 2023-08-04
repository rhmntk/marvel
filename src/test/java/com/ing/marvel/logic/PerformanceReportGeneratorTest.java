package com.ing.marvel.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.ing.marvel.model.log.HttpRequestLog;
import com.ing.marvel.model.log.enums.LogTypeEnum;
import com.ing.marvel.model.log.enums.StatusEnum;
import com.ing.marvel.model.response.EndpointStatistic;
import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceReportGeneratorTest {

  @Mock
  LogFileReader logFileReaderMock;
  @InjectMocks
  PerformanceReportGenerator performanceReportGenerator;

  final List<HttpRequestLog> httpRequestLogs1 = List.of(
      HttpRequestLog.builder()
          .id(1)
          .logType(LogTypeEnum.REQUEST)
          .endpointName("api/test1")
          .startTimestampInUnix(1558086340382L)
          .endTimestampInUnix(1558086349421L)
          .status(StatusEnum.OK)
          .build(),
      HttpRequestLog.builder()
          .id(2)
          .logType(LogTypeEnum.REQUEST)
          .endpointName("api/test1")
          .startTimestampInUnix(1558086340603L)
          .endTimestampInUnix(1558086349416L)
          .status(StatusEnum.OK)
          .build()
  );

  final List<HttpRequestLog> httpRequestLogs2 = List.of(
      HttpRequestLog.builder()
          .id(3)
          .logType(LogTypeEnum.REQUEST)
          .endpointName("api/test2")
          .startTimestampInUnix(1558086339961L)
          .endTimestampInUnix(1558086349398L)
          .status(StatusEnum.OK)
          .build()
  );
  final Map<String, List<HttpRequestLog>> httpRequestLogsByEndpointMap = Stream.of(
          new AbstractMap.SimpleEntry<>("api/test1", httpRequestLogs1),
          new AbstractMap.SimpleEntry<>("api/test2", httpRequestLogs2))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

  @Test
  void given_correct_log_file_returns_expected_results() throws MarvelBusinessException {
    when(logFileReaderMock.read()).thenReturn(httpRequestLogsByEndpointMap);

    PerformanceReport performanceReport = performanceReportGenerator.generate();

    assertEquals(2, performanceReport.getEndpointStatistics().size());
    //1st endpoint
    EndpointStatistic endpointStatistic1 = performanceReport.getEndpointStatistics().get(0);
    assertEquals("api/test1", endpointStatistic1.getPath());
    assertEquals(2, endpointStatistic1.getRequestTotal());
    assertEquals(0, endpointStatistic1.getFailedRequestTotal());
    assertEquals(8926.0d, endpointStatistic1.getResponseTimeAverage());
    assertEquals(9039.0d, endpointStatistic1.getPercentile95th());
    assertEquals(9039.0, endpointStatistic1.getPercentile99th());

    //2nd endpoint
    EndpointStatistic endpointStatistic2 = performanceReport.getEndpointStatistics().get(1);
    assertEquals("api/test2", endpointStatistic2.getPath());
    assertEquals(1, endpointStatistic2.getRequestTotal());
    assertEquals(0, endpointStatistic2.getFailedRequestTotal());
    assertEquals(9437.0d, endpointStatistic2.getResponseTimeAverage());
    assertEquals(9437.0d, endpointStatistic2.getPercentile95th());
    assertEquals(9437.0d, endpointStatistic2.getPercentile95th());

  }
}