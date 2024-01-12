package edu.matc.restdemo;

import edu.matc.controller.Auth;
import edu.matc.controller.Login;
import edu.matc.controller.SearchItems;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

// Defines the base URI for all resource URIs
@ApplicationPath("/weatherwhere_war") // You may want to add a value here so that all traffic isn't routed to the class below.

// The java class declares root resource and provider classes
public class WeatherWhereApplication extends Application {

    // The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(Outfit.class);
        /*h.add(Auth.class);
        h.add(Login.class);
        h.add(SearchItems.class);*/
        return h;
    }
}
