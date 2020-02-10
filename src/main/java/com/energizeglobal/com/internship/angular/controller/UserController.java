package com.energizeglobal.com.internship.angular.controller;

import com.energizeglobal.com.internship.angular.model.UserEntity;
import com.energizeglobal.com.internship.angular.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@Api
@Controller
@RequestMapping("/api/user")
@Transactional
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserEntity> createUser(@PathVariable Long userId, @Valid @RequestBody UserEntity userEntity) {
        final UserEntity saved = userRepository.save(userEntity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> readUser(@PathVariable(required = false) Long userId) {
            final Optional<UserEntity> byId = userRepository.findById(userId);
            return byId.map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> readAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserEntity> update(@RequestBody @Valid UserEntity userEntity) {
        if (userRepository.findById(userEntity.getId()).isPresent()) {
            userRepository.save(userEntity);
            return new ResponseEntity(userEntity, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> delete(@PathVariable Long userId) {
        final Optional<UserEntity> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}


