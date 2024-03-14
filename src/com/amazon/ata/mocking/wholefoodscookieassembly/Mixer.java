package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Mixes the ingredients.
 */
public class Mixer {
    private Random random = new Random();

    public Mixer() {}

    public Mixer(Random random) {
        this.random = random;
    }

    /**
     * Runs the mixer to mix the ingredients together for a single cookie.
     * @return The list of ingredients that have been mixed together.
     */
    public List<CookieIngredient> mix() {
        List<CookieIngredient> ingredientTypes = new ArrayList<>();
        for (int i = -1; i < getRandomIngredientIndex(); i++) {
            ingredientTypes.add(getRandomIngredient());
        }
        return ingredientTypes;
    }

    private int getRandomIngredientIndex() {
        return random.nextInt(CookieIngredient.values().length);
    }

    private CookieIngredient getRandomIngredient() {
        return CookieIngredient.values()[getRandomIngredientIndex()];
    }
}
