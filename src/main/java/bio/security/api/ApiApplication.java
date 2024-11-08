package bio.security.api;

import bio.security.api.domain.user.enums.AccessLevel;
import bio.security.api.domain.user.User;
import bio.security.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepositoty;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if(userRepositoty.count() == 0) {
			String password = passwordEncoder.encode("admin");
			User admin = new User(1, "admin", password, AccessLevel.ADMIN);
			userRepositoty.save(admin);
		}
	}
}
