package com.start.nationlflagdown.common;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public String handleArithmeticException(Exception e, Model model) {
		//산술 연산에서 유효하지 않은 오류가 발생했을 때 발생하는 예외 예)정수를 0으로 나누는 경우
		model.addAttribute("errorMessage", "ArithmeticException 에러 발생" + e.getMessage());
		return "error/error";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(Exception e, Model model) {
		model.addAttribute("errorMessage", "NullPointerException 에러 발생" + e.getMessage());
		return "error/error";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("errorMessage", "Exception 에러 발생" + e.getMessage());
		return "error/error";
	}

}
