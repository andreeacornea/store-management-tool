package ro.project.store_management_tool;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Import(StoreManagementToolApplicationTests.SecurityTestConfig.class)

class StoreManagementToolApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	static class SecurityTestConfig {

		@Bean
		public UserDetailsService userDetailsService() {
			return Mockito.mock(UserDetailsService.class);
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return Mockito.mock(PasswordEncoder.class);
		}
	}
}
