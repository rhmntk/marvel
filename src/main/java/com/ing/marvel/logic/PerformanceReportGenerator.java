package com.ing.marvel.logic;

import com.ing.marvel.model.log.HttpRequestLog;
import com.ing.marvel.model.log.enums.StatusEnum;
import com.ing.marvel.model.response.EndpointStatistic;
import com.ing.marvel.model.response.PerformanceReport;
import com.ing.marvel.web.exeption.MarvelBusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PerformanceReportGenerator {

  private final Percentile percentile = new Percentile();
  private final LogFileReader logFileReader;

  public PerformanceReportGenerator(final LogFileReader logFileReader) {
    this.logFileReader = logFileReader;
  }

  public PerformanceReport generate() throws MarvelBusinessException {
    log.info("start generating performance report");

    final Map<String, List<HttpRequestLog>> httpRequestLogsByEndpointMap = logFileReader.read();

    List<EndpointStatistic> endpointStatistics = new ArrayList<>();
    httpRequestLogsByEndpointMap.forEach((endpoint, httpRequestLogs) -> {

      log.info("Endpoint: {}", endpoint);

      //remove duplicates
      final Set<Long> responseTimeSet = httpRequestLogs.stream()
          .filter(l -> l.getStatus() == StatusEnum.OK)
          .mapToLong(l -> l.getEndTimestampInUnix() - l.getStartTimestampInUnix())
          .boxed().collect(Collectors.toSet());

      //ascending sort
      final double[] orderedAndUniqueResponseTimeList = responseTimeSet.stream()
          .mapToDouble(Double::valueOf)
          .boxed()
          .sorted()
          .collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).toArray();

      endpointStatistics.add(EndpointStatistic.builder()
              .path(endpoint)
              .requestTotal(httpRequestLogs.size())
              .failedRequestTotal((int) httpRequestLogs.stream().filter(l -> l.getStatus() == StatusEnum.KO).count())
              .responseTimeAverage(
                  httpRequestLogs.stream()
                  .filter(l -> l.getStatus() == StatusEnum.OK)
                  .mapToLong(l -> l.getEndTimestampInUnix() - l.getStartTimestampInUnix())
                  .average().getAsDouble())
              .percentile95th(percentile.evaluate(orderedAndUniqueResponseTimeList, 95.0))
              .percentile99th(percentile.evaluate(orderedAndUniqueResponseTimeList, 99.0))
          .build());
    });

    return PerformanceReport.builder()
        .endpointStatistics(endpointStatistics)
        .build();
  }
}
