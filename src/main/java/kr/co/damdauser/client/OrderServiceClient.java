package kr.co.damdauser.client;

import kr.co.error.model.ResponseFormat;
import kr.co.dto.ResponseOrderDto;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;


@FeignClient(name = "damda-order")
public interface OrderServiceClient {
    ResponseFormat<List<ResponseOrderDto.READ_ORDER_INFO>> findOrderInfosByIdentity(String identity);
}
