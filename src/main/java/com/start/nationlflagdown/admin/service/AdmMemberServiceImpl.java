package com.start.nationlflagdown.admin.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.start.nationlflagdown.admin.dto.AdmLoginRequestDto;
import com.start.nationlflagdown.admin.repository.AdmMemberRepository;

@Service
public class AdmMemberServiceImpl implements AdmMemberService{
	
	private final AdmMemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public AdmMemberServiceImpl(AdmMemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public boolean login(AdmLoginRequestDto loginRequest) {
		return memberRepository.findById(loginRequest.getId()).map(member -> {
			return passwordEncoder.matches(loginRequest.getPassword(), member.getPassword());
		}).orElse(false);
	}

}
