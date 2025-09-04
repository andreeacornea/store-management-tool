package ro.project.store_management_tool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class BaseWebMvcConfigurationSupport implements WebMvcConfigurer {

    private final LoggerRequestInterceptorAdapter loggerInterceptor;

    @Autowired
    public BaseWebMvcConfigurationSupport(LoggerRequestInterceptorAdapter loggerInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor);
    }
}
