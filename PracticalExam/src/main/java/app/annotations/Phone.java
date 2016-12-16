package app.annotations;

import javax.validation.Payload;

public @interface Phone {

    String message() default "Enter valid email address.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
