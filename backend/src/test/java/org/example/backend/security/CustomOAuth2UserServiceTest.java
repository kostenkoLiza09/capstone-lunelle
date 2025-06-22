package org.example.backend.security;

import org.example.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.mockito.Mockito.*;

class CustomOAuth2UserServiceTest {



    @Test
    void loadUser_simple() {
        UserRepository userRepo = mock(UserRepository.class);


        CustomOAuth2UserService service = new CustomOAuth2UserService(userRepo) {
            @Override
            public OAuth2User loadUser(OAuth2UserRequest request) {
                OAuth2User oauthUser = mock(OAuth2User.class);
                when(oauthUser.getName()).thenReturn("user123");
                userRepo.findById(oauthUser.getName())
                        .orElseGet(() -> createAndSaveUser(oauthUser));
                return oauthUser;
            }
        };

        OAuth2UserRequest req = mock(OAuth2UserRequest.class);
        service.loadUser(req);

        verify(userRepo).findById("user123");
    }
}



