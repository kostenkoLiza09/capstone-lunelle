package org.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.record.AppUser;
import org.example.backend.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauthUser = super.loadUser(userRequest);

        userRepo.findById(oauthUser.getName())
                .orElseGet(() -> createAndSaveUser(oauthUser));

        return oauthUser;
    }

    private AppUser createAndSaveUser(OAuth2User oauthUser) {
        AppUser newUser = new AppUser(
                oauthUser.getName(),
                oauthUser.getAttribute("login"),
                oauthUser.getAttribute("avatar_url"),
                null,
                null,
                null,
                null,
                null
        );
        return userRepo.save(newUser);
    }

}