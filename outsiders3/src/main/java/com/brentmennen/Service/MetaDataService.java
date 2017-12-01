package com.brentmennen.Service;

import com.brentmennen.Dao.MetaDataInterface;
import com.brentmennen.Entity.MetaData;
import com.brentmennen.Entity.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MetaDataService {

    @Autowired
    @Qualifier("metadata")
    private MetaDataInterface metaDataInterface;

    public void insertMetaData(MetaData metaData, TimeSeries timeSeries) {
        this.metaDataInterface.insertMetaDataToDb(metaData, timeSeries);
    }

    public MetaData getRecentMetaData() {
        return this.metaDataInterface.getRecentMetaData();
    }

}

