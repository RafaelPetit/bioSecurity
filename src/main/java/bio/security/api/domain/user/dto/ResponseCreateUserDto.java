package bio.security.api.domain.user.dto;

import bio.security.api.domain.user.User;

public record ResponseCreateUserDto (Long id, String username) {
    public ResponseCreateUserDto(User user) {
        this(user.getId(), user.getUsername());
    }
}
