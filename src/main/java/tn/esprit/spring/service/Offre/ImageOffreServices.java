package tn.esprit.spring.service.Offre;

import java.util.List;

import tn.esprit.spring.entity.Offre.ImageOffre;


public interface ImageOffreServices {
	
	public ImageOffre save(ImageOffre image);
	public ImageOffre findImageOffres(Long idOffre);
	public List<ImageOffre> findAllImagesOffres(Long idOffre);

}
