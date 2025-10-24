package com.example.jaxrs.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.jaxrs.controllers.CompteRestJaxRSAPI;

@Configuration
public class MyConfig {

    @Bean
    public ResourceConfig jerseyConfig() {
        ResourceConfig config = new ResourceConfig();
        config.register(CompteRestJaxRSAPI.class);

        // ✅ Enregistre les providers JSON et XML
        config.packages(
                "org.glassfish.jersey.jackson.internal.jackson.jaxrs.json",
                "org.glassfish.jersey.media.jaxb"
        );

        return config;
    }
}

