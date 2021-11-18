package com.ahci.postservice.service;

import com.ahci.postservice.model.Post;
import com.ahci.postservice.model.PostList;
import com.ahci.postservice.repo.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post addPost(Post post){
        log.info("Saving post: " + post);
        return postRepository.save(post);
    }

    public PostList getUserPosts(Long userId){
        log.info("Get posts for user:" + userId);
        List posts = postRepository.findAllByUserId(userId);
        return new PostList(posts);
    }
}
