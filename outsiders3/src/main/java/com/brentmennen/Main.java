package com.brentmennen;

import com.brentmennen.Entity.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.json.JsonSlurper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Main {

    private static final String JSON_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);

        URL url = null;
        try {
            url = new URL(JSON_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream urlStream = null;
        try {
            try {
                urlStream = url.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream));
            JsonSlurper jsonSlurper = new JsonSlurper();
            Object result = jsonSlurper.parse(reader);

            Map jsonResult = (Map) result; //parsed the result into a big big java map, with everything.

            Map metadata = (Map) jsonResult.get("Meta Data"); //grab a sub section, but its key value - in this case "Meta Data" - matching the key from the example json
            //String symbol = (String) metadata.get("2. Symbol");
            System.out.println("opened!" + metadata);

            //build a pojo
            MetaData myPojo = new MetaDataFactory().construct(metadata); //use the map info, in a nice interface/adapter class to build our POJO
            System.out.println("opened!" + myPojo);


            //grab just the most recent information, and to do that, we could sort the keys of the map
            Map timeSeriesInfo = (Map) jsonResult.get("Time Series (1min)"); //get the sub map into a handy object
            System.out.println("opened!" + timeSeriesInfo);

            Set<String> keys = timeSeriesInfo.keySet(); //grab all the keys
//TODO sort keys, and find the most recent one
            String mostRecentKey = "";

            //Grab just the sub elements of the most recent key
            Map mostRecentTimeSeriesData = (Map) timeSeriesInfo.get(mostRecentKey);
            String open = (String) timeSeriesInfo.get("1. open");

            //build a pojo
            TimeSeries timePojo = new TimeSeriesFactory().construct(timeSeriesInfo);
            System.out.println("opened!" + timePojo);


        } finally {
        if (urlStream != null) {
            try {
                urlStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }
    }
}
       /* return args -> {

            ObjectMapper mapper = new ObjectMapper();
            //Live mapper = new ObjectMapper().readValue("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo", Live.class);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            TypeReference<List<Live>> typeReference = new TypeReference<List<Live>>() {
            };
            InputStream input = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo").openStream();
            System.out.println("opened!" + input);


            try {
                List<Live> lives = mapper.readValue(input, typeReference);
                liveService.save(lives);
                System.out.println("Live Saved!" + lives);


            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }; */

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




