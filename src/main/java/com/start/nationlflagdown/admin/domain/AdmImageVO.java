package com.start.nationlflagdown.admin.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="image")
public class AdmImageVO {
	
	//JPA 요구사항: 기본 생성자 (필수)
	protected AdmImageVO() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;
	@ManyToOne(fetch = FetchType.LAZY)	//N:1 관계 설정
	@JoinColumn(name="nationId")	//객체가 db의 nationId와 연결된다고 명시
	private AdmNationVO nation;	//참조할 엔티티 객체를 필드로 사용(엔티티 필드라고 한다)
	private String fileName;
	private String originalFileName;
	
	/*
	 * @Column : 필드를 테이블의 컬럼에 매핑시켜주는 어노테이션 옵션으로 unique = true가 있다.
	 * @JoinColumn : 테이블간 연관관계를 설정할 때 필드를 테이블의 컬럼에 매핑시켜주는 어노테이션 @OneToMany 어노테이션의 mappedBy과 동일한 필드를 만들고 해당 필드에 컬럼명 명시
	 * */
	 
	
	//생성자 파일 이름 인자로 초기화
	public AdmImageVO(String fileName, String originalFileName) {
		this.fileName = fileName;
		this.originalFileName = originalFileName;
	}
	
	public Long getImageId() {
		return imageId;
	}
	
	public AdmNationVO getNation() {
		return nation;
	}
	public void setNation(AdmNationVO nation) {
		this.nation = nation;
		//contains(this) : nation.getImages()의 반환값이 AdmImageVO 인지 검사
		if (!nation.getImages().contains(this)) {
            nation.getImages().add(this);
        }
	}
	public String getFileName() {
		return fileName;
	}
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	

}
