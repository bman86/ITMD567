package com.brentmennen;

import com.brentmennen.Entity.*;
import com.brentmennen.Service.MetaDataService;
import groovy.json.JsonSlurper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

@Component
public class Parser {



    public Parser() {

    }
    @Autowired
    MetaDataService metaDataService;

    @Scheduled(initialDelay = 0, fixedRate = 300000)
    public void parse() {

        String JSON_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo";


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
                System.out.println("opened!" + metadata);

                //build a pojo
                MetaData myPojo = new MetaDataFactory().construct(metadata); //use the map info, in a nice interface/adapter class to build our POJO
                System.out.println("opened!" + myPojo);


                //grab just the most recent information, and to do that, we could sort the keys of the map
                Map timeSeriesInfo = (Map) jsonResult.get("Time Series (1min)"); //get the sub map into a handy object
                System.out.println("opened!" + timeSeriesInfo);

                Set<String> keys = timeSeriesInfo.keySet(); //grab all the keys to print and verify
                System.out.println("ALl keys!" + keys);

                Object key = timeSeriesInfo.keySet().toArray()[0]; //grab first key
                System.out.println("key!" + key);

                //Grab just the sub elements of the most recent key
                Map mostRecentTimeSeriesData = (Map) timeSeriesInfo.get(key);
                System.out.println("recent!" + mostRecentTimeSeriesData);

                //build a pojo
                TimeSeries timePojo = new TimeSeriesFactory().construct(mostRecentTimeSeriesData);
                System.out.println("opened!" + timePojo);


                metaDataService.insertMetaData(myPojo, timePojo);
                System.out.println("saved");



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


