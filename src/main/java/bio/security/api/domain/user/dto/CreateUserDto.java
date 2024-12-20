package bio.security.api.domain.user.dto;

import bio.security.api.domain.user.enums.AccessLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character and no whitespace.")
        String password,
        @NotNull
        int biometricId,
        @NotNull
        AccessLevel accessLevel) {
}
