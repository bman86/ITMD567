package com.brentmennen;

import com.brentmennen.Entity.Live;
import com.brentmennen.Service.LiveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(LiveService liveService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Live>> typeReference = new TypeReference<List<Live>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/nowstock.json");
            try {
                List<Live> nowstock = mapper.readValue(inputStream,typeReference);
                liveService.save(nowstock);
                System.out.println("Stocks saved");
            } catch (IOException e){
                System.out.println("Didnt save users" + e.getMessage());
            }
        };
    }



}
