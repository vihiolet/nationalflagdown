package com.start.nationlflagdown.admin.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.start.nationlflagdown.admin.domain.AdmImageVO;
import com.start.nationlflagdown.admin.domain.AdmNationVO;
import com.start.nationlflagdown.admin.dto.AdmNationImgsDto;
import com.start.nationlflagdown.admin.dto.AdmNationImgsDto.ImageTypeDTO;
import com.start.nationlflagdown.admin.dto.AdmNationListDto;
import com.start.nationlflagdown.admin.dto.AdmSearchCond;
import com.start.nationlflagdown.admin.repository.AdmNationRepository;
import com.start.nationlflagdown.admin.repository.AdmimageRepository;

import org.springframework.transaction.annotation.Transactional;

@Service("admNationService")
@Transactional
public class AdmNationServiceImpl implements AdmNationService{
	
	@Value("${file.upload.dir}")
    private String fileDir;
	
	private final AdmimageRepository imageRepository;
	private final AdmNationRepository nationRepository;
	
	public AdmNationServiceImpl(AdmimageRepository imageRepository, AdmNationRepository nationRepository) {
		this.imageRepository = imageRepository;
		this.nationRepository = nationRepository;
	}

	//글 등록 기능
	@Override
	public Long insertNation(AdmNationImgsDto form, List<MultipartFile> uploadFiles) throws IOException {
		
		AdmNationVO nation = AdmNationVO.createNation(form);
		
		File uploadDir = new File(fileDir);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		int fileIdx = 0;
		List<String> sortedKeys = form.getTypeList().keySet().stream()
				.sorted()
				.toList();
		
		for(String key : sortedKeys) {
			MultipartFile file = uploadFiles.get(fileIdx++);
			
			if(!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
				
				AdmImageVO image = new AdmImageVO(uniqueFileName, originalFileName);
				
				String imageType = form.getTypeList().get(key).getImageType();
				image.setImageType(imageType);
				
				nation.addImages(image);
				
				try {
					file.transferTo(new File(fileDir + uniqueFileName));
				}catch(IOException e){
					throw new IOException("파일 저장 실패: " + e.getMessage(), e);
				}
			}
		}
		
		//부모만 저장 (CascadeType.ALL에 의해 이미지들도 한꺼번에 insert됨) cascade 설정이 되어있으므로 나중에 한 번만 해도 
	    AdmNationVO saveNation = nationRepository.save(nation);
		return saveNation.getNationId();
	}
	
	@Override
	@Transactional(readOnly = true)
	//글 수정 form
	public AdmNationImgsDto updateNationForm(Long nationId) {
		
		AdmNationVO nation = nationRepository.findById(nationId)
				.orElseThrow(() -> new IllegalArgumentException("Nation ID(" + nationId + ")를 찾을 수 없습니다."));
		
		List<AdmImageVO> images = imageRepository.findByNationNationId(nationId);
		
		return new AdmNationImgsDto(nation, images);
	}
	
	//글 수정 기능
	@Override
	public void updateNation(Long nationId, AdmNationImgsDto form, List<MultipartFile> uploadFiles) throws IOException {
		
		//id로 엔티티를 검색(영속 상태로 가져옴)
		AdmNationVO nation = nationRepository.findById(nationId)
				.orElseThrow(() -> new IllegalArgumentException("Nation ID(" + nationId + ")를 찾을 수 없습니다."));
		//findById(Long Id)는 Optional<T> 타입으로 반환하는데 nation은 AdmNationVO 타입이 orElseThrow()를 사용하여 객체 추출
		
		//영속 상태이므로 save() 메서드 불필요
		nation.updateNation(form);		
		
		if (nation.getImages() != null && form.getTypeList() != null) {
	        for (AdmImageVO image : nation.getImages()) {
	            String key = String.valueOf(image.getImageId());
	            
	            if (form.getTypeList().containsKey(key)) {
	                ImageTypeDTO typeDto = form.getTypeList().get(key);
	                // 영속 상태인 엔티티의 값을 변경
	                image.setImageType(typeDto.getImageType());
	            }
	        }
	    }
		
		int fileIdx = 0;
		List<String> sortedKeys = form.getTypeList().keySet().stream()
				.filter(k -> k.startsWith("new_"))
				.sorted()
				.toList();
		
		for(String key : sortedKeys) {
			MultipartFile file = uploadFiles.get(fileIdx++);
			
			if(!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
				
				AdmImageVO image = new AdmImageVO(uniqueFileName, originalFileName);
				
				String imageType = form.getTypeList().get(key).getImageType();
				image.setImageType(imageType);
				
				nation.addImages(image);
				
				try {
					file.transferTo(new File(fileDir + uniqueFileName));
				}catch(IOException e){
					throw new IOException("파일 저장 실패: " + e.getMessage(), e);
				}
			}
		}
		
//		if(file != null && !file.isEmpty()) {		//새로운 파일을 업로드 한 경우
//			
//			String originalFileName = file.getOriginalFilename();
//			String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
//			
//			AdmImageVO image = new AdmImageVO(uniqueFileName, originalFileName);
//			image.setNation(nation);
//			imageRepository.save(image);
//			
//			try {
//				file.transferTo(new File(fileDir + uniqueFileName));
//			}catch(IOException e){
//				throw new IOException("파일 저장 실패: " + e.getMessage(), e);
//			}
//		}else if(imageIds != null && !imageIds.isEmpty()) {		//기존에 업로드된 파일이 있을 경우
//			
//		}else {	//새 파일 없고 기존 파일도 없을 경우
//			//파일 없는지 검사하고
//			if(nation.hashFile()) {
//				//엔티티 비우기
//				nation.clearFile();
//			}
//		}				
	}
	
	//수정 form에서 이미지 삭제
	@Override
	public void deleteNationImg(Long imageId) {
		imageRepository.deleteById(imageId);
		
	}
	
	//글 목록
	//아래 NationList() 메서드 사용하므로 해당 메서드 사용하지 않음
	@Override
	public List<AdmNationListDto> selectNation() {
		
		Sort sortNationList = Sort.by(Sort.Direction.DESC, "regDate");
		
		List<AdmNationVO> nation = nationRepository.findAll(sortNationList);
		
		return nation.stream().map(nationVo -> {
            List<AdmImageVO> imageList = imageRepository.findByNationNationId(nationVo.getNationId());
            
            //리스트에서 대표 이미지 (첫 번째 요소)를 선택
            AdmImageVO firstImageVo = imageList.stream().findFirst().orElse(null); // 이미지가 없으면 null
                                                
            //DTO 생성자에 AdmNationVO와 AdmImageVO 두 개를 전달
            return new AdmNationListDto(nationVo, firstImageVo); 
        }).collect(Collectors.toList());
		
		/*
		 * .stream() : List<AdmNationVO> -> List<NationDTO> 변환
		 * .map(AdmNationDTO::new) : 
		 * .collect(Collectors.toList()) : 최종적으로 List<AdmNationDto> 반환
		 * */
	}
	
	//글 목록
	//위 selectNation() 메서드에서 페이징 기능 추가
	@Override
	@Transactional(readOnly = true)
	public Page<AdmNationListDto> nationList(AdmSearchCond cond, int page){
		
		Pageable pageable = PageRequest.of(page - 1, 10);
		
		Page<AdmNationVO> nations;
		nations = nationRepository.search(cond, pageable);
		
		return nations.map(admNationVo -> {
			//N+1 이슈 제거
			//List<AdmImageVO> imgList = imageRepository.findByNationNationId(admNationVo.getNationId());
			//AdmImageVO firstImgVo = imgList.stream().findFirst().orElse(null);
			AdmImageVO firstImgVo = admNationVo.getImages().stream().findFirst().orElse(null);
			return new AdmNationListDto(admNationVo, firstImgVo);
		});		
	}
	
	//글 삭제
	@Override
	public void deleteNation(Long nationId) {
		if(!nationRepository.existsById(nationId)) {
			throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND, 
	                "Nation ID(" + nationId + ")를 찾을 수 없어 삭제할 수 없습니다."
	            );
		}
		
		nationRepository.deleteById(nationId);	
	}


}
