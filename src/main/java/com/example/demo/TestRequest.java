package com.example.demo;

import io.github.renatolsjf.chassis.Chassis;
import io.github.renatolsjf.chassis.context.Context;
import io.github.renatolsjf.chassis.integration.RestOperation;
import io.github.renatolsjf.chassis.monitoring.timing.TimedOperation;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

import java.util.Collections;
import java.util.List;

public class TestRequest extends Request {

    public TestRequest() {
        super("DEMO_OPERATION", null, null);
    }

    @Override
    protected Media doProcess() {

        Context.forRequest().withRequestContextEntry("fixedField", "Present in all messages!")
                .createLogger()
                .info("A message")
                .attach("aField", "aValue")
                .attach("anotherField", "anotherValue")
                .log();

        Context.forRequest()
                .createLogger()
                .info("A  second message")
                .attach("aThridField", "aThirdValue")
                .log();

        TimedOperation.http().run(() -> System.out.println("Pretend I am a database call!"));

        RestOperation.get("GOOGLE", "SEARCH", "SEARCH", "https://www.google.com", null, null).call(null);
        return Media.empty();
    }

    @Override
    public RequestOutcome doResolveError(Throwable throwable) {
        return RequestOutcome.SERVER_ERROR;
    }


}
