package tn.esprit.spring.service.Cv;

import tn.esprit.spring.entity.Cv.CvCandidat;

public interface CvCandidatService {
	
	public CvCandidat ajouterCv(CvCandidat cv);
	public CvCandidat findCvCandidat(Long idUser);
	public CvCandidat findOne(long id);

}
