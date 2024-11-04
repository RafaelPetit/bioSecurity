package bio.security.api.domain.post;

import bio.security.api.domain.user.AccessLevel;
import bio.security.api.domain.user.User;
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
    private AccessLevel accessLevel;
    @Setter
    private boolean status;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Post(@NotBlank String title, @NotBlank String content, User autenticatedUser) {
        this.title = title;
        this.content = content;
        this.author = autenticatedUser.getUsername();
        this.accessLevel = autenticatedUser.getAccessLevel();
    }

}
