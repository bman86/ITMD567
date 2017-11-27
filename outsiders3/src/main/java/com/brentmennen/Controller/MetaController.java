package com.brentmennen.Controller;

import com.brentmennen.Entity.MetaData;
import com.brentmennen.Entity.TimeSeries;
import com.brentmennen.Service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metadata")
public class MetaController {

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(method = RequestMethod.POST)
    public void insertMetaData(@RequestBody MetaData metaData, TimeSeries timeSeries) {metaDataService.insertMetaData(metaData, timeSeries);}

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
    public MetaData getRecentMetaData() { return metaDataService.getRecentMetaData(); }

}

