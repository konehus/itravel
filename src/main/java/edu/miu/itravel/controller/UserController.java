package edu.miu.itravel.controller;

import edu.miu.itravel.controller.exhandlers.BindingErrorsException;
import edu.miu.itravel.model.User;
import edu.miu.itravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") @Digits(integer = Integer.MAX_VALUE, fraction = 0) int page,
            @RequestParam(value = "size", defaultValue = "3") @Digits(integer = 50, fraction = 0) int size
    ) {
        if (this.userService.getAllUsers(page, size).isEmpty())
            throw new ResourceNotFoundException("Users Data is Empty!");

        return ResponseEntity.ok().body(this.userService.getAllUsers(page, size));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") @Min(value = 1) Long userId) {
        return ResponseEntity.ok().body(this.userService.getUserById(userId));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") @Min(value = 1) Long userId,
                                           @RequestBody User user,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);
        user.setId(userId);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") @Min(value = 1) Long userId) {
        this.userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }


}
