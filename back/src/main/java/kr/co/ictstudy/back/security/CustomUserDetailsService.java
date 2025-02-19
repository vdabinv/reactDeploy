package kr.co.ictstudy.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Configuration 빈환경설정이기 때문에 스프링 시큐리티가 자동으로 사용할 수 있다.
// UserDetailsService 인터페이스를 구현한 클래스
// UserDetails 객체를 로드하는 역할을 수행
// UserDetails 객체는 사용자의 인증 및 권한 부여 과정에서 사용된다. 
// 예를 들어, 사용자의 비밀번호가 입력된 비밀번호와 일치하는지, 사용자가 어떤 권한을 가지고 있는지 등을 확인하는 데 사용

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // userRepository.findByUsername(username)을 호출하여 주어진 사용자 이름으로 사용자 정보를 검색
            System.out.println("Optional<User> =>" + userRepository.findByUsername(username));
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new Exception("user Not found "));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}