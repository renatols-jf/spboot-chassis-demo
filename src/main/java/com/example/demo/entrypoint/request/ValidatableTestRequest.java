package com.example.demo.entrypoint.request;

import com.example.demo.entrypoint.request.util.validation.AValidatable;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

public class ValidatableTestRequest extends Request {

    private AValidatable aValidatable;

    public ValidatableTestRequest(String operation, AValidatable aValidatable) {
        super(operation, null, null);
        this.aValidatable = aValidatable;
    }

    @Override
    protected Media doProcess() {
        this.aValidatable.validate();
        return Media.empty();
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }
}
