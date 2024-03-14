package com.amazon.ata.mocking.wholefoodscookieassembly.cookie;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChocolateChipCookieTest {
    private Size size = Size.MEDIUM;
    private int numberOfChips = 10;
    private List<CookieIngredient> ingredients = Arrays.asList(CookieIngredient.CHOCOLATE);
    private ChocolateChipCookie cookie = new ChocolateChipCookie(size, numberOfChips, ingredients);

    @Test
    public void equals_nullObject_false() {
        // GIVEN
        ChocolateChipCookie other = null;

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_sameObject_true() {
        // GIVEN
        ChocolateChipCookie other = cookie;

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_differentClass_false() {
        // GIVEN
        String other = "test";

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_differentSize_false() {
        // GIVEN
        ChocolateChipCookie other = new ChocolateChipCookie(Size.SMALL, numberOfChips, ingredients);

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_differentNumberOfChips_false() {
        // GIVEN
        ChocolateChipCookie other = new ChocolateChipCookie(size, numberOfChips + 1, ingredients);

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_differentIngredients_false() {
        // GIVEN
        ChocolateChipCookie other = new ChocolateChipCookie(Size.SMALL, numberOfChips,
                Arrays.asList(CookieIngredient.RAISIN));

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_sameSizeNumberOfChipsAndIngredients_true() {
        // GIVEN
        ChocolateChipCookie other = new ChocolateChipCookie(size, numberOfChips, ingredients);

        // WHEN
        boolean isEqual = cookie.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void hashCode_equalObjects_sameHashcode() {
        // GIVEN
        ChocolateChipCookie other = new ChocolateChipCookie(size, numberOfChips, ingredients);

        // WHEN
        int hashCode = cookie.hashCode();

        //THEN
        assertEquals(other.hashCode(), hashCode);
    }

    @Test
    public void hashCode_notEqualObjects_differentHashcode() {
        // GIVEN
        ChocolateChipCookie other = new ChocolateChipCookie(size, numberOfChips + 10, ingredients);

        // WHEN
        int hashCode = cookie.hashCode();

        //THEN
        assertNotEquals(other.hashCode(), hashCode);
    }
}
