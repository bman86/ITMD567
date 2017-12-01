package com.brentmennen.Entity;

import java.util.Map;

public class MetaDataFactory {

    public MetaData construct(Map webapiinfo) {
        MetaData obj = new MetaData();
        obj.setMetaData((String)webapiinfo.get("2. Symbol")); //gets one field from the map

        return obj;

    }
}