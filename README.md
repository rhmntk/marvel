# marvel

## ING Marvelous squad technical assignment

After running this spring-boot app, an API request can be made via postman by calling a GET request to retrieve the API performance statistics e.g.:

### API endpoint
```
localhost:8080/endpoint/report
```

### Example Response:
```
{
    "endpointStatistics": [
        {
            "path": "api/test/{testId}/history",
            "requestTotal": 26842,
            "failedRequestTotal": 0,
            "responseTimeAverage": 2912.838536621712,
            "percentile95th": 9421.749999999993,
            "percentile99th": 14863.869999999999
        },
        {
            "path": "api/test/{testId}/analysis",
            "requestTotal": 26842,
            "failedRequestTotal": 0,
            "responseTimeAverage": 37.758550033529545,
            "percentile95th": 393.75,
            "percentile99th": 5102.5
        },
        {
            "path": "api/test",
            "requestTotal": 13421,
            "failedRequestTotal": 0,
            "responseTimeAverage": 75.92638402503539,
            "percentile95th": 13401.500000000002,
            "percentile99th": 24711.220000000056
        },
        {
            "path": "api/test/{testId}",
            "requestTotal": 13421,
            "failedRequestTotal": 0,
            "responseTimeAverage": 18.3228522464794,
            "percentile95th": 9771.15,
            "percentile99th": 10661.33
        },
        {
            "path": "api/test/{testId}/reference",
            "requestTotal": 13421,
            "failedRequestTotal": 0,
            "responseTimeAverage": 81.82706206691006,
            "percentile95th": 5677.249999999998,
            "percentile99th": 9004.450000000008
        },
        {
            "path": "api/test/{testId}/overview",
            "requestTotal": 26842,
            "failedRequestTotal": 0,
            "responseTimeAverage": 37.51307652186871,
            "percentile95th": 411.79999999999853,
            "percentile99th": 4180.13999999996
        },
        {
            "path": "api/test/{testId}/summary",
            "requestTotal": 26842,
            "failedRequestTotal": 0,
            "responseTimeAverage": 38.49243722524402,
            "percentile95th": 783.0499999999995,
            "percentile99th": 3283.4499999999994
        }
    ]
}
```