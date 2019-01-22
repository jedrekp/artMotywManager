package motyw.art.artMotywManager.controller;

import motyw.art.artMotywManager.domain.Product;
import motyw.art.artMotywManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/displayImage")
    public void displayImage(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        Product product = productService.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if (product != null) response.getOutputStream().write(product.getImageData());
        response.getOutputStream().close();
    }
}
