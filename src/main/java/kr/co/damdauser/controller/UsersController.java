package kr.co.damdauser.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.dto.ResponseDto;
import kr.co.damdauser.error.model.ResponseFormat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import kr.co.damdauser.service.UsersService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @ApiOperation("일반회원 회원가입")
    @PostMapping
    public ResponseFormat<String> createUser(@RequestBody @Valid RequestDto.CREATE_USER create){
        usersService.signUp(create);
        return ResponseFormat.ok(create.getName() + "님 회원가입 완료");
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<ResponseDto.TOKEN> login(@RequestBody @Valid RequestDto.LOGIN login){
        return ResponseFormat.ok(usersService.login(login));
    }

    @ApiOperation("회원 정보 조회(주문 기록 포함) / 본인, 관리자만 가능")
    @GetMapping
    public ResponseFormat<ResponseDto.READ_USER_INFO> getUserInfoByIdentity(@RequestParam("identity") String identity){
        return ResponseFormat.ok(usersService.getUserInfoByIdentity(identity));
    }

    @ApiOperation("모든 회원 정보 조회(주문 기록 포함) / 관리자 권한만 가능")
    @GetMapping("/all")
    public ResponseFormat<List<ResponseDto.READ_USER_INFO>> getAllUserInfos(){
        return ResponseFormat.ok(usersService.getAllUserInfos());
    }

    @ApiOperation("회원 탈퇴 / 본인, 관리자만 가능")
    @DeleteMapping
    public ResponseFormat<String> deleteUser(@RequestBody @Valid RequestDto.DELETE_USER delete){
        //TODO delete user service
        return null;
    }
}
