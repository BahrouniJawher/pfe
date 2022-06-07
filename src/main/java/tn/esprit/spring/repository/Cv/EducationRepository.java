package tn.esprit.spring.repository.Cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Cv.Education;


@Repository
public interface EducationRepository extends JpaRepository<Education,Long> {
	
	@Query(value = "SELECT * FROM education  WHERE candedu_user_id=?1", nativeQuery = true)
	public List<Education> findEducationByUser(Long iduser);

}
