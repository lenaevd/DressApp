package Dress.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DressAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DressAppApplication.class, args);
	}

}
