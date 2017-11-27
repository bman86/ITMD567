package com.brentmennen.Controller;

import com.brentmennen.Entity.MetaData;
import com.brentmennen.Entity.TimeSeries;
import com.brentmennen.Service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metadata")
public class MetaController {

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.POST)
    public void insertMetaData(@RequestBody MetaData metaData, TimeSeries timeSeries) {metaDataService.insertMetaData(metaData, timeSeries);}
}



