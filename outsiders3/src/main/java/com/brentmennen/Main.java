package com.brentmennen;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableScheduling
public class Main {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
                corsRegistry.addMapping("/stocks").allowedOrigins("http://localhost:4200");
            }
        };

    }
}