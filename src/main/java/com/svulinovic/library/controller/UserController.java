package com.svulinovic.library.controller;

import com.svulinovic.library.exception.BadRequestException;
import com.svulinovic.library.model.UserRequest;
import com.svulinovic.library.model.UserResponse;
import com.svulinovic.library.service.UserService;
import com.svulinovic.library.util.Helper;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@Valid @RequestBody UserRequest request, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            throw new BadRequestException(Helper.errorsToString(errors));
        }

        userService.createUser(request);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@NotNull @PathVariable Integer id, @Valid @RequestBody UserRequest request, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            throw new BadRequestException(Helper.errorsToString(errors));
        }

        userService.updateUser(id, request);
    }

    @GetMapping(value = "/top/overdue-date", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserByLongestOverdueDate() {
        return userService.getUserByLongestOverdueDate();
    }

}
