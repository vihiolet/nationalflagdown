package com.start.nationlflagdown.client.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nation")
public class NationVO {
	
	@Id
	private Long nationId;
	private String nationCode;
	private String nationNameKo;
	private String nationNameEn;
	private String capitarKo;
	private String capitarEn;
	private String continent;
	private int downCnt;
	private int viewCnt;
	@OneToMany(mappedBy = "nation", cascade = CascadeType.ALL)
	private List<ImageVO> images = new ArrayList<>();
	
	public NationVO() {}

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

	public int getDownCnt() {
		return downCnt;
	}
	
	//Setter대체 비즈니스 메서드
	public void increaseDownCnt() {
		downCnt++;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	//Setter대체 비즈니스 메서드
	public void increasetViewCnt() {
		viewCnt++;
	}

	public List<ImageVO> getImages() {
		return images;
	}

	public void setImages(List<ImageVO> images) {
		this.images = images;
	}
}
