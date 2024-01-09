/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapi.Wind;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class SWAPIDao {
    Wind getWind() {
        Client client = ClientBuilder.newClient();
        // TODO read in the uri from a properties file
        WebTarget target = client.target("");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Wind wind = null;
        try {
            wind = mapper.readValue(response, Wind.class);
        }
        catch (JsonProcessingException e) {
            //TODO set up logging and write this to the log
            e.printStackTrace();
        }
        return wind;
    }
}*/