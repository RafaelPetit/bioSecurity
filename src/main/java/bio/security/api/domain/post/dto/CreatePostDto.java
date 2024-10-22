package bio.security.api.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostDto (
        @NotBlank
        String title,
        @NotBlank
        String content
) {
}
