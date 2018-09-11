package motyw.art.artMotywManager.controller;

import motyw.art.artMotywManager.service.ClothingService;
import motyw.art.artMotywManager.service.JeweleryService;
import motyw.art.artMotywManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static motyw.art.artMotywManager.util.StaticValues.*;
import static motyw.art.artMotywManager.util.ViewsAndRedirects.*;


@Controller
public class StatisticsController {
    @Autowired
    ProductService productService;
    @Autowired
    ClothingService clothingService;
    @Autowired
    JeweleryService jeweleryService;

    @GetMapping(value = {"/", "statistics"})
    public String showHomePage(Model model) {
        model.addAttribute("statistics", productService.getSalesStatistics(
                clothingService.getClothingSalesStatistics(Optional.empty()),
                jeweleryService.getJewelerySalesStatistics(Optional.empty())));
        model.addAttribute("title", STATISTICS_TITLE);
        return STATISTICS_VIEW;
    }

    @GetMapping("/monthlyStatistics")
    public String showStatistics(@RequestParam int month, @RequestParam int year) {
        return REDIRECT_TO_STATISTICS + month + "/" + year;
    }

    @GetMapping("/statistics/{month}/{year}")
    public String showMonthlyStatistics(@PathVariable("month") int month, @PathVariable("year") int year, Model model) {
        int monthAndYear[] = {month, year};
        model.addAttribute("statistics", productService.getSalesStatistics(
                clothingService.getClothingSalesStatistics(Optional.of(monthAndYear)),
                jeweleryService.getJewelerySalesStatistics(Optional.of(monthAndYear))));
        model.addAttribute("title", productService.getMonthlyStatisticsTitle(MONTHLY_STATISTICS_TITLE, monthAndYear));
        return STATISTICS_VIEW;
    }
}