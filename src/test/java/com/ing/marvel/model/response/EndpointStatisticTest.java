package com.ing.marvel.model.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EndpointStatisticTest {

  private final EndpointStatistic endpointStatistic = EndpointStatistic.builder()
      .path("api/test/{testId}/history")
      .requestTotal(100)
      .failedRequestTotal(0)
      .responseTimeAverage(1558086340382L)
      .percentile95th(9421.749999999993)
      .percentile99th(14863.869999999999)
      .build();

  @Test
  void testBuilder() {

    assertEquals("api/test/{testId}/history", endpointStatistic.getPath());
    assertEquals(100, endpointStatistic.getRequestTotal());
    assertEquals(0, endpointStatistic.getFailedRequestTotal());
    assertEquals(1558086340382L, endpointStatistic.getResponseTimeAverage());
    assertEquals(9421.749999999993, endpointStatistic.getPercentile95th());
    assertEquals(14863.869999999999, endpointStatistic.getPercentile99th());

  }

  @Test
  void testToString() {

    assertEquals(
        "EndpointStatistic(path=api/test/{testId}/history, requestTotal=100, failedRequestTotal=0, "
            + "responseTimeAverage=1.558086340382E12, percentile95th=9421.749999999993, percentile99th=14863.869999999999)",
        endpointStatistic.toString());

  }

}