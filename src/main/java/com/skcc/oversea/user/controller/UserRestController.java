package com.skcc.oversea.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.skcc.oversea.user.dto.ApiResponse;
import com.skcc.oversea.user.dto.PageInfo;
import com.skcc.oversea.user.controller.request.UserAuthRequest;
import com.skcc.oversea.user.controller.request.UserCreateRequest;
import com.skcc.oversea.user.controller.request.UserUpdateRequest;

import com.skcc.oversea.user.controller.response.UserResponse;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponse> signUp(@Valid @RequestBody UserCreateRequest userCreateRequest) {

        User user = userService.signUp(userCreateRequest.toModel());
        return ApiResponse.ok(UserResponse.fromUser(user));
    }

    // 로그인
    @PostMapping("/authenticate")
    public ApiResponse<String> authenticate(@RequestBody UserAuthRequest userAuthRequest) {
        String result = userService.authenticate(userAuthRequest.getEmail(), userAuthRequest.getPassword());
        return ApiResponse.ok(result);
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> searchAllUser(Pageable pageable) {
    
        Page<User> result = userService.findAll(pageable);

        return ApiResponse.ok(result
                .stream()
                .map(UserResponse::fromUser)
                .toList(), PageInfo.fromPage(result));
    }

    @GetMapping("/all")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ApiResponse.ok(users.stream()
                .map(UserResponse::fromUser)
                .toList());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable long id) {
        return ApiResponse
                .ok(UserResponse.fromUser(userService.getById(id)));
    }

    @GetMapping("/admin")
    public ApiResponse<List<UserResponse>> getAdminUsers(Pageable pageable) {
        log.info("[Controller] : {}" , pageable);
        Page<User> result = userService.findAdminUsers(pageable);
        return ApiResponse.ok(result
                .stream()
                .map(UserResponse::fromUser)
                .toList(), PageInfo.fromPage(result));
    }

    @PatchMapping
    public ApiResponse<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.updateUser(userUpdateRequest.toModel());
        return ApiResponse.ok(UserResponse.fromUser(user));
    }

}