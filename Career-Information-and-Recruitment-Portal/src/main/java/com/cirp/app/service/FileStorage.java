package com.cirp.app.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorage {
	
	public static final String UPLOAD_PATH = System.getProperty("user.home") + "\\CIRP\\";
	
	public FileStorage() throws IOException, URISyntaxException {
		
		String picsfolder[] = {"//profile-pictures//", "//backgrounds//"};
		
		for(int i = 0; i<2; i++) {
			File folder = new File(UPLOAD_PATH + picsfolder[i]);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
		
		String source_filename[] = {"college.png", "company.png", "students.png", "default_background.png"};
		String dest_filename[] = {"default_college.png", "default_recruiter.png", "default_student.png", "default_background.png"};
		
		for(int i = 0; i<4; i++) {
			Path sourcepath = Paths.get(ClassLoader.getSystemResource("static/css/assets/" + source_filename[i]).toURI());
			Path destinationpath;
			if(i != 3) {
				destinationpath = Paths.get(UPLOAD_PATH + picsfolder[0] + dest_filename[i]);
			} else {
				destinationpath = Paths.get(UPLOAD_PATH + picsfolder[1] + dest_filename[i]);
			}
			Files.copy(sourcepath,destinationpath, StandardCopyOption.REPLACE_EXISTING);
		}
		
	}

	public static String storeFile(MultipartFile file, String username, String foldername) {

		String filename = username + "."
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		try {
			Path path = Paths.get(UPLOAD_PATH + foldername + filename);		
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("REACHED HERE");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return filename;
	}
}
