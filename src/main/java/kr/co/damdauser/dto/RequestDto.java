package kr.co.damdauser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class RequestDto {

    @Getter
    @AllArgsConstructor
    public static class CREATE{
        @ApiModelProperty(example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @ApiModelProperty(example = "사용할 아이디")
        @NotBlank(message = "아이디를 입력해주세요.")
        private String identity;

        @ApiModelProperty(example = "사용할 비밀번호")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        @ApiModelProperty(example = "사용할 비밀번호를 확인해주세요.")
        @NotBlank(message = "확인 비밀번호를 입력해주세요.")
        private String checkPassword;

        @ApiModelProperty(example = "example@email.com")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class LOGIN{
        @ApiModelProperty(example = "사용자 아이디")
        @NotBlank(message = "아이디를 입력해주세요")
        private String identity;

        @ApiModelProperty(example = "사용자 비밀번호")
        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;
    }
}
