package kr.co.ictstudy.back.employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index"; // 뷰 이름
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 뷰 이름
    }

    @GetMapping("/logout")
    public String logout() {
        return "login"; // 로그아웃 후 보여줄 페이지
    }
}
