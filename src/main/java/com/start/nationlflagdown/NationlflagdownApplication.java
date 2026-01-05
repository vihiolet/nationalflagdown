package com.start.nationlflagdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	//JAP Auditing 기능 활성화
public class NationlflagdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationlflagdownApplication.class, args);
	}

}
