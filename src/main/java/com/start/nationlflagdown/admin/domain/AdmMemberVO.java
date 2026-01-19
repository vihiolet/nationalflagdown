package com.start.nationlflagdown.admin.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@EntityListeners(AuditingEntityListener.class)

@Entity
public class AdmMemberVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginId;
	@Column(unique = true)
	private String id;
	private String password;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regDate;
	
	public AdmMemberVO() {}
	
	public AdmMemberVO(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	
}
