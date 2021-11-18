package com.ahci.userservice.service;

import com.ahci.userservice.model.User;
import com.ahci.userservice.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id)
    {
        log.info("Finding user with id:" + id);
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        log.info("Saving user:" + user);
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
