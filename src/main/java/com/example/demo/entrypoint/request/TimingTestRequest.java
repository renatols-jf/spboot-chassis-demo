package com.example.demo.entrypoint.request;

import io.github.renatolsjf.chassis.monitoring.timing.TimedOperation;
import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

public class TimingTestRequest extends Request {

    private String tag;

    public TimingTestRequest(String tag) {
        super(Operations.TIMING_TEST_OPERATION, null, null);
        this.tag = tag;
    }

    @Override
    protected Media doProcess() {

        TimedOperation.db().run(() -> {
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        new TimedOperation<>(this.tag).run(() -> {
            try {
                Thread.sleep(567);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        return Media.empty();
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }
}
