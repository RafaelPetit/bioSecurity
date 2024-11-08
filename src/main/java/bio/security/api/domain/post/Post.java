package bio.security.api.domain.post;

import bio.security.api.domain.post.dto.CreatePostDto;
import bio.security.api.domain.user.enums.AccessLevel;
import bio.security.api.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Entity(name = "Post")
@Table(name = "posts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;
    @Column(name = "access_level")
    private AccessLevel accessLevel;
    @Setter
    private boolean status;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Post(CreatePostDto createPostDto, User autenticatedUser) {
        this.title = createPostDto.title();
        this.content = createPostDto.content();
        this.author = autenticatedUser.getUsername();
        this.accessLevel = createPostDto.accessLevel();
        this.user = autenticatedUser;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
