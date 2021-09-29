package com.example.mavbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MavBackendApplication {

    @Value("${app.client.url}")
    String clientURL;
    /**
     * it is initialized when the server get up and allow or reject routes depend the env when is invocked ?
     *
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(clientURL).allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
            }

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(MavBackendApplication.class, args);
    }

}
