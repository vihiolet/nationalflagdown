package com.start.nationlflagdown.admin.dto;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.start.nationlflagdown.admin.domain.AdmImageVO;
import com.start.nationlflagdown.admin.domain.AdmNationVO;

//View
public class AdmNationImgsDto {
	
	private Long nationId;
	private String nationCode;
	private String nationNameKo;
	private String nationNameEn;
	private String capitarKo;
	private String capitarEn;
	private String continent;
	private List<Long> imageIds;
	private Long imageId;
	private List<String> fileName;
	private List<String> originalFileName;
	private String viewFileName;
	
	private List<String> imgUrls = new ArrayList<>();
	private String imgUrl;
	
	private Map<String, ImageTypeDTO> typeList = new LinkedHashMap<>();

	public AdmNationImgsDto() {}
	
	//데이터 채울 때 쓰는 생성자
	public AdmNationImgsDto(AdmNationVO nationVo, List<AdmImageVO> imgList) {
		this.setNationId(nationVo.getNationId());
		this.nationCode = nationVo.getNationCode();
		this.nationNameKo = nationVo.getNationNameKo();
		this.nationNameEn = nationVo.getNationNameEn();
		this.capitarKo = nationVo.getCapitarKo();
		this.capitarEn = nationVo.getCapitarEn();
		this.continent = nationVo.getContinent();
		this.imageIds = imgList.stream().map(AdmImageVO::getImageId).collect(Collectors.toList());
		this.fileName = imgList.stream().map(AdmImageVO::getFileName).collect(Collectors.toList());
		this.originalFileName = imgList.stream().map(AdmImageVO::getOriginalFileName).collect(Collectors.toList());
		this.imgUrls = imgList.stream().map(img -> "/upload/" + img.getFileName()).collect(Collectors.toList());
		this.imgUrl = "/upload/" + this.getFileName();
		this.typeList = imgList.stream().collect(Collectors.toMap(
				img -> String.valueOf(img.getImageId()),
				img -> {
					ImageTypeDTO dto = new ImageTypeDTO();
					dto.setImageType(img.getImageType());
			        return dto;
				}));
	}
	
	public AdmNationImgsDto(Long imageId, String imgUrl, String originalFileName, ImageTypeDTO typeDto){
		this.setImageId(imageId); 
		this.setImgUrl(imgUrl);
		this.setViewFileName(originalFileName);
		this.typeList = new LinkedHashMap<>(); 
	    if (typeDto != null) {
	        this.typeList.put(String.valueOf(imageId), typeDto);
	    }
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

	public List<Long> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getViewFileName() {
		return viewFileName;
	}

	public void setViewFileName(String viewFileName) {
		this.viewFileName = viewFileName;
	}
	
	public Map<String, ImageTypeDTO> getTypeList() {
		return typeList;
	}

	public void setTypeList(Map<String, ImageTypeDTO> typeList) {
		this.typeList = typeList;
	}
	
	//--- 내부 정적 클래스 ---
	public static class ImageTypeDTO{
		private String imageType; // 라디오 버튼으로 입력받을 값
		private MultipartFile uploadFile;

		public String getImageType() {
			return imageType;
		}

		public void setImageType(String imageType) {
			this.imageType = imageType;
		}
		
		public MultipartFile getUploadFile() {
		    return uploadFile;
		}
		public void setUploadFile(MultipartFile uploadFile) {
		    this.uploadFile = uploadFile;
		}
	}

}
