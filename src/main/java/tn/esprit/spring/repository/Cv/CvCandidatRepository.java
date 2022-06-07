package tn.esprit.spring.repository.Cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Cv.CvCandidat;


@Repository
public interface CvCandidatRepository extends JpaRepository<CvCandidat, Long> {
	
	@Query(value = "SELECT * FROM cv_candidat WHERE user_id_user_id=?1", nativeQuery = true)
	public List<CvCandidat> findCvCandidat(Long idUser);

}
