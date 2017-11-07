package com.brentmennen.Service;

import com.brentmennen.Entity.Live;
import com.brentmennen.Repository.LiveRepository;
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

    public Live save(Live live) {
        return liveRepository.save(live);
    }

    public void save(List<Live> lives) {
        liveRepository.save(lives);
    }
}