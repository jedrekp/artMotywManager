package motyw.art.artMotywManager.service;

import motyw.art.artMotywManager.dao.JeweleryDao;
import motyw.art.artMotywManager.domain.Jewelery;
import motyw.art.artMotywManager.util.JewelerySubstance;
import motyw.art.artMotywManager.util.JeweleryType;
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
public class JeweleryService {

    @Autowired
    private JeweleryDao jeweleryDao;

    public Jewelery findById(String id) {
        return jeweleryDao.findById(id);
    }

    public List<Jewelery> filterJewelery(String priceMin, String priceMax, String availability, String jeweleryType, String substance) {

        if (priceMin.equals("")) priceMin = MINIMUM_PRICE;
        if (priceMax.equals("")) priceMax = MAXIMUM_PRICE;

        List<Jewelery> filteredJeweleryList = jeweleryDao.getAllJeweleryInPriceRange(Double.parseDouble(priceMin), Double.parseDouble(priceMax));

        /*Following methods will only be called when user wants to filter specific category, otherwise user selects "all" which is not a valid Enum value*/
        if (EnumUtils.isValidEnum(ProductAvailability.class, availability))
            filteredJeweleryList = filterAvailability(filteredJeweleryList, Enum.valueOf(ProductAvailability.class, availability));
        if (EnumUtils.isValidEnum(JeweleryType.class, jeweleryType))
            filteredJeweleryList = filterJeweleryType(filteredJeweleryList, Enum.valueOf(JeweleryType.class, jeweleryType));
        if (EnumUtils.isValidEnum(JewelerySubstance.class, substance))
            filteredJeweleryList = filterSubstance(filteredJeweleryList, Enum.valueOf(JewelerySubstance.class, substance));

        return filteredJeweleryList;
    }

    public Map<String, Double> getJewelerySalesStatistics(int[] monthAndYear) {
        Map<String, Double> jewelerySalesStatistics = new HashMap<>();
        List<Jewelery> soldJewelery;
        if (monthAndYear.length == 0) {
            soldJewelery = getAllSoldJewelery();
        } else {
            soldJewelery = getAllSoldJeweleryForMonth(monthAndYear);
        }
        jewelerySalesStatistics = getGeneralStatistics(jewelerySalesStatistics, soldJewelery);
        jewelerySalesStatistics = getJeweleryTypeStatistics(jewelerySalesStatistics, soldJewelery);
        jewelerySalesStatistics = getSubstanceStatistics(jewelerySalesStatistics, soldJewelery);
        return jewelerySalesStatistics;

    }

    private List<Jewelery> getAllSoldJewelery() {
        return jeweleryDao.getAllSoldJewelery();
    }

    private List<Jewelery> getAllSoldJeweleryForMonth(int[] monthAndYear) {
        return jeweleryDao.getAllSoldJeweleryForMonth(monthAndYear);
    }

    private double getTotalIncomeOf(List<Jewelery> jeweleryList) {
        return jeweleryList.stream().mapToDouble(jewelery -> jewelery.getPrice()).sum();
    }

    private Map<String, Double> getGeneralStatistics(Map<String, Double> jewelerySalesStatistics, List<Jewelery> soldJewelery) {
        jewelerySalesStatistics.put(JEWELERY + SALES, (double) (soldJewelery.size()));
        jewelerySalesStatistics.put(JEWELERY + INCOME, getTotalIncomeOf(soldJewelery));
        return jewelerySalesStatistics;
    }


    private Map<String, Double> getJeweleryTypeStatistics(Map<String, Double> jewelerySalesStatistics, List<Jewelery> soldJewelery) {
        for (JeweleryType type : JeweleryType.values()) {
            List<Jewelery> filteredByTypeList = filterJeweleryType(soldJewelery, type);
            jewelerySalesStatistics.put(type + SALES, (double) (filteredByTypeList.size()));
            jewelerySalesStatistics.put(type + INCOME, getTotalIncomeOf(filteredByTypeList));
        }
        return jewelerySalesStatistics;
    }

    private Map<String, Double> getSubstanceStatistics(Map<String, Double> jewelerySalesStatistics, List<Jewelery> soldJewelery) {
        for (JewelerySubstance substance : JewelerySubstance.values()) {
            List<Jewelery> filteredBySubstanceList = filterSubstance(soldJewelery, substance);
            jewelerySalesStatistics.put(substance + SALES, (double) filteredBySubstanceList.size());
            jewelerySalesStatistics.put(substance + INCOME, getTotalIncomeOf(filteredBySubstanceList));
        }
        return jewelerySalesStatistics;
    }

    private List<Jewelery> filterAvailability(List<Jewelery> filteredJeweleryList, ProductAvailability availability) {
        return filteredJeweleryList.stream().filter(jewelery -> jewelery.getAvailability() == availability).collect(Collectors.toList());
    }

    private List<Jewelery> filterJeweleryType(List<Jewelery> filteredJeweleryList, JeweleryType jeweleryType) {
        return filteredJeweleryList.stream().filter(jewelery -> jewelery.getJeweleryType() == jeweleryType).collect(Collectors.toList());
    }

    private List<Jewelery> filterSubstance(List<Jewelery> filteredJeweleryList, JewelerySubstance substance) {
        return filteredJeweleryList.stream().filter(jewelery -> jewelery.getSubstance() == substance).collect(Collectors.toList());
    }

}

