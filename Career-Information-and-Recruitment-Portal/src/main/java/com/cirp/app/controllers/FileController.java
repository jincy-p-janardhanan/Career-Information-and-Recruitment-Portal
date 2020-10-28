package com.cirp.app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.EditProfile;
import com.cirp.app.service.FileStorage;
import com.cirp.app.service.FindClass;

@Controller
public class FileController {

	@Autowired
	private EditProfile edit;

	@Autowired
	private FindClass find;

	@Autowired
	private CirpRepository repo;
	
	@PostMapping("/update-profile-pic")
	public String updateProfilePic(@RequestParam("file") MultipartFile image, RedirectAttributes redirectAttributes,
			Authentication authentication) {
		String username = authentication.getName();
		String filename = FileStorage.storeFile(image, username, "\\profile-pictures\\");
		edit.updateProfilePic(filename, username);
		return getRedirectUrl(username);
	}

	@GetMapping("/view-profile-pic/{id}")
	public ResponseEntity<byte[]> viewProfilePic(@PathVariable("id") String id, Authentication authentication) throws IOException {

		if(repo.findById(id) instanceof NonAdmin) {
			NonAdmin user = repo.findById(id);
			String image = user.getProfile_pic();
			File img = new File(FileStorage.UPLOAD_PATH + "\\profile-pictures\\" + image);
			return ResponseEntity.ok()
					.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
					.body(Files.readAllBytes(img.toPath()));
		}
		else {
			Job job = repo.findById(id);
			String image = job.getProfile_pic();
			File img = new File(FileStorage.UPLOAD_PATH + "\\profile-pictures\\" + image);
			return ResponseEntity.ok()
					.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
					.body(Files.readAllBytes(img.toPath()));
		}
	}

	@PostMapping("/update-bg-img")
	public String updateBgImg(@RequestParam("file") MultipartFile image, RedirectAttributes redirectAttributes,
			Authentication authentication) {
		String username = authentication.getName();
		String filename = FileStorage.storeFile(image, username, "\\backgrounds\\");
		edit.updateBgImg(filename, username);
		return getRedirectUrl(username);
	}

	@GetMapping("/view-bg-img/{image:.+}")
	public ResponseEntity<byte[]> viewBgImg(@PathVariable("image") String image) throws IOException {
		File img = new File(FileStorage.UPLOAD_PATH + "\\backgrounds\\" + image);
		
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
				.body(Files.readAllBytes(img.toPath()));
	}
	
	public String getRedirectUrl(String username) {
		Class<?> user_class = find.findClass(username);
		if (user_class == College.class)
			return "redirect:/college/home";
		else if (user_class == Recruiter.class)
			return "redirect:/recruiter/home";
		else if (user_class == Student.class)
			return "redirect:/student/home";
		else if (user_class == Alumnus.class)
			return "redirect:/alumnus/home";
		else
			return "redirect:/error";
	}
}
