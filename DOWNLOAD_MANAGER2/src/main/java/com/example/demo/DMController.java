package com.example.demo;





import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class DMController {
	
	@Autowired
	private ExcellenceDocumentRepository repo;
	
	@GetMapping("/")	
	public String myHomePage() 
	{
		return "index.html";
	}
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("document") MultipartFile multipartFile, RedirectAttributes ra ) throws IOException 
	{
		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		ExcellenceDocument excDocument=new ExcellenceDocument();
		excDocument.setName(fileName);
		excDocument.setContent(multipartFile.getBytes());
		excDocument.setSize(multipartFile.getSize());
		excDocument.setUploadTime(new Date());
		repo.save(excDocument);
		ra.addFlashAttribute("message", "WOW!The File has been uploaded successfully ");
		
		
		return "redirect:/";
	}
	
	
	
	
	

}
