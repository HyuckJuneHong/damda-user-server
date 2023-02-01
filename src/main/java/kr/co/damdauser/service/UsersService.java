package kr.co.damdauser.service;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.entity.UsersEntity;
import kr.co.damdauser.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public void signUp(RequestDto.CREATE create){
        checkPassword(create.getPassword(), create.getCheckPassword());
        isIdentity(create.getIdentity());
        final UsersEntity newUser = UsersEntity.of(create);

        usersRepository.save(newUser);
    }

    private void checkPassword(String password, String checkPassword){
        if(!password.equals(checkPassword))
            throw new RuntimeException("check password error");
    }

    private void isIdentity(String identity){
        if(usersRepository.existsByIdentity(identity))
            throw new RuntimeException("duplicated identity");
    }
}
