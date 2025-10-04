package com.BankApplication.BankApplication.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to ensure Swagger UI is served correctly.
 * This class explicitly maps the base URL to the Swagger documentation page.
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Adds a view controller to redirect from the root path to the Swagger UI.
     * This is an alternative fix for when the Swagger UI isn't found at the default URL.
     *
     * @param registry The ViewControllerRegistry used to add view controllers.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirecting from the root /swagger-ui/ to index.html
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui/index.html");
    }
}
