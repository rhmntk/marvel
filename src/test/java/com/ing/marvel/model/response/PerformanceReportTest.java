package com.ing.marvel.model.response;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PerformanceReportTest {

  final PerformanceReport performanceReport = PerformanceReport.builder()
      .endpointStatistics(
          List.of(
              EndpointStatistic.builder()
                  .path("api/test/{testId}/history")
                  .requestTotal(100)
                  .failedRequestTotal(0)
                  .responseTimeAverage(1558086340382L)
                  .percentile95th(9421.749999999993)
                  .percentile99th(14863.869999999999)
                  .build(),
              EndpointStatistic.builder()
                  .path("api/test/{testId}")
                  .requestTotal(150)
                  .failedRequestTotal(0)
                  .responseTimeAverage(3558086340382L)
                  .percentile95th(8421.749999999993)
                  .percentile99th(12863.869999999999)
                  .build()
          )
      )
      .build();

  @Test
  void testBuilder() {

    assertEquals(2, performanceReport.getEndpointStatistics().size());

    EndpointStatistic endpointStatistic1 = performanceReport.getEndpointStatistics().get(0);
    assertEquals("api/test/{testId}/history", endpointStatistic1.getPath());
    assertEquals(100, endpointStatistic1.getRequestTotal());
    assertEquals(0, endpointStatistic1.getFailedRequestTotal());
    assertEquals(1558086340382L, endpointStatistic1.getResponseTimeAverage());
    assertEquals(9421.749999999993, endpointStatistic1.getPercentile95th());
    assertEquals(14863.869999999999, endpointStatistic1.getPercentile99th());

    EndpointStatistic endpointStatistic2 = performanceReport.getEndpointStatistics().get(1);
    assertEquals("api/test/{testId}", endpointStatistic2.getPath());
    assertEquals(150, endpointStatistic2.getRequestTotal());
    assertEquals(0, endpointStatistic2.getFailedRequestTotal());
    assertEquals(3558086340382L, endpointStatistic2.getResponseTimeAverage());
    assertEquals(8421.749999999993, endpointStatistic2.getPercentile95th());
    assertEquals(12863.869999999999, endpointStatistic2.getPercentile99th());

  }

  @Test
  void testToString() {

    assertEquals(
        "PerformanceReport(endpointStatistics=[EndpointStatistic(path=api/test/{testId}/history, "
            + "requestTotal=100, failedRequestTotal=0, responseTimeAverage=1.558086340382E12, percentile95th=9421.749999999993, "
            + "percentile99th=14863.869999999999), EndpointStatistic(path=api/test/{testId}, requestTotal=150, "
            + "failedRequestTotal=0, responseTimeAverage=3.558086340382E12, percentile95th=8421.749999999993, "
            + "percentile99th=12863.869999999999)])",
        performanceReport.toString());

  }



}