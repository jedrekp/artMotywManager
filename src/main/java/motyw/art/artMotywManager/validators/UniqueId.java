package motyw.art.artMotywManager.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueIdValidator.class})
public @interface UniqueId {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
