package com.brentmennen.Dao;

import com.brentmennen.Entity.Live;
import com.brentmennen.Entity.Stock;

import java.util.Collection;

public interface LiveRepository {
    Collection<Live> getAllLive();

    Stock getLiveById(int id);

    void removeLiveById(int id);

    void updateLive(Stock stock);

    void insertLiveToDb(Stock stock);
}
