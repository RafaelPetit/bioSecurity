package bio.security.api.domain.user;

import bio.security.api.domain.user.dto.CreateUserDto;
import bio.security.api.domain.user.dto.ResponseCreateUserDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserDto createUserDto, UriComponentsBuilder uriBuilder) {
        {
            var encodedPassword = passwordEncoder.encode(createUserDto.password());

            var createdUser = repository.save(new User(createUserDto.biometricId(), createUserDto.username(), encodedPassword, createUserDto.role()));

            var uri = uriBuilder.path("/user/{id}").buildAndExpand(createdUser.getId()).toUri();

            return ResponseEntity.created(uri).body(new ResponseCreateUserDto(createdUser));
        }
    }
}
