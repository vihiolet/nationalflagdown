package com.start.nationlflagdown.client.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//Locale 정보를 추출하여 언어의 메세지 적용
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver localResolver = new SessionLocaleResolver();
		localResolver.setDefaultLocale(Locale.KOREA);
		
		return localResolver;

	}
	
	//lang 파라미터로 Locale이 전송되면 해당 Locale로 변경
	@Bean 
	public LocaleChangeInterceptor localeChangeInterceptor() {
		
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		
		//Locale 변경을 트리거할 요청 파라미터 명 설정 (예. /url?lang=ko)
		interceptor.setParamName("lang");

		return interceptor;
		
	}
	
	// MessageConfig에서 정의한 MessageSource와 연동하기 위해 이 인터셉터가 필요
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(localeChangeInterceptor());
	}

}
