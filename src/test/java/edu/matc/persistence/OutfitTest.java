package edu.matc.persistence;

import com.eferreira.persistence.WeatherDao;
import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import edu.matc.rest.Outfit;
import edu.matc.test.util.Database;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutfitTest {
    WeatherDao weatherDao;
    Outfit outfit;
    JSONObject mainObject;

    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {
        /*genericDao = new GenericDao(ItemNote.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");*/
        weatherDao = new WeatherDao();
        outfit = new Outfit();
    }

    /**
     * Checks the wind speed.
     */
    @Test
    void getWindSuccess() {
        mainObject = weatherDao.getMainJsonObject("48.0722", "89.4008");
        int windSpeed = outfit.getWindSpeed(mainObject);
        assertNotNull(windSpeed);
        assertEquals(2, windSpeed);

    }

    /**
     * Checks the temperature.
     */
    @Test
    void getTemperatureSuccess() {
        mainObject = weatherDao.getMainJsonObject("48.0722", "89.4008");
        int temperature = outfit.getTemperature(mainObject);
        assertNotNull(temperature);
        assertEquals(2, temperature);

    }

    /**
     * Gets the temperature success.
     */
    @Test
    void buildDefaultItemListsSuccess() {
        outfit.buildDefaultItemLists();
        assertNotNull(outfit.getAllDefaultItemLists());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void buildDynamicItemListsSuccess() {
        outfit.buildDynamicItemLists();
        assertNotNull(outfit.getAllDynamicItemLists());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void sweatersItemListSuccess() {
        assertNotNull(outfit.getSweatersList());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void lightJacketsItemListSuccess() {
        assertNotNull(outfit.getLightJacketsList());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void glovesItemListSuccess() {
        assertNotNull(outfit.getGlovesList());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void scarvesItemListSuccess() {
        assertNotNull(outfit.getScarvesList());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void earMuffsItemListSuccess() {
        assertNotNull(outfit.getEarMuffsList());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void heavyJacketsItemListSuccess() {
        assertNotNull(outfit.getHeavyJacketsList());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getFootwearObjectSuccess() {
        assertNotNull(outfit.getFootwearObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getShirtsObjectSuccess() {
        assertNotNull(outfit.getShirtsObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getSocksObjectSuccess() {
        assertNotNull(outfit.getSocksObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getLegwearObjectSuccess() {
        assertNotNull(outfit.getLegwearObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getLightJacketObjectSuccess() {
        assertNotNull(outfit.getLightJacketObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getGlovesObjectSuccess() {
        assertNotNull(outfit.getGlovesObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getSweaterObjectSuccess() {
        assertNotNull(outfit.getSweaterObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getScarfObjectSuccess() {
        assertNotNull(outfit.getScarfObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getEarMuffsObjectSuccess() {
        assertNotNull(outfit.getEarMuffsObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getHeavyJacketObjectSuccess() {
        assertNotNull(outfit.getHeavyJacketObjectSuccess());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getFootwearNameSuccess() {
        assertNotNull(outfit.getFootwearName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getFootwearDescriptionSuccess() {
        assertNotNull(outfit.getFootwearDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getSocksNameSuccess() {
        assertNotNull(outfit.getSocksName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getSocksDescriptionSuccess() {
        assertNotNull(outfit.getSocksDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getShirtNameSuccess() {
        assertNotNull(outfit.getShirtName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getShirtDescriptionSuccess() {
        assertNotNull(outfit.getShirtDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getLegwearNameSuccess() {
        assertNotNull(outfit.getLegwearName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getLegwearDescriptionSuccess() {
        assertNotNull(outfit.getLegwearDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getSweaterNameSuccess() {
        assertNotNull(outfit.getSweaterName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getSweaterDescriptionSuccess() {
        assertNotNull(outfit.getSweaterDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getLightJacketNameSuccess() {
        assertNotNull(outfit.getLightJacketName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getLightJacketDescriptionSuccess() {
        assertNotNull(outfit.getLightJacketDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getGlovesNameSuccess() {
        assertNotNull(outfit.getGlovesName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getGlovesDescriptionSuccess() {
        assertNotNull(outfit.getGlovesDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getScarfNameSuccess() {
        assertNotNull(outfit.getScarfName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getScarfDescriptionSuccess() {
        assertNotNull(outfit.getScarfDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getEarMuffsNameSuccess() {
        assertNotNull(outfit.getEarMuffsName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getEarMuffsDescriptionSuccess() {
        assertNotNull(outfit.getEarMuffsDescription());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getHeavyJacketNameSuccess() {
        assertNotNull(outfit.getHeavyJacketName());
    }

    /**
     * Gets the temperature success.
     */
    @Test
    void getHeavyJacketDescriptionSuccess() {
        assertNotNull(outfit.getHeavyJacketDescription());
    }

}
