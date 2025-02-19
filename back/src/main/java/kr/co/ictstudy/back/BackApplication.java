package kr.co.ictstudy.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.ictstudy.back.employees.Employee;
import kr.co.ictstudy.back.employees.EmployeeRepository;
import kr.co.ictstudy.back.security.Role;
import kr.co.ictstudy.back.security.User;
import kr.co.ictstudy.back.security.UserRepository;

@SpringBootApplication
public class BackApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	// 추가하기
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer crosConfigurer() {
		return new WebMvcConfigurer() {
			// 마우스 우클릭 소스작업.. 클릭
			// 오버라이딩 있음
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("Test==========");
				registry.addMapping("/**")
						.allowedOrigins("http://192.168.0.42:3001/", "http://localhost:3001/")
						// .allowedOrigins("http://192.168.0.9:3000/", "http://localhost:3000/")
						.allowedHeaders("*")
						.allowedMethods("*").maxAge(3600);
			}

		};
	}

	// Spring Boot가 실행이 될 때 여기에 정의한 코드를 작성 해놓으면 된다.
	// 애플리케이션 초기화 시 데이터베이스에 데이터 추가할 값이 있으면 이 메서드를 활용해도 됨
	// 메인은 스프링 부트는 만드는 과정이고 ,
	// 이 메서드는 애플리케이션이 완전히 시작된 후에 수행할 초기화 작업(스프링 빈이 완전 생성이 된 후)
	@Override
	public void run(String... args) throws Exception {

		// 여기에 일반 사용자 하나를 등록 해놓는다.
		/*
		 * public Employee(String name, String phone, String email, String position) {
		 * this.name = name;
		 * this.phone = phone;
		 * this.email = email;
		 * this.position = position;
		 * }
		 */
		employeeRepository.save(new Employee("tess", "010-8888-8888", "javabook@naver.com", "TEACHER01"));

		// User 객체를 데이터베이스에 저장
		String encodedPassword = passwordEncoder.encode("tess01");
		userRepository.save(new User("tess", "hong", "xman@gmail.com", encodedPassword, Role.ADMIN));
		userRepository.save(new User("tess2", "hong2", "xman2@gmail.com", encodedPassword, Role.USER));
	}

}
