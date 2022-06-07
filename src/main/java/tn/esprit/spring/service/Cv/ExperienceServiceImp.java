package tn.esprit.spring.service.Cv;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Cv.Experience;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.ExperienceRepository;

@Service
public class ExperienceServiceImp {
	
	@Autowired
	ExperienceRepository expser;
	
	
	public Experience save(Experience e) {
		// TODO Auto-generated method stub
		return expser.save(e);
	}
	


}
