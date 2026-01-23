package com.start.nationlflagdown.admin.service;

import com.start.nationlflagdown.admin.dto.AdmLoginRequestDto;

public interface AdmMemberService {
	
	public boolean login(AdmLoginRequestDto loginRequest);

}
