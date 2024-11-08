package bio.security.api.domain.post;

import bio.security.api.domain.user.enums.AccessLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            select p
            from Post p
            where p.accessLevel >= :accessLevel
            """)
    Page<Post> findAllByAccessLevel(@Param("accessLevel")AccessLevel accessLevel, Pageable pageable);

}
