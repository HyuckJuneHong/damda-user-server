package kr.co.damdauser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class RequestDto {

    @Getter
    @AllArgsConstructor
    public static class CREATE{
        private String name;

        private String identity;

        private String password;

        private String checkPassword;

        private String email;
    }
}
