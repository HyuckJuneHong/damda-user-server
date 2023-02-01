package kr.co.damdauser.entity;

import kr.co.damdauser.dto.RequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;

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

    public static UsersEntity of(RequestDto.CREATE create, PasswordEncoder passwordEncoder){
        return UsersEntity.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .email(create.getEmail())
                .build();
    }
}
