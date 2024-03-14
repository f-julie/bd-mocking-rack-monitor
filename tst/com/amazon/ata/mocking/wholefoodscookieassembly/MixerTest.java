package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MixerTest {
    private Mixer mixer;

    @BeforeEach
    public void setup() {
        mixer = new Mixer(new Random(10));
    }

    @Test
    public void mix_mixRandomIngredients_returnsListOfIngredients() throws Exception {
        // GIVEN
        List<CookieIngredient> expectedIngredients = Arrays.asList(
                CookieIngredient.BAKING_SODA,
                CookieIngredient.GRANULATED_SUGAR,
                CookieIngredient.EGG);

        // WHEN
        List<CookieIngredient> ingredients = mixer.mix();

        // THEN
        assertEquals(expectedIngredients, ingredients);
    }

}
