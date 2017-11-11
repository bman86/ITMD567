package com.brentmennen.Dao;

import com.brentmennen.Entity.AlphaPojo;

import java.util.Collection;

public interface AlphaInterface {
    Collection<AlphaPojo>getAllAlpha();

    AlphaPojo getAlphaById(int id);

    void insertAlphaToDb(AlphaPojo alphaPojo);

}
