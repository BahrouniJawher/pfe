package tn.esprit.spring.repository.Cv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Cv.InformationsProfessionnelles;


@Repository
public interface InformationProffessionellesRepository extends JpaRepository<InformationsProfessionnelles,Long> {
	
	@Query(value = "SELECT * FROM public.info_proff  WHERE userinfo_user_id=?1", nativeQuery = true)
	public List<InformationsProfessionnelles> findCompetenceByUser(Long iduser);

}
