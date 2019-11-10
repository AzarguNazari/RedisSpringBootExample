package com.sudin.redis.springbootredisexample.controllers;

import com.sudin.redis.springbootredisexample.model.User;
import com.sudin.redis.springbootredisexample.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/rest/user")
public class UserResourceController {


    private UserRepository userRepository;

    public UserResourceController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable("id") final String id, @RequestBody User user, @RequestParam("firstname") String firstname) {
        User tempUser = userRepository.findById(id);
        tempUser.setFirstName(firstname);
        userRepository.delete(id);
        userRepository.update(tempUser);
        return userRepository.findById(id);
    }

    @GetMapping("/all")
    public Map<String,User> all() {
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, User> delete(@PathVariable("id") final String id) {
        userRepository.delete(id);
        return all();
    }

}
