package it.pegasoft.usersecurity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import it.pegasoft.usersecurity.logging.LogExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Security API", version = "0.1", description = "Test API and JWT security"))
@SecurityScheme(name = "userservice", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class UsersecurityApplication {

	static final Logger logger = LogManager.getLogger(LogExample.class);
	public static void main(String[] args) {

		SpringApplication.run(UsersecurityApplication.class, args);
		logger.info("Application started");

	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
//	@Bean
/*	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "Max Power", "max", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Ricky Gervais", "ricky", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Turuzzu Alive", "turuzzu", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Bruce Willis", "bruce", "1234", new ArrayList<>()));

			userService.addRoleToUser("turuzzu", "ROLE_USER");
			userService.addRoleToUser("ricky", "ROLE_USER");
			userService.addRoleToUser("bruce", "ROLE_ADMIN");
			userService.addRoleToUser("max", "ROLE_ADMIN");

//			libroService.addLibro(new Libro(null, "Piccoli inutili nascondigli", "Giorgio Faletti", "giallo", 9.90));
		};
	}*/

//	@Bean
/*	CommandLineRunner run(LibroService libroService) {
		return args -> {
			libroService.saveLibro(new Libro(null, "Piccoli inutili nascondigli", "Giorgio Faletti", "giallo", 9.90));
			libroService.saveLibro(new Libro(null, "Appunti di un venditore di donne", "Giorgio Faletti", "giallo", 8.00));
			libroService.saveLibro(new Libro(null, "Io sono leggenda", "Richard Mateson", "fantasy", 6.00));
			libroService.saveLibro(new Libro(null, "Un karma pesante", "Daria Bignardi", "narrrativa", 7.40));
		};
	}
*/

}
