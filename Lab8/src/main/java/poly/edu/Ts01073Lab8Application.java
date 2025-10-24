package poly.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"poly.edu.controller", "poly.edu.impl", "poly.edu.dao"})
public class Ts01073Lab8Application {

	public static void main(String[] args) {
		SpringApplication.run(Ts01073Lab8Application.class, args);
	}

}
