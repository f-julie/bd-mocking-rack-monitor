package com.amazon.ata.mocking.wholefoodscookieassembly.cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CookieBoxTest {
    private ChocolateChipCookie cookie = new ChocolateChipCookie(Size.MEDIUM, 1, new ArrayList<>());
    private CookieBox cookieBox;

    @BeforeEach
    public void setup() {
        cookieBox = new CookieBox();
    }

    @Test
    public void addCookie_lessThanTenCookiesInBox_cookieAdded() {
        // GIVEN
        int initialSize = 5;
        for (int i = 0; i < initialSize; i++) {
            cookieBox.addCookie(cookie);
        }

        // WHEN
        cookieBox.addCookie(cookie);

        // THEN
        assertEquals(initialSize + 1, cookieBox.getNumberOfCookies());
    }

    @Test
    public void addCookie_tenCookiesInBox_throwsArrayIndexOutOfBoundsException() {
        // GIVEN
        for (int i = 0; i < 10; i++) {
            cookieBox.addCookie(cookie);
        }

        // WHEN + THEN
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> cookieBox.addCookie(cookie));
    }
}
