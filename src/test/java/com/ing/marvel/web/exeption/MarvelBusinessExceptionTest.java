package com.ing.marvel.web.exeption;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MarvelBusinessExceptionTest {

  @Test
  void testConstructor() {
    MarvelBusinessException marvelBusinessException = new MarvelBusinessException("errorMessage");

    assertEquals("errorMessage", marvelBusinessException.getMessage());
  }

}