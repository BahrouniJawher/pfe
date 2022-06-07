package tn.esprit.spring.service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.repository.User.ImageUserRepository;

@Service
public class ImageServiceImp implements ImageService {
	
	
	
	@Autowired
	ImageUserRepository imageUserRepository;

	@Override
	public ImageUser ajouterImage(ImageUser image) {
		return imageUserRepository.save(image);
	}
	@Override
	public ImageUser findOne(long id){
		return imageUserRepository.findById(id).get();
		}

	@Override
	public ImageUser findImageUser(Long idUser) {
		return imageUserRepository.findImageUser(idUser);
		
	}

}
