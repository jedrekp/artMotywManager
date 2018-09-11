package motyw.art.artMotywManager.validators;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static motyw.art.artMotywManager.util.StaticValues.IMAGE_PATTERN;

public class ImageFileValidator implements ConstraintValidator<ImageFile,CommonsMultipartFile> {

    @Override
    public void initialize(ImageFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(CommonsMultipartFile imageFile, ConstraintValidatorContext constraintValidatorContext) {
        if (imageFile.getSize()==0) return true;
        Pattern pattern = Pattern.compile(IMAGE_PATTERN);
        return pattern.matcher(imageFile.getOriginalFilename()).matches();
    }
}

