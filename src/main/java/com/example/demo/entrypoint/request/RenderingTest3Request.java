package com.example.demo.entrypoint.request;

import com.example.demo.entrypoint.request.util.rendering.ARenderable;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

import java.util.List;

public class RenderingTest3Request extends Request {

    public RenderingTest3Request(List<String> projection) {
        super(Operations.RENDERING_TEST_3_OPERATION, null, null, projection);
    }

    @Override
    protected Media doProcess() {
        return Media.ofRenderable(new ARenderable());
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }
}
