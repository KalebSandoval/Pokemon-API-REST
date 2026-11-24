package sandoval.brian.pokeApi;

import jakarta.servlet.FilterRegistration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import sandoval.brian.pokeApi.security.JwtFilter;

@SpringBootApplication
public class PokeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokeApiApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistrationBean(JwtFilter jwtFilter) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

}
