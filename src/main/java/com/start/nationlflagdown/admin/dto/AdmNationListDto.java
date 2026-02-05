package com.start.nationlflagdown.admin.dto;

import com.start.nationlflagdown.admin.domain.AdmImageVO;
import com.start.nationlflagdown.admin.domain.AdmNationVO;

//List
public class AdmNationListDto {
	
	private Long nationId;
	private String nationCode;
	private String nationNameKo;
	private String nationNameEn;
	private String capitarKo;
	private String capitarEn;
	private String continent;
	private String fileName;
	private String originalFileName;
	private String imgUrl;
	
	public AdmNationListDto() {}
	
	public AdmNationListDto(AdmNationVO nationVo, AdmImageVO imgVo) {
		this.setNationId(nationVo.getNationId());
		this.nationCode = nationVo.getNationCode();
		this.nationNameKo = nationVo.getNationNameKo();
		this.nationNameEn = nationVo.getNationNameEn();
		this.capitarKo = nationVo.getCapitarKo();
		this.capitarEn = nationVo.getCapitarEn();
		this.continent = nationVo.getContinent();
		this.fileName = imgVo.getFileName();
		this.originalFileName = imgVo.getOriginalFileName();
		this.imgUrl = "/upload/" + imgVo.getFileName();
	}
	
	public Long getNationId() {
		return nationId;
	}

	public void setNationId(Long nationId) {
		this.nationId = nationId;
	}

	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	public String getNationNameKo() {
		return nationNameKo;
	}
	public void setNationNameKo(String nationNameKo) {
		this.nationNameKo = nationNameKo;
	}
	public String getNationNameEn() {
		return nationNameEn;
	}
	public void setNationNameEn(String nationNameEn) {
		this.nationNameEn = nationNameEn;
	}
	public String getCapitarKo() {
		return capitarKo;
	}
	public void setCapitarKo(String capitarKo) {
		this.capitarKo = capitarKo;
	}
	public String getCapitarEn() {
		return capitarEn;
	}
	public void setCapitarEn(String capitarEn) {
		this.capitarEn = capitarEn;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

}
