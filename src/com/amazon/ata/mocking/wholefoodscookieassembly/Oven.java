package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.Size;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieDependencyException;

import java.util.List;
import java.util.Random;

/**
 * Bakes the cookies.
 */
public class Oven {
    private Random random = new Random();
    private boolean on = false;

    public Oven() {
        on = true;
    }

    public Oven(Random random) {
        this.random = random;
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    /**
     * Bakes the ingredients into a cookie.
     * @param ingredients the ingredients to bake
     * @return cookie ready for packaging
     */
    public ChocolateChipCookie bake(List<CookieIngredient> ingredients) {
        if (!on) {
            throw new CookieDependencyException("Unable to bake cookies when the oven in off.");
        }
        return new ChocolateChipCookie(getRandomSize(), random.nextInt(25), ingredients);
    }

    private Size getRandomSize() {
        int randomSizeIndex = random.nextInt(Size.values().length);
        return Size.values()[randomSizeIndex];
    }
}
