package com.example.academy.modules.user.controller;

import com.example.academy.core.domain.mapper.UserMapper;
import com.example.academy.core.domain.request.user.UserUpdateRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<?> getAllActiveUsers(
            @RequestParam(name = "roleName", required = false) String roleName) {
        Page<UserResponse> users = userService.getAllUsers(roleName, PageRequest.of(0, 50), true);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAnyRole('SUPER','ADMIN','MANEGER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /*// Update User Methods for Authority users
    @PreAuthorize("hasRole('SUPER')")
    @PutMapping("/{id}/roles")
    public ResponseEntity<?> updateUserRoles(
            @PathVariable Long id,
            @RequestBody Set<Long> roleIds) {
        userService.updateUserRoles(id, roleIds);
        return ResponseEntity.ok("User roles updated successfully");
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(id, userUpdateRequest);
        return ResponseEntity.ok("User updated successfully");
    }

    // Restore User Methods for ADMIN and SUPER
    @PreAuthorize("hasAnyRole('SUPER','ADMIN','MANEGER')")
    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restoreUser(@PathVariable Long id) {
        userService.restoreUser(id);
        return ResponseEntity.ok("User restored successfully");
    }

    @PreAuthorize("hasAnyRole('SUPER','ADMIN','MANEGER')")
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam(name = "id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Restore Password Methods for full users
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String identifier) {
        userService.resetPasswordByIdentifier(identifier);
        return ResponseEntity.ok("Password reset email or SMS sent successfully");
    }
    @PostMapping("/confirm-reset")
    public ResponseEntity<?> confirmPasswordReset(
            @RequestParam String token,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }
        userService.updatePasswordWithToken(token, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserEntity byUsername = userService.findByUsername(username);
        return ResponseEntity.ok(UserMapper.entityToResponse(byUsername));
    }

    /*@PreAuthorize("hasAuthority('VIEW_USER_STATS')")
    @GetMapping("/stats")
    public ResponseEntity<UserStatisticsResponse> getUserStatistics() {
        UserStatisticsResponse stats = userService.getUserStatistics();
        return ResponseEntity.ok(stats);
    }*/

    /*@PreAuthorize("hasAuthority('UPDATE_USER_STATUS')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        userService.updateUserStatus(id, active);
        return ResponseEntity.ok("User status updated successfully");
    }*/


}
