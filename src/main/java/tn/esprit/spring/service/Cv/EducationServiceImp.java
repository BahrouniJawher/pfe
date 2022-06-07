package tn.esprit.spring.service.Cv;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.EducationRepository;
import tn.esprit.spring.service.User.UserService;

@Service
public class EducationServiceImp {
	
	@Autowired
	EducationRepository edserv;
	@Autowired
	UserService userservice;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	public Education save(Education d) {
		return edserv.save(d);
	}
	
	
	public Education AddEducation(String EducationtJson,Long idcand)
			throws JsonMappingException, JsonProcessingException, IOException {
		Education e = objectMapper.readValue(EducationtJson, Education.class);
		User user = userservice.findOne(idcand);
		e.setCandedu(user);
		
		edserv.save(e);

		return e;
	}

}
