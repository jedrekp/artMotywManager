package motyw.art.artMotywManager.controller;

import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.domain.Product;
import motyw.art.artMotywManager.service.ProductService;
import motyw.art.artMotywManager.util.UserMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static motyw.art.artMotywManager.util.ViewsAndRedirects.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productServiceMock;

    private MockMvc mockMvc;

    private List<Product> testProductList;

    private static final String VALID_ID = "valid_ID";
    private static final String INVALID_ID = "invalid_ID";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(productController).setViewResolvers(viewResolver).build();

        testProductList = new ArrayList<>();
        Product testProduct1 = new Clothing();
        testProduct1.setId(VALID_ID);
        testProductList.add(testProduct1);
    }

    @Test
    void testShowProduct() throws Exception {
        Product testProduct1 = testProductList.get(0);
        when(productServiceMock.findById(VALID_ID)).thenReturn(testProduct1);

        mockMvc.perform(get("/product/{id}", VALID_ID))
                .andExpect(status().isOk())
                .andExpect(view().name(SINGLE_PRODUCT_VIEW))
                .andExpect(model().attribute("product", testProduct1))
                .andExpect(model().attribute("product", hasProperty("id", is(VALID_ID))));

        verify(productServiceMock, times(1)).findById(VALID_ID);
    }

    @Test
    void testShowProductWhenProductNotFound() throws Exception {
        when(productServiceMock.findById(INVALID_ID)).thenReturn(null);

        mockMvc.perform(get("/product/{id}", INVALID_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/showMessage/"))
                .andExpect(flash().attribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage()));

        verify(productServiceMock, times(1)).findById(INVALID_ID);
    }

    @Test
    void testFindProduct() throws Exception {
        mockMvc.perform(get("/product/find")
                .param("id", VALID_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/" + VALID_ID));
    }

    @Test
    void testShowAllProducts() throws Exception {
        when(productServiceMock.getListOfAllProducts()).thenReturn(testProductList);

        mockMvc.perform(get("/product/showAllProducts"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/productList/"))
                .andExpect(flash().attribute("productList", testProductList))
                .andExpect(flash().attribute("productList", hasSize(1)))
                .andExpect(flash().attribute("productList", hasItem(hasProperty("id", is(VALID_ID)))));

        verify(productServiceMock, times(1)).getListOfAllProducts();
    }

    @Test
    void testShowAllProductsWhenDatabaseIsEmpty() throws Exception {
        when(productServiceMock.getListOfAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/product/showAllProducts"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/showMessage/"))
                .andExpect(flash().attribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage()));

        verify(productServiceMock, times(1)).getListOfAllProducts();
    }

    @Test
    void testShowProductList() throws Exception {
        mockMvc.perform(get("/product/productList"))
                .andExpect(status().isOk())
                .andExpect(view().name(PRODUCT_LIST_VIEW));
    }

    @Test
    void testMarkAsSold() throws Exception {
        Product testProduct1 = testProductList.get(0);
        when(productServiceMock.findById(VALID_ID)).thenReturn(testProduct1);

        mockMvc.perform(post("/product/markAsSold/{id}", VALID_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/" + VALID_ID));

        verify(productServiceMock, times(1)).markAsSold(testProduct1);
    }

    @Test
    void testDeleteProduct() throws Exception {
        Product testProduct1 = testProductList.get(0);
        when(productServiceMock.findById(VALID_ID)).thenReturn(testProduct1);

        mockMvc.perform(post("/product/deleteProduct/{id}", VALID_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("message", UserMessages.PRODUCT_DELETED_MSG.getUserMessage() + VALID_ID))
                .andExpect(redirectedUrl("/product/showMessage/"));

        verify(productServiceMock, times(1)).deleteProduct(testProduct1);
    }

    @Test
    void testShowMessage() throws Exception {
        mockMvc.perform(get("/product/showMessage"))
                .andExpect(status().isOk())
                .andExpect(view().name(MESSAGE_VIEW));
    }
}
