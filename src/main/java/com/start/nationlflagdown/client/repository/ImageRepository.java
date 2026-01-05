package com.start.nationlflagdown.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.nationlflagdown.client.domain.ImageVO;

public interface ImageRepository extends JpaRepository<ImageVO, Long>{
	
	List<ImageVO> findByNationNationId(Long nationId);
}
