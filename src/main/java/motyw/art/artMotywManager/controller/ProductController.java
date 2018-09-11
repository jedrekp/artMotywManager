package motyw.art.artMotywManager.controller;


import motyw.art.artMotywManager.domain.Product;
import motyw.art.artMotywManager.service.ProductService;
import motyw.art.artMotywManager.util.UserMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static motyw.art.artMotywManager.util.ViewsAndRedirects.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return SINGLE_PRODUCT_VIEW;
        } else {
            redirectAttributes.addFlashAttribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage());
            return REDIRECT_TO_MESSAGE;
        }
    }

    @GetMapping("/find")
    public String searchForProduct(@RequestParam("id") String id) {
        return REDIRECT_TO_PRODUCT + id;
    }

    @GetMapping("/showAllProducts")
    public String showAllProducts(RedirectAttributes redirectAttributes) {
        List<Product> listOfAllProducts = productService.getListOfAllProducts();
        if (listOfAllProducts.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage());
            return REDIRECT_TO_MESSAGE;
        } else redirectAttributes.addFlashAttribute("productList", listOfAllProducts);
        return REDIRECT_TO_PRODUCT_LIST;
    }

    @PostMapping("/markAsSold/{id}")
    public String markAsSold(@PathVariable("id") String id) {
        Optional<Product> product = productService.findById(id);
        product.ifPresent(p -> productService.markAsSold(p));
        return REDIRECT_TO_PRODUCT + id;
    }

    @GetMapping("/productList")
    public String productList() {
        return PRODUCT_LIST_VIEW;
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productService.findById(id);
        product.ifPresent(p -> productService.deleteProduct(p));
        redirectAttributes.addFlashAttribute("message", UserMessages.PRODUCT_DELETED_MSG.getUserMessage() + id);
        return REDIRECT_TO_MESSAGE;
    }

    @GetMapping("/showMessage")
    public String showMessage() {
        return MESSAGE_VIEW;
    }
}
