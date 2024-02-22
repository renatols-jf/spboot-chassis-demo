package com.example.demo.entrypoint.request.util.rendering;

import io.github.renatolsjf.chassis.rendering.config.RenderTransformer;

public class IntegerToBooleanRenderTransformer implements RenderTransformer<Boolean, Integer> {
    @Override
    public Boolean transform(Integer value) {
        if (value == null) {
            return true;
        }
        return value % 2 == 0
                ? true
                : false;
    }
}