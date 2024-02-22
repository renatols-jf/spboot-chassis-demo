package com.example.demo.entrypoint.request.util.validation;

import io.github.renatolsjf.chassis.validation.Validatable;
import io.github.renatolsjf.chassis.validation.annotation.Nullable;
import io.github.renatolsjf.chassis.validation.annotation.Validation;

public class NestedValidatable implements Validatable {

    @Validation(nullable = @Nullable(Nullable.NullableType.CANT_BE_NULL))
    private String cantBeNull;

    private String canBeNull;

    /*
    Setters so Spring can initialize these automatically.
    */
    public void setCantBeNull(String cantBeNull) {
        this.cantBeNull = cantBeNull;
    }

    public void setCanBeNull(String canBeNull) {
        this.canBeNull = canBeNull;
    }

}
