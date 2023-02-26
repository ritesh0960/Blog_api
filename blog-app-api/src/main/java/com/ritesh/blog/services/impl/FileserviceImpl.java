package com.ritesh.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ritesh.blog.services.FileService;

@Service
public class FileserviceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//file name
		String name = file.getOriginalFilename();
		
		//generate the random name of the file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//genetae the full path the file is to be uploaded
		String filePath = path + File.separator + fileName1;
		
		//create folder if not created
		File f = new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		
		//copying file to the destination folder
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String filename) throws IOException {
		
		String fullPath = path + File.separator + filename;
		
		InputStream is = new FileInputStream(fullPath);
		
		return is;
	}

}
