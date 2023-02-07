package kr.co.damdauser.dto;

import kr.co.damdauser.dto.client.ResponseClientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ResponseDto {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class TOKEN{
        private String accessToken;

        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_USER_INFO {
        private String name;

        private String email;

        private List<ResponseClientDto.READ_ORDER_INFO> readOrders;
    }
}
