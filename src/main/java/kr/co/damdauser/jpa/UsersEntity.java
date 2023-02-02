package kr.co.damdauser.jpa;

import kr.co.damdauser.dto.RequestDto;
import kr.co.damdauser.dto.ResponseDto;
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

    @Column(name = "email", unique = true, length = 50)
    @Email
    private String email;

    @Builder
    public UsersEntity(String identity,
                       String password,
                       String name,
                       String email) {
        this.identity = identity;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UsersEntity of(RequestDto.CREATE create,
                                 PasswordEncoder passwordEncoder){
        return UsersEntity.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .email(create.getEmail())
                .build();
    }

    public static ResponseDto.READ_DETAIL of(UsersEntity usersEntity,
                                             List<ResponseDto.READ_ORDER> readOrders){
        return ResponseDto.READ_DETAIL.builder()
                .name(usersEntity.name)
                .email(usersEntity.email)
                .readOrders(readOrders)
                .build();
    }

    public static List<ResponseDto.READ_DETAIL> of(List<UsersEntity> usersEntities){
        List<ResponseDto.READ_DETAIL> readDetails = new ArrayList<>();
        for(UsersEntity usersEntity : usersEntities){
            List<ResponseDto.READ_ORDER> readOrders = new ArrayList<>();
            readDetails.add(UsersEntity.of(usersEntity, readOrders));
        }

        return readDetails;
    }
}
