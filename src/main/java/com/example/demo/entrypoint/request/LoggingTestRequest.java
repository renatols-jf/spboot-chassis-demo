package com.example.demo.entrypoint.request;

import io.github.renatolsjf.chassis.context.ApplicationLogger;
import io.github.renatolsjf.chassis.context.data.Classified;
import io.github.renatolsjf.chassis.context.data.cypher.HiddenClassifiedCypher;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

import java.util.Map;

public class LoggingTestRequest extends Request {

    public LoggingTestRequest(String requestContextEntries) {
        super(Operations.LOGGING_TEST_OPERATION, null, null, null, requestContextEntries);
    }

    public LoggingTestRequest(Map<String, String> requestContextEntries) {
        super(Operations.LOGGING_TEST_OPERATION, null, null, null, requestContextEntries);
    }

    @Override
    protected Media doProcess() {
        ApplicationLogger logger = this.context.createLogger();
        logger.info("This is a message without attachments!").log();
        logger.info("This is a message with attached fields!")
                .attach("aField", "aValue")
                .attach("anotherField", "anotherValue")
                .log();
        logger.info("This is a message with an attached object!")
                .attachObject(new SomeClass())
                .log();
        return Media.empty();
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }
}

class SomeClass {

    private String name = "SomeClass";
    private boolean aBoolean = true;
    @Classified(HiddenClassifiedCypher.class)
    private String hiddenNullField;
    @Classified(HiddenClassifiedCypher.class)
    private String hiddenField = "My value will not be shown!";
    @Classified
    private int age = 10; //Forbidden for underage! Best not to show!

}
