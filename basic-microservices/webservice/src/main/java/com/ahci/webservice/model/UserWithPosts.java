package com.ahci.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserWithPosts {
    private User user;

    public UserWithPosts(User user, PostList postList){
        log.info("Inside UserWithPosts constructor");
        user.setPosts(postList.getPosts());
        this.user = user;
    }

}
