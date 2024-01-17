package kr.co.ooweat.config;

import kr.co.ooweat.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/static/webjars/",
            "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "classpath:/META-INF/resources/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST")
                .maxAge(3000);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        SessionInterceptor sessionInterceptor = new SessionInterceptor();
        registry.addInterceptor((HandlerInterceptor) sessionInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(sessionInterceptor.allowList);
    }
}
