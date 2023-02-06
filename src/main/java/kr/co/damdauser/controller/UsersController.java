package kr.co.damdauser.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.dto.RequestUserDto;
import kr.co.dto.ResponseUserDto;
import kr.co.damdauser.service.UsersService;
import kr.co.error.model.ResponseFormat;
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

    @ApiOperation("일반회원 회원가입")
    @PostMapping
    public ResponseFormat<String> createUser(@RequestBody @Valid RequestUserDto.CREATE_USER create){
        usersService.signUp(create);
        return ResponseFormat.ok(create.getName() + "님 회원가입 완료");
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<ResponseUserDto.TOKEN> login(@RequestBody @Valid RequestUserDto.LOGIN login){
        return ResponseFormat.ok(usersService.login(login));
    }

    @GetMapping
    public ResponseFormat<ResponseUserDto.READ_USER_DETAIL> getUserInfoByIdentity(@RequestParam("identity") String identity){
        return ResponseFormat.ok(usersService.getUserInfoByIdentity(identity));
    }

    @GetMapping("/all")
    public ResponseFormat<List<ResponseUserDto.READ_USER_DETAIL>> getAllUserInfo(){
        return ResponseFormat.ok(usersService.getAllUserInfo());
    }
}
