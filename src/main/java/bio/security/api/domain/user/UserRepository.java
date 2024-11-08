package bio.security.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.biometricId = :biometricId")
    User findByBiometricId(Integer biometricId);
}

