package com.start.nationlflagdown.client.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.start.nationlflagdown.client.dto.NationDto;
import com.start.nationlflagdown.client.dto.NationSearchCond;
import com.start.nationlflagdown.client.dto.NationViewDto;

import org.springframework.data.domain.Page;

public interface NationService {
	
	//목록
	Page<NationDto> NationList(NationSearchCond cond, String lang, int page);
	
	//상세보기
	NationViewDto ViewNation(Long nationId, String lang);

	ResponseEntity<Resource> Downloadfile(Long nationId, Long imageId) throws MalformedURLException;
}
