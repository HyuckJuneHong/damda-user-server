package kr.co.damdauser.controller;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
