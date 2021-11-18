package com.ahci.postservice.controller;

import com.ahci.postservice.model.Post;
import com.ahci.postservice.model.PostList;
import com.ahci.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @GetMapping ("/{id}")
    public PostList getUserPosts(@PathVariable("id") Long userId){
        return postService.getUserPosts(userId);
    }

}
