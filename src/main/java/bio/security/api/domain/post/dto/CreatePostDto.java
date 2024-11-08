package bio.security.api.domain.post.dto;

import bio.security.api.domain.user.enums.AccessLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostDto (
        @NotBlank
        String title,
        @NotBlank
        String content,
        @NotNull
        AccessLevel accessLevel
) {
}
