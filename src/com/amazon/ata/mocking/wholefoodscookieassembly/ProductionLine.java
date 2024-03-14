package com.amazon.ata.mocking.wholefoodscookieassembly;

import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.ChocolateChipCookie;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieBox;
import com.amazon.ata.mocking.wholefoodscookieassembly.cookie.CookieIngredient;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.AllergenContaminantException;
import com.amazon.ata.mocking.wholefoodscookieassembly.exception.CookieProductionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Automates the Whole Foods cookie production line.
 */
public class ProductionLine {
    private Logger logger = LogManager.getLogger(ProductionLine.class);
    private Mixer mixer;
    private Oven oven;
    private CookiePackager cookiePackager;

    public ProductionLine(Mixer mixer, Oven oven, CookiePackager cookiePackager) {
        this.mixer = mixer;
        this.oven = oven;
        this.cookiePackager = cookiePackager;
    }

    /**
     * Runs the production line to produce the given number of cookies. Each cookie should be mixed, baked, and
     * packaged into boxes. If any allergen is detected, halt production, the machinery needs to be decontaminated.
     * @param numberOfCookiesToMake Number of cookies to attempt to make and package.
     * @throws CookieProductionException if an issue occurs and the production line needs to be halted
     * @return The boxes of cookies that are ready to be sold.
     */
    public List<CookieBox> createBatch(int numberOfCookiesToMake){
        for (int i = 0; i < numberOfCookiesToMake; i++) {
            List<CookieIngredient> ingredientTypes = mixer.mix();
            ChocolateChipCookie cookie = oven.bake(ingredientTypes);
            try {
                cookiePackager.packageCookie(cookie);
            } catch (AllergenContaminantException e) {
                logger.fatal("Allergen contamination detected. bad cookie: {} ", cookie, e);
                throw new CookieProductionException(e);
            }
        }
        return cookiePackager.getCookieBoxes();
    }
}
