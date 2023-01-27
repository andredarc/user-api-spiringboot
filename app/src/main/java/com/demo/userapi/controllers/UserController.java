package com.demo.userapi.controllers;

import com.demo.userapi.models.Address;
import com.demo.userapi.models.User;
import com.demo.userapi.services.AddressService;
import com.demo.userapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService usersService;

    @Autowired
    private AddressService addressService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> getById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @GetMapping(path = "/{id}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<List<Address>>> getAddressesByUserId(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(usersService.getUserAddressesByUserId(id));
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(usersService.createUser(user));
    }

    @PatchMapping(path = "/{id}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(usersService.updateUser(id, user));
    }
}
