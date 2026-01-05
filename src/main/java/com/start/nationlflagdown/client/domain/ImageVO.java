package com.start.nationlflagdown.client.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="image")
public class ImageVO {
	
	@Id
	private Long imageId;
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name="nationId")
	private NationVO nation;	//참조할 엔티티 객체를 필드로 사용(엔티티 필드라고 한다)
	private String fileName;
	private String originalFileName;
	
	
	protected ImageVO() {}
	
	public ImageVO(String fileName, String originalFileName) {
		this.fileName = fileName;
		this.originalFileName = originalFileName;
	}
	
	public Long getImageId() {
		return imageId;
	}
	
	public NationVO getNation() {
		return nation;
	}
	public void setNation(NationVO vo) {
		this.nation = vo;
		//contains(this) : nation.getImages()의 반환값이 ImageVO 인지 검사
		if (!nation.getImages().contains(this)) {
            nation.getImages().add(this);
        }
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String originalFileName) {
		this.fileName = originalFileName;
	}


	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	
	
}
