package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.form.FileUploadForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("uploadForm") FileUploadForm uploadForm,
					Model map) {
		
		List<MultipartFile> files = uploadForm.getFiles();

		List<String> fileNames = new ArrayList<String>();
		
		if(null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				//FormFile myFile = myModel.getFile();
				//Get the upload directory real path name
				String filePath = "D://backupE//j2ee//correct code//navith//CTS//fileupload//FileUploadSample//upload"; //give your path to upload
			    System.out.println("FilePath to upload : "+ filePath);

			    //create the upload folder if not exists
			    File folder = new File(filePath);
			    if(!folder.exists()){
			    	folder.mkdir();
			    }
		 		//String contentType = myFile.getContentType();
				//String fileName = myFile.getFileName();
				//int fileSize = fileName.getFileSize();
				byte[] fileData = fileName.getBytes();
				
				
				if(fileName != null && (!fileName.equals(""))){  
					File newFile = new File(filePath, fileName);
		 
			        if(!newFile.exists()){
			          FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(newFile);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          try {
						fos.write(fileData);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          try {
						fos.flush();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          
			        }
				}
		 
				fileNames.add(fileName);
				//Handle file content - multipartFile.getInputStream()
				

			}
		}
		
		map.addAttribute("files", fileNames);
		return "file_upload_success";
	}
}
