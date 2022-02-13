package com.cinekodigo.cinekodigo.controller;

import com.cinekodigo.cinekodigo.entity.Users;
import com.cinekodigo.cinekodigo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CrudApiRestController {
    @Autowired
    UserRepository UserRepository;
    @GetMapping("/Crud")
    public ResponseEntity<List<Users>> getAllCruds(@RequestParam(required = false) String name) {
        try {
            List<Users> users = new ArrayList<Users>();
            if (name == null)
                UserRepository.findAll().forEach(users::add);
            else
                UserRepository.findByNameContaining(name).forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Crud/{id}")
    public ResponseEntity<Users> getCrudById(@PathVariable("id") long id) {
        Optional<Users> CrudData = UserRepository.findById(id);
        if (CrudData.isPresent()) {
            return new ResponseEntity<>(CrudData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/Crud")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        try {
            Users _Users = UserRepository
                    .save(new Users(user.getName(), user.getEmail(), user.getMobile_number()));
            return new ResponseEntity<>(_Users, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/Crud/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
        Optional<Users> CrudData = UserRepository.findById(id);
        if (CrudData.isPresent()) {
            Users _Users = CrudData.get();
            _Users.setName(user.getName());
            _Users.setEmail(user.getEmail());
            _Users.setMobile_number(user.getMobile_number());
            return new ResponseEntity<>(UserRepository.save(_Users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/Crud/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            UserRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/Crud")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            UserRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}