# spboot-chassis demo
This application was created as an introduction to
[spboot-chassis](https://github.com/renatols-jf/spboot-chassis).
It aims to give a brief display of several of the `spboot-chassis` features.

# Usage
This is a standard Spring Boot application. It provides a few HTTP
endpoints to test requests that implement `spbbot-chassis` features.
This project, by no means, represents a general guideline for development practices.
Its only purpose is to show framework usage. As such, concerns like security,
error checking and handling, and input validation are left out. 

# Requests

## Testing projections

### class
`com.example.demo.entrypoint.request.ProjectionTestRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/projection-test
```

### query strings
- `projection`: list of fields to be returned by the response.

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/projection-test?projection=nestedField.anotherNestedField%2CisThisAnotherField%2CnestedField.secondColletion'
```

### Description
Tests the ability to filter responses by providing a projection query string. The
default response without providing a projection is:
```
{
    "aField": "avalue",
    "anotherField": "anotherValue",
    "isThisAnotherField": true,
    "fieldsDeclaredAbove": 3,
    "nestedField": {
        "aNestedField": "aNestedValue",
        "anotherNestedField": false,
        "aNestedInception": {
            "aNestedInceptionField": "",
            "changedFieldNamingPattern": "not cool",
            "firstCollection": [
                {
                    "colField1": 11,
                    "colField2": 12
                },
                {
                    "colField1": 21,
                    "colField2": 22
                },
                {
                    "colField1": 31,
                    "colField2": 32
                }
            ]
        },
        "secondColletion": [
            {
                "whatever": true,
                "wheneve": "true",
                "whoever": 1
            },
            {
                "whatever": true,
                "wheneve": "true",
                "whoever": 1
            },
            {
                "whatever": false,
                "wheneve": "false",
                "whoever": 0
            }
        ]
    }
}
```

The curl above transforms the response to:
```
{
    "isThisAnotherField": true,
    "nestedField": {
        "anotherNestedField": false,
        "secondColletion": [
            {
                "whatever": true,
                "wheneve": "true",
                "whoever": 1
            },
            {
                "whatever": true,
                "wheneve": "true",
                "whoever": 1
            },
            {
                "whatever": false,
                "wheneve": "false",
                "whoever": 0
            }
        ]
    }
}
```
Change  the projection query string to filter the response accordingly.

## Test logs

### class
`com.example.demo.entrypoint.request.LoggingTestRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/logging-test
```

### query strings
Not applicable

### request body
Not applicable

### request headers
- `X-CONTEXT-ENTRIES`: list of fields to be logged in each message. It's a String
  that has key-value entries, in which the key is separated from the value using `:`,
  and the entries are separated from each other using `;`.

### curl
```
curl --location 'localhost:8080/logging-test' \
--header 'X-CONTEXT-ENTRIES: aFixedKey:aFixedValue;isSecondField:true;theLastKey:I don'\''t want to end!'
```

### Description
Test the ability to add fixed attributes to logging as well as attributes
related to the message. It also displays the usage of `@Classified` to change 
attributes. The application console should be checked to view the results.
Change the `X-CONTEXT-ENTRIES` value to change logged attributes. The curl above
produces:

```
{"@timestamp":"2024-02-22T16:43:54.078-03:00","message":"Starting request: LoggingTestRequest","logger_name":"io.github.renatolsjf.chassis.request.Request","level":"INFO","context":"{}","operationTimes":"{internal=0, total=0}","isSecondField":"true","theLastKey":"I don't want to end!","operation":"LOGGING_TEST","aFixedKey":"aFixedValue","transactionId":"d1e66606-e714-4078-b3ba-c88656365374","elapsedTime":"0","application":"chassis-demo"}
{"@timestamp":"2024-02-22T16:43:54.079-03:00","message":"This is a message without attachments!","logger_name":"com.example.demo.entrypoint.request.LoggingTestRequest","level":"INFO","context":"{}","operationTimes":"{internal=1, total=1}","isSecondField":"true","theLastKey":"I don't want to end!","operation":"LOGGING_TEST","aFixedKey":"aFixedValue","transactionId":"d1e66606-e714-4078-b3ba-c88656365374","elapsedTime":"1","application":"chassis-demo"}
{"@timestamp":"2024-02-22T16:43:54.079-03:00","message":"This is a message with attached fields!","logger_name":"com.example.demo.entrypoint.request.LoggingTestRequest","level":"INFO","context":"{\"anotherField\":\"anotherValue\",\"aField\":\"aValue\"}","operationTimes":"{internal=1, total=1}","isSecondField":"true","theLastKey":"I don't want to end!","operation":"LOGGING_TEST","aFixedKey":"aFixedValue","transactionId":"d1e66606-e714-4078-b3ba-c88656365374","elapsedTime":"1","application":"chassis-demo"}
{"@timestamp":"2024-02-22T16:43:54.079-03:00","message":"This is a message with an attached object!","logger_name":"com.example.demo.entrypoint.request.LoggingTestRequest","level":"INFO","context":"{\"aBoolean\":true,\"name\":\"SomeClass\",\"hiddenNullField\":\"Classified; NOT informed\",\"hiddenField\":\"Classified; informed\"}","operationTimes":"{internal=1, total=1}","isSecondField":"true","theLastKey":"I don't want to end!","operation":"LOGGING_TEST","aFixedKey":"aFixedValue","transactionId":"d1e66606-e714-4078-b3ba-c88656365374","elapsedTime":"1","application":"chassis-demo"}
{"@timestamp":"2024-02-22T16:43:54.079-03:00","message":"Completed request: LoggingTestRequest","logger_name":"io.github.renatolsjf.chassis.request.Request","level":"INFO","context":"{\"result\":\"success\"}","operationTimes":"{internal=1, total=1}","isSecondField":"true","theLastKey":"I don't want to end!","operation":"LOGGING_TEST","aFixedKey":"aFixedValue","transactionId":"d1e66606-e714-4078-b3ba-c88656365374","elapsedTime":"1","application":"chassis-demo"}
```

## Test rendering #1

### class
`com.example.demo.entrypoint.request.RenderingTestRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/rendering-test
```

### query strings
- `age`: a ramdom integer.

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/rendering-test?age=20'
```

### Description
Tests the ability to render and transform the data based on the operation.
For this operation, the field `name` is ignored and the field `age` is
renamed to `fromIntegerToBoolean`. It's also transformed to output
false in case of an odd age number or true otherwise.


## Test rendering #2

### class
`com.example.demo.entrypoint.request.RenderingTestRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/rendering-test-2
```

### query strings
- `age`: a ramdom integer.

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/rendering-test-2?age=20'
```

### Description
Tests the ability to render and transform the data based on the operation.
For this operation, the field `name` is renamed to `givneName` and the field `age` is
transformed to output a value that is equal to three times its original value.


## Test rendering #3

### class
`com.example.demo.entrypoint.request.RenderingTest3Request`

### HTTP method
`GET`

### endpoint
```
localhost:8080/rendering-test-3
```

### query strings
Not applicable

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/rendering-test-3'
```

### Description
Tests the ability to render data based on `Renderable#render` implementation
and to render nested objects. A call to this request outputs:
```
{
    "name": "Pedro",
    "country": "Spain",
    "isMarried": false,
    "spokenLanguages": [
        {
            "name": "Spanish"
        },
        {
            "name": "English"
        }
    ]
}
```


## Validation test #1

### class
`com.example.demo.entrypoint.request.ValidatableTestRequest`

### HTTP method
`POST`

### endpoint
```
localhost:8080/validation-test
```

### query strings
Not applicable

### request body
- `name`: String; can't be null.
- `age`: Integer; can't be null and must be 18 or above.
- `nickName`: String; must be null.
- `nestedValidatable.cantBeNull`: String, can't be null
  (The object nestedValidatable can be null though).
- `nestedValidatable.canBeNull`: String.

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/validation-test' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Andrew",
    "age": 18,
    "nestedValidatable": {
        "cantBeNull": "Not null"
    }
    
}'
```

### Description
Tests the ability to validate input based on the operation. Change the request body
to test the validation rules.


## Validation test #2

### class
`com.example.demo.entrypoint.request.ValidatableTestRequest`

### HTTP method
`POST`

### endpoint
```
localhost:8080/validation-test-2
```

### query strings
Not applicable

### request body
- `name`: String; can't be null.
- `age`: Integer; can be null, but must be 5 or above if provided.
- `nickName`: String; can be null, but must be equal to `spidey`, `ted`, `or rex`
  if provided.
- `nestedValidatable.cantBeNull`: String, can't be null 
  (The object nestedValidatable can be null though).
- `nestedValidatable.canBeNull`: String.

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/validation-test-2' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Peter",
    "age": 5,
    "nickName": "spidey",
    "nestedValidatable": {
        "cantBeNull": "Not null"
    }
    
}'
```

### Description
Tests the ability to validate input based on the operation. Change the request body
to test the validation rules.


## Integration test

### class
`com.example.demo.entrypoint.request.IntegrationTestRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/integration-test
```

### query strings
Not applicable

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/integration-test'
```

### Description
Tests the ability to log HTTP requests and measure their time. Check the application
logs to verify.


## Timing test

### class
`com.example.demo.entrypoint.request.TimingTestRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/timing-test
```

### query strings
- `tag`: String; a tag to be used to log execution time.

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/timing-test?tag=aTagValue'
```

### Description
Tests the ability to record execution time under different tags. Check the application
logs to verify. Change the `tag` query string to change one of the tags being used.


## Prometheus

### class
Not applicable

### HTTP method
`GET`

### endpoint
```
localhost:8080/prometheus
```

### query strings
Not applicable

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/prometheus'
```

### Description
Metrics to be exported to Prometheus. Call this to verify how the other requests
update the metrics.


## HealthCheck

### class
`io.github.renatolsjf.chassis.monitoring.request.HealthRequest`

### HTTP method
`GET`

### endpoint
```
localhost:8080/healthcheck
```

### query strings
Not applicable

### request body
Not applicable

### request headers
Not applicable

### curl
```
curl --location 'localhost:8080/healthcheck'
```

### Description
Application health information displayed in `json`. Call this to verify how the 
other requests update the data. Sample snapshot:
```
{
    "application": {
        "health": 100.0,
        "load": {
            "requestCount": 5,
            "requestTime": {
                "internal": {
                    "quantiles": {
                        "0.5": 1,
                        "0.95": 4,
                        "0.99": 4
                    }
                },
                "aTagValue": {
                    "quantiles": {
                        "0.5": 568,
                        "0.95": 568,
                        "0.99": 568
                    }
                },
                "db": {
                    "quantiles": {
                        "0.5": 1114,
                        "0.95": 1114,
                        "0.99": 1114
                    }
                }
            }
        },
        "result": {
            "success": 5,
            "clientError": 0,
            "serverError": 0
        }
    },
    "operations": [
        {
            "name": "RENDERING_TEST",
            "health": 100.0,
            "load": {
                "requestCount": 1,
                "requestTime": {
                    "internal": {
                        "quantiles": {
                            "0.5": 3,
                            "0.95": 3,
                            "0.99": 3
                        }
                    }
                }
            },
            "result": {
                "success": 1,
                "clientError": 0,
                "serverError": 0
            }
        },
        {
            "name": "RENDERING_TEST_3",
            "health": 100.0,
            "load": {
                "requestCount": 1,
                "requestTime": {
                    "internal": {
                        "quantiles": {
                            "0.5": 1,
                            "0.95": 1,
                            "0.99": 1
                        }
                    }
                }
            },
            "result": {
                "success": 1,
                "clientError": 0,
                "serverError": 0
            }
        },
        {
            "name": "TIMING_TEST",
            "health": 100.0,
            "load": {
                "requestCount": 1,
                "requestTime": {
                    "aTagValue": {
                        "quantiles": {
                            "0.5": 568,
                            "0.95": 568,
                            "0.99": 568
                        }
                    },
                    "internal": {
                        "quantiles": {
                            "0.5": 1,
                            "0.95": 1,
                            "0.99": 1
                        }
                    },
                    "db": {
                        "quantiles": {
                            "0.5": 1114,
                            "0.95": 1114,
                            "0.99": 1114
                        }
                    }
                }
            },
            "result": {
                "success": 1,
                "clientError": 0,
                "serverError": 0
            }
        },
        {
            "name": "VALIDATION_TEST",
            "health": 100.0,
            "load": {
                "requestCount": 1,
                "requestTime": {
                    "internal": {
                        "quantiles": {
                            "0.5": 1,
                            "0.95": 1,
                            "0.99": 1
                        }
                    }
                }
            },
            "result": {
                "success": 1,
                "clientError": 0,
                "serverError": 0
            }
        },
        {
            "name": "VALIDATION_TEST_2",
            "health": 100.0,
            "load": {
                "requestCount": 1,
                "requestTime": {
                    "internal": {
                        "quantiles": {
                            "0.5": 4,
                            "0.95": 4,
                            "0.99": 4
                        }
                    }
                }
            },
            "result": {
                "success": 1,
                "clientError": 0,
                "serverError": 0
            }
        }
    ]
}
```


