package motyw.art.artMotywManager.controller;

import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.service.ClothingService;
import motyw.art.artMotywManager.service.ProductService;
import motyw.art.artMotywManager.util.*;
import motyw.art.artMotywManager.validators.AddValidation;
import motyw.art.artMotywManager.validators.EditValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static motyw.art.artMotywManager.util.ViewsAndRedirects.*;

@Controller
@RequestMapping("/clothing")
public class ClothingController {

    @Autowired
    private ClothingService clothingService;
    @Autowired
    private ProductService productService;

    @GetMapping("/addNewClothing")
    public String showAddNewClothingForm(Model model) {
        model.addAttribute("availability", ProductAvailability.AVAILABLE);
        model.addAttribute("clothing", new Clothing());
        addClothingSubCategoriesToModel(model);
        return ADD_NEW_CLOTHING_VIEW;
    }

    @PostMapping("/addNewClothing")
    public String addNewClothing(@Validated({AddValidation.class}) Clothing clothing, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addClothingSubCategoriesToModel(model);
            return ADD_NEW_CLOTHING_VIEW;
        }
        productService.setProductImageData(clothing);
        productService.saveOrUpdateProduct(clothing);
        return REDIRECT_TO_PRODUCT + clothing.getId();
    }

    @GetMapping("/editClothing/{id}")
    public String showEditClothingForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("clothing", clothingService.findById(id));
        addClothingSubCategoriesToModel(model);
        return EDIT_CLOTHING_VIEW;
    }

    @PostMapping("editClothing/{id}")
    public String editClothing(@PathVariable("id") String id, @Validated({EditValidation.class}) Clothing clothing, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addClothingSubCategoriesToModel(model);
            return EDIT_CLOTHING_VIEW;
        }
        productService.setProductImageData(clothing);
        productService.saveOrUpdateProduct(clothing);
        return REDIRECT_TO_PRODUCT + id;
    }

    @GetMapping("/filterClothes")
    public String showFilterClothesForm(Model model) {
        model.addAttribute("productAvailability", ProductAvailability.values());
        addClothingSubCategoriesToModel(model);
        return FILTER_CLOTHING_VIEW;
    }

    @GetMapping("/clothingSearchResults")
    public String showClothingSearchResults(String priceMin, String priceMax, String availability, String clothingType,
                                            String size, String theme, String cutType, RedirectAttributes redirectAttributes) {

        List<Clothing> filteredClothes = clothingService.getAllClothesInPriceRange(priceMin, priceMax);
        if (!filteredClothes.isEmpty())
            filteredClothes = clothingService.filterClothes(filteredClothes, availability, clothingType, size, theme, cutType);

        if (filteredClothes.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage());
            return REDIRECT_TO_MESSAGE;
        }
        redirectAttributes.addFlashAttribute("productList", filteredClothes);
        return REDIRECT_TO_PRODUCT_LIST;
    }

    private void addClothingSubCategoriesToModel(Model model) {
        model.addAttribute("clothingTypes", ClothingType.values());
        model.addAttribute("clothingSizes", ClothingSize.values());
        model.addAttribute("clothingThemes", ClothingTheme.values());
    }
}

