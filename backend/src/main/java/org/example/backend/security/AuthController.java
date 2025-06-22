package org.example.backend.security;

import org.example.backend.model.dto.AppUserDto;
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
    public AppUserDto getMe(@AuthenticationPrincipal OAuth2User oauthUser) {
        String id = oauthUser.getName();

        AppUser user = userRepo.findById(id)
                .orElseGet(() -> new AppUser(
                        id,
                        oauthUser.getAttribute("login"),
                        oauthUser.getAttribute("avatar_url"),
                        null, null, null, null, null));

        return new AppUserDto(
                user.username(),
                user.avatarUrl(),
                user.firstName(),
                user.lastName(),
                user.city(),
                user.address(),
                user.phoneNumber()
        );
    }


    @PutMapping("/me")
    public AppUserDto updateMe(@AuthenticationPrincipal OAuth2User oauthUser,
                               @RequestBody AppUserDto updatedUserDto) {
        String id = oauthUser.getName();

        AppUser updatedUser = userRepo.findById(id)
                .map(existingUser -> new AppUser(
                        existingUser.id(),
                        updatedUserDto.username() != null ? updatedUserDto.username() : existingUser.username(),
                        updatedUserDto.avatarUrl() != null ? updatedUserDto.avatarUrl() : existingUser.avatarUrl(),
                        updatedUserDto.firstName() != null ? updatedUserDto.firstName() : existingUser.firstName(),
                        updatedUserDto.lastName() != null ? updatedUserDto.lastName() : existingUser.lastName(),
                        updatedUserDto.city() != null ? updatedUserDto.city() : existingUser.city(),
                        updatedUserDto.address() != null ? updatedUserDto.address() : existingUser.address(),
                        updatedUserDto.phoneNumber() != null ? updatedUserDto.phoneNumber() : existingUser.phoneNumber()
                ))
                .map(userRepo::save)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AppUserDto(
                updatedUser.username(),
                updatedUser.avatarUrl(),
                updatedUser.firstName(),
                updatedUser.lastName(),
                updatedUser.city(),
                updatedUser.address(),
                updatedUser.phoneNumber()
        );
    }

}