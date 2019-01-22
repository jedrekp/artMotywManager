package motyw.art.artMotywManager.util;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static motyw.art.artMotywManager.util.ViewsAndRedirects.REDIRECT_TO_MESSAGE;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UploadSizeExceededAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String uploadSizeExceeded(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message",UserMessages.UPLOAD_SIZE_EXCEEDED.getUserMessage());
        return REDIRECT_TO_MESSAGE;
    }
}
