package tn.esprit.spring.controller.User;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.service.User.ImageService;


@Controller(value = "ImageUserController")
@ELBeanName(value = "ImageUserController")
public class ImageUserController {
	
	@Autowired
	ImageService imageuserservice;
	
	private Long id;
	private String image;
	
	public ImageUser getImageUser(Long idUser){
		ImageUser img=imageuserservice.findImageUser(idUser);
		System.out.println(img);
		return img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

}
