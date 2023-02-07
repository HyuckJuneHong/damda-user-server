package kr.co.damdauser.service.client;

import kr.co.damdauser.dto.client.ResponseClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "damda-order")
public interface OrderServiceClient {

    @GetMapping("/{identity}/orders")
    List<ResponseClientDto.READ_ORDER_INFO> findOrderInfosByIdentity(@PathVariable("identity") String identity);
 }
