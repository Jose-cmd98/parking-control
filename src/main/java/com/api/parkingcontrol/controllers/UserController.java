package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.UserDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        userService.save(userDto.convertToModel());
        return ResponseEntity.ok().header("message", "User created").build();
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") String id){
        Optional<UserModel> userModel = userService.findById(UUID.fromString(id));
        if (userModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "Esse id não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") String id) {
        Optional<UserModel> userModel = userService.findById(UUID.fromString(id));
        if (userModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "Esse usuário não existe");
        }
        userService.delete(userModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") String id, @RequestBody UserDto userDto) {
        Optional<UserModel> userModel = userService.findById(UUID.fromString(id));
        if (userModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + "Esse usuário não existe.");
        }

        userService.save(userDto.convertToModel(userModel.get()));

        return ResponseEntity.ok().body("Usuário atualizado.");
    }
}
