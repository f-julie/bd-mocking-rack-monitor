package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.Size;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieTooLargeException;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieTooSmallException;

/**
 * Uses a Deep Lens to visually inspect the cookies and machine learning models that can determine the size and number
 * of chips in a cookie.
 */
public class DeepLensCookieInspector implements CookieInspector {

    /**
     * Inspects and validates a cookie to ensure that it can be sold by Whole Foods Market.
     * @param cookie The cookie to validate
     * @throws CookieTooLargeException Thrown if a cookie is too large to fit in a box
     * @throws CookieTooSmallException Thrown if a cookie is too small to be sold retail
     */
    @Override
    public void inspect(ChocolateChipCookie cookie) throws CookieTooLargeException, CookieTooSmallException {
        if (Size.LARGE == cookie.getSize()) {
            throw new CookieTooLargeException();
        }

        if (Size.SMALL == cookie.getSize()) {
            throw new CookieTooSmallException();
        }
    }
}
