package com.start.nationlflagdown.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.nationlflagdown.admin.domain.AdmMemberVO;

public interface AdmMemberRepository extends JpaRepository<AdmMemberVO, Long>{
	
	Optional<AdmMemberVO> findById(String id);

}
