package com.start.nationlflagdown.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.start.nationlflagdown.admin.interceptor.LoginCheckInterceptor;

@Configuration
public class AdmWebConfig implements WebMvcConfigurer{
	
	//업로드 이미지 폴더 경로
	@Value("${file.upload.dir}")
    private String uploadePath;
	
	//리소스 폴더(logo) 경로
	@Value("${file.images.dir}")
	private String resourcePath;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//요청 url 패턴 지정(가상 경로)
		String handler = "/upload/**"; 		
		//물리적 위치 지정(실제 경로)
		String loation = "file:///" + uploadePath;
		registry.addResourceHandler(handler)
		.addResourceLocations(loation); 
				
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + resourcePath);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
		.order(1)
		.addPathPatterns("/**")
		.excludePathPatterns("/", "/login", "/css/**", "/*.ico", "/error", "/nation", "/viewNation", "/images/**", "/upload/**");
	}

}
