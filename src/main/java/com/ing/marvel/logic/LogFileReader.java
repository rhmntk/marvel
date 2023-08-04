package com.ing.marvel.logic;

import static com.ing.marvel.model.log.enums.LogTypeEnum.REQUEST;

import com.ing.marvel.model.log.HttpRequestLog;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogFileReader {

  @Value("${marvel.logfile.path}")
  private String filePath;
  @Value("${marvel.logfile.filename}")
  private String fileName;
  private static final String DELIMITER = "\t";

  private final HttpRequestLogMapper httpRequestLogMapper;

  public LogFileReader(final HttpRequestLogMapper httpRequestLogMapper) {
    this.httpRequestLogMapper = httpRequestLogMapper;
  }

  //read lines grouped by endpoint into a map
  public Map<String, List<HttpRequestLog>> read() throws MarvelBusinessException {
    log.info("start reading request log file");

    Map<String, List<HttpRequestLog>> httpRequestLogsByEndpointMap;

    //read only request lines
    List<String> requestLines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(
        new File(
            Objects.requireNonNull(
                    getClass().getClassLoader().getResource(filePath + File.separator + fileName))
                .toURI())))) {
      String currentLine;
      while ((currentLine = br.readLine()) != null) {

        if (currentLine.startsWith(REQUEST.name())) {
          requestLines.add(currentLine);
        }
      }
    } catch (NullPointerException ex) {
      log.error("log file not found due for: {}", filePath + File.separator + fileName);
      throw new MarvelBusinessException("file not found");
    } catch (IOException ex) {
      log.error("failed to read log file due to: {}", ex.getMessage());
      throw new MarvelBusinessException("log file issue");
    } catch (URISyntaxException ex) {
      log.error("failed to locate log file URI due to: {}", ex.getMessage());
      throw new MarvelBusinessException("log file URI issue");
    }

    if (!requestLines.isEmpty()) {
      log.info("successfully reading {} amount of request lines from log file",
          requestLines.size());

      httpRequestLogsByEndpointMap = requestLines.stream()
          .map(line -> line.split(DELIMITER))
          .map(httpRequestLogMapper::map) //map every line as HttpRequestLog
          .collect(Collectors.groupingBy(HttpRequestLog::getEndpointName));

    } else {
      log.error("file does not contain any request line or is empty");
      throw new MarvelBusinessException("empty log file");
    }

    return httpRequestLogsByEndpointMap;
  }

}
