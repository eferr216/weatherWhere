package edu.matc.persistence;

import com.eferreira.persistence.SWAPIDao;
import com.swapi.Forecast;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class SWAPIDaoTest {

    @Test
    void getTimezoneSuccess() {
        SWAPIDao dao = new SWAPIDao();
        Forecast forecast = dao.getForecast("-89.3826","43.0778", "99cb12ee7bf388635c3b8d6538da8e35");
        assertEquals(46800, dao.getForecast("-89.3826","43.0778", "99cb12ee7bf388635c3b8d6538da8e35").getTimezone());
    }
}
