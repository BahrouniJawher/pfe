package tn.esprit.spring.service.Offre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.repository.Offre.ImagesOffreRepository;

@Service
public class ImagesOffreServiceImpl implements ImageOffreServices {
	
	@Autowired
	ImagesOffreRepository imagesOffreRepository;

	
	public ImageOffre save(ImageOffre image) {
		return imagesOffreRepository.save(image);
	}

	
	public ImageOffre findImageOffres(Long idOffre) {
	return imagesOffreRepository.findImageOffres(idOffre);
	}

	
	public List<ImageOffre> findAllImagesOffres(Long idOffre) {
		return imagesOffreRepository.findAllImagesOffres(idOffre);
	}

}
