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

import static motyw.art.artMotywManager.util.Constants.*;


@Service
public class JeweleryService {

    @Autowired
    private JeweleryDao jeweleryDao;

    public Jewelery findById(String id) {
        return jeweleryDao.findById(id);
    }

    public List<Jewelery> getAllJeweleryInPriceRange(String priceMin, String priceMax) {
        if (priceMin.equals("")) priceMin = MINIMUM_VALID_PRICE;
        if (priceMax.equals("")) priceMax = MAXIMUM_VALID_PRICE;

        return jeweleryDao.getAllJeweleryInPriceRange(Double.parseDouble(priceMin), Double.parseDouble(priceMax));
    }

    public List<Jewelery> filterJewelery(List<Jewelery> listToFilter, String availability, String jeweleryType, String substance) {
        if (EnumUtils.isValidEnum(ProductAvailability.class, availability))
            listToFilter = filterAvailability(listToFilter, Enum.valueOf(ProductAvailability.class, availability));
        if (EnumUtils.isValidEnum(JeweleryType.class, jeweleryType))
            listToFilter = filterJeweleryType(listToFilter, Enum.valueOf(JeweleryType.class, jeweleryType));
        if (EnumUtils.isValidEnum(JewelerySubstance.class, substance))
            listToFilter = filterSubstance(listToFilter, Enum.valueOf(JewelerySubstance.class, substance));

        return listToFilter;
    }

    public Map<String, Double> getJewelerySalesStatistics(int[] monthAndYear) {
        Map<String, Double> jewelerySalesStatistics = new HashMap<>();
        List<Jewelery> soldJewelery;
        if (monthAndYear.length == 0) {
            soldJewelery = jeweleryDao.getAllSoldJewelery();
        } else {
            soldJewelery = jeweleryDao.getAllSoldJeweleryForMonth(monthAndYear);
        }
        jewelerySalesStatistics = getGeneralStatistics(jewelerySalesStatistics, soldJewelery);
        jewelerySalesStatistics = getJeweleryTypeStatistics(jewelerySalesStatistics, soldJewelery);
        jewelerySalesStatistics = getSubstanceStatistics(jewelerySalesStatistics, soldJewelery);
        return jewelerySalesStatistics;

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

    private double getTotalIncomeOf(List<Jewelery> jeweleryList) {
        return jeweleryList.stream().mapToDouble(Jewelery::getPrice).sum();
    }


    private List<Jewelery> filterAvailability(List<Jewelery> listToFilter, ProductAvailability availability) {
        return listToFilter.stream().filter(jewelery -> jewelery.getAvailability() == availability).collect(Collectors.toList());
    }

    private List<Jewelery> filterJeweleryType(List<Jewelery> listToFilter, JeweleryType jeweleryType) {
        return listToFilter.stream().filter(jewelery -> jewelery.getJeweleryType() == jeweleryType).collect(Collectors.toList());
    }

    private List<Jewelery> filterSubstance(List<Jewelery> listToFilter, JewelerySubstance substance) {
        return listToFilter.stream().filter(jewelery -> jewelery.getSubstance() == substance).collect(Collectors.toList());
    }

}

