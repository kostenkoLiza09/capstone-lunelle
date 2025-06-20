package org.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@ImportAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class
})

class BackendApplicationTests {

	@Test
	void contextLoads() {
		//test
	}

}
