package com.start.nationlflagdown.client.service;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import com.start.nationlflagdown.client.domain.ImageVO;
import com.start.nationlflagdown.client.domain.NationVO;
import com.start.nationlflagdown.client.dto.NationDto;
import com.start.nationlflagdown.client.dto.NationSearchCond;
import com.start.nationlflagdown.client.dto.NationViewDto;
import com.start.nationlflagdown.client.repository.ImageRepository;
import com.start.nationlflagdown.client.repository.NationRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service("NationService")
@Transactional
public class NationServiceImpl implements NationService{
	
	@Value("${file.upload.dir}")
	private String fileDir;
	
	private final ImageRepository imageRepository;
	private final NationRepository nationRepository;
	
	public NationServiceImpl(NationRepository nationRepository, ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
		this.nationRepository = nationRepository;
	}
	
	//상세보기
	@Override
	public NationViewDto ViewNation(Long nationId, String lang) {
		
		//lang이 null로 들어올 경우 처리
		String currentLang = (lang!=null) ? lang : "ko";
		
		NationVO nation = nationRepository.findById(nationId).orElseThrow(() -> new IllegalArgumentException("Nation ID(" + nationId + ")를 찾을 수 없습니다."));
		
		List<ImageVO> images = imageRepository.findByNationNationId(nationId);
		
		nation.increasetViewCnt();
		
		return new NationViewDto(nation, images, currentLang);
	}
	
	//이미지 다운로드
	@Override
	public ResponseEntity<Resource> Downloadfile(Long nationId, Long imageId) throws MalformedURLException{
		
		NationVO nation = nationRepository.findById(nationId).orElseThrow(() -> new IllegalArgumentException("Nation ID(" + nationId + ")를 찾을 수 없습니다."));
		
		//다운로드수 증가
		nation.increaseDownCnt();
		
		ImageVO image = imageRepository.findById(imageId).orElseThrow(() -> new IllegalArgumentException("Nation ID(" + nationId + ")를 찾을 수 없습니다."));
		
		Path path = Paths.get(fileDir +image.getFileName());
		
		Resource resource = new UrlResource(path.toUri());
		
		String encodedFileName = UriUtils.encode(image.getOriginalFileName(), StandardCharsets.UTF_8);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
                .body(resource);
		
	}

	//목록	
//	@Override
//	public List<NationDto> NationList(NationSearchCond cond, String lang) {
//		
//		//NationRepositoryImpl 에서 orderby() 로 정렬하여 주석 처리 함
//		//Sort sortNationList =Sort.by(Sort.Direction.ASC, "nationNameKo");
//		
//		List<NationVO> nation = nationRepository.search(cond);
//
//		
//		return nation.stream().map(nationVo -> {
//			
//			List<ImageVO> imgList = imageRepository.findByNationNationId(nationVo.getNationId());
//			
//			ImageVO firstImgVo = imgList.stream().findFirst().orElse(null);
//			
//			return new NationDto(nationVo, firstImgVo, lang);
//		}).collect(Collectors.toList());
//	}
	
	//목록
	//위 메서드에 페이징 기능을 추가하기 위해 수정함
	@Override
	public Page<NationDto> NationList(NationSearchCond cond, String lang, int page){
		
		Pageable pageable = PageRequest.of(page - 1, 10);
			
		Page<NationVO> nations;
		
		if(cond.getSearch() != null) {
			nations = nationRepository.search(cond, pageable);
		}else {
			nations = nationRepository.select(cond, pageable);
		}
		//Page 객체는 stream()의 기능을 자체적으로 내장
		return nations.map(nationVo -> {
			List<ImageVO> imgList = imageRepository.findByNationNationId(nationVo.getNationId());
			ImageVO firstImgVo = imgList.stream().findFirst().orElse(null);
			return new NationDto(nationVo, firstImgVo, lang);
		});
		
	}
	

}
