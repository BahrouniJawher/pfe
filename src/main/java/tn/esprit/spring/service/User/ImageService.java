package tn.esprit.spring.service.User;

import tn.esprit.spring.entity.User.ImageUser;

public interface ImageService {
	
	public ImageUser ajouterImage(ImageUser image);
	public ImageUser findImageUser(Long idUser);
	public ImageUser findOne(long id);


}
