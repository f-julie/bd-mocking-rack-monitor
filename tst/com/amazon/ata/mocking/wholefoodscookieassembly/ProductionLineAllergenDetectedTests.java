package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieBox;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.Size;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.AllergenContaminantException;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieProductionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductionLineAllergenDetectedTests {
    private List<CookieIngredient> ingredients = Arrays.asList(CookieIngredient.CHOCOLATE, CookieIngredient.FLOUR);
    private ChocolateChipCookie cookie = new ChocolateChipCookie(Size.MEDIUM, 10, ingredients);
    private ChocolateChipCookie allergicCookie = new ChocolateChipCookie(Size.SMALL, 15, Arrays.asList());

    private ProductionLine productionLine;

    @Mock
    private Mixer mixer;

    @Mock
    private Oven oven;

    @Mock
    private CookiePackager cookiePackager;

    @Mock
    private CookieBox cookieBox;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        productionLine = new ProductionLine(mixer, oven, cookiePackager);
    }

    @Test
    public void createBatch_allergenDetectedAfter5CookiesBaked_throwsCookieProductionException() throws AllergenContaminantException {
        //GIVEN
        int numberOfCookiesToMake = 10;

        // WHEN + THEN
        assertThrows(CookieProductionException.class, () -> productionLine.createBatch(numberOfCookiesToMake));
    }
}
