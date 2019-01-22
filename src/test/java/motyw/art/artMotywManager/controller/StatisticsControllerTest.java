package motyw.art.artMotywManager.controller;


import motyw.art.artMotywManager.service.ClothingService;
import motyw.art.artMotywManager.service.JeweleryService;
import motyw.art.artMotywManager.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;
import java.util.Map;

import static motyw.art.artMotywManager.util.StaticValues.*;
import static motyw.art.artMotywManager.util.ViewsAndRedirects.STATISTICS_VIEW;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StatisticsControllerTest {

    @InjectMocks
    private StatisticsController statisticsController;
    @Mock
    private ProductService productServiceMock;
    @Mock
    private ClothingService clothingServiceMock;
    @Mock
    private JeweleryService jeweleryServiceMock;
    private MockMvc mockMvc;
    private Map<String, Double> testClothingStatisticsMap;
    private Map<String, Double> testJeweleryStatisticsMap;
    private Map<String, Double> testStatisticsMap;
    private static final String MONTH = "11";
    private static final String YEAR = "2018";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).setViewResolvers(viewResolver).build();

        createStatisticsStubs();
    }

    @Test
    void testShowHomePage() throws Exception {
        when(clothingServiceMock.getClothesSalesStatistics(aryEq(new int[0]))).thenReturn(testClothingStatisticsMap);
        when(jeweleryServiceMock.getJewelerySalesStatistics(aryEq(new int[0]))).thenReturn(testJeweleryStatisticsMap);
        when(productServiceMock.getSalesStatistics(testClothingStatisticsMap, testJeweleryStatisticsMap)).thenReturn(testStatisticsMap);

        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(view().name(STATISTICS_VIEW))
                .andExpect(model().attribute("statistics", allOf(
                        hasEntry(CLOTHING + SALES, 10d),
                        hasEntry(CLOTHING + INCOME, 1300d),
                        hasEntry(JEWELERY + SALES, 15d),
                        hasEntry(JEWELERY + INCOME, 2000d),
                        hasEntry(TOTAL + SALES, 25d),
                        hasEntry(TOTAL + INCOME, 3300d))))
                .andExpect(model().attribute("title", STATISTICS_TITLE));

        verify(clothingServiceMock, times(1)).getClothesSalesStatistics(aryEq(new int[0]));
        verify(jeweleryServiceMock, times(1)).getJewelerySalesStatistics(aryEq(new int[0]));
        verify(productServiceMock, times(1)).getSalesStatistics(testClothingStatisticsMap, testJeweleryStatisticsMap);
    }

    @Test
    void testShowStatisticsForMonth() throws Exception {
        mockMvc.perform(get("/statisticsForMonth")
                .param("month", MONTH)
                .param("year", YEAR))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/statistics/11/2018"));

    }

    @Test
    void testShowMonthlyStatistics() throws Exception {
        int[] monthAndYear = {Integer.parseInt(MONTH), Integer.parseInt(YEAR)};
        when(clothingServiceMock.getClothesSalesStatistics(aryEq(monthAndYear))).thenReturn(testClothingStatisticsMap);
        when(jeweleryServiceMock.getJewelerySalesStatistics(aryEq(monthAndYear))).thenReturn(testJeweleryStatisticsMap);
        when(productServiceMock.getSalesStatistics(testClothingStatisticsMap, testJeweleryStatisticsMap)).thenReturn(testStatisticsMap);
        when(productServiceMock.getMonthlyStatisticsTitle(eq(MONTHLY_STATISTICS_TITLE), aryEq(monthAndYear)))
                .thenReturn(MONTHLY_STATISTICS_TITLE + monthAndYear[0] + "/" + monthAndYear[1]);

        mockMvc.perform(get("/statistics/{month}/{year}", MONTH, YEAR))
                .andExpect(status().isOk())
                .andExpect(view().name(STATISTICS_VIEW))
                .andExpect(model().attribute("statistics", allOf(
                        hasEntry(CLOTHING + SALES, 10d),
                        hasEntry(CLOTHING + INCOME, 1300d),
                        hasEntry(JEWELERY + SALES, 15d),
                        hasEntry(JEWELERY + INCOME, 2000d),
                        hasEntry(TOTAL + SALES, 25d),
                        hasEntry(TOTAL + INCOME, 3300d))))
                .andExpect(model().attribute("title", MONTHLY_STATISTICS_TITLE + monthAndYear[0] + "/" + monthAndYear[1]));

        verify(clothingServiceMock, times(1)).getClothesSalesStatistics(aryEq(monthAndYear));
        verify(jeweleryServiceMock, times(1)).getJewelerySalesStatistics(aryEq(monthAndYear));
        verify(productServiceMock, times(1)).getSalesStatistics(testClothingStatisticsMap, testJeweleryStatisticsMap);
        verify(productServiceMock, times(1)).getMonthlyStatisticsTitle(eq(MONTHLY_STATISTICS_TITLE), aryEq(monthAndYear));
    }

    private void createStatisticsStubs() {
        testClothingStatisticsMap = new HashMap<>();
        testClothingStatisticsMap.put(CLOTHING + SALES, 10d);
        testClothingStatisticsMap.put(CLOTHING + INCOME, 1300d);
        testJeweleryStatisticsMap = new HashMap<>();
        testJeweleryStatisticsMap.put(JEWELERY + SALES, 15d);
        testJeweleryStatisticsMap.put(JEWELERY + INCOME, 2000d);
        testStatisticsMap = new HashMap<>();
        testStatisticsMap.putAll(testClothingStatisticsMap);
        testStatisticsMap.putAll(testJeweleryStatisticsMap);
        testStatisticsMap.put(TOTAL + SALES, (testStatisticsMap.get((CLOTHING + SALES)) + (testStatisticsMap.get(JEWELERY + SALES))));
        testStatisticsMap.put(TOTAL + INCOME, (testStatisticsMap.get((CLOTHING + INCOME)) + (testStatisticsMap.get(JEWELERY + INCOME))));
    }
}
