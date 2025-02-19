package kr.co.ictstudy.back.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageVO {
    private String msg;

    public MessageVO(String msg) {
        this.msg = msg;
    }
}
