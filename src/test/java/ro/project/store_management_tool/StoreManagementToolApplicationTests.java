package ro.project.store_management_tool;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebMergedContextConfiguration;
import ro.project.store_management_tool.config.SecurityConfig;

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
