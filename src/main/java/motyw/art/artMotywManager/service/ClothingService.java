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
import java.util.stream.Collectors;

import static motyw.art.artMotywManager.util.StaticValues.*;


@Service
public class ClothingService {

    @Autowired
    private ClothingDao clothingDao;

    public Clothing findById(String id) {
        return clothingDao.findById(id);
    }

    public List<Clothing> getAllClothesInPriceRange(String priceMin, String priceMax) {
        if (priceMin.equals("")) priceMin = MINIMUM_VALID_PRICE;
        if (priceMax.equals("")) priceMax = MAXIMUM_VALID_PRICE;

        return clothingDao.getAllClothingInPriceRange(Double.parseDouble(priceMin), Double.parseDouble(priceMax));
    }

    public List<Clothing> filterClothes(List<Clothing> listToFilter, String availability, String clothingType,
                                        String size, String theme, String cutType) {
        if (EnumUtils.isValidEnum(ProductAvailability.class, availability))
            listToFilter = filterAvailability(listToFilter, Enum.valueOf(ProductAvailability.class, availability));
        if (EnumUtils.isValidEnum(ClothingType.class, clothingType))
            listToFilter = filterClothingType(listToFilter, Enum.valueOf(ClothingType.class, clothingType));
        if (EnumUtils.isValidEnum(ClothingSize.class, size))
            listToFilter = filterSize(listToFilter, Enum.valueOf(ClothingSize.class, size));
        if (EnumUtils.isValidEnum(ClothingTheme.class, theme))
            listToFilter = filterTheme(listToFilter, Enum.valueOf(ClothingTheme.class, theme));

        if (!cutType.equals(""))
            listToFilter = filterCutType(listToFilter, cutType);

        return listToFilter;
    }

    public Map<String, Double> getClothingSalesStatistics(int[] monthAndYear) {
        Map<String, Double> clothingSalesStatistics = new HashMap<>();
        List<Clothing> soldClothing;
        if (monthAndYear.length == 0) {
            soldClothing = getAllSoldClothing();
        } else {
            soldClothing = getAllSoldClothingForMonth(monthAndYear);
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

    private List<Clothing> filterAvailability(List<Clothing> listToFilter, ProductAvailability availability) {
        return listToFilter.stream()
                .filter(clothing -> clothing.getAvailability() == availability)
                .collect(Collectors.toList());
    }

    private List<Clothing> filterClothingType(List<Clothing> listToFilter, ClothingType clothingType) {
        return listToFilter.stream().filter(clothing -> clothing.getClothingType() == clothingType).collect(Collectors.toList());
    }

    private List<Clothing> filterSize(List<Clothing> listToFilter, ClothingSize size) {
        return listToFilter.stream().filter(clothing -> clothing.getSize() == size).collect(Collectors.toList());
    }

    private List<Clothing> filterTheme(List<Clothing> listToFilter, ClothingTheme theme) {
        return listToFilter.stream().filter(clothing -> clothing.getTheme() == theme).collect(Collectors.toList());
    }

    private List<Clothing> filterCutType(List<Clothing> listToFilter, String cutType) {
        return listToFilter.stream().filter(clothing -> clothing.getCutType().equals(cutType)).collect(Collectors.toList());
    }
}

