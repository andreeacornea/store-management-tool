package ro.project.store_management_tool.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

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
