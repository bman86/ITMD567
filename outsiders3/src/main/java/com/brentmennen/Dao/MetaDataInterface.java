package com.brentmennen.Dao;

import com.brentmennen.Entity.MetaData;
import com.brentmennen.Entity.TimeSeries;

public interface MetaDataInterface {

    void insertMetaDataToDb(MetaData metaData, TimeSeries timeSeries);

    MetaData getRecentMetaData();

}
