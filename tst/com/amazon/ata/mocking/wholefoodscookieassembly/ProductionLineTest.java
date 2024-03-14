package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieProductionException;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductionLineTest {
    private ProductionLine productionLine;

    @BeforeEach
    public void setUp() {
        productionLine = new ProductionLine(new Mixer(),
                new Oven(),
                new CookiePackager(Arrays.asList(new DeepLensCookieInspector(), new IngredientCookieInspector())));
    }

    @Test
    public void createBatch_request5Cookies_returnsCookieBoxes() {
        //GIVEN
        int numberOfCookiesToMake = 5;

        // WHEN
        List<CookieBox> cookieBoxes = productionLine.createBatch(numberOfCookiesToMake);

        // THEN
        assertFalse(cookieBoxes.isEmpty());
    }

    @Test
    public void createBatch_zeroCookiesRequested_zeroCookieBoxesAssembled() {
        //GIVEN
        int numberOfCookiesToMake = 0;

        // WHEN
        List<CookieBox> cookieBoxes = productionLine.createBatch(numberOfCookiesToMake);

        // THEN
        assertTrue(cookieBoxes.isEmpty());
    }

    @Test
    public void createBatch_allergenDetectedImmediately_throwsCookieProductionException() {
        //GIVEN
        int numberOfCookiesToMake = 5;

        // WHEN + THEN
        assertThrows(CookieProductionException.class, () -> productionLine.createBatch(numberOfCookiesToMake));
    }
}
