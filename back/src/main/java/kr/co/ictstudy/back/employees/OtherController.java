package kr.co.ictstudy.back.employees;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/other")
public class OtherController {
    // @GetMapping("/test")
    // public String getMethodName(Model model) {
    // // 현재 인증된 사용자의 정보를 가져옴
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // // Authentication에서 스프링 시큐리티가 관리하는 객체인데 애를 통해서 사용자의 이름을 가져옴 (로긴한 ..)
    // String currentUserName = authentication.getName();
    // System.out.println(currentUserName);
    // model.addAttribute("username", currentUserName);
    // return "other";
    // }
    @GetMapping("/test")
    public String getMethodName(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        String role = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
            role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst().orElse(null);
        }
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "other";
    }
}
