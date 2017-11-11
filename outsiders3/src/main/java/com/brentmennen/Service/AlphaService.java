package com.brentmennen.Service;

import com.brentmennen.Dao.AlphaDao;
import com.brentmennen.Dao.AlphaInterface;
import com.brentmennen.Entity.AlphaPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AlphaService {

    @Autowired
    @Qualifier("alpha")
    private AlphaInterface alphaInterface;

    public Collection<AlphaPojo> getAllAlpha() {return this.alphaInterface.getAllAlpha();}

    public AlphaPojo getAlphaById(int id) {return this.alphaInterface.getAlphaById(id);}

    public void insertAlpha(AlphaPojo alphaPojo) {this.alphaInterface.insertAlphaToDb(alphaPojo);}
}


