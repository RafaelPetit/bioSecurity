package bio.security.api.domain.user;

import bio.security.api.domain.user.dto.BiometricDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Boolean verifyBiometry(Integer biometricId, User authenticatedUser) {
        User user = userRepository.findByBiometricId(biometricId);


        System.out.println("UbiometricId: " + biometricId);
        System.out.println("Authenticated User: " + authenticatedUser.getBiometricId());

        return authenticatedUser.getBiometricId() == biometricId;
    }
}
