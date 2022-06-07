package tn.esprit.spring.repository.Cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Cv.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {
	
	@Query(value = "SELECT * FROM experience  WHERE candexp_user_id=?1", nativeQuery = true)
	public List<Experience> findExperienceByUser(Long iduser);

}
