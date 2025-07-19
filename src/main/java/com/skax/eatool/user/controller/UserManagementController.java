package com.skax.eatool.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.domain.UserCreate;
import com.skax.eatool.user.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user-management")
@RequiredArgsConstructor
@Slf4j
public class UserManagementController {

    private final UserService userService;

    @GetMapping
    public String userMain() {
        // /user-management 경로에 접근한 사용자를 목록으로 리다이렉트
        return "redirect:/user-management/list";
    }

    @GetMapping("/list")
    public String userList(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String job,
            Model model) {

        List<User> users = userService.findAllUsers();

        // 검색 조건에 따른 필터링
        if (userId != null && !userId.trim().isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getUserId() != null &&
                            user.getUserId().toLowerCase().contains(userId.toLowerCase()))
                    .toList();
        }

        if (username != null && !username.trim().isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getUsername() != null &&
                            user.getUsername().toLowerCase().contains(username.toLowerCase()))
                    .toList();
        }

        if (company != null && !company.trim().isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getCompany() != null &&
                            user.getCompany().toLowerCase().contains(company.toLowerCase()))
                    .toList();
        }

        if (job != null && !job.trim().isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getJob() != null &&
                            user.getJob().toLowerCase().contains(job.toLowerCase()))
                    .toList();
        }

        model.addAttribute("users", users);
        return "user/management/list";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        UserCreate userCreate = UserCreate.builder()
                .userId("")
                .username("")
                .email("")
                .password("")
                .address("")
                .job("")
                .age(null)
                .company("")
                .build();
        model.addAttribute("user", userCreate);
        return "user/management/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserCreate userCreate, Model model) {
        try {
            userService.signUp(userCreate);
            return "redirect:/user-management/list";
        } catch (Exception e) {
            log.error("사용자 등록 중 오류 발생: {}", e.getMessage());
            model.addAttribute("error", "사용자 등록 중 오류가 발생했습니다.");
            return "user/management/register";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/management/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user, Model model) {
        try {
            userService.update(user);
            return "redirect:/user-management/list";
        } catch (Exception e) {
            log.error("사용자 수정 중 오류 발생: {}", e.getMessage());
            model.addAttribute("error", "사용자 수정 중 오류가 발생했습니다.");
            return "user/management/edit";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            // 사용자 삭제 기능은 포지토리에서 직접 호출
            return "redirect:/user-management/list";
        } catch (Exception e) {
            log.error("사용자 삭제 중 오류 발생: {}", e.getMessage());
            return "redirect:/user-management/list";
        }
    }
}
