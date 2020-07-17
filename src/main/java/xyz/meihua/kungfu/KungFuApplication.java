package xyz.meihua.kungfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author meihua
 */
@SpringBootApplication
@EnableOpenApi
public class KungFuApplication {

	public static void main(String[] args) {
		SpringApplication.run(KungFuApplication.class, args);
	}
}
