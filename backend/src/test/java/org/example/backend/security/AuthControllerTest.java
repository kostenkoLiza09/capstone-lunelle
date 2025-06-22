package org.example.backend.security;

import org.example.backend.model.record.AppUser;
import org.example.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getMe() throws Exception {
        mockMvc.perform(get("/api/auth/me")
                        .with(oidcLogin().userInfoToken(token -> token
                                .claim("login", "testUser"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }


    @Test
    void updateMe() throws Exception {
        AppUser user = new AppUser("user", "oldUser", "old.png", null,
                null, null, null, null);
        userRepository.save(user);

        String body = """
    {
      "username": "updatedUser",
      "avatarUrl": "new.png",
      "firstName": "Anna",
      "lastName": "K.",
      "city": "Hamburg",
      "address": "Street 42",
      "phoneNumber": "777777"
    }
    """;

        mockMvc.perform(put("/api/auth/me")
                        .with(oidcLogin().idToken(token -> token
                                .claim("sub", "user")
                                .claim("login", "oldUser")
                                .claim("avatar_url", "old.png")))
                        .contentType("application/json")
                        .content(body))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedUser"))
                .andExpect(jsonPath("$.city").value("Hamburg"));
    }

}
