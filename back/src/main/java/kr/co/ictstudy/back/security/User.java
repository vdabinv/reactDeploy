package kr.co.ictstudy.back.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
@SequenceGenerator(name = "user_seq_gen", sequenceName = "_user_seq", allocationSize = 1)
public class User implements UserDetails {

    // 사용자의 고유 식별자. 데이터베이스에서 자동으로 생성
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    private Long id;
    private String firstName; // 이름
    private String lastName; // 성
    private String username; // 아이디
    private String password; // 비밀번호

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // SimpleGrantedAuthority는
    // 사용자에게 부여된 권한(ADMIN, USER)을 읽어 오기 위한 객체
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // 사용자의 아이디를 반환 하는데 , 사용자를 식별할 때 사용되는 메서드
    @Override
    public String getUsername() {
        return this.username;
    }

    // 계정이 만료되었는지 여부를 반환 하는데 , true를 반환해서 계정이 만료 안됨을 반환하도록 설정했다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 여부를 반환 true이면 계정 안잠김
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명(비밀번호)이 만료되었는지 여부를 반환. 마찬가지로 만료 안됨
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되어 있는지 여부를 반환, 계정이 활성화되었음을 반환
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

}
