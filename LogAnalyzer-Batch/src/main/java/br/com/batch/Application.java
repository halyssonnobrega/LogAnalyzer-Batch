package br.com.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.batch.configuration.LogParameterParse;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		LogParameterParse ParameterParse = new LogParameterParse();
		ParameterParse.setSystemParameters(args);
		
		SpringApplication.run(Application.class, args);
	}

}
