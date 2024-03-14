package com.amazon.ata.mocking.wholefoodscookieassembly.cookie;

import java.util.ArrayList;
import java.util.List;

/**
 * A box of chocolate chip cookies to be sold. A box can fit 10 MEDIUM cookies.
 */
public class CookieBox {
    private List<ChocolateChipCookie> cookies = new ArrayList<>(10);

    /**
     * Add a cookie to the box. If there are already 10 cookies in the box, a new cookie cannot fit.
     * @param cookie The cookie to try to put in the box.
     */
    public void addCookie(ChocolateChipCookie cookie) {
        if (cookies.size() == 10) {
            throw new ArrayIndexOutOfBoundsException();
        }
        cookies.add(cookie);
    }

    public int getNumberOfCookies() {
        return cookies.size();
    }

    public boolean isFull() {
        return cookies.size() == 10;
    }

    @Override
    public String toString() {
        return cookies.toString();
    }
}
