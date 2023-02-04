package kr.co.damdauser.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TOKEN{
        @ApiModelProperty(example = "사용자 인증을 위한 accessToken")
        private String accessToken;
        @ApiModelProperty(example = "자동 로그인을 위한 refreshToken")
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_DETAIL {
        @ApiModelProperty(example = "홍길동")
        private String name;

        @ApiModelProperty(example = "example@email.com")
        private String email;

        private List<READ_ORDER> readOrders;
    }

    @Getter
    @AllArgsConstructor
    public static class READ_ORDER{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private LocalDateTime createdAt;

        private String orderCode;
    }
}
