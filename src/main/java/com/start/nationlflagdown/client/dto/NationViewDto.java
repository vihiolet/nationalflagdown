package com.start.nationlflagdown.client.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.start.nationlflagdown.client.domain.ImageVO;
import com.start.nationlflagdown.client.domain.NationVO;

public class NationViewDto {
	
	private Long nationId;
	private String nationCode;
	private String nationName;
	private String capitar;
	private String continent;
	private List<Long> imageIds;
	private List<String> fileName;
	private List<String> originalFileName;
	private List<String> imgUrls = new ArrayList<>();
	private int viewCnt;
	private int downCnt;
	
	private List<String> typeList = new ArrayList<>();
	
	public NationViewDto() {}
	
	public NationViewDto(NationVO nationVo, List<ImageVO> imgList, String lang) {
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
		this.imageIds = imgList.stream().map(ImageVO::getImageId).collect(Collectors.toList());
		this.fileName = imgList.stream().map(ImageVO::getFileName).collect(Collectors.toList());
		this.originalFileName = imgList.stream().map(ImageVO::getOriginalFileName).collect(Collectors.toList());
		this.imgUrls = imgList.stream().map(img -> "/images/" + img.getFileName()).collect(Collectors.toList());
		this.viewCnt = nationVo.getViewCnt();
		this.downCnt = nationVo.getDownCnt(); 
		this.typeList = imgList.stream().map(ImageVO::getImageType).collect(Collectors.toList());
	}
	
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
	public List<Long> getImageIds() {
		return imageIds;
	}
	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(List<String> originalFileName) {
		this.originalFileName = originalFileName;
	}

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public int getDownCnt() {
		return downCnt;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setImageType(List<String> typeList) {
		this.typeList = typeList;
	}
	
}
