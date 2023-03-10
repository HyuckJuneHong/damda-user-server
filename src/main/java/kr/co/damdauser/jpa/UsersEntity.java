package kr.co.damdauser.jpa;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.dto.ResponseDto;
import kr.co.damdauser.dto.client.ResponseClientDto;
import kr.co.damdauser.jpa.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersEntity {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "identity", unique = true, nullable = false, length = 50)
    private String identity;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    @Email
    private String email;

    @Column(name = "user_role", length = 25, nullable = false)
    private UserRole userRole;

    @Builder
    public UsersEntity(String identity,
                       String password,
                       String name,
                       String email,
                       UserRole userRole) {
        this.identity = identity;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public static UsersEntity of(RequestDto.CREATE_USER create,
                                 PasswordEncoder passwordEncoder){
        return UsersEntity.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .email(create.getEmail())
                .userRole(UserRole.ROLE_USER)
                .build();
    }

    public static ResponseDto.READ_USER_INFO of(UsersEntity usersEntity,
                                                List<ResponseClientDto.READ_ORDER_INFO> readOrderInfos){
        return ResponseDto.READ_USER_INFO.builder()
                .name(usersEntity.name)
                .email(usersEntity.email)
                .readOrders(readOrderInfos)
                .build();
    }

    public static List<ResponseDto.READ_USER_INFO> of(List<UsersEntity> usersEntities){
        List<ResponseDto.READ_USER_INFO> readUserInfos = new ArrayList<>();

        for(UsersEntity usersEntity : usersEntities){
            List<ResponseClientDto.READ_ORDER_INFO> readOrderInfos = new ArrayList<>();
            readUserInfos.add(UsersEntity.of(usersEntity, readOrderInfos));
        }

        return readUserInfos;
    }
}
