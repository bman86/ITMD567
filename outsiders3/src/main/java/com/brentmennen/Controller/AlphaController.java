package com.brentmennen.Controller;

import com.brentmennen.Entity.AlphaPojo;
import com.brentmennen.Service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<AlphaPojo> getAllAlpha() {return alphaService.getAllAlpha(); }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AlphaPojo getAlphaById(@PathVariable("id") int id) {return alphaService.getAlphaById(id); }

    @RequestMapping(method = RequestMethod.POST)
    public void insertAlpha(@RequestBody AlphaPojo alphaPojo) { alphaService.insertAlpha(alphaPojo);}
}


