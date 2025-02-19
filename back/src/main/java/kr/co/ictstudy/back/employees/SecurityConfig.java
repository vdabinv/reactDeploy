package kr.co.ictstudy.back.employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth

                        // 리액트, Vue의 경로이동은 DefaultController 에서 index.html 즉
                        // 빌드된 곧으로 forward 되기 때문에 이 경로는 프론트단의 비동기식 경로이다.
                        // 그래서 이것의 제어는 각 프론트에서 store에 저장해가지고 비교 하는 방법이 있다.
                        // .requestMatchers("/", "/myapp0719/**").permitAll()
                        // store방법을 하기 전에 여기서는 Thymeleaf 으로 일단 테스트 해보자.
                        .requestMatchers("/", "/main").permitAll() // /, /main 즉 templates로 가는 컨트럴러 경로 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로는 ADMIN 권한 필요
                        .requestMatchers("/api/employees/**").permitAll() // /api/employees 경로 인증 없이 접근 허용
                        .anyRequest().authenticated() // 다른 모든 요청은 인증 필요
                )
                // Vue나 React일경우 필요 없는 설정이 된다.(개념 테스트 용 )
                // templates/login.html에 로그인 폼을 만든다.
                // login폼의 action을 처리하기 위해서
                // build.gradle에 Thymeleaf 의존성 추가 : implementation
                // 'org.springframework.boot:spring-boot-starter-thymeleaf'
                // application.properties에 등록
                /*
                 * spring.thymeleaf.cache=false
                 * spring.thymeleaf.prefix=classpath:/templates/
                 * spring.thymeleaf.suffix=.html
                 * spring.thymeleaf.mode=HTML5
                 * spring.thymeleaf.encoding=UTF-8
                 * spring.thymeleaf.content-type=text/html
                 */
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
                        .permitAll() // 로그인 페이지는 누구나 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 경로 설정
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 후 리다이렉트 경로
                        .permitAll() // 로그아웃 경로는 누구나 접근 가능
                );
        return http.build();
    }

}