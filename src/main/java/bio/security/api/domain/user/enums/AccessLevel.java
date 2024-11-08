package bio.security.api.domain.user.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AccessLevel implements GrantedAuthority {
    ADMIN(0),
    ENVIRONMENTAL_MINISTER(1),
    DIVISION_DIRECTOR(2),
    USER(3);

    private final int level;

    AccessLevel(int level) {
        this.level = level;
    }

    public boolean canView(AccessLevel other) {
        return this.level <= other.level;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
