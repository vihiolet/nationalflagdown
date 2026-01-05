package com.start.nationlflagdown.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	
	@Pointcut("execution(* com.start.nationlflagdowna.service.AdmNationServiceImpl.*(..))") 
	public void allPointcut() {}
	
	@AfterThrowing(pointcut = "allPointcut()", throwing = "exception")
	public void afterThrowingLog(JoinPoint js, Exception exception) {
		
		String method = js.getSignature().getName();
		
		Object[] args = js.getArgs(); //대상 메서드에 전달된 모든 인자를 배열로 가져옴
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++) {
				Object arg = args[i];
				System.out.println(arg.toString());
			}
		}
	}
	

}
