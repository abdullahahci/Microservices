package com.ahci.webservice.service;

import com.ahci.webservice.model.PostList;
import com.ahci.webservice.model.User;
import com.ahci.webservice.model.UserWithPosts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;

    /**
     * Rest template is synchronous, one line waits for the other
     */
    public User getUserWithPostsRT(Long userId) {
        log.info("Getting user info with Rest Template");
        User user = restTemplate.getForObject("http://localhost:9001/user/"+userId, User.class);

        log.info("Getting posts of the user");
        PostList postList = restTemplate.getForObject("http://localhost:9002/post/"+userId, PostList.class);

        log.info("Setting posts");
        user.setPosts(postList.getPosts());

        log.info("End of getUserWithPostsRT");
        return user;
    }

    /**
     * WebClient is asynchronous, one line doesn't wait for the other
     * all the requests are made and function ends and returns a Mono object
     * Spring waits for all to finish then responds
     */
    
    public Mono<UserWithPosts> getUserWithPostsWC(Long userId) {
        log.info("Getting user info");
        WebClient userClient = builder.baseUrl("http://localhost:9001")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Mono<User> userMono = userClient.get().uri("/user/" + userId)
                .retrieve()
                .bodyToMono(User.class);
        userMono.subscribe(user -> log.info("Returned user:" + user));

        log.info("Getting posts");
        WebClient postClient = builder.baseUrl("http://localhost:9002")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Mono<PostList> postListMono= postClient.get().uri("/post/" + userId)
                .retrieve()
                .bodyToMono(PostList.class);

        postListMono.subscribe(postList -> log.info("Returned posts with size:" + postList.getPosts().size()));

        log.info("Zipping results");
        Mono<UserWithPosts> result = Mono.zip(userMono, postListMono, UserWithPosts::new);

        log.info("End of getUserWithPostsWC");
        return result;
    }
}
