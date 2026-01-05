package com.start.nationlflagdown.admin.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.start.nationlflagdown.admin.dto.AdmNationImgsDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener; // <<-- 이 import 문이 필요합니다.
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="nation")
@EntityListeners(AuditingEntityListener.class)	//시간을 기록한다고 알리기 위해 엔티티 클래스에 리스너 등록. regDate 필드에 필요한 어노테이션
public class AdmNationVO {
	
	//JPA 요구사항: 기본 생성자 (필수)
	protected AdmNationVO() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nationId;
	@Column(unique = true)
	private String nationCode;
	private String nationNameKo;
	private String nationNameEn;
	private String capitarKo;
	private String capitarEn;
	private String continent;
	private int downCnt;
	private int viewCnt;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regDate;
	
	@OneToMany(mappedBy = "nation", cascade = CascadeType.ALL)
	private List<AdmImageVO> images = new ArrayList<>();
	
	//생성자의 AdmNationFormDto 인자로 초기화
	public AdmNationVO(AdmNationImgsDto dto) {
		this.nationCode = dto.getNationCode();
		this.nationNameKo = dto.getNationNameKo();
		this.nationNameEn = dto.getNationNameEn();
		this.capitarKo = dto.getCapitarKo();
		this.capitarEn = dto.getCapitarEn();
		this.continent = dto.getContinent();
	}
	
	public Long getNationId() {
		return nationId;
	}
	
	public String getNationCode() {
		return nationCode;
	}
	
	public String getNationNameKo() {
		return nationNameKo;
	}
	
	public String getNationNameEn() {
		return nationNameEn;
	}
	
	public String getCapitarKo() {
		return capitarKo;
	}
	
	public String getCapitarEn() {
		return capitarEn;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public int getDownCnt() {
		return downCnt;
	}
	
	//Setter대체 비즈니스 메서드 - admin에서는 없어도 되겠네
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
	public LocalDateTime getRegDate() {
		return regDate;
	}
	
	public List<AdmImageVO> getImages() {
		return images;
	}
	
	public void addImages(AdmImageVO image) {
		this.images.add(image);
		if (image.getNation() != this) {
            image.setNation(this); 
        }
		
	}
	
	//이미지 없는지 체크
	public boolean hashFile() {
		return !this.images.isEmpty();		
	}
	
	public void clearFile() {
		this.images.clear();
	}
	
//	DTO에서 생성자 만드는 걸로 변경	
//	//VO -> DTO 변환 메서드 
//	public static AdmNationDto toDto(AdmNationVO vo) {
//		return new AdmNationDto(
//				vo.nationId, vo.nationCode, vo.nationNameKo, vo.nationNameEn, vo.capitar, vo.continent);
//	}
	
	//글 등록 메서드
	//DTO 변환 메서드 : 이 메서드로 service 단계에서 dto 객체에 있는 값을 AdmNationVO 객체에 바인딩한다.
	public static AdmNationVO createNation(AdmNationImgsDto dto) {
		//객체가 생성되면서 AdmNationFormDto가 인자인 생성자로인해 초기화 된다.
        return new AdmNationVO(dto);
    }
	
	//글 수정 메서드
	public void updateNation(AdmNationImgsDto dto) {
		this.nationCode = dto.getNationCode();
		this.nationNameKo = dto.getNationNameKo();
		this.nationNameEn = dto.getNationNameEn();
		this.capitarKo = dto.getCapitarKo();
		this.capitarEn = dto.getCapitarEn();
		this.continent = dto.getContinent();
		
		
	}
	

}
