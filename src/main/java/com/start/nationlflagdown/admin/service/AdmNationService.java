package com.start.nationlflagdown.admin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import com.start.nationlflagdown.admin.dto.AdmNationImgsDto;
import com.start.nationlflagdown.admin.dto.AdmNationListDto;

public interface AdmNationService {
	
	Long insertNation(AdmNationImgsDto form, List<MultipartFile> file) throws IOException;
	
	void updateNation(Long nationId, AdmNationImgsDto form,  MultipartFile file) throws IOException;
	
	List<AdmNationListDto> selectNation();
	
	AdmNationImgsDto updateNationForm(Long nationId);
	
	void deleteNation(Long nationId);
	
	void deleteNationImg(Long imageId);

}
