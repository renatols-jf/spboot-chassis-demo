package com.example.demo.entrypoint.request.util.rendering;

import io.github.renatolsjf.chassis.rendering.config.RenderTransformer;

public class MultiplyingRenderTransformer implements RenderTransformer<Integer, Integer> {
    @Override
    public Integer transform(Integer value) {
        return value != null
                ? value * 3
                : null;
    }
}
