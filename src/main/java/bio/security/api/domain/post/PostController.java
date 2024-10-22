package bio.security.api.domain.post;

import bio.security.api.domain.post.dto.CreatePostDto;
import bio.security.api.domain.post.dto.ResponseCreatePostDto;
import bio.security.api.domain.user.AccessLevel;
import bio.security.api.domain.user.User;
import bio.security.api.infra.exception.ValidationException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository repository;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreatePostDto createPostDto, UriComponentsBuilder uriBuilder, Authentication authentication) {

        User autenticatedUser = (User) authentication.getPrincipal();

        var createdPost = repository.save(new Post(createPostDto.title(), createPostDto.content(), autenticatedUser));

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(createdPost.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponseCreatePostDto(createdPost));
    }

    @GetMapping
    public ResponseEntity<?> getAllByAccesLevel(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        User autenticatedUser = (User) authentication.getPrincipal();

        AccessLevel accessLevel = autenticatedUser.getAccessLevel();

        var page = repository.findAllByAccessLevel(accessLevel, pageable)
                .map(ResponseCreatePostDto::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var post = repository.findById(id).orElseThrow(() -> new ValidationException("Post not found with id " + id));
        if (post.isStatus()) {
            post.setStatus(false);
            repository.save(post);
        }
        return ResponseEntity.noContent().build();
    }
}
