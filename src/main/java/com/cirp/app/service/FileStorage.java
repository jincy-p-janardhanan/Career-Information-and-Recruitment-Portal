package com.cirp.app.service;

/*
 * References: 
 *  1. Using environment variables for AWS S3 Authentication - 
 *  	https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
 *  2. Upload and download - 
 *  	https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-objects.html 
 */

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorage {
	
	public static final String UPLOAD_PATH = System.getProperty("user.home") + "\\CIRP\\";

//	public static String storeFile(MultipartFile file, String username, String foldername) {
//
//		String filename = username + "."
//				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
//		try {
//			Path path = Paths.get(UPLOAD_PATH + foldername + filename);		
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return filename;
//	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileStorage.class);
	
	private static AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new EnvironmentVariableCredentialsProvider())
            .build();
	private static final String bucket_name = "ietcirp";
	
	public static String storeFile(MultipartFile multipartfile, String username, String foldername) {
		String filename = username + "."
				+ multipartfile.getOriginalFilename().substring(multipartfile.getOriginalFilename().lastIndexOf(".") + 1);
		String key_name = foldername + "/" + filename;
		
		File file = new File("temp_"+filename);

		try (OutputStream os = new FileOutputStream(file)) {
		    os.write(multipartfile.getBytes());
		} catch(IOException e) {
			logger.error(e.getMessage());
		}
		
		try {
		    s3.putObject(bucket_name, key_name, file);
		    logger.info("File uploaded to aws s3 bucket.");
		} catch (AmazonServiceException e) {
			logger.error(e.getMessage());
		}
		return filename;
	}
	
	public static File retrieveFile(String filename, String foldername) {
		String key_name = foldername + "/" + filename;
		File f = new File(foldername+filename);
		try {
			S3Object o = s3.getObject(bucket_name, key_name);
		    S3ObjectInputStream s3is = o.getObjectContent();
		    FileOutputStream fos = new FileOutputStream(f, false);
		    byte[] read_buf = new byte[1024];
		    int read_len = 0;
		    while ((read_len = s3is.read(read_buf)) > 0) {
		        fos.write(read_buf, 0, read_len);
		    }
		    s3is.close();
		    fos.close();
		    logger.info("Downloaded image from aws s3 bucket.");
		} catch (AmazonServiceException e) {
		    logger.error(e.getErrorMessage());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return f;
	}
}
