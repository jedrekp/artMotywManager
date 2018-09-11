package motyw.art.artMotywManager.validators;

import motyw.art.artMotywManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueIdValidator implements ConstraintValidator<UniqueId, String> {
    @Autowired
    private ProductService productService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        boolean validId = false;
        if (!productService.findById(id).isPresent()) validId = true;
        return validId;
    }
}
