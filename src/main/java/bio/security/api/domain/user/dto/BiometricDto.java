package bio.security.api.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public record BiometricDto(@NotNull Integer biometricId) {
}
