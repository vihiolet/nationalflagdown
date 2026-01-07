package com.start.nationlflagdown.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.start.nationlflagdown.admin.domain.AdmNationVO;
import com.start.nationlflagdown.admin.dto.AdmSearchCond;

public interface AdmRepositoryCustom {
	
	Page<AdmNationVO> search(AdmSearchCond cond, Pageable pageable);

}
