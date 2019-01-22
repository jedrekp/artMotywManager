package motyw.art.artMotywManager.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*overrides main package validators for controller testing */

public class UniqueIdValidator implements ConstraintValidator<UniqueId, String> {

    @Override
    public void initialize(UniqueId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return id.equals("uniqueId");
    }
}
