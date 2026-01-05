package com.start.nationlflagdown.client.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {
	
	@Bean
	public MessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("utf-8");
		messageSource.setFallbackToSystemLocale(false); // 시스템 로케일 대신 지정된 DefaultLocale을 사용
		messageSource.setCacheSeconds(10); // 10초마다 변경 사항 확인
		
		return messageSource;
		
	}

}
