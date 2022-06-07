package tn.esprit.spring.controller.Offre;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.service.Offre.ImageOffreServices;


@Controller(value = "ControllerImagesOffre")
@ELBeanName(value = "ControllerImagesOffre")
public class ControllerImagesOffre {
	
	@Autowired
	ImageOffreServices imageoffre;
	
	private Long id;
	private String image;
	
	public ImageOffre getImageProduits(Long idoffre){
		ImageOffre img=imageoffre.findAllImagesOffres(idoffre).get(0);
		return img;
	}
	
	public List<ImageOffre> getAllImagesProduits(Long idProduit){
		return imageoffre.findAllImagesOffres(idProduit);
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
