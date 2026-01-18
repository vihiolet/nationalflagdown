package com.start.nationlflagdown.client.controller;

import java.util.List;

import javax.imageio.ImageIO;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.start.nationlflagdown.client.dto.NationDto;
import com.start.nationlflagdown.client.dto.NationViewDto;
import com.start.nationlflagdown.client.dto.NationImgDto;
import com.start.nationlflagdown.client.dto.NationSearchCond;
import com.start.nationlflagdown.client.service.NationService;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

@Controller
public class NationController {
	
	@Autowired
	private NationService nationService;
	
	@Value("${file.upload.dir}")
    private String resourcePath;
	
	//목록
	@GetMapping("/nation")
	public String listNation(Model model, @RequestParam(value = "lang", required = false) String lang,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@ModelAttribute("cond") NationSearchCond cond) {
		
		if(lang == null) lang = "ko";
		
		String continent = cond.getContinent();
		if(continent == null) {
			continent = "All";
			cond.setContinent("All");
		}
		
		//db에 한글로 들어가있어서 영어로 변환 
		switch(continent) {
			case "Asia":
				cond.setContinent("아시아");
				break;
			case "Africa":
				cond.setContinent("아프리카");
				break;
			case "Europe":
				cond.setContinent("유럽");
				break;
			case "North America":
				cond.setContinent("북아메리카");
				break;
			case "South America":
				cond.setContinent("남아메리카");
				break;
			case "Oceania":
				cond.setContinent("오세아니아");
				break;
		}
		
		Page<NationDto> nationList = nationService.NationList(cond, lang, page);
		
		model.addAttribute("nationList", nationList);
		model.addAttribute("cond", cond);	//검색어 유지
		
		return "client/listNation";
	}
	
	//상세 보기
	@GetMapping("/viewNation")
	public String viewNation(@RequestParam("nationId") Long nationId, 
			@RequestParam(value = "lang", required = false) String lang, Model model) {
		
		NationViewDto nation = nationService.ViewNation(nationId, lang);
		
		String continent = nation.getContinent();
		
		if(lang.equals("en")) {
			switch(continent) {
				case "아시아":
					nation.setContinent("Asia");
					break;
				case "아프리카":
					nation.setContinent("Africa");
					break;
				case "유럽":
					nation.setContinent("Europe");
					break;
				case "북아메리카":
					nation.setContinent("North America");
					break;
				case "남아메리카":
					nation.setContinent("South America");
					break;
				case "오세아니아":
					nation.setContinent("Oceania");
					break;	
			}
		}
		
		List<Long> imageIds = nation.getImageIds();
		List<String> imgUrls = nation.getImgUrls();
		List<String> originalfileNames = nation.getOriginalFileName();
		List<String> fileNames = nation.getFileName();
		List<String> imageTypes = nation.getTypeList();
		
		List<NationImgDto> imageGroup = new ArrayList<>();
		
		for(int i = 0; i < imageIds.size(); i++) {
			
			String imageType = "";
			
			if(lang.equals("ko") || lang.isEmpty()) {
				switch(imageTypes.get(i)) {
				case "ORIGIN":
					imageType = "원본";
					break;
				case "CIRCLE":
					imageType = "원형";
					break;
				case "SQUARE":
					imageType = "사각형";
					break;
				}
			}else {
				imageType = imageTypes.get(i);
			}
			
			
			NationImgDto imgtmp = new NationImgDto(nationId, imageIds.get(i), imgUrls.get(i), originalfileNames.get(i), imageType);
			
			//File 에서 파일 경로는 물리적 경로를 가져와야 한다.
			String filePath = resourcePath + fileNames.get(i);
			File file = new File(filePath);
			
			try {	
				//이미지 파일을 읽어 BufferedImage 객체로 변환
				BufferedImage image = ImageIO.read(file);
				
				//이미지의 가로, 세로(해상도) 추출
				int width = image.getWidth();
				int height = image.getHeight();
				
				//NationImgDto에 가로, 세로 값 세팅
				imgtmp.setWidth(width);
				imgtmp.setHeight(height);
				
			} catch (IOException e) {
				System.out.println(file.getAbsolutePath());
				e.printStackTrace();
			}
			
			//이미지 파일 용량 추출
			long fileSizeBytes = file.length();
			double fileSizeInKB = fileSizeBytes / 1024.0;
			
			//용량 소수점 1자리까지 
			String formattedSize = String.format("%.1f", fileSizeInKB);
			
			imgtmp.setSize(formattedSize);
			
			imageGroup.add(imgtmp);
		}
		
		model.addAttribute("nation", nation);
		model.addAttribute("imageGroup", imageGroup);
		
		return "client/viewNation";
	}
	
	//다운로드
	@GetMapping("/viewNationDown/{nationId}/{imageId}")
	public ResponseEntity<Resource> viewNationDown(@PathVariable Long nationId, @PathVariable Long imageId) throws MalformedURLException {
		
		return nationService.Downloadfile(nationId, imageId);
		
	}

}
