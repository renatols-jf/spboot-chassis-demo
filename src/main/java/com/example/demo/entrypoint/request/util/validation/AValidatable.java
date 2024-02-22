package com.example.demo.entrypoint.request.util.validation;

import com.example.demo.entrypoint.request.Operations;
import io.github.renatolsjf.chassis.validation.Validatable;
import io.github.renatolsjf.chassis.validation.annotation.Minimum;
import io.github.renatolsjf.chassis.validation.annotation.Nullable;
import io.github.renatolsjf.chassis.validation.annotation.OneOf;
import io.github.renatolsjf.chassis.validation.annotation.Validation;

public class AValidatable implements Validatable {

    /*
    Can't be null under any operation
     */
    @Validation(nullable = @Nullable(Nullable.NullableType.CANT_BE_NULL))
    private String name;

    /*
    For VALIDATION_TEST, it can't be null and must be above 18.
    For VALIDATION_TEST_2, it can't be under 5 if a value was provided
     */
    @Validation(operation = Operations.VALIDATION_TEST_OPERATION,
            nullable = @Nullable(Nullable.NullableType.CANT_BE_NULL),
            minimum = @Minimum(value = 18, message = "Dude, you are underage!"))
    @Validation(operation = Operations.VALIDATION_TEST_2_OPERATION,
            minimum = @Minimum(5))
    private Integer age;

    /*
    For VALIDATION_TEST, it must be null
    For any other operation, must be one of spidey, ted, or rex
     */
    @Validation(operation = Operations.VALIDATION_TEST_OPERATION,
            nullable = @Nullable(Nullable.NullableType.MUST_BE_NULL))
    @Validation(oneOf = @OneOf({"spidey", "ted", "rex"}))
    private String nickName;

    private NestedValidatable nestedValidatable;

    /*
    Setters so Spring can initialize these automatically.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setNestedValidatable(NestedValidatable nestedValidatable) {
        this.nestedValidatable = nestedValidatable;
    }

}
