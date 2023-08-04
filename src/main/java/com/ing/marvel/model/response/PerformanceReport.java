package com.ing.marvel.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PerformanceReport {

  List<EndpointStatistic> endpointStatistics;

}
