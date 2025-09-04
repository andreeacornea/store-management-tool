package ro.project.store_management_tool.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Store Management Tool API",
                description = "Store management tool",
                version = "1.0.0",
                contact = @Contact(
                        email = "andreeacornea.a@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
