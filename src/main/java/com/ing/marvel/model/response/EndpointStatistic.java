package com.ing.marvel.model.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EndpointStatistic {

  String path;
  int requestTotal;
  int failedRequestTotal;
  double responseTimeAverage;
  double percentile95th;
  double percentile99th;

}
