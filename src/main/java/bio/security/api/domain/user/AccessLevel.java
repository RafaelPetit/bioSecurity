package bio.security.api.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum AccessLevel implements GrantedAuthority {
    ADMIN,
    USER,
    DIVISION_DIRECTOR,
    ENVIRONMENTAL_MINISTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
