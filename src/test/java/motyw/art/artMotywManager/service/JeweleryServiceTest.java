package motyw.art.artMotywManager.service;

import motyw.art.artMotywManager.dao.JeweleryDao;
import motyw.art.artMotywManager.domain.Jewelery;
import motyw.art.artMotywManager.util.JewelerySubstance;
import motyw.art.artMotywManager.util.JeweleryType;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static motyw.art.artMotywManager.util.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class JeweleryServiceTest {

    @InjectMocks
    private JeweleryService jeweleryService;
    @Mock
    private JeweleryDao jeweleryDaoMock;

    private static Jewelery testJewelery1;
    private static Jewelery testJewelery2;
    private static Jewelery testJewelery3;
    private static Jewelery testJewelery4;
    private static Jewelery testJewelery5;
    private static Jewelery testJewelery6;
    private static List<Jewelery> jeweleryListStub;
    private static final String TEST_DESCRIPTION = "testDescription";

    @BeforeAll
    static void createStubs() {
        testJewelery1 = new Jewelery("test1", ProductAvailability.SOLD, TEST_DESCRIPTION,
                180, JeweleryType.NECKLACE_TYPE, JewelerySubstance.METAL_SUBSTANCE);

        testJewelery2 = new Jewelery("test2", ProductAvailability.SOLD, TEST_DESCRIPTION,
                250, JeweleryType.NECKLACE_TYPE, JewelerySubstance.METAL_SUBSTANCE);

        testJewelery3 = new Jewelery("test3", ProductAvailability.SOLD, TEST_DESCRIPTION,
                140, JeweleryType.NECKLACE_TYPE, JewelerySubstance.BEADING_SUBSTANCE);

        testJewelery4 = new Jewelery("test4", ProductAvailability.SOLD, TEST_DESCRIPTION,
                90, JeweleryType.EARINGS_TYPE, JewelerySubstance.BEADING_SUBSTANCE);

        testJewelery5 = new Jewelery("test5", ProductAvailability.SOLD, TEST_DESCRIPTION,
                120, JeweleryType.DIFFERENT_JEWELERY_TYPE, JewelerySubstance.BEADING_SUBSTANCE);

        testJewelery6 = new Jewelery("test6", ProductAvailability.SOLD, TEST_DESCRIPTION,
                100, JeweleryType.DIFFERENT_JEWELERY_TYPE, JewelerySubstance.BEADING_SUBSTANCE);

        jeweleryListStub = Arrays.asList(testJewelery1, testJewelery2, testJewelery3,
                testJewelery4, testJewelery5, testJewelery6);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllJeweleryInPriceRange_whenPriceRangeNotDefined() {
        when(jeweleryDaoMock.getAllJeweleryInPriceRange(0, 10000)).thenReturn(jeweleryListStub);

        List<Jewelery> testList = jeweleryService.getAllJeweleryInPriceRange("", "");

        assertEquals(jeweleryListStub, testList);
        verify(jeweleryDaoMock, times(1)).getAllJeweleryInPriceRange(Double.parseDouble(MINIMUM_VALID_PRICE),
                Double.parseDouble(MAXIMUM_VALID_PRICE));
    }

    @Test
    void testGetAllJeweleryInPriceRange_whenPriceRangeIsDefined() {
        when(jeweleryDaoMock.getAllJeweleryInPriceRange(100, 500)).thenReturn(jeweleryListStub);

        List<Jewelery> testList = jeweleryService.getAllJeweleryInPriceRange("100", "500");

        assertEquals(jeweleryListStub, testList);
        verify(jeweleryDaoMock, times(1)).getAllJeweleryInPriceRange(100, 500);
    }

    @Test
    void testFilterJewelery_whenNoCategoriesHaveDefinedValues_andExpectMatchingJeweleryFound() {
        List<Jewelery> expectedResult = jeweleryListStub;

        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                "any", "any", "any");

        assertEquals(6, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterJewelery_whenAvailabilityIsDefined_andExpectMatchingJeweleryFound() {
        List<Jewelery> expectedResult = jeweleryListStub;

        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                ProductAvailability.SOLD.toString(), "any", "any");

        assertEquals(6, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterJewelery_whenAvailabilityIsDefined_andExpectNoMatchingJeweleryFound() {
        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                ProductAvailability.AVAILABLE.toString(), "any", "any");

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterJewelery_whenJeweleryTypeIsDefined_andExpectMatchingJeweleryFound() {
        List<Jewelery> expectedResult = Arrays.asList(testJewelery1, testJewelery2, testJewelery3);

        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                "any", JeweleryType.NECKLACE_TYPE.toString(), "any");

        assertEquals(3, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterJewelery_whenJeweleryTypeIsDefined_andExpectNoMatchingJeweleryFound() {
        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                "any", JeweleryType.BRACELET_TYPE.toString(), "any");

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterJewelery_whenJewelerySubstanceIsDefined_andExpectMatchingJeweleryFound() {
        List<Jewelery> expectedResult = Arrays.asList(testJewelery3, testJewelery4, testJewelery5, testJewelery6);

        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                "any", "any", JewelerySubstance.BEADING_SUBSTANCE.toString());

        assertEquals(4, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterJewelery_whenJewelerySubstanceIsDefined_andExpectNoMatchingJeweleryFound() {
        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub,
                "any", "any", JewelerySubstance.PRECIOUS_METAL_SUBSTANCE.toString());

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterJewelery_whenAllCategoriesAreDefined_andExpectMatchingJeweleryFound() {
        List<Jewelery> expectedResult = Arrays.asList(testJewelery1, testJewelery2);

        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub, ProductAvailability.SOLD.toString(),
                JeweleryType.NECKLACE_TYPE.toString(), JewelerySubstance.METAL_SUBSTANCE.toString());

        assertEquals(2, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterJewelery_whenAllCategoriesAreDefined_andExpectNoMatchingJeweleryFound() {
        List<Jewelery> testList = jeweleryService.filterJewelery(jeweleryListStub, ProductAvailability.SOLD.toString(),
                JeweleryType.DIFFERENT_JEWELERY_TYPE.toString(), JewelerySubstance.METAL_SUBSTANCE.toString());

        assertTrue(testList.isEmpty());
    }

    @Test
    void testGetJewelerySalesStatistics() {
        when(jeweleryDaoMock.getAllSoldJewelery()).thenReturn(jeweleryListStub);

        Map<String, Double> testJeweleryStatistics = jeweleryService.getJewelerySalesStatistics(new int[0]);

        assertEquals(16, testJeweleryStatistics.size());
        assertThat(testJeweleryStatistics, allOf(
                hasEntry(JEWELERY + SALES, 6d),
                hasEntry(JEWELERY + INCOME, 880d),
                hasEntry(JeweleryType.NECKLACE_TYPE + SALES, 3d),
                hasEntry(JeweleryType.NECKLACE_TYPE + INCOME, 570d),
                hasEntry(JeweleryType.EARINGS_TYPE + SALES, 1d),
                hasEntry(JeweleryType.EARINGS_TYPE + INCOME, 90d),
                hasEntry(JeweleryType.BRACELET_TYPE + SALES, 0d),
                hasEntry(JeweleryType.BRACELET_TYPE + INCOME, 0d),
                hasEntry(JeweleryType.DIFFERENT_JEWELERY_TYPE + SALES, 2d),
                hasEntry(JeweleryType.DIFFERENT_JEWELERY_TYPE + INCOME, 220d),
                hasEntry(JewelerySubstance.METAL_SUBSTANCE + SALES, 2d),
                hasEntry(JewelerySubstance.METAL_SUBSTANCE + INCOME, 430d),
                hasEntry(JewelerySubstance.PRECIOUS_METAL_SUBSTANCE + SALES, 0d),
                hasEntry(JewelerySubstance.PRECIOUS_METAL_SUBSTANCE + INCOME, 0d),
                hasEntry(JewelerySubstance.BEADING_SUBSTANCE + SALES, 4d),
                hasEntry(JewelerySubstance.BEADING_SUBSTANCE + INCOME, 450d)));

        verify(jeweleryDaoMock, times(1)).getAllSoldJewelery();
        verifyNoMoreInteractions(jeweleryDaoMock);
    }

    @Test
    void testGetJewelerySalesStatisticsForSpecificMonth() {
        int[] monthAndYear = new int[]{10, 2018};
        when(jeweleryDaoMock.getAllSoldJeweleryForMonth(monthAndYear)).thenReturn(jeweleryListStub);

        Map<String, Double> testJeweleryStatistics = jeweleryService.getJewelerySalesStatistics(monthAndYear);

        assertEquals(16, testJeweleryStatistics.size());
        assertThat(testJeweleryStatistics, allOf(
                hasEntry(JEWELERY + SALES, 6d),
                hasEntry(JEWELERY + INCOME, 880d),
                hasEntry(JeweleryType.NECKLACE_TYPE + SALES, 3d),
                hasEntry(JeweleryType.NECKLACE_TYPE + INCOME, 570d),
                hasEntry(JeweleryType.EARINGS_TYPE + SALES, 1d),
                hasEntry(JeweleryType.EARINGS_TYPE + INCOME, 90d),
                hasEntry(JeweleryType.BRACELET_TYPE + SALES, 0d),
                hasEntry(JeweleryType.BRACELET_TYPE + INCOME, 0d),
                hasEntry(JeweleryType.DIFFERENT_JEWELERY_TYPE + SALES, 2d),
                hasEntry(JeweleryType.DIFFERENT_JEWELERY_TYPE + INCOME, 220d),
                hasEntry(JewelerySubstance.METAL_SUBSTANCE + SALES, 2d),
                hasEntry(JewelerySubstance.METAL_SUBSTANCE + INCOME, 430d),
                hasEntry(JewelerySubstance.PRECIOUS_METAL_SUBSTANCE + SALES, 0d),
                hasEntry(JewelerySubstance.PRECIOUS_METAL_SUBSTANCE + INCOME, 0d),
                hasEntry(JewelerySubstance.BEADING_SUBSTANCE + SALES, 4d),
                hasEntry(JewelerySubstance.BEADING_SUBSTANCE + INCOME, 450d)));

        verify(jeweleryDaoMock, times(1)).getAllSoldJeweleryForMonth(monthAndYear);
        verifyNoMoreInteractions(jeweleryDaoMock);
    }
}