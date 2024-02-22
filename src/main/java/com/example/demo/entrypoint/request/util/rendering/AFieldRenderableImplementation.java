package com.example.demo.entrypoint.request.util.rendering;

import com.example.demo.entrypoint.request.Operations;
import io.github.renatolsjf.chassis.rendering.FieldRenderable;
import io.github.renatolsjf.chassis.rendering.config.*;

public class AFieldRenderableImplementation implements FieldRenderable {

    @RenderConfig(operation = Operations.RENDERING_TEST_OPERATION, policy = @RenderPolicy(RenderPolicy.Policy.IGNORE))
    @RenderConfig(operation = Operations.RENDERING_TEST_2_OPERATION, alias = @RenderAlias("givenName"))
    private String name = "Nameless";

    private String greeting = "Hey you!";

    @RenderConfig(operation = Operations.RENDERING_TEST_OPERATION,
            alias = @RenderAlias("fromIntegerToBoolean"),
            transformer = @RenderTransform(IntegerToBooleanRenderTransformer.class))
    @RenderConfig(operation = Operations.RENDERING_TEST_2_OPERATION,
            transformer = @RenderTransform(MultiplyingRenderTransformer.class))
    private Integer age;

    public AFieldRenderableImplementation(Integer age) {
        this.age = age;
    }

}




