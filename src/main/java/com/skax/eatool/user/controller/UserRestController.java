package com.skax.eatool.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.skax.eatool.user.dto.ApiResponse;
import com.skax.eatool.user.dto.PageInfo;
import com.skax.eatool.user.controller.request.UserAuthRequest;
import com.skax.eatool.user.controller.request.UserCreateRequest;
import com.skax.eatool.user.controller.request.UserUpdateRequest;

import com.skax.eatool.user.controller.response.UserResponse;
import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponse> signUp(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        log.info("[UserRestController] signUp START - email: {}", userCreateRequest.getEmail());
        
        User user = userService.signUp(userCreateRequest.toModel());
        
        log.info("[UserRestController] signUp END - userId: {}", user.getUserId());
        return ApiResponse.ok(UserResponse.fromUser(user));
    }

    // 로그??
    @PostMapping("/authenticate")
    public ApiResponse<String> authenticate(@RequestBody UserAuthRequest userAuthRequest) {
        log.info("[UserRestController] authenticate START - email: {}", userAuthRequest.getEmail());
        
        String result = userService.authenticate(userAuthRequest.getEmail(), userAuthRequest.getPassword());
        
        log.info("[UserRestController] authenticate END - result: {}", result);
        return ApiResponse.ok(result);
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> searchAllUser(Pageable pageable) {
        log.info("[UserRestController] searchAllUser START - pageable: {}", pageable);
    
        Page<User> result = userService.findAll(pageable);

        log.info("[UserRestController] searchAllUser END - totalElements: {}", result.getTotalElements());
        return ApiResponse.ok(result
                .stream()
                .map(UserResponse::fromUser)
                .toList(), PageInfo.fromPage(result));
    }

    @GetMapping("/all")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        log.info("[UserRestController] getAllUsers START");
        
        List<User> users = userService.findAllUsers();
        
        log.info("[UserRestController] getAllUsers END - userCount: {}", users.size());
        return ApiResponse.ok(users.stream()
                .map(UserResponse::fromUser)
                .toList());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable long id) {
        log.info("[UserRestController] getById START - id: {}", id);
        
        User user = userService.getById(id);
        
        log.info("[UserRestController] getById END - userId: {}", user.getUserId());
        return ApiResponse.ok(UserResponse.fromUser(user));
    }

    @GetMapping("/admin")
    public ApiResponse<List<UserResponse>> getAdminUsers(Pageable pageable) {
        log.info("[UserRestController] getAdminUsers START - pageable: {}", pageable);
        
        Page<User> result = userService.findAdminUsers(pageable);
        
        log.info("[UserRestController] getAdminUsers END - totalElements: {}", result.getTotalElements());
        return ApiResponse.ok(result
                .stream()
                .map(UserResponse::fromUser)
                .toList(), PageInfo.fromPage(result));
    }

    @PatchMapping
    public ApiResponse<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        log.info("[UserRestController] updateUser START - userId: {}", userUpdateRequest.getUserId());
        
        User user = userService.updateUser(userUpdateRequest.toModel());
        
        log.info("[UserRestController] updateUser END - userId: {}", user.getUserId());
        return ApiResponse.ok(UserResponse.fromUser(user));
    }

}
