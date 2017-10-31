package com.brentmennen.Service;

import com.brentmennen.Dao.LiveRepository;
import com.brentmennen.Entity.Live;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveService {

    private LiveRepository liveRepository;

    public LiveService(LiveRepository liveRepository) {
        this.liveRepository = liveRepository;
    }

    public Iterable<Live> list() {
        return liveRepository.findAll();
    }


    public Iterable<Live> save(List<Live> nowstock) {
        return liveRepository.save(nowstock);
    }

}
