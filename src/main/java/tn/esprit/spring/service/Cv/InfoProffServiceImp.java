package tn.esprit.spring.service.Cv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Cv.InformationsProfessionnelles;
import tn.esprit.spring.repository.Cv.InformationProffessionellesRepository;

@Service
public class InfoProffServiceImp {
	
	@Autowired
	InformationProffessionellesRepository infoprofser;
	
	
	public InformationsProfessionnelles save(InformationsProfessionnelles i) {
		// TODO Auto-generated method stub
		return infoprofser.save(i);
	}

}
