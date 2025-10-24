package poly.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.edu.Interceptor.LogInterceptor;
import poly.edu.Interceptor.AuthInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(authInterceptor)
        .addPathPatterns("/account/**", "/order/**", "/admin/**")
        .excludePathPatterns("/auth/**", "/home/**");
        
        registry.addInterceptor(logInterceptor)
        .addPathPatterns("/account/**", "/order/**", "/admin/**")
        .excludePathPatterns("/auth/**", "/home/**");
    }
}
