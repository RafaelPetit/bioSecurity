package bio.security.api.domain.auth;

import bio.security.api.domain.auth.dto.AuthData;
import bio.security.api.domain.auth.dto.ResponseJwtTokenDto;
import bio.security.api.domain.auth.token.TokenService;
import bio.security.api.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthData data) {
        System.out.println("DATA QUE ESTA CHEGNADO" + data);
        var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(authToken);

        var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseJwtTokenDto(jwtToken));
    }

}
