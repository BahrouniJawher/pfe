package tn.esprit.spring.controller.User;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.ImageService;
import tn.esprit.spring.service.User.UserService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
@Component
public class ProfileController {
	
	@Autowired
	UserService userService;
	@Autowired
	ImageService iImageUserService;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	private UploadedFiles file;
	
	@PostMapping("/addimage")
	public void updateProfileImage()
	{
		ImageUser image = new ImageUser();
		User u = new User();
		u=userService.findOne(LoginController.userDetails.getId());
		for (UploadedFile f : file.getFiles()) {
         	String newFileName = fileStorageServiceImpl.UploadImages(f);
         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
			
			image = iImageUserService.findImageUser(u.getId());
			
			image.setImage(fileDownloadUri);
			iImageUserService.ajouterImage(image);
		}
		
		
	}


	public UploadedFiles getFile() {
		return file;
	}


	public void setFile(UploadedFiles file) {
		this.file = file;
	}
	
	


}
