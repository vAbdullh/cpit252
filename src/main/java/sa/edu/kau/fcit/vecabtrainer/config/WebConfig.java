package sa.edu.kau.fcit.vecabtrainer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private FirebaseAuthInterceptor firebaseAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firebaseAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/index.html", "/error"); // Excluding users for registration if needed, otherwise just /, index.html
    }
}
