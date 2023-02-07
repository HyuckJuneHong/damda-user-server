package kr.co.damdauser.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ResponseClientDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class READ_ORDER_INFO{
        private String productCode;

        private int amount;

        private int price;

        private int totalPrice;

        private String orderCode;
    }
}
