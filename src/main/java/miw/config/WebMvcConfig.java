package miw.config;

import miw.miscellaneous.TimeBasedAccessInterceptor;
import miw.restControllers.AdminResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeBasedAccessInterceptor())
                .addPathPatterns(AdminResource.ADMINS + AdminResource.OUT_OF_TIME + "/**")
                .excludePathPatterns("/foo/**");
    }

}
