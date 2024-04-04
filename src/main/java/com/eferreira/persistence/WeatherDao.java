package com.eferreira.persistence;

import org.json.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * This class...
 */
public class WeatherDao {
    public JSONObject getMainJsonObject(String x, String y) {
        Client client = ClientBuilder.newClient();

        String openWeatherMapKey = "99cb12ee7bf388635c3b8d6538da8e35";

        // Call 2nd API using GPS coordinates
        String nationalWeatherServiceApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + x + "&lon=" + y + "&appid=" + openWeatherMapKey + "&units=imperial";
        WebTarget target = client.target(nationalWeatherServiceApiUrl);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        JSONObject mainObject = new JSONObject(response);

        return mainObject;
    }

}
