package bio.security.api.domain.post;

import bio.security.api.domain.user.AccessLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByAccessLevel(AccessLevel accessLevel, Pageable pageable);

}
