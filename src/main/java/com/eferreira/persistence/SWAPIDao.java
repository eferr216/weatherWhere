package com.eferreira.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapi.Forecast;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class SWAPIDao {
    public Forecast getForecast(String x, String y, String openWeatherMapKey) {
        Client client = ClientBuilder.newClient();
        // TODO read in the uri from a properties file
        WebTarget target = client.target("https://api.openweathermap.org/data/2.5/weather?lat=" + x + "&lon=" + y + "&appid=" + openWeatherMapKey + "&units=imperial");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Forecast forecast = null;

        try {
            forecast = mapper.readValue(response, Forecast.class);
        } catch(JsonProcessingException e) {
            //TODO set up logging and write this to the log
            e.printStackTrace();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return forecast;
    }
}