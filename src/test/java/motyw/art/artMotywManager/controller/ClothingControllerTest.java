package motyw.art.artMotywManager.controller;

import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.service.ClothingService;
import motyw.art.artMotywManager.service.ProductService;
import motyw.art.artMotywManager.util.*;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static motyw.art.artMotywManager.util.ViewsAndRedirects.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ClothingControllerTest {

    private static final String UNIQUE_ID = "uniqueId";
    private static final String NON_UNIQUE_ID = "nonUniqueId";
    private static final String TEST_DESCRIPTION = "testDescription";
    private static final String TOO_LONG_DESCRIPTION = StringUtils.repeat("a", 251);
    private static final String TEST_CUT_TYPE = "testCutType";
    @InjectMocks
    private ClothingController clothingController;
    @Mock
    private ClothingService clothingServiceMock;
    @Mock
    private ProductService productServiceMock;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(clothingController).setViewResolvers(viewResolver).build();
    }

    @Test
    void testShowAddNewClothingForm() throws Exception {
        mockMvc.perform(get("/clothing/addNewClothing"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_CLOTHING_VIEW))
                .andExpect(model().attribute("clothing", Matchers.any(Clothing.class)))
                .andExpect(model().attribute("availability", ProductAvailability.AVAILABLE))
                .andExpect(model().attribute("clothingTypes", ClothingType.values()))
                .andExpect(model().attribute("clothingSizes", ClothingSize.values()))
                .andExpect(model().attribute("clothingThemes", ClothingTheme.values()));
    }

    @Test
    void testAddNewClothingWithoutErrors() throws Exception {
        mockMvc.perform(post("/clothing/addNewClothing")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", UNIQUE_ID)
                .param("description", TEST_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "150")
                .param("clothingType", ClothingType.DRESS_TYPE.toString())
                .param("size", ClothingSize.M_SIZE.toString())
                .param("theme", ClothingTheme.ABSTRACT_THEME.toString())
                .param("cutType", TEST_CUT_TYPE)
                .sessionAttr("clothing", new Clothing()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/" + UNIQUE_ID))
                .andExpect(model().attributeHasNoErrors());

        ArgumentCaptor<Clothing> argument = ArgumentCaptor.forClass(Clothing.class);

        verify(productServiceMock, times(1)).setProductImageData(argument.capture());
        verify(productServiceMock, times(1)).saveOrUpdateProduct(argument.capture());

        assertEquals(UNIQUE_ID, argument.getValue().getId());
        assertEquals(TEST_DESCRIPTION, argument.getValue().getDescription());
        assertEquals(ProductAvailability.AVAILABLE, argument.getValue().getAvailability());
        assertEquals(150, argument.getValue().getPrice());
        assertEquals(ClothingType.DRESS_TYPE, argument.getValue().getClothingType());
        assertEquals(ClothingSize.M_SIZE, argument.getValue().getSize());
        assertEquals(ClothingTheme.ABSTRACT_THEME, argument.getValue().getTheme());
        assertEquals(TEST_CUT_TYPE.toLowerCase(), argument.getValue().getCutType());
    }

    @Test
    void testAddNewClothingWithErrors() throws Exception {
        mockMvc.perform(post("/clothing/addNewClothing")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", NON_UNIQUE_ID)
                .param("description", TOO_LONG_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "-150")
                .param("clothingType", ClothingType.DRESS_TYPE.toString())
                .param("size", ClothingSize.M_SIZE.toString())
                .param("theme", ClothingTheme.ABSTRACT_THEME.toString())
                .param("cutType", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_CLOTHING_VIEW))
                .andExpect(model().attributeHasFieldErrors("clothing", "id"))
                .andExpect(model().attributeHasFieldErrors("clothing", "description"))
                .andExpect(model().attributeHasFieldErrors("clothing", "price"))
                .andExpect(model().attributeHasFieldErrors("clothing", "cutType"))
                .andExpect(model().attribute("clothing", allOf(
                        hasProperty("id", is(NON_UNIQUE_ID)),
                        hasProperty("description", is(TOO_LONG_DESCRIPTION)),
                        hasProperty("availability", is(ProductAvailability.AVAILABLE)),
                        hasProperty("price", is(-150d)),
                        hasProperty("clothingType", is(ClothingType.DRESS_TYPE)),
                        hasProperty("size", is(ClothingSize.M_SIZE)),
                        hasProperty("theme", is(ClothingTheme.ABSTRACT_THEME)),
                        hasProperty("cutType", is("")))))
                .andExpect(model().attribute("clothingTypes", ClothingType.values()))
                .andExpect(model().attribute("clothingSizes", ClothingSize.values()))
                .andExpect(model().attribute("clothingThemes", ClothingTheme.values()));

        verifyZeroInteractions(productServiceMock);
    }

    @Test
    void testShowEditClothingForm() throws Exception {
        Clothing testClothing = new Clothing(UNIQUE_ID, ProductAvailability.AVAILABLE, TEST_DESCRIPTION, 150, ClothingType.DRESS_TYPE,
                ClothingSize.L_SIZE, ClothingTheme.ABSTRACT_THEME, TEST_CUT_TYPE);
        when(clothingServiceMock.findById(UNIQUE_ID)).thenReturn(testClothing);

        mockMvc.perform(get("/clothing/editClothing/{id}", UNIQUE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name(EDIT_CLOTHING_VIEW))
                .andExpect(model().attribute("clothing", allOf(
                        hasProperty("id", is(UNIQUE_ID)),
                        hasProperty("availability", is(ProductAvailability.AVAILABLE)),
                        hasProperty("description", is(TEST_DESCRIPTION)),
                        hasProperty("price", is(150d)),
                        hasProperty("clothingType", is(ClothingType.DRESS_TYPE)),
                        hasProperty("size", is(ClothingSize.L_SIZE)),
                        hasProperty("theme", is(ClothingTheme.ABSTRACT_THEME)),
                        hasProperty("cutType", is(TEST_CUT_TYPE.toLowerCase())))))
                .andExpect(model().attribute("clothingTypes", ClothingType.values()))
                .andExpect(model().attribute("clothingSizes", ClothingSize.values()))
                .andExpect(model().attribute("clothingThemes", ClothingTheme.values()));
    }

    @Test
    void testEditClothingWithValidChanges() throws Exception {
             /*NON_UNIQUE_ID is used to prove that editClothing controller method uses 'EditValidation' validation group,
         which does not include @UniqueId validation*/

        mockMvc.perform(post("/clothing/editClothing/{id}", NON_UNIQUE_ID)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", NON_UNIQUE_ID)
                .param("description", TEST_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "150")
                .param("clothingType", ClothingType.DRESS_TYPE.toString())
                .param("size", ClothingSize.M_SIZE.toString())
                .param("theme", ClothingTheme.ABSTRACT_THEME.toString())
                .param("cutType", TEST_CUT_TYPE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/" + NON_UNIQUE_ID))
                .andExpect(model().attributeHasNoErrors());


        ArgumentCaptor<Clothing> argument = ArgumentCaptor.forClass(Clothing.class);

        verify(productServiceMock, times(1)).setProductImageData(argument.capture());
        verify(productServiceMock, times(1)).saveOrUpdateProduct(argument.capture());

        assertEquals(NON_UNIQUE_ID, argument.getValue().getId());
        assertEquals(TEST_DESCRIPTION, argument.getValue().getDescription());
        assertEquals(ProductAvailability.AVAILABLE, argument.getValue().getAvailability());
        assertEquals(150, argument.getValue().getPrice());
        assertEquals(ClothingType.DRESS_TYPE, argument.getValue().getClothingType());
        assertEquals(ClothingSize.M_SIZE, argument.getValue().getSize());
        assertEquals(ClothingTheme.ABSTRACT_THEME, argument.getValue().getTheme());
        assertEquals(TEST_CUT_TYPE.toLowerCase(), argument.getValue().getCutType());
    }

    @Test
    void testEditClothingWithErrors() throws Exception {
        mockMvc.perform(post("/clothing/editClothing/{id}", NON_UNIQUE_ID)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", NON_UNIQUE_ID)
                .param("description", TOO_LONG_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "-150")
                .param("clothingType", ClothingType.DRESS_TYPE.toString())
                .param("size", ClothingSize.M_SIZE.toString())
                .param("theme", ClothingTheme.ABSTRACT_THEME.toString())
                .param("cutType", ""))
                .andExpect(status().isOk())
                .andExpect(view().name(EDIT_CLOTHING_VIEW))
                .andExpect(model().attributeHasFieldErrors("clothing", "description"))
                .andExpect(model().attributeHasFieldErrors("clothing", "price"))
                .andExpect(model().attributeHasFieldErrors("clothing", "cutType"))
                .andExpect(model().attribute("clothing", allOf(
                        hasProperty("id", is(NON_UNIQUE_ID)),
                        hasProperty("description", is(TOO_LONG_DESCRIPTION)),
                        hasProperty("availability", is(ProductAvailability.AVAILABLE)),
                        hasProperty("price", is(-150d)),
                        hasProperty("clothingType", is(ClothingType.DRESS_TYPE)),
                        hasProperty("size", is(ClothingSize.M_SIZE)),
                        hasProperty("theme", is(ClothingTheme.ABSTRACT_THEME)),
                        hasProperty("cutType", is("")))))
                .andExpect(model().attribute("clothingTypes", ClothingType.values()))
                .andExpect(model().attribute("clothingSizes", ClothingSize.values()))
                .andExpect(model().attribute("clothingThemes", ClothingTheme.values()));

        verifyZeroInteractions(productServiceMock);
    }

    @Test
    void testShowFilterClothingForm() throws Exception {
        mockMvc.perform(get("/clothing/filterClothing"))
                .andExpect(status().isOk())
                .andExpect(view().name(FILTER_CLOTHING_VIEW))
                .andExpect(model().attribute("productAvailability", ProductAvailability.values()))
                .andExpect(model().attribute("clothingTypes", ClothingType.values()))
                .andExpect(model().attribute("clothingSizes", ClothingSize.values()))
                .andExpect(model().attribute("clothingThemes", ClothingTheme.values()));
    }

    @Test
    void testShowClothingSearchResultWhenMatchingProductsFound() throws Exception {
        List<Clothing> testList = Arrays.asList(new Clothing(), new Clothing());
        when(clothingServiceMock.filterClothing("", "", "all", "all", "all", "all", ""))
                .thenReturn(testList);

        mockMvc.perform(get("/clothing/clothingSearchResults")
                .param("priceMin", "")
                .param("priceMax", "")
                .param("availability", "all")
                .param("clothingType", "all")
                .param("size", "all")
                .param("theme", "all")
                .param("cutType", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/productList/"))
                .andExpect(flash().attribute("productList", testList))
                .andExpect(flash().attribute("productList", hasSize(2)));

        verify(clothingServiceMock, times(1)).filterClothing("", "", "all",
                "all", "all", "all", "");
    }

    @Test
    void testShowClothigSearchResultsWhenNoMatchingProductsFound() throws Exception {
        List<Clothing> emptyTestList = new ArrayList<>();
        when(clothingServiceMock.filterClothing("100", "300", ProductAvailability.SOLD.toString(),
                ClothingType.DRESS_TYPE.toString(), ClothingSize.XXL_SIZE.toString(), ClothingTheme.FLORAL_THEME.toString(), TEST_CUT_TYPE))
                .thenReturn(emptyTestList);

        mockMvc.perform(get("/clothing/clothingSearchResults")
                .param("priceMin", "100")
                .param("priceMax", "300")
                .param("availability", ProductAvailability.SOLD.toString())
                .param("clothingType", ClothingType.DRESS_TYPE.toString())
                .param("size", ClothingSize.XXL_SIZE.toString())
                .param("theme", ClothingTheme.FLORAL_THEME.toString())
                .param("cutType", TEST_CUT_TYPE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/showMessage/"))
                .andExpect(flash().attribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage()));
    }

}
