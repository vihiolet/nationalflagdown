package com.start.nationlflagdown.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.start.nationlflagdown.admin.dto.AdmLoginRequestDto;
import com.start.nationlflagdown.admin.service.AdmMemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AdmMemberController {
	
	private final AdmMemberService memberServiice;
	
	public AdmMemberController(AdmMemberService memberServiice) {
		this.memberServiice = memberServiice;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AdmLoginRequestDto loginRequest, HttpServletRequest request){
		
		boolean loginResult = memberServiice.login(loginRequest);
		
		if(loginResult) {
			//로그인 성공 시 세선 생성
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginRequest.getId());
			
			return ResponseEntity.ok("로그인 성공!");
		}else {
			return ResponseEntity.status(401).body("아이디 또는 비밀번호가 틀렸습니다.");
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		return ResponseEntity.ok("로그아웃 성공");
	}

}
