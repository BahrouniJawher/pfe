package tn.esprit.spring.repository.Offre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.Offre.ImageOffre;

public interface ImagesOffreRepository extends JpaRepository<ImageOffre, Long> {
	
	@Query(value = "SELECT * FROM image_offre  WHERE idoffre_id=?1", nativeQuery = true)
	public ImageOffre findImageOffres(Long idOffre);
	@Query(value = "SELECT * FROM image_offre  WHERE idoffre_id=?1", nativeQuery = true)
	public List<ImageOffre> findAllImagesOffres(Long idOffre);

}
