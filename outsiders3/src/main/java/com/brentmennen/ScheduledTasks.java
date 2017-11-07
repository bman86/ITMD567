package com.brentmennen;

import com.brentmennen.Entity.Live;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("y-M-d H:m:00");

    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {


        log.info("The time is now {}", dateFormat.format(new Date()));
        ObjectMapper mapper = new ObjectMapper();
        //Live obj1 = mapper.readValue(new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo"), Live.class);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        TypeReference<List<Live>> typeReference = new TypeReference<List<Live>>(){};
        InputStream input = null;
        try {
            input = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("opened!" + input);


    }

}
