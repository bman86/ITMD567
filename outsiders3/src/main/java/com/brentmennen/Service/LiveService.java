package com.brentmennen.Service;

import com.brentmennen.Dao.LiveDao;
import com.brentmennen.Dao.LiveRepository;
import com.brentmennen.Entity.Live;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveService {

    @Autowired
    @Qualifier("liveDataBase")
    private LiveDao liveDao;

}
