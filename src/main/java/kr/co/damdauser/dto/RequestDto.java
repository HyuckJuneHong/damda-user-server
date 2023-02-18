package kr.co.damdauser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class RequestDto {

    @Getter
    @AllArgsConstructor
    public static class CREATE_USER{
        private String name;

        private String identity;

        private String password;

        private String checkPassword;

        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class LOGIN{
        private String identity;

        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class DELETE_USER{
        private String identity;

        private String password;
    }
}
