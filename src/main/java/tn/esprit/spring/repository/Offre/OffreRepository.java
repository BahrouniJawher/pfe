package tn.esprit.spring.repository.Offre;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Offre.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre , Long> {
	  Page<Offre> findByTitleContaining(String title, String adresse, Pageable pageable);
	  List<Offre> findByTitleContaining(String title, String adresse, Sort sort);
	  
	  @Query(value = "SELECT * FROM offre WHERE title LIKE ?1%", nativeQuery = true)
		public List<Offre> findByTitle(String title);
	  
	  @Query(value = "SELECT * FROM offre WHERE adresse LIKE ?1%", nativeQuery = true)
		public List<Offre> findByAdresse(String adresse);
	  
	  @Query(value = "SELECT * FROM offre  WHERE offreusers_user_id=?1", nativeQuery = true)
		public List<Offre> findOfrreByUser(Long iduser);
	  
	  @Query(value = "SELECT COUNT(*) FROM Offre")
	    public Long countemp();
	  

}
