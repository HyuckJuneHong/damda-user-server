package kr.co.damdauser.service;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.dto.ResponseDto;
import kr.co.damdauser.jpa.UsersEntity;
import kr.co.damdauser.jpa.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * user create service
     * @param create : user create info dto
     */
    public void signUp(RequestDto.CREATE create){
        checkPassword(create.getPassword(), create.getCheckPassword());
        isIdentity(create.getIdentity());
        final UsersEntity newUser = UsersEntity.of(create, passwordEncoder);

        usersRepository.save(newUser);
    }

    /**
     * user detail info read service
     * @param identity : user identity
     * @return user detail info
     */
    public ResponseDto.READ_DETAIL getUserInfoByIdentity(String identity){
        final UsersEntity usersEntity = findUserByIdentity(identity);
        final List<ResponseDto.READ_ORDER> readOrders = new ArrayList<>();
        final ResponseDto.READ_DETAIL readDetail = UsersEntity.of(usersEntity, readOrders);

        return readDetail;
    }

    /**
     * user detail info all list read  service
     * @return : user detail info list read
     */
    public List<ResponseDto.READ_DETAIL> getAllUserInfo(){
        final List<UsersEntity> usersEntities = usersRepository.findAll();
        final List<ResponseDto.READ_DETAIL> readDetails = UsersEntity.of(usersEntities);

        return readDetails;
    }

    /**
     * find user by identity
     * @param identity : find user identity
     * @return usersEntity
     */
    private UsersEntity findUserByIdentity(String identity){
        return usersRepository.findByIdentity(identity)
                .orElseThrow(() -> new RuntimeException("No exist Identity User"));
    }

    /**
     * password check
     * @param password : use PW
     * @param checkPassword : check PW
     */
    private void checkPassword(String password, String checkPassword){
        if(!password.equals(checkPassword))
            throw new RuntimeException("check password error");
    }

    /**
     * identity duplicated check
     * @param identity : user identity
     */
    private void isIdentity(String identity){
        if(usersRepository.existsByIdentity(identity))
            throw new RuntimeException("duplicated identity");
    }
}
