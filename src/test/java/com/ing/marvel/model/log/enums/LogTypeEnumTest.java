package com.ing.marvel.model.log.enums;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class LogTypeEnumTest {

  @ParameterizedTest
  @ValueSource(strings = {"request", "user", "run", "error","REQUEST", "USER", "RUN", "ERROR"})
  void given_correct_parameter_value_returns_valid_log_type(String logTypeInString){

    assertNotNull(LogTypeEnum.getValue(logTypeInString));

  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", " "})
  void given_false_parameter_value_returns_valid_log_type(String logTypeInString){

    assertNull(LogTypeEnum.getValue(logTypeInString));

  }

}