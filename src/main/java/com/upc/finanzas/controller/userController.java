package com.upc.finanzas.controller;

import com.upc.finanzas.entity.User;
import com.upc.finanzas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> fetchById(@PathVariable("id") Long id) {
        try {
            User user = userService.findById(id);
            if(user != null){
                return ResponseEntity.ok(user);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User bono , BindingResult result) {

        try {
            User userCreate = userService.save(bono);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            User userDB = userService.findById(id);
            if (userDB != null) {
                User userCreate = userService.update(user, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteById(@PathVariable("id") Long id){


        try {
            User optionalUser = userService.findById(id);
            if(optionalUser != null){
                userService.deleteById(id);
                return new ResponseEntity<User>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
