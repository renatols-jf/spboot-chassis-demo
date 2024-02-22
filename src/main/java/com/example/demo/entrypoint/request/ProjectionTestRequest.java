package com.example.demo.entrypoint.request;

import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.request.Request;
import io.github.renatolsjf.chassis.request.RequestOutcome;

import java.util.List;

/**
 * Request designed to display the projection feature. A projection can be applied to any request, but this one
 * has a big enough body to test it.
 */
public class ProjectionTestRequest extends Request {

    public ProjectionTestRequest(List<String> projection) {
        super(Operations.PROJECTION_TEST_OPERATION, null, null, projection);
    }

    @Override
    protected Media doProcess() {
        return Media.ofRenderable(media ->
                media.print("aField", "avalue")
                        .print("anotherField", "anotherValue")
                        .print("isThisAnotherField", true)
                        .print("fieldsDeclaredAbove", 3)
                        .forkRenderable("nestedField", m2 ->
                                m2.print("aNestedField", "aNestedValue")
                                        .print("anotherNestedField", false)
                                        .forkRenderable("aNestedInception", m3 ->
                                                m3.print("aNestedInceptionField", "")
                                                        .print("changedFieldNamingPattern", "not cool")
                                                        .forkCollection("firstCollection",
                                                                m4 -> m4.print("colField1", 11).print("colField2", 12),
                                                                m5 -> m5.print("colField1", 21).print("colField2", 22),
                                                                m6 -> m6.print("colField1", 31).print("colField2", 32))
                                        ).forkCollection("secondColletion",
                                        m7 -> m7.print("whatever", true).print("wheneve", "true").print("whoever", 1),
                                        m8 -> m8.print("whatever", true).print("wheneve", "true").print("whoever", 1),
                                        m9 -> m9.print("whatever", false).print("wheneve", "false").print("whoever", 0))
                        )
        );
    }

    @Override
    protected RequestOutcome doResolveError(Throwable t) {
        return null;
    }

}
