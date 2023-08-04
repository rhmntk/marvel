package com.ing.marvel.model.log.enums;

import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum StatusEnum {
  OK,
  KO;

  public static StatusEnum getValue(final String status) {

    log.debug("get status enum for {}", status);

    Optional<StatusEnum> statusEnum = Arrays.stream(StatusEnum.values())
        .filter(currentStatusENum -> currentStatusENum.name().equalsIgnoreCase(status))
        .findFirst();

    if (statusEnum.isEmpty()) {
      log.warn("unexpected log status: {}", status);
      return null;
    } else {
      return statusEnum.get();
    }
  }
}
