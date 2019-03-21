package motyw.art.artMotywManager.service;


import motyw.art.artMotywManager.dao.ProductDao;
import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.domain.Product;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static motyw.art.artMotywManager.util.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductDao productDaoMock;

    private Product testProduct1;

    private static final String VALID_ID = "validId";

    private static final String INVALID_ID = "invalidId";

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        testProduct1 = new Clothing(VALID_ID);
    }

    @Test
    void testFindById_whenProductInDB() {
        when(productDaoMock.findById(VALID_ID)).thenReturn(testProduct1);

        Product optionalProduct = productService.findById(VALID_ID);

        assertNotNull(optionalProduct);
        assertEquals(VALID_ID, optionalProduct.getId());
        verify(productDaoMock, times(1)).findById(VALID_ID);
    }

    @Test
    void testFindById_whenProductNotInDB() {
        when(productDaoMock.findById(INVALID_ID)).thenReturn(null);

        Product optionalProduct = productService.findById(INVALID_ID);

        assertNull(optionalProduct);
        verify(productDaoMock, times(1)).findById(INVALID_ID);
    }

    @Test
    void testGetListOfAllProducts() {
        List<Product> testProductList = Arrays.asList(testProduct1);
        when(productDaoMock.getListOfAllProducts()).thenReturn(testProductList);

        assertEquals(testProductList, productService.getListOfAllProducts());

        verify(productDaoMock, times(1)).getListOfAllProducts();
    }

    @Test
    void testSaveOrUpdateProduct() {
        productService.saveOrUpdateProduct(testProduct1);

        verify(productDaoMock, times(1)).saveOrUpdateProduct(testProduct1);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(testProduct1);

        verify(productDaoMock, times(1)).deleteProduct(testProduct1);
    }

    @Test
    void testMarkAsSold() {
        productService.markAsSold(testProduct1);

        assertEquals(ProductAvailability.SOLD, testProduct1.getAvailability());
        assertNotNull(testProduct1.getSaleDate());
        verify(productDaoMock, times(1)).saveOrUpdateProduct(testProduct1);
    }

    @Test
    void testGetSalesStatistics() {
        Map<String, Double> testClothingStatistics = new HashMap<>();
        testClothingStatistics.put(CLOTHING + SALES, 10d);
        testClothingStatistics.put(CLOTHING + INCOME, 1500d);
        Map<String, Double> testJeweleryStatistics = new HashMap<>();
        testJeweleryStatistics.put(JEWELERY + SALES, 15d);
        testJeweleryStatistics.put(JEWELERY + INCOME, 1700d);

        Map<String, Double> testStatistics = productService.getSalesStatistics(testClothingStatistics, testJeweleryStatistics);

        assertEquals(6, testStatistics.size());
        assertThat(testStatistics, allOf(
                hasEntry(CLOTHING + SALES, 10d),
                hasEntry(CLOTHING + INCOME, 1500d),
                hasEntry(JEWELERY + SALES, 15d),
                hasEntry(JEWELERY + INCOME, 1700d),
                hasEntry(TOTAL + SALES, 25d),
                hasEntry(TOTAL + INCOME, 3200d)));
    }
}
