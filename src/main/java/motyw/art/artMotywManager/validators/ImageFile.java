package motyw.art.artMotywManager.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFileValidator.class})
public @interface ImageFile {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
