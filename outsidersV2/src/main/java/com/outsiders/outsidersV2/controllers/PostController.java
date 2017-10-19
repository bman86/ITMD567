package com.outsiders.outsidersV2.controllers;

import com.outsiders.outsidersV2.domain.Post;
import com.outsiders.outsidersV2.domain.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @RequestMapping(value="", method=RequestMethod.GET)
    public String listPosts(Model model) {
        model.addAttribute("posts", repository.findAll());
        return "posts/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/posts");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newProject() {
        return "posts/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("stkSymbl") String stkSymbl,
                               @RequestParam("own") Boolean own,
                               @RequestParam("entry") Double entry,
                               @RequestParam("up") Boolean up,
                               @RequestParam("target") Double target,
                               @RequestParam("time") String time,
                               Post()) {
        Post = repository.save(new Post);
        return new ModelAndView("redirect:/posts");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("post_id") long id, @RequestParam("stkSymbl") String stkSymbl) {
        Post post = repository.findOne(id);
        post.setStkSymbl(stkSymbl);
        repository.save(post);
        return new ModelAndView("redirect:/posts");
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        Post post = repository.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }
}
