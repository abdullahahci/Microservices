package com.ahci.webservice.controller;

import com.ahci.webservice.model.User;
import com.ahci.webservice.model.UserWithPosts;
import com.ahci.webservice.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    private WebService webService;

    @GetMapping("/sync/user/{id}")
    public User getUser(@PathVariable("id") Long userId){
        return webService.getUserWithPostsRT(userId);
    }

    @GetMapping("/async/user/{id}")
    public Mono<UserWithPosts> getUserAsync(@PathVariable("id") Long userId){
        return webService.getUserWithPostsWC(userId);
    }
}
