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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        testClothing3 = new Clothing("test3", ProductAvailability.SOLD, TEST_DESCRIPTION, 150,
                ClothingType.JACKET_TYPE, ClothingSize.S_SIZE, ClothingTheme.ANIMAL_THEME, TEST_CUT_TYPE_1);

        testClothing4 = new Clothing("test4", ProductAvailability.SOLD, TEST_DESCRIPTION, 200,
                ClothingType.SKIRT_TYPE, ClothingSize.L_SIZE, ClothingTheme.NO_THEME, TEST_CUT_TYPE_2);

        testClothing5 = new Clothing("test5", ProductAvailability.SOLD, TEST_DESCRIPTION, 300,
                ClothingType.PANTS_TYPE, ClothingSize.L_SIZE, ClothingTheme.NO_THEME, TEST_CUT_TYPE_2);

        testClothing6 = new Clothing("test6", ProductAvailability.SOLD, TEST_DESCRIPTION, 500,
                ClothingType.HAT_TYPE, ClothingSize.UNIVERSAL_SIZE, ClothingTheme.NO_THEME, TEST_CUT_TYPE_2);

        clothingListStub = Arrays.asList(testClothing1, testClothing2, testClothing3, testClothing4,
                testClothing5, testClothing6);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFilterClothes_whenNoCategoriesHaveDefinedValues_andExpectMatchingClothesFound() {
        List<Clothing> expectedResult = new ArrayList<>(clothingListStub);
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", "any", "");

        assertEquals(6, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
<<<<<<< HEAD
    void testFilterClothes_whenAvailabilityIsDefined_andExpectMatchingClothesFound() {
=======
    void testFilterClothes_whenAvailabilityIsDefined_anExpectMatchingClothesFound() {
>>>>>>> 02bb88b439aa4ccf9cc32e1d6820fd0fb7c5f2d2
        List<Clothing> expectedResult = Arrays.asList(testClothing1, testClothing2, testClothing3, testClothing4, testClothing5, testClothing6);
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
<<<<<<< HEAD
    void testFilterClothes_whenClothingTypeIsDefined_andExpectMatchingClothesFound() {
=======
    void testFilterClothes_whenClothingTypeIsDefined_anExpectMatchingClothesFound() {
>>>>>>> 02bb88b439aa4ccf9cc32e1d6820fd0fb7c5f2d2
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
<<<<<<< HEAD
    void testFilterClothes_whenClothingSizeIsDefined_andExpectMatchingClothesFound() {
=======
    void testFilterClothes_whenClothingSizeIsDefined_anExpectMatchingClothesFound() {
>>>>>>> 02bb88b439aa4ccf9cc32e1d6820fd0fb7c5f2d2
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
<<<<<<< HEAD
    void testFilterClothes_whenClothingThemeIsDefined_andExpectMatchingClothesFound() {
=======
    void testFilterClothes_whenClothingThemeIsDefined_anExpectMatchingClothesFound() {
>>>>>>> 02bb88b439aa4ccf9cc32e1d6820fd0fb7c5f2d2
        List<Clothing> expectedResult = Arrays.asList(testClothing4, testClothing5, testClothing6);
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", ClothingTheme.NO_THEME.toString(), "");

        assertEquals(3, testList.size());
        assertEquals(expectedResult, testList);
    }

    @Test
    void testFilterClothes_whenClothingThemeIsDefined_andExpectNoMatchingClothesFound() {
        List<Clothing> testList = clothingService.filterClothes(clothingListStub,
                "any", "any", "any", ClothingTheme.OTHER_THEME.toString(), "");

        assertTrue(testList.isEmpty());
    }

    @Test
<<<<<<< HEAD
    void testFilterClothes_whenModelNameIsDefined_andExpectMatchingClothesFound() {
=======
    void testFilterClothes_whenModelNameIsDefined_anExpectMatchingClothesFound() {
>>>>>>> 02bb88b439aa4ccf9cc32e1d6820fd0fb7c5f2d2
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
        List<Clothing> expectedResult = new ArrayList<>();
        expectedResult.add(testClothing3);
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

}

