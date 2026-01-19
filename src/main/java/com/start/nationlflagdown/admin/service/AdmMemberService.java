package com.start.nationlflagdown.admin.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.start.nationlflagdown.admin.dto.AdmLoginRequestDto;
import com.start.nationlflagdown.admin.repository.AdmMemberRepository;

public class AdmMemberService {
	
	private final AdmMemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public AdmMemberService(AdmMemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean login(AdmLoginRequestDto loginRequest) {
		return memberRepository.findById(loginRequest.getId()).map(member -> {
			return passwordEncoder.matches(loginRequest.getPassword(), member.getPassword());
		}).orElse(false);
	}

}
