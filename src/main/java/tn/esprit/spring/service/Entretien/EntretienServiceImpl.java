package tn.esprit.spring.service.Entretien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Entretien.Entretien;
import tn.esprit.spring.repository.Entretien.EntretienRepository;

@Service
public class EntretienServiceImpl implements EntretienService {
	
	
	@Autowired
	EntretienRepository entretienrep;

	
	public Entretien save(Entretien e) {
		return entretienrep.save(e);
	}

}
