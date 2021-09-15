package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.Size;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.AllergenContaminantException;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.GrossCookieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IngredientCookieInspectorTest {
    private IngredientCookieInspector inspector;

    @BeforeEach
    public void setup() {
        inspector = new IngredientCookieInspector();
    }

    @Test
    public void inspect_cookieContainsPeanuts_throwsAllergenException() throws Exception {
        // GIVEN
        ChocolateChipCookie cookie = new ChocolateChipCookie(Size.MEDIUM,
                10,
                Arrays.asList(CookieIngredient.PEANUT));

        // WHEN + THEN
        assertThrows(AllergenContaminantException.class, () -> inspector.inspect(cookie));
    }
}
