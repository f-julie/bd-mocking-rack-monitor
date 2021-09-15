package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.AllergenContaminantException;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieSizeException;

/**
 * Quality Control on the Whole Foods cookie production line.
 */
public interface CookieInspector {

    /**
     * Inspects and validates a cookie to ensure that it can be sold by Whole Foods Market.
     * @param cookie The cookie to validate
     * @throws CookieSizeException Thrown if a cookie is not the right size to be packaged
     * @throws AllergenContaminantException Thrown if a cookie contains an allergen
     */
    void inspect(ChocolateChipCookie cookie) throws CookieSizeException, AllergenContaminantException;
}
