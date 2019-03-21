package motyw.art.artMotywManager.service;

import motyw.art.artMotywManager.dao.ClothingDao;
import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.util.ClothingSize;
import motyw.art.artMotywManager.util.ClothingTheme;
import motyw.art.artMotywManager.util.ClothingType;
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

class ClothingServiceTest {

    @InjectMocks
    private ClothingService clothingService;
    @Mock
    private ClothingDao clothingDaoMock;

    private static Clothing testClothing1;
    private static Clothing testClothing2;
    private static Clothing testClothing3;
    private static Clothing testClothing4;
    private static Clothing testClothing5;
    private static Clothing testClothing6;
    private static List<Clothing> clothingListStub;
    private static final String TEST_DESCRIPTION = "testDescription";
    private static final String TEST_CUT_TYPE_1 = "testModelName1";
    private static final String TEST_CUT_TYPE_2 = "testModelName2";
    private static final String TEST_CUT_TYPE_3 = "testModelName3";

    @BeforeAll
    static void createStubs() {
        testClothing1 = new Clothing("test1", ProductAvailability.SOLD, TEST_DESCRIPTION, 250,
                ClothingType.DRESS_TYPE, ClothingSize.XXL_SIZE, ClothingTheme.ABSTRACT_THEME, TEST_CUT_TYPE_1);

        testClothing2 = new Clothing("test2", ProductAvailability.SOLD, TEST_DESCRIPTION, 400,
                ClothingType.DRESS_TYPE, ClothingSize.M_SIZE, ClothingTheme.FLORAL_THEME, TEST_CUT_TYPE_1);

        testClothing3 = new Clothing("test3", ProductAvailability.SOLD, TEST_DESCRIPTION, 500,
                ClothingType.JACKET_TYPE, ClothingSize.S_SIZE, ClothingTheme.ANIMAL_THEME, TEST_CUT_TYPE_1);

        testClothing4 = new Clothing("test4", ProductAvailability.SOLD, TEST_DESCRIPTION, 200,
                ClothingType.SKIRT_TYPE, ClothingSize.L_SIZE, ClothingTheme.NO_THEME, TEST_CUT_TYPE_2);

        testClothing5 = new Clothing("test5", ProductAvailability.SOLD, TEST_DESCRIPTION, 300,
                ClothingType.PANTS_TYPE, ClothingSize.L_SIZE, ClothingTheme.NO_THEME, TEST_CUT_TYPE_2);

        testClothing6 = new Clothing("test6", ProductAvailability.SOLD, TEST_DESCRIPTION, 150,
                ClothingType.HAT_TYPE, ClothingSize.UNIVERSAL_SIZE, ClothingTheme.NO_THEME, TEST_CUT_TYPE_2);

        clothingListStub = Arrays.asList(testClothing1, testClothing2, testClothing3, testClothing4,
                testClothing5, testClothing6);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllClothesInPriceRange_whenPriceRangeNotDefined() {
        when(clothingDaoMock.getAllClothesInPriceRange(0, 10000)).thenReturn(clothingListStub);

        List<Clothing> testList = clothingService.getAllClothesInPriceRange("", "");

        assertEquals(clothingListStub, testList);
        verify(clothingDaoMock, times(1)).getAllClothesInPriceRange(Double.parseDouble(MINIMUM_VALID_PRICE),
                Double.parseDouble(MAXIMUM_VALID_PRICE));
    }

    @Test
    void testGetAllClothesInPriceRange_whenPriceRangeIsDefined() {
        when(clothingDaoMock.getAllClothesInPriceRange(100, 500)).thenReturn(clothingListStub);

        List<Clothing> testList = clothingService.getAllClothesInPriceRange("100", "500");

        assertEquals(clothingListStub, testList);
        verify(clothingDaoMock, times(1)).getAllClothesInPriceRange(100, 500);
    }

    @Test
    void testFilterClothes_whenNoCategoriesHaveDefinedValues_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = clothingListStub;

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", "any", "");

        assertEquals(6, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenAvailabilityIsDefined_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = clothingListStub;

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                ProductAvailability.SOLD.toString(), "any", "any", "any", "");

        assertEquals(6, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenAvailabilityIsDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                ProductAvailability.AVAILABLE.toString(), "any", "any", "any", "");

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterClothes_whenClothingTypeIsDefined_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = Arrays.asList(testClothing1, testClothing2);

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", ClothingType.DRESS_TYPE.toString(), "any", "any", "");

        assertEquals(2, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenClothingTypeIsDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", ClothingType.SUIT_TYPE.toString(), "any", "any", "");

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterClothes_whenClothingSizeIsDefined_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = Arrays.asList(testClothing4, testClothing5);

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", ClothingSize.L_SIZE.toString(), "any", "");

        assertEquals(2, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenClothingSizeIsDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", ClothingSize.XL_SIZE.toString(), "any", "");

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterClothes_whenClothingThemeIsDefined_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = Arrays.asList(testClothing4, testClothing5, testClothing6);

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", ClothingTheme.NO_THEME.toString(), "");

        assertEquals(3, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenClothingThemeIsDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", ClothingTheme.DIFFERENT_THEME.toString(), "");

        assertTrue(testList.isEmpty());
    }

    @Test
    void testFilterClothes_whenModelNameIsDefined_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = Arrays.asList(testClothing4, testClothing5, testClothing6);

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", "any", TEST_CUT_TYPE_2);

        assertEquals(3, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenModelNameIsDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", "any", TEST_CUT_TYPE_3);

        assertTrue(testList.isEmpty());
    }


    @Test
    void testFilterClothes_whenAllCategoriesAreDefined_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = Arrays.asList(testClothing3);

        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                ProductAvailability.SOLD.toString(), ClothingType.JACKET_TYPE.toString(),
                ClothingSize.S_SIZE.toString(), ClothingTheme.ANIMAL_THEME.toString(), TEST_CUT_TYPE_1);

        assertEquals(1, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenAllCategoriesAreDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                ProductAvailability.SOLD.toString(), ClothingType.HAT_TYPE.toString(),
                ClothingSize.M_SIZE.toString(), ClothingTheme.ABSTRACT_THEME.toString(), TEST_CUT_TYPE_2);

        assertTrue(testList.isEmpty());
    }

    @Test
    void testGetClothesSalesStatistics() {
        when(clothingDaoMock.getAllSoldClothes()).thenReturn(clothingListStub);

        Map<String, Double> testClothingStatistics = clothingService.getClothesSalesStatistics(new int[0]);

        assertEquals(44, testClothingStatistics.size());
        assertThat(testClothingStatistics, allOf(
                hasEntry(CLOTHING + SALES, 6d),
                hasEntry(CLOTHING + INCOME, 1800d),
                hasEntry(ClothingType.DRESS_TYPE + SALES, 2d),
                hasEntry(ClothingType.DRESS_TYPE + INCOME, 650d),
                hasEntry(ClothingType.SKIRT_TYPE + SALES, 1d),
                hasEntry(ClothingType.SKIRT_TYPE + INCOME, 200d),
                hasEntry(ClothingType.PANTS_TYPE + SALES, 1d),
                hasEntry(ClothingType.PANTS_TYPE + INCOME, 300d),
                hasEntry(ClothingType.SHIRT_TYPE + SALES, 0d),
                hasEntry(ClothingType.SHIRT_TYPE + INCOME, 0d),
                hasEntry(ClothingType.SWEATSHIRT_TYPE + SALES, 0d),
                hasEntry(ClothingType.SWEATSHIRT_TYPE + INCOME, 0d),
                hasEntry(ClothingType.HAT_TYPE + SALES, 1d),
                hasEntry(ClothingType.HAT_TYPE + INCOME, 150d),
                hasEntry(ClothingType.JACKET_TYPE + SALES, 1d),
                hasEntry(ClothingType.JACKET_TYPE + INCOME, 500d),
                hasEntry(ClothingType.SUIT_TYPE + SALES, 0d),
                hasEntry(ClothingType.SUIT_TYPE + INCOME, 0d),
                hasEntry(ClothingType.DIFFERENT_CLOTHING_TYPE + SALES, 0d),
                hasEntry(ClothingType.DIFFERENT_CLOTHING_TYPE + INCOME, 0d),
                hasEntry(ClothingSize.XS_SIZE + SALES, 0d),
                hasEntry(ClothingSize.XS_SIZE + INCOME, 0d),
                hasEntry(ClothingSize.S_SIZE + SALES, 1d),
                hasEntry(ClothingSize.S_SIZE + INCOME, 500d),
                hasEntry(ClothingSize.M_SIZE + SALES, 1d),
                hasEntry(ClothingSize.M_SIZE + INCOME, 400d),
                hasEntry(ClothingSize.L_SIZE + SALES, 2d),
                hasEntry(ClothingSize.L_SIZE + INCOME, 500d),
                hasEntry(ClothingSize.XL_SIZE + SALES, 0d),
                hasEntry(ClothingSize.XL_SIZE + INCOME, 0d),
                hasEntry(ClothingSize.XXL_SIZE + SALES, 1d),
                hasEntry(ClothingSize.XXL_SIZE + INCOME, 250d),
                hasEntry(ClothingSize.UNIVERSAL_SIZE + SALES, 1d),
                hasEntry(ClothingSize.UNIVERSAL_SIZE + INCOME, 150d),
                hasEntry(ClothingTheme.ANIMAL_THEME + SALES, 1d),
                hasEntry(ClothingTheme.ANIMAL_THEME + INCOME, 500d),
                hasEntry(ClothingTheme.FLORAL_THEME + SALES, 1d),
                hasEntry(ClothingTheme.FLORAL_THEME + INCOME, 400d),
                hasEntry(ClothingTheme.ABSTRACT_THEME + SALES, 1d),
                hasEntry(ClothingTheme.ABSTRACT_THEME + INCOME, 250d),
                hasEntry(ClothingTheme.DIFFERENT_THEME + SALES, 0d),
                hasEntry(ClothingTheme.DIFFERENT_THEME + INCOME, 0d),
                hasEntry(ClothingTheme.NO_THEME + SALES, 3d),
                hasEntry(ClothingTheme.NO_THEME + INCOME, 650d)));

        verify(clothingDaoMock, times(1)).getAllSoldClothes();
        verifyNoMoreInteractions(clothingDaoMock);
    }

    @Test
    void testGetClothesSalesStatisticsForSpecificMonth() {
        int[] monthAndYear = new int[]{10, 2018};
        when(clothingDaoMock.getAllSoldClothesForMonth(monthAndYear)).thenReturn(clothingListStub);

        Map<String, Double> testClothingStatistics = clothingService.getClothesSalesStatistics(monthAndYear);

        assertEquals(44, testClothingStatistics.size());
        assertThat(testClothingStatistics, allOf(
                hasEntry(CLOTHING + SALES, 6d),
                hasEntry(CLOTHING + INCOME, 1800d),
                hasEntry(ClothingType.DRESS_TYPE + SALES, 2d),
                hasEntry(ClothingType.DRESS_TYPE + INCOME, 650d),
                hasEntry(ClothingType.SKIRT_TYPE + SALES, 1d),
                hasEntry(ClothingType.SKIRT_TYPE + INCOME, 200d),
                hasEntry(ClothingType.PANTS_TYPE + SALES, 1d),
                hasEntry(ClothingType.PANTS_TYPE + INCOME, 300d),
                hasEntry(ClothingType.SHIRT_TYPE + SALES, 0d),
                hasEntry(ClothingType.SHIRT_TYPE + INCOME, 0d),
                hasEntry(ClothingType.SWEATSHIRT_TYPE + SALES, 0d),
                hasEntry(ClothingType.SWEATSHIRT_TYPE + INCOME, 0d),
                hasEntry(ClothingType.HAT_TYPE + SALES, 1d),
                hasEntry(ClothingType.HAT_TYPE + INCOME, 150d),
                hasEntry(ClothingType.JACKET_TYPE + SALES, 1d),
                hasEntry(ClothingType.JACKET_TYPE + INCOME, 500d),
                hasEntry(ClothingType.SUIT_TYPE + SALES, 0d),
                hasEntry(ClothingType.SUIT_TYPE + INCOME, 0d),
                hasEntry(ClothingType.DIFFERENT_CLOTHING_TYPE + SALES, 0d),
                hasEntry(ClothingType.DIFFERENT_CLOTHING_TYPE + INCOME, 0d),
                hasEntry(ClothingSize.XS_SIZE + SALES, 0d),
                hasEntry(ClothingSize.XS_SIZE + INCOME, 0d),
                hasEntry(ClothingSize.S_SIZE + SALES, 1d),
                hasEntry(ClothingSize.S_SIZE + INCOME, 500d),
                hasEntry(ClothingSize.M_SIZE + SALES, 1d),
                hasEntry(ClothingSize.M_SIZE + INCOME, 400d),
                hasEntry(ClothingSize.L_SIZE + SALES, 2d),
                hasEntry(ClothingSize.L_SIZE + INCOME, 500d),
                hasEntry(ClothingSize.XL_SIZE + SALES, 0d),
                hasEntry(ClothingSize.XL_SIZE + INCOME, 0d),
                hasEntry(ClothingSize.XXL_SIZE + SALES, 1d),
                hasEntry(ClothingSize.XXL_SIZE + INCOME, 250d),
                hasEntry(ClothingSize.UNIVERSAL_SIZE + SALES, 1d),
                hasEntry(ClothingSize.UNIVERSAL_SIZE + INCOME, 150d),
                hasEntry(ClothingTheme.ANIMAL_THEME + SALES, 1d),
                hasEntry(ClothingTheme.ANIMAL_THEME + INCOME, 500d),
                hasEntry(ClothingTheme.FLORAL_THEME + SALES, 1d),
                hasEntry(ClothingTheme.FLORAL_THEME + INCOME, 400d),
                hasEntry(ClothingTheme.ABSTRACT_THEME + SALES, 1d),
                hasEntry(ClothingTheme.ABSTRACT_THEME + INCOME, 250d),
                hasEntry(ClothingTheme.DIFFERENT_THEME + SALES, 0d),
                hasEntry(ClothingTheme.DIFFERENT_THEME + INCOME, 0d),
                hasEntry(ClothingTheme.NO_THEME + SALES, 3d),
                hasEntry(ClothingTheme.NO_THEME + INCOME, 650d)));

        verify(clothingDaoMock, times(1)).getAllSoldClothesForMonth(monthAndYear);
        verifyNoMoreInteractions(clothingDaoMock);
    }
}

