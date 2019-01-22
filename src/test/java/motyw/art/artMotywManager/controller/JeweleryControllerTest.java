package motyw.art.artMotywManager.controller;


import motyw.art.artMotywManager.domain.Jewelery;
import motyw.art.artMotywManager.service.JeweleryService;
import motyw.art.artMotywManager.service.ProductService;
import motyw.art.artMotywManager.util.JewelerySubstance;
import motyw.art.artMotywManager.util.JeweleryType;
import motyw.art.artMotywManager.util.ProductAvailability;
import motyw.art.artMotywManager.util.UserMessages;
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

class JeweleryControllerTest {

    @InjectMocks
    private JeweleryController jeweleryController;
    @Mock
    private JeweleryService jeweleryServiceMock;
    @Mock
    private ProductService productServiceMock;
    private MockMvc mockMvc;

    private static final String UNIQUE_ID = "uniqueId";
    private static final String NON_UNIQUE_ID = "nonUniqueId";
    private static final String TEST_DESCRIPTION = "testDescription";
    private static final String TOO_LONG_DESCRIPTION = StringUtils.repeat("a", 251);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(jeweleryController).setViewResolvers(viewResolver).build();
    }

    @Test
    void testShowAddNewJeweleryForm() throws Exception {
        mockMvc.perform(get("/jewelery/addNewJewelery"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_JEWELERY_VIEW))
                .andExpect(model().attribute("jewelery", Matchers.any(Jewelery.class)))
                .andExpect(model().attribute("availability", ProductAvailability.AVAILABLE))
                .andExpect(model().attribute("jeweleryTypes", JeweleryType.values()))
                .andExpect(model().attribute("jewelerySubstances", JewelerySubstance.values()));
    }

    @Test
    void testAddNewJeweleryWithoutErrors() throws Exception {
        mockMvc.perform(post("/jewelery/addNewJewelery")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", UNIQUE_ID)
                .param("description", TEST_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "100")
                .param("jeweleryType", JeweleryType.NECKLACE_TYPE.toString())
                .param("substance", JewelerySubstance.METAL_SUBSTANCE.toString())
                .sessionAttr("jewelery", new Jewelery()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/" + UNIQUE_ID))
                .andExpect(model().attributeHasNoErrors());

        ArgumentCaptor<Jewelery> argument = ArgumentCaptor.forClass(Jewelery.class);

        verify(productServiceMock, times(1)).setProductImageData(argument.capture());
        verify(productServiceMock, times(1)).saveOrUpdateProduct(argument.capture());

        assertEquals(UNIQUE_ID, argument.getValue().getId());
        assertEquals(TEST_DESCRIPTION, argument.getValue().getDescription());
        assertEquals(ProductAvailability.AVAILABLE, argument.getValue().getAvailability());
        assertEquals(100, argument.getValue().getPrice());
        assertEquals(JeweleryType.NECKLACE_TYPE, argument.getValue().getJeweleryType());
        assertEquals(JewelerySubstance.METAL_SUBSTANCE, argument.getValue().getSubstance());

    }

    @Test
    void testAddNewJeweleryWithErrors() throws Exception {
        mockMvc.perform(post("/jewelery/addNewJewelery")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", NON_UNIQUE_ID)
                .param("description", TOO_LONG_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "-100")
                .param("jeweleryType", JeweleryType.NECKLACE_TYPE.toString())
                .param("substance", JewelerySubstance.BEADING_SUBSTANCE.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_NEW_JEWELERY_VIEW))
                .andExpect(model().attributeHasFieldErrors("jewelery", "id"))
                .andExpect(model().attributeHasFieldErrors("jewelery", "description"))
                .andExpect(model().attributeHasFieldErrors("jewelery", "price"))
                .andExpect(model().attribute("jewelery", allOf(
                        hasProperty("id", is(NON_UNIQUE_ID)),
                        hasProperty("description", is(TOO_LONG_DESCRIPTION)),
                        hasProperty("availability", is(ProductAvailability.AVAILABLE)),
                        hasProperty("price", is(-100d)),
                        hasProperty("jeweleryType", is(JeweleryType.NECKLACE_TYPE)),
                        hasProperty("substance", is(JewelerySubstance.BEADING_SUBSTANCE)))))
                .andExpect(model().attribute("jeweleryTypes", JeweleryType.values()))
                .andExpect(model().attribute("jewelerySubstances", JewelerySubstance.values()));

        verifyZeroInteractions(productServiceMock);
    }

    @Test
    void testShowEditJeweleryForm() throws Exception {
        Jewelery testJewelery = new Jewelery(UNIQUE_ID, ProductAvailability.AVAILABLE, TEST_DESCRIPTION,
                100, JeweleryType.BRACELET_TYPE, JewelerySubstance.BEADING_SUBSTANCE);
        when(jeweleryServiceMock.findById(UNIQUE_ID)).thenReturn(testJewelery);

        mockMvc.perform(get("/jewelery/editJewelery/{id}", UNIQUE_ID))
                .andExpect(status().isOk())
                .andExpect(view().name(EDIT_JEWELERY_VIEW))
                .andExpect(model().attribute("jewelery", allOf(
                        hasProperty("id", is(UNIQUE_ID)),
                        hasProperty("availability", is(ProductAvailability.AVAILABLE)),
                        hasProperty("description", is(TEST_DESCRIPTION)),
                        hasProperty("price", is(100d)),
                        hasProperty("jeweleryType", is(JeweleryType.BRACELET_TYPE)),
                        hasProperty("substance", is(JewelerySubstance.BEADING_SUBSTANCE)))))
                .andExpect(model().attribute("jeweleryTypes", JeweleryType.values()))
                .andExpect(model().attribute("jewelerySubstances", JewelerySubstance.values()));
    }

    @Test
    void testEditJeweleryWithValidChanges() throws Exception {
        /*NON_UNIQUE_ID is used to prove that editJewelery controller method uses 'EditValidation' validation group,
         which does not include @UniqueId validation*/

        mockMvc.perform(post("/jewelery/editJewelery/{id}", NON_UNIQUE_ID)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", NON_UNIQUE_ID)
                .param("description", TEST_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "100")
                .param("jeweleryType", JeweleryType.BRACELET_TYPE.toString())
                .param("substance", JewelerySubstance.BEADING_SUBSTANCE.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/" + NON_UNIQUE_ID))
                .andExpect(model().attributeHasNoErrors());


        ArgumentCaptor<Jewelery> argument = ArgumentCaptor.forClass(Jewelery.class);

        verify(productServiceMock, times(1)).setProductImageData(argument.capture());
        verify(productServiceMock, times(1)).saveOrUpdateProduct(argument.capture());

        assertEquals(NON_UNIQUE_ID, argument.getValue().getId());
        assertEquals(TEST_DESCRIPTION, argument.getValue().getDescription());
        assertEquals(ProductAvailability.AVAILABLE, argument.getValue().getAvailability());
        assertEquals(100, argument.getValue().getPrice());
        assertEquals(JeweleryType.BRACELET_TYPE, argument.getValue().getJeweleryType());
        assertEquals(JewelerySubstance.BEADING_SUBSTANCE, argument.getValue().getSubstance());
    }

    @Test
    void testEditJeweleryWithErrors() throws Exception {
        mockMvc.perform(post("/jewelery/editJewelery/{id}", NON_UNIQUE_ID)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", NON_UNIQUE_ID)
                .param("description", TOO_LONG_DESCRIPTION)
                .param("availability", ProductAvailability.AVAILABLE.toString())
                .param("price", "-100")
                .param("jeweleryType", JeweleryType.BRACELET_TYPE.toString())
                .param("substance", JewelerySubstance.BEADING_SUBSTANCE.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name(EDIT_JEWELERY_VIEW))
                .andExpect(model().attributeHasFieldErrors("jewelery", "description"))
                .andExpect(model().attributeHasFieldErrors("jewelery", "price"))
                .andExpect(model().attribute("jewelery", allOf(
                        hasProperty("id", is(NON_UNIQUE_ID)),
                        hasProperty("description", is(TOO_LONG_DESCRIPTION)),
                        hasProperty("availability", is(ProductAvailability.AVAILABLE)),
                        hasProperty("price", is(-100d)),
                        hasProperty("jeweleryType", is(JeweleryType.BRACELET_TYPE)),
                        hasProperty("substance", is(JewelerySubstance.BEADING_SUBSTANCE)))))
                .andExpect(model().attribute("jeweleryTypes", JeweleryType.values()))
                .andExpect(model().attribute("jewelerySubstances", JewelerySubstance.values()));

        verifyZeroInteractions(productServiceMock);
    }

    @Test
    void testShowFilterJeweleryForm() throws Exception {
        mockMvc.perform(get("/jewelery/filterJewelery"))
                .andExpect(status().isOk())
                .andExpect(view().name(FILTER_JEWELERY_VIEW))
                .andExpect(model().attribute("productAvailability", ProductAvailability.values()))
                .andExpect(model().attribute("jeweleryTypes", JeweleryType.values()))
                .andExpect(model().attribute("jewelerySubstances", JewelerySubstance.values()));
    }

    @Test
    void testShowJewelerySearchResult_WhenMatchingProductsFound() throws Exception {
        List<Jewelery> testList = Arrays.asList(new Jewelery(), new Jewelery());
        when(jeweleryServiceMock.getAllJeweleryInPriceRange("", "")).thenReturn(testList);
        when(jeweleryServiceMock.filterJewelery(testList, "any", "any", "any"))
                .thenReturn(testList);

        mockMvc.perform(get("/jewelery/jewelerySearchResults")
                .param("priceMin", "")
                .param("priceMax", "")
                .param("availability", "any")
                .param("jeweleryType", "any")
                .param("substance", "any"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/productList/"))
                .andExpect(flash().attribute("productList", testList))
                .andExpect(flash().attribute("productList", hasSize(2)));

        verify(jeweleryServiceMock, times(1)).filterJewelery(testList, "any",
                "any", "any");
    }

    @Test
    void testShowJewelerySearchResults_WhenNoMatchingProductsFound() throws Exception {
        List<Jewelery> testList = Arrays.asList(new Jewelery(), new Jewelery());
        List<Jewelery> emptyTestList = new ArrayList<>();
        when(jeweleryServiceMock.getAllJeweleryInPriceRange("100", "200")).thenReturn(testList);
        when(jeweleryServiceMock.filterJewelery(testList, ProductAvailability.SOLD.toString(),
                JeweleryType.EARINGS_TYPE.toString(), JewelerySubstance.PRECIOUS_METAL_SUBSTANCE.toString()))
                .thenReturn(emptyTestList);

        mockMvc.perform(get("/jewelery/jewelerySearchResults")
                .param("priceMin", "100")
                .param("priceMax", "200")
                .param("availability", ProductAvailability.SOLD.toString())
                .param("jeweleryType", JeweleryType.EARINGS_TYPE.toString())
                .param("substance", JewelerySubstance.PRECIOUS_METAL_SUBSTANCE.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/showMessage/"))
                .andExpect(flash().attribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage()));

        verify(jeweleryServiceMock, times(1)).getAllJeweleryInPriceRange("100", "200");
        verify(jeweleryServiceMock, times(1)).filterJewelery(testList, ProductAvailability.SOLD.toString(),
                JeweleryType.EARINGS_TYPE.toString(), JewelerySubstance.PRECIOUS_METAL_SUBSTANCE.toString());
    }

    @Test
    void testShowJewelerySearchResults_WhenNoJeweleryInPriceRangeFound()throws Exception{
        List<Jewelery> emptyTestList = new ArrayList<>();
        when(jeweleryServiceMock.getAllJeweleryInPriceRange("100", "110")).thenReturn(emptyTestList);

        mockMvc.perform(get("/jewelery/jewelerySearchResults")
                .param("priceMin", "100")
                .param("priceMax", "110")
                .param("availability", "any")
                .param("jeweleryType", "any")
                .param("substance", "any"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/showMessage/"))
                .andExpect(flash().attribute("message", UserMessages.PRODUCT_NOT_FOUND_MSG.getUserMessage()));
        
        verify(jeweleryServiceMock,times(1)).getAllJeweleryInPriceRange("100","110");
        verifyNoMoreInteractions(jeweleryServiceMock);
    }


}