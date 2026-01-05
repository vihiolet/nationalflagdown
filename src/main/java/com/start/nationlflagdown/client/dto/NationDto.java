package com.start.nationlflagdown.client.dto;

import com.start.nationlflagdown.client.domain.ImageVO;
import com.start.nationlflagdown.client.domain.NationVO;

public class NationDto {
	private Long nationId;
	private String nationCode;
	private String nationName;
	private String capitar;
	private String continent;
	private int downCnt;
	private int viewCnt;
	private String fileName;
	private String originalFileName;
	private String imgUrl;
	
	public NationDto() {};
	
	public NationDto(NationVO nationVo, ImageVO imgVo, String lang) {
		this.nationId = nationVo.getNationId();
		this.nationCode = nationVo.getNationCode();
		this.nationName = switch(lang) {
			case "ko" -> nationVo.getNationNameKo();
			case "en" -> nationVo.getNationNameEn();
			default -> nationVo.getNationNameKo();
		};
		this.capitar = switch(lang) {
			case "ko" -> nationVo.getCapitarKo();
			case "en" -> nationVo.getCapitarEn();
			default -> nationVo.getCapitarKo();
		};
		this.continent = nationVo.getContinent();
		this.downCnt = nationVo.getDownCnt();
		this.viewCnt = nationVo.getViewCnt();
		this.fileName = imgVo.getFileName();
		this.imgUrl = "/images/" + imgVo.getFileName();
	};
	
	public Long getNationId() {
		return nationId;
	}
	
	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationNameKo(String nationName) {
		this.nationName = nationName;
	}
	public String getCapitar() {
		return capitar;
	}
	public void setCapitar(String capitar) {
		this.capitar = capitar;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getDownCnt() {
		return downCnt;
	}

	//setDownCnt는 엔티티의 increaseDownCnt가 있으므로 없어도 무방

	public int getViewCnt() {
		return viewCnt;
	}

	//setViewCnt는 엔티티의 increasetViewCnt가 있으므로 없어도 무방

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
