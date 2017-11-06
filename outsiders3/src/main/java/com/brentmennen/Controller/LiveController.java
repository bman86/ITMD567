package com.brentmennen.Controller;

import com.brentmennen.Entity.Live;
import com.brentmennen.Service.LiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/live")
public class LiveController {

    private LiveService liveService;

    public LiveController(LiveService liveService) {
        this.liveService = liveService;
    }

    @GetMapping("/list")
    public Iterable<Live> list() {
        return liveService.list();
    }

}