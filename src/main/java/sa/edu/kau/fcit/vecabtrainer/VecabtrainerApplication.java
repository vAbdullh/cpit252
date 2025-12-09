package sa.edu.kau.fcit.vecabtrainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sa.edu.kau.fcit.vecabtrainer.config.Firebase;

@SpringBootApplication
public class VecabtrainerApplication {

	public static void main(String[] args) {
        Firebase.init();
		SpringApplication.run(VecabtrainerApplication.class, args);
	}

}
