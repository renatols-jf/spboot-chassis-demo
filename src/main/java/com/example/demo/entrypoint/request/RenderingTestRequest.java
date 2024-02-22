package com.example.demo.entrypoint.request;

import com.example.demo.entrypoint.request.util.rendering.AFieldRenderableImplementation;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

import java.util.List;

public class RenderingTestRequest extends Request {

    //We won't validate any data in this test!
    private Integer age;

    //Generally, we don't want our requests to receive the operation as a paramenter!
    public RenderingTestRequest(String operation, List<String> projection, Integer age) {
        super(operation, null, null, projection);
        this.age = age;
    }

    @Override
    protected Media doProcess() {
        return Media.ofRenderable(new AFieldRenderableImplementation(age));
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }
}

