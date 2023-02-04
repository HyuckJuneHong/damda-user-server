package kr.co.damdauser.service;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.dto.ResponseDto;
import kr.co.damdauser.jpa.UsersEntity;
import kr.co.damdauser.jpa.UsersRepository;
import kr.co.enums.ErrorCode;
import kr.co.error.exception.BadRequestException;
import kr.co.error.exception.BusinessLogicException;
import kr.co.error.exception.DuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * user create service
     * @param create : user create info dto
     */
    @Transactional
    public void signUp(RequestDto.CREATE create){
        checkPassword(create.getPassword(), create.getCheckPassword());
        isIdentity(create.getIdentity());
        final UsersEntity newUser = UsersEntity.of(create, passwordEncoder);

        usersRepository.save(newUser);
    }

    /**
     * 로그인
     * @param login : 로그인하기 위한 아이디 패스워드
     * @return : 회원의 AccessToken과 RefreshToken을 담은 객체
     * @Exception BadRequestException : 아이디 혹은 비밀번호가 틀렸을 경우 발생하는 예외.
     */
    public ResponseDto.TOKEN login(RequestDto.LOGIN login) {
        UsersEntity usersEntity = findUserByIdentity(login.getIdentity());

        checkEncodePassword(login.getPassword(), usersEntity.getPassword());
        String[] tokens = generateToken(usersEntity);

        return ResponseDto.TOKEN.builder()
                .accessToken(tokens[0])
                .refreshToken(tokens[1])
                .build();
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
                .orElseThrow(() -> new BadRequestException(ErrorCode.WRONG_IDENTITY));
    }

    /**
     * password check
     * @param password : use PW
     * @param checkPassword : check PW
     */
    private void checkPassword(String password, String checkPassword){
        if(!password.equals(checkPassword))
            throw new BadRequestException(ErrorCode.WRONG_PASSWORD);
    }

    /**
     * 암호화된 비밀번호 체크
     * @param password 확인할 비밀번호
     * @param encodePassword 암호화된비밀번호
     */
    private void checkEncodePassword(String password, String encodePassword){
        if(!passwordEncoder.matches(password, encodePassword))
            throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
    }

    /**
     * identity duplicated check
     * @param identity : user identity
     */
    private void isIdentity(String identity){
        if(usersRepository.existsByIdentity(identity))
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
    }

    private String[] generateToken(UsersEntity usersEntity){
        String accessToken = jwtService.createAccessToken(usersEntity.getIdentity()
                , usersEntity.getUserRole(), usersEntity.getName());
        String refreshToken = jwtService.createRefreshToken(usersEntity.getIdentity()
                , usersEntity.getUserRole(), usersEntity.getName());

        return new String[]{accessToken, refreshToken};
    }
}
