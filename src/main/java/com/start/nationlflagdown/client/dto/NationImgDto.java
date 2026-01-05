package com.start.nationlflagdown.client.dto;


public class NationImgDto {
	
	private Long nationId;
	private Long imageId;
	private String imgUrl;
	private String fileName;
	private int width;
	private int height;
	private String size;
	
	public NationImgDto(Long nationId, Long imageId, String imgUrl, String originalFileName) { 
		this.nationId = nationId;
		this.imageId = imageId;
		this.imgUrl = imgUrl;
		this.fileName = originalFileName;
	}
	
	public Long getNationId() {
		return nationId;
	}
	
	public Long getImageId() {
		return imageId;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getfileName() {
		return fileName;
	}

	public void setOriginalFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHight() {
		return height;
	}

	public void setHeight(int hight) {
		this.height = hight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
