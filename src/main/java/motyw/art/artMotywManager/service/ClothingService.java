package motyw.art.artMotywManager.service;

import motyw.art.artMotywManager.dao.ClothingDao;
import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.util.ClothingSize;
import motyw.art.artMotywManager.util.ClothingTheme;
import motyw.art.artMotywManager.util.ClothingType;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static motyw.art.artMotywManager.util.StaticValues.*;


@Service
public class ClothingService {

    @Autowired
    private ClothingDao clothingDao;

    public Clothing findById(String id) {
        return clothingDao.findById(id);
    }

    public List<Clothing> filterClothing
            (String priceMin, String priceMax, String availability, String clothingType, String size, String theme, String cutType) {

        if (priceMin.equals("")) priceMin = MINIMUM_PRICE;
        if (priceMax.equals("")) priceMax = MAXIMUM_PRICE;

        List<Clothing> filteredClothingList = clothingDao.getAllClothingInPriceRange(Double.parseDouble(priceMin), Double.parseDouble(priceMax));

        /*Following methods will only be called when user wants to filter specific category, otherwise user selects "all" which is not a valid Enum value*/
        if (EnumUtils.isValidEnum(ProductAvailability.class, availability))
            filteredClothingList = filterAvailability(filteredClothingList, Enum.valueOf(ProductAvailability.class, availability));
        if (EnumUtils.isValidEnum(ClothingType.class, clothingType))
            filteredClothingList = filterClothingType(filteredClothingList, Enum.valueOf(ClothingType.class, clothingType));
        if (EnumUtils.isValidEnum(ClothingSize.class, size))
            filteredClothingList = filterSize(filteredClothingList, Enum.valueOf(ClothingSize.class, size));
        if (EnumUtils.isValidEnum(ClothingTheme.class, theme))
            filteredClothingList = filterTheme(filteredClothingList, Enum.valueOf(ClothingTheme.class, theme));
        /*Following method will not be called when user doesn't want to filter cutType and therefore leaves the cutType field blank*/
        if (!cutType.equals(""))
            filteredClothingList = filterCutType(filteredClothingList, cutType);

        return filteredClothingList;
    }

    public Map<String, Double> getClothingSalesStatistics(Optional<int[]> monthAndYear) {
        Map<String, Double> clothingSalesStatistics = new HashMap<>();
        List<Clothing> soldClothing;
        if (monthAndYear.isPresent()) {
            soldClothing = getAllSoldClothingForMonth(monthAndYear.get());
        } else {
            soldClothing = getAllSoldClothing();
        }
        clothingSalesStatistics = getGeneralStatistics(clothingSalesStatistics, soldClothing);
        clothingSalesStatistics = getClothingTypeStatistics(clothingSalesStatistics, soldClothing);
        clothingSalesStatistics = getSizeStatistics(clothingSalesStatistics, soldClothing);
        clothingSalesStatistics = getThemeStatistics(clothingSalesStatistics, soldClothing);
        return clothingSalesStatistics;

    }

    private List<Clothing> getAllSoldClothing() {
        return clothingDao.getAllSoldClothing();
    }

    private List<Clothing> getAllSoldClothingForMonth(int[] monthAndYear) {
        return clothingDao.getAllSoldClothingForMonth(monthAndYear);
    }

    private double getTotalIncomeOf(List<Clothing> productList) {
        return productList.stream().mapToDouble(clothing -> clothing.getPrice()).sum();
    }

    private Map<String, Double> getGeneralStatistics(Map<String, Double> clothingSalesStatistics, List<Clothing> soldClothing) {
        clothingSalesStatistics.put(CLOTHING + SALES, (double) soldClothing.size());
        clothingSalesStatistics.put(CLOTHING + INCOME, getTotalIncomeOf(soldClothing));
        return clothingSalesStatistics;
    }

    private Map<String, Double> getClothingTypeStatistics(Map<String, Double> clothingSalesStatistics, List<Clothing> soldClothing) {
        for (ClothingType type : ClothingType.values()) {
            List<Clothing> filteredByTypeList = filterClothingType(soldClothing, type);
            clothingSalesStatistics.put(type + SALES, (double) filteredByTypeList.size());
            clothingSalesStatistics.put(type + INCOME, getTotalIncomeOf(filteredByTypeList));
        }
        return clothingSalesStatistics;
    }

    private Map<String, Double> getSizeStatistics(Map<String, Double> clothingSalesStatistics, List<Clothing> soldClothing) {
        for (ClothingSize size : ClothingSize.values()) {
            List<Clothing> filteredBySizeList = filterSize(soldClothing, size);
            clothingSalesStatistics.put(size + SALES, (double) filteredBySizeList.size());
            clothingSalesStatistics.put(size + INCOME, getTotalIncomeOf(filteredBySizeList));
        }
        return clothingSalesStatistics;
    }

    private Map<String, Double> getThemeStatistics(Map<String, Double> clothingSalesStatistics, List<Clothing> soldClothing) {
        for (ClothingTheme theme : ClothingTheme.values()) {
            List<Clothing> filteredByThemeList = filterTheme(soldClothing, theme);
            clothingSalesStatistics.put(theme + SALES, (double) filteredByThemeList.size());
            clothingSalesStatistics.put(theme + INCOME, getTotalIncomeOf(filteredByThemeList));
        }
        return clothingSalesStatistics;
    }

    private List<Clothing> filterAvailability(List<Clothing> filteredClothingList, ProductAvailability availability) {
        return filteredClothingList.stream().filter(clothing -> clothing.getAvailability() == availability).collect(Collectors.toList());
    }

    private List<Clothing> filterClothingType(List<Clothing> filteredClothingList, ClothingType clothingType) {
        return filteredClothingList.stream().filter(clothing -> clothing.getClothingType() == clothingType).collect(Collectors.toList());
    }

    private List<Clothing> filterSize(List<Clothing> filteredClothingList, ClothingSize size) {
        return filteredClothingList.stream().filter(clothing -> clothing.getSize() == size).collect(Collectors.toList());
    }

    private List<Clothing> filterTheme(List<Clothing> filteredClothingList, ClothingTheme theme) {
        return filteredClothingList.stream().filter(clothing -> clothing.getTheme() == theme).collect(Collectors.toList());
    }

    private List<Clothing> filterCutType(List<Clothing> filteredClothingList, String cutType) {
        return filteredClothingList.stream().filter(clothing -> clothing.getCutType().equals(cutType)).collect(Collectors.toList());
    }
}

