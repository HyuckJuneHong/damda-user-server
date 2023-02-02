package kr.co.damdauser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_DETAIL {
        private String name;

        private String email;

        private List<READ_ORDER> readOrders;
    }

    @Getter
    @AllArgsConstructor
    public static class READ_ORDER{
        private String productCode;

        private Integer amount;

        private Integer price;

        private Integer totalPrice;

        private LocalDateTime createdAt;

        private String orderCode;
    }
}
