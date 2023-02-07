package kr.co.damdauser.handler.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import kr.co.error.exception.FeignClientException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 404:
                if(methodKey.contains("findOrderInfosByIdentity")){
                    return FeignClientException.builder()
                            .status(404)
                            .message("주문 정보 조회 API를 불러올 수 없습니다.")
                            .methodName("findOrderInfosByIdentity")
                            .build();
                }
                break;
            default:
                return FeignClientException.builder()
                        .status(400)
                        .message(response.reason())
                        .methodName(methodKey + "Can not method name")
                        .build();
        }
        return null;
    }
}
