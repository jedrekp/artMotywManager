package motyw.art.artMotywManager.controller;

import motyw.art.artMotywManager.domain.Jewelery;
import motyw.art.artMotywManager.service.JeweleryService;
import motyw.art.artMotywManager.service.ProductService;
import motyw.art.artMotywManager.util.JewelerySubstance;
import motyw.art.artMotywManager.util.JeweleryType;
import motyw.art.artMotywManager.util.ProductAvailability;
import motyw.art.artMotywManager.util.UserMessages;
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
@RequestMapping("jewelery")
public class JeweleryController {

    @Autowired
    private JeweleryService jeweleryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/addNewJewelery")
    public String showAddNewJeweleryForm(Model model) {
        model.addAttribute(new Jewelery());
        model.addAttribute("availability", ProductAvailability.AVAILABLE);
        addJewelerySubCategoriesToModel(model);
        return ADD_NEW_JEWELERY_VIEW;
    }

    @PostMapping("/addNewJewelery")
    public String addNewJewelery(@Validated({AddValidation.class}) Jewelery jewelery, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addJewelerySubCategoriesToModel(model);
            return ADD_NEW_JEWELERY_VIEW;
        }
        productService.setProductImageData(jewelery);
        productService.saveOrUpdateProduct(jewelery);
        return REDIRECT_TO_PRODUCT + jewelery.getId();
    }

    @GetMapping("/editJewelery/{id}")
    public String showEditJeweleryForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("jewelery", jeweleryService.findById(id));
        addJewelerySubCategoriesToModel(model);
        return EDIT_JEWELERY_VIEW;
    }

    @PostMapping("editJewelery/{id}")
    public String editJewelery(@PathVariable("id") String id, @Validated({EditValidation.class}) Jewelery jewelery,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            addJewelerySubCategoriesToModel(model);
            return EDIT_JEWELERY_VIEW;
        }
        productService.setProductImageData(jewelery);
        productService.saveOrUpdateProduct(jewelery);
        return REDIRECT_TO_PRODUCT + id;
    }

    @GetMapping("/filterJewelery")
    public String showFilterJeweleryForm(Model model) {
        model.addAttribute("productAvailability", ProductAvailability.values());
        addJewelerySubCategoriesToModel(model);
        return FILTER_JEWELERY_VIEW;
    }

    @GetMapping("/jewelerySearchResults")
    public String showJewelerySearchResults(String priceMin, String priceMax, String availability, String jeweleryType, String substance,
                                            RedirectAttributes redirectAttributes) {

        List<Jewelery> filteredJewelery = jeweleryService.getAllJeweleryInPriceRange(priceMin, priceMax);

        if (!filteredJewelery.isEmpty())
            filteredJewelery = jeweleryService.filterJewelery(filteredJewelery, availability, jeweleryType, substance);

        if (filteredJewelery.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage());
            return REDIRECT_TO_MESSAGE;
        }
        redirectAttributes.addFlashAttribute("productList", filteredJewelery);
        return REDIRECT_TO_PRODUCT_LIST;
    }

    private void addJewelerySubCategoriesToModel(Model model) {
        model.addAttribute("jeweleryTypes", JeweleryType.values());
        model.addAttribute("jewelerySubstances", JewelerySubstance.values());
    }
}

