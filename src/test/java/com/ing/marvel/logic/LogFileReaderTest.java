package com.ing.marvel.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import com.ing.marvel.model.log.HttpRequestLog;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
class LogFileReaderTest {

  @Mock
  HttpRequestLogMapper httpRequestLogMapperMock;
  @InjectMocks
  LogFileReader logFileReader;

  @BeforeEach
  void init() {
    lenient().when(httpRequestLogMapperMock.map(any()))
        .thenReturn(HttpRequestLog.builder().endpointName("api/test1").build());
  }

  @Test
  void given_correct_log_file_returns_expected_results() throws MarvelBusinessException {
    ReflectionTestUtils.setField(logFileReader, "filePath", "logs");
    ReflectionTestUtils.setField(logFileReader, "fileName", "test-data.log");

    Map<String, List<HttpRequestLog>> httpRequestLogsByEndpointMap = logFileReader.read();

    assertEquals(1, httpRequestLogsByEndpointMap.size());
    assertEquals(1, httpRequestLogsByEndpointMap.keySet().size());
    assertEquals("[api/test1]", httpRequestLogsByEndpointMap.keySet().toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"test-data-empty.log", "test-data-no-request.log"})
  void given_log_file_is_empty_throws_exception(String fileName) {
    ReflectionTestUtils.setField(logFileReader, "filePath", "logs");
    ReflectionTestUtils.setField(logFileReader, "fileName", fileName);

    Exception exception = assertThrows(MarvelBusinessException.class, () -> logFileReader.read());

    assertEquals("empty log file", exception.getMessage());
  }

  @Test
  void given_log_file_not_found_throws_exception() {
    ReflectionTestUtils.setField(logFileReader, "filePath", "logs");
    ReflectionTestUtils.setField(logFileReader, "fileName", "test-data-xxx.log");

    Exception exception = assertThrows(MarvelBusinessException.class, () -> logFileReader.read());

    assertEquals("file not found", exception.getMessage());
  }

}