package com.ing.marvel.model.log.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class StatusEnumTest {

  @ParameterizedTest
  @ValueSource(strings = {"ok", "OK", "ko", "KO"})
  void given_correct_parameter_value_returns_valid_status(String statusInString){

    assertNotNull(StatusEnum.getValue(statusInString));

  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", " "})
  void given_false_parameter_value_returns_null(String logTypeInString){

    assertNull(StatusEnum.getValue(logTypeInString));

  }

}