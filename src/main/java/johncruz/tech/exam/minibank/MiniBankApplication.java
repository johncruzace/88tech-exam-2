package johncruz.tech.exam.minibank;

import johncruz.tech.exam.minibank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniBankApplication {

	public static void main(String[] args) {

		SpringApplication.run(MiniBankApplication.class, args);
	}

}
