package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.Size;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieDependencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OvenTest {
    private Oven oven;

    @BeforeEach
    public void setUp() {
        oven = new Oven(new Random(10));
    }

    @Test
    public void bake_bakeIngredients_returnsRandomCookie() throws Exception {
        // GIVEN
        List<CookieIngredient> ingredients = Arrays.asList(CookieIngredient.CHOCOLATE);

        // WHEN
        ChocolateChipCookie cookie = oven.bake(ingredients);

        // THEN
        ChocolateChipCookie expectedCookie = new ChocolateChipCookie(Size.SMALL, 5,ingredients);
        assertEquals(expectedCookie, cookie);
    }

    @Test
    public void bake_ovenOff_throwsCookieDependencyException() throws Exception {
        // GIVEN
        List<CookieIngredient> ingredients = Arrays.asList(CookieIngredient.CHOCOLATE);
        oven.turnOff();

        // WHEN + THEN
        assertThrows(CookieDependencyException.class, () -> oven.bake(ingredients));
    }
}
