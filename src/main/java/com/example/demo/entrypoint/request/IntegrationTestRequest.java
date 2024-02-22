package com.example.demo.entrypoint.request;

import io.github.renatolsjf.chassis.integration.RestOperation;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

public class IntegrationTestRequest extends Request {

    public IntegrationTestRequest() {
        super(Operations.INTEGRATION_TEST_OPERATION, null, null);
    }

    @Override
    protected Media doProcess() {
        RestOperation.get("GOOGLE", "SEARCH", "SEARCH", "https://www.google.com", null, null)
                .call(null);
        return Media.empty();
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }
}
