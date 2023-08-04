package com.ing.marvel.model.log.enums;

import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum LogTypeEnum {
  REQUEST,
  USER,
  RUN,
  ERROR;

  public static LogTypeEnum getValue(final String logType) {

    log.debug("get logType enum for {}", logType);

    Optional<LogTypeEnum> logTypeEnum = Arrays.stream(LogTypeEnum.values())
        .filter(currentLogTypeEnum -> currentLogTypeEnum.name().equalsIgnoreCase(logType))
        .findFirst();

    if (logTypeEnum.isEmpty()) {
      log.warn("unexpected log type: {}", logType);
      return null;
    } else {
      return logTypeEnum.get();
    }
  }

}
