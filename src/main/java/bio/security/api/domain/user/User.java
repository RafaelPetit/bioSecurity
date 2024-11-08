package bio.security.api.domain.user;

import bio.security.api.domain.post.Post;
import bio.security.api.domain.user.enums.AccessLevel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "biometric_id")
    private int biometricId;
    private String username;
    private String password;
    private boolean status = true;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Post> posts;

    @Column(name = "access_level")
    @Enumerated(EnumType.ORDINAL)
    private AccessLevel accessLevel;

    public User(String username, String encodedPassword) {
        this.username = username;
        this.password = encodedPassword;
    }


    public User(int biometricId, String username, String password, AccessLevel accessLevel) {
        this.biometricId = biometricId;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public User(User autenticatedUser) {
        this.id = autenticatedUser.getId();
        this.biometricId = autenticatedUser.getBiometricId();
        this.username = autenticatedUser.getUsername();
        this.password = autenticatedUser.getPassword();
        this.accessLevel = autenticatedUser.getAccessLevel();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + accessLevel.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "accessLevel=" + accessLevel +
                ", id=" + id +
                ", biometricId=" + biometricId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
