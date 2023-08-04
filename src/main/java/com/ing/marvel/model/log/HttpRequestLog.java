package com.ing.marvel.model.log;

import lombok.Builder;
import lombok.Value;
import com.ing.marvel.model.log.enums.LogTypeEnum;
import com.ing.marvel.model.log.enums.StatusEnum;

@Value
@Builder
public class HttpRequestLog {

  LogTypeEnum logType;
  int id;
  String endpointName;
  long startTimestampInUnix;
  long endTimestampInUnix;
  StatusEnum status;
  String message;

}
