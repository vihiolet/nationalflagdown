package com.start.nationlflagdown.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.start.nationlflagdown.admin.dto.AdmNationImgsDto;
import com.start.nationlflagdown.admin.dto.AdmNationListDto;
import com.start.nationlflagdown.admin.dto.AdmSearchCond;
import com.start.nationlflagdown.admin.service.AdmNationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Controller
public class AdmNationController {
	
	@Autowired
	private AdmNationService nationService;
	
	//생성자 주입할 때는 nationService를 final로 선언하기
//	public AdmNationConroller(AdmNationService nationService) {
//		this.nationService = nationService;
//	}
	
	//글 등록 form
	@GetMapping("/insertNation")
	public String inertNationFormView() {
		return "admin/insertNation";
	}
	
	//글 등록
	@PostMapping("/insertNation")
	public String insertNation(
			@ModelAttribute AdmNationImgsDto form
			, @RequestParam("uploadFile") List<MultipartFile> file
			, RedirectAttributes redirectAttributes) throws IOException {
		
		//파일 검사
//		if(file.isEmpty() || file.getSize() == 0) {
//			redirectAttributes.addFlashAttribute("errorMessage", "국기 이미지를 첨부해야 합니다.");
//			return "error";
//		}
		
//		try {
//			Long newNationId = nationService.insertNation(form, file);
//			return "redirect:/insertNation.do" + newNationId;
//		}catch(Exception e) {
//			return "error";
//		}
		
		//Long newNationId = 
				nationService.insertNation(form, file);
		//redirectAttributes.addFlashAttribute(newNationId);
		return "redirect:adminNation";
		
	}
	
	//글 수정 form
	@GetMapping("/updateNation")
	public String updateNationFormView(@RequestParam("nationId") Long nationId, Model model) {
		
		AdmNationImgsDto nation = nationService.updateNationForm(nationId);
		
		List<Long> imageIds = nation.getImageIds();
		List<String> imgUrls = nation.getImgUrls();
		List<String> originalFileName = nation.getOriginalFileName();
		
		List<AdmNationImgsDto> imageGroup = new ArrayList<>();
		
		for(int i=0; i < imageIds.size(); i++) {
			
			AdmNationImgsDto imgtmp = new AdmNationImgsDto(imageIds.get(i), imgUrls.get(i), originalFileName.get(i));
	        imageGroup.add(imgtmp);
			
		}
		
		model.addAttribute("nation", nation);
		model.addAttribute("imageGroup", imageGroup);		
		
		return "admin/updateNation";
	}
	
	//글 수정
	@PostMapping("/updateNation")
	public String updateNation(
			@RequestParam("nationId") Long nationId,  
			@ModelAttribute AdmNationImgsDto form
			, @RequestParam(value = "uploadFile", required = false) MultipartFile file
			, RedirectAttributes redirectAttributes) throws IOException{
		
		//파일 유효성 검사 추후 추가
		nationService.updateNation(nationId, form, file);
		
		return "redirect:adminNation";
	}
	
	//글 수정 form에서 업로드 파일 삭제
	@PostMapping("/nationImgDel")
	public String nationImgDel(@RequestParam("imageId") Long nationId) {
		
		nationService.deleteNationImg(nationId);
		
		return "redirect:updateNation";
	}
	
	//글 목록
	@GetMapping("/adminNation")
	public String nationList(Model model,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@ModelAttribute("cond") AdmSearchCond cond) {
		
		Page<AdmNationListDto> admNationList = nationService.nationList(cond, page);
		
		model.addAttribute("nationList", admNationList);
		model.addAttribute("cond", cond);	//검색어 유지
		
		return "admin/listNation";
	}
	
	//글 삭제
	@GetMapping("/deleteNation")
	public String deleteNation(@RequestParam("nationId") Long nationId) {
		
		nationService.deleteNation(nationId);
		
		return "redirect:adminNation";
	}

}
