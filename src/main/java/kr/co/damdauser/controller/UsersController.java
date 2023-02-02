package kr.co.damdauser.controller;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.dto.ResponseDto;
import kr.co.damdauser.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public String createUser(@RequestBody @Valid RequestDto.CREATE create){
        usersService.signUp(create);
        return create.getName() + "님 회원가입 완료";
    }

    @GetMapping
    public ResponseDto.READ_DETAIL getUserInfoByIdentity(@RequestParam("identity") String identity){
        return usersService.getUserInfoByIdentity(identity);
    }

    @GetMapping("/all")
    public List<ResponseDto.READ_DETAIL> getAllUserInfo(){
        return usersService.getAllUserInfo();
    }
}
