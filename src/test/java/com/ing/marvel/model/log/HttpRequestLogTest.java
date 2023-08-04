package com.ing.marvel.model.log;

import static org.junit.jupiter.api.Assertions.*;

import com.ing.marvel.model.log.enums.LogTypeEnum;
import com.ing.marvel.model.log.enums.StatusEnum;
import org.junit.jupiter.api.Test;

class HttpRequestLogTest {
  final HttpRequestLog httpRequestLog = HttpRequestLog.builder()
      .id(1)
      .logType(LogTypeEnum.REQUEST)
      .endpointName("api/test1")
      .startTimestampInUnix(1558086340382L)
      .endTimestampInUnix(1558086349421L)
      .status(StatusEnum.OK)
      .build();

  @Test
  void testBuilder() {

    assertEquals(1, httpRequestLog.getId());
    assertEquals("api/test1", httpRequestLog.getEndpointName());
    assertEquals(1558086340382L, httpRequestLog.getStartTimestampInUnix());
    assertEquals(1558086349421L, httpRequestLog.getEndTimestampInUnix());
    assertEquals(StatusEnum.OK, httpRequestLog.getStatus());
    assertNull(httpRequestLog.getMessage());

  }

  @Test
  void testToString() {

    assertEquals(
        "HttpRequestLog(logType=REQUEST, id=1, endpointName=api/test1, startTimestampInUnix=1558086340382, "
            + "endTimestampInUnix=1558086349421, status=OK, message=null)",
        httpRequestLog.toString());

  }

}