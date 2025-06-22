package org.example.backend.security;

import org.example.backend.model.record.AppUser;
import org.example.backend.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/me")
    public AppUser getMe(@AuthenticationPrincipal OAuth2User oauthUser) {

        String id = oauthUser.getName();


        return userRepo.findById(id)
                .orElseGet(() -> new AppUser(
                        id,
                        oauthUser.getAttribute("login"),
                        oauthUser.getAttribute("avatar_url"),
                        null, null, null, null, null));
    }

    @PutMapping("/me")
    public AppUser updateMe(@AuthenticationPrincipal OAuth2User oauthUser,
                            @RequestBody AppUser updatedUser) {
        String id = oauthUser.getName();
        return userRepo.findById(id)
                .map(existingUser -> {
                    AppUser userToSave = new AppUser(
                            existingUser.id(),
                            updatedUser.username() != null ? updatedUser.username() : existingUser.username(),
                            updatedUser.avatarUrl() != null ? updatedUser.avatarUrl() : existingUser.avatarUrl(),
                            updatedUser.firstName() != null ? updatedUser.firstName() : existingUser.firstName(),
                            updatedUser.lastName() != null ? updatedUser.lastName() : existingUser.lastName(),
                            updatedUser.city() != null ? updatedUser.city() : existingUser.city(),
                            updatedUser.address() != null ? updatedUser.address() : existingUser.address(),
                            updatedUser.phoneNumber() != null ? updatedUser.phoneNumber() : existingUser.phoneNumber()
                    );
                    return userRepo.save(userToSave);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}