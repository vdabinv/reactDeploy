package kr.co.ictstudy.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictstudy.back.vo.MessageVO;

@RestController
public class GetJsonDemoController {
    // http://192.168.0.42/myapp0719/hello?title=hello
    @GetMapping("/hello")
    public MessageVO getMethodName(@RequestParam("title") String title) {
        return new MessageVO("안녕하세요 " + title + "입니다.");
    }
}
