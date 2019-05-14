package motyw.art.artMotywManager.service;

import motyw.art.artMotywManager.dao.ProductDao;
import motyw.art.artMotywManager.domain.Product;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

import static motyw.art.artMotywManager.util.Constants.*;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product findById(String id) {
        return productDao.findById(id);
    }

    public List<Product> getListOfAllProducts() {
        return productDao.getListOfAllProducts();
    }

    public void saveOrUpdateProduct(Product product) {
        productDao.saveOrUpdateProduct(product);
    }

    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    public void setProductImageData(Product product) {
        if (product.getImageFile() != null && product.getImageFile().getSize() != 0) {
            product.setImageData(product.getImageFile().getBytes());
        } else { //keep previous image if it was not changed by user
            Product previousProduct = findById(product.getId());
            if (previousProduct != null) {
                product.setImageData(previousProduct.getImageData());
            }
        }
    }

    public void markAsSold(Product product) {
        product.setAvailability(ProductAvailability.SOLD);
        product.setSaleDate(new Date());
        saveOrUpdateProduct(product);
    }

    public Map<String, Double> getSalesStatistics(Map<String, Double> clothingSalesStatistics, Map<String, Double> jewelerySalesStatistics) {
        Map<String, Double> salesStatistics = new HashMap<>();
        salesStatistics.putAll(clothingSalesStatistics);
        salesStatistics.putAll(jewelerySalesStatistics);
        return getTotalSalesAndIncome(salesStatistics);
    }

    public String getMonthlyStatisticsTitle(String title, int[] monthAndYear) {
        return title + Month.of(monthAndYear[0]).getDisplayName(TextStyle.FULL, new Locale("pl")) + " " + monthAndYear[1];
    }

    private Map<String, Double> getTotalSalesAndIncome(Map<String, Double> salesStatistics) {
        salesStatistics.put(TOTAL + SALES, salesStatistics.get(CLOTHING + SALES) + salesStatistics.get(JEWELERY + SALES));
        salesStatistics.put(TOTAL + INCOME, salesStatistics.get(CLOTHING + INCOME) + salesStatistics.get(JEWELERY + INCOME));
        return salesStatistics;
    }
}
