package com.ing.marvel.logic;

import static org.junit.jupiter.api.Assertions.*;

import com.ing.marvel.model.log.HttpRequestLog;
import com.ing.marvel.model.log.enums.LogTypeEnum;
import com.ing.marvel.model.log.enums.StatusEnum;
import org.junit.jupiter.api.Test;


class HttpRequestLogMapperTest {

  final HttpRequestLogMapper httpRequestLogMapper = new HttpRequestLogMapper();

  @Test
  void given_line_with_OK_status_return_http_request_log_without_message() {

    final String[] lines = {"REQUEST", "1", "", "api/endpoint", "1558086340382", "1558086349421",
        "OK"};

    HttpRequestLog httpRequestLog = httpRequestLogMapper.map(lines);
    assertEquals(LogTypeEnum.REQUEST, httpRequestLog.getLogType());
    assertEquals(1, httpRequestLog.getId());
    assertEquals("api/endpoint", httpRequestLog.getEndpointName());
    assertEquals(1558086340382L, httpRequestLog.getStartTimestampInUnix());
    assertEquals(1558086349421L, httpRequestLog.getEndTimestampInUnix());
    assertEquals(StatusEnum.OK, httpRequestLog.getStatus());
    assertNull(httpRequestLog.getMessage());
  }

  @Test
  void given_line_with_KO_status_return_http_request_log_with_message() {
    final String[] lines = {"REQUEST", "2", "", "api/endpoint", "1558086340382", "1558086349421",
        "KO", "Connection refused"};

    HttpRequestLog httpRequestLog = httpRequestLogMapper.map(lines);
    assertEquals(LogTypeEnum.REQUEST, httpRequestLog.getLogType());
    assertEquals(2, httpRequestLog.getId());
    assertEquals("api/endpoint", httpRequestLog.getEndpointName());
    assertEquals(1558086340382L, httpRequestLog.getStartTimestampInUnix());
    assertEquals(1558086349421L, httpRequestLog.getEndTimestampInUnix());
    assertEquals(StatusEnum.KO, httpRequestLog.getStatus());
    assertEquals("Connection refused", httpRequestLog.getMessage());
  }

}