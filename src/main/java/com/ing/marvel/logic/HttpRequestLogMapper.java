package com.ing.marvel.logic;

import static com.ing.marvel.model.log.enums.StatusEnum.KO;

import com.ing.marvel.model.log.HttpRequestLog;
import com.ing.marvel.model.log.enums.LogTypeEnum;
import com.ing.marvel.model.log.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HttpRequestLogMapper {

  HttpRequestLog map(final String[] lines) {

    log.debug("start mapping line {}", lines);

    if (KO.name().equalsIgnoreCase(lines[6])) {
      return HttpRequestLog.builder()
          .logType(LogTypeEnum.getValue(lines[0]))
          .id(Integer.parseInt(lines[1]))
          .endpointName(lines[3])
          .startTimestampInUnix(Long.parseLong(lines[4]))
          .endTimestampInUnix(Long.parseLong(lines[5]))
          .status(StatusEnum.getValue(lines[6]))
          .message(lines[7])
          .build();
    } else { // status is OK without any message
      return HttpRequestLog.builder()
          .logType(LogTypeEnum.getValue(lines[0]))
          .id(Integer.parseInt(lines[1]))
          .endpointName(lines[3])
          .startTimestampInUnix(Long.parseLong(lines[4]))
          .endTimestampInUnix(Long.parseLong(lines[5]))
          .status(StatusEnum.getValue(lines[6]))
          .build();
    }
  }

}
