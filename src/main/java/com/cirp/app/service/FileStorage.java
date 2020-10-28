package com.cirp.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorage {
	
	public static final String UPLOAD_PATH = System.getProperty("user.home") + "\\CIRP\\";

	public static String storeFile(MultipartFile file, String username, String foldername) {

		String filename = username + "."
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		try {
			Path path = Paths.get(UPLOAD_PATH + foldername + filename);		
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return filename;
	}
}
