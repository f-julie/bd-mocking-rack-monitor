package com.amazon.ata.mocking.wholefoodscookieassembly.cookie;

import java.util.List;
import java.util.Objects;

/**
 * Chocolate chip cookie produced by Whole Foods Markets.
 */
public class ChocolateChipCookie {
    private Size size;
    private int numberOfChips;
    private List<CookieIngredient> ingredients;

    public ChocolateChipCookie(Size size, int numberOfChips, List<CookieIngredient> ingredients) {
        this.size = size;
        this.numberOfChips = numberOfChips;
        this.ingredients = ingredients;
    }

    public Size getSize() {
        return size;
    }

    public int getNumberOfChips() {
        return numberOfChips;
    }

    public List<CookieIngredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        ChocolateChipCookie other = (ChocolateChipCookie) o;
        return Objects.equals(size, other.size)
                && Objects.equals(numberOfChips, other.numberOfChips)
                && Objects.equals(ingredients, other.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, numberOfChips, ingredients);
    }

    @Override
    public String toString() {
        return String.format("Cookie: {size: %s, numberOfChips: %d, ingredients: %s}", size, numberOfChips, ingredients);
    }
}
