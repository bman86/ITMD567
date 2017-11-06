package com.brentmennen;

import com.brentmennen.Entity.Live;
import com.brentmennen.Service.LiveService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
//import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner runner(LiveService liveService) {
        return args -> {

            ObjectMapper mapper = new ObjectMapper();
            //Live obj1 = mapper.readValue(new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo"), Live.class);

            TypeReference<List<Live>> typeReference = new TypeReference<List<Live>>(){};
            InputStream input = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo").openStream();


            try {
                List<Live> lives = mapper.readValue(input,typeReference);
                liveService.save(lives);
                System.out.println("Live Saved!" + lives);



        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

       /* @Bean
        CommandLineRunner runner(UserService userService){
            return args -> {
                // read JSON and load json
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
                InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");

                InputStream input = new URL("http://www.somewebsite.com/a.txt").openStream();


                try {
                    List<User> users = mapper.readValue(inputStream,typeReference);
                    userService.save(users);
                    System.out.println("Users Saved!");
                } catch (IOException e){
                    System.out.println("Unable to save users: " + e.getMessage());
                }
            };
        } */
    }
    /*
    @Bean
    CommandLineRunner runner(LiveService liveService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Live>> typeReference = new TypeReference<List<Live>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/nowstock.json");
            try {
                List<Live> livestk = mapper.readValue(inputStream,typeReference);
                liveService.save(livestk);
                System.out.println("Stocks saved");
            } catch (IOException e){
                System.out.println("Didnt save users" + e.getMessage());
            }
        };
    } */



}
