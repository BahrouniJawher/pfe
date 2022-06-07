package tn.esprit.spring.controller.Cv;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Utils.AppConstants;
import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.EducationRepository;
import tn.esprit.spring.service.Cv.EducationServiceImp;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Education")
public class EducationController {
	
	@Autowired
	EducationServiceImp edcontroller;
	
	@Autowired
	EducationRepository educrep;
	
	@Autowired
	UserService userservice;
	
	// Ajouter info : http://localhost:8084/contacter
			//{"competence":"java","langue":"Français"}

		@PostMapping("/save-education")
		@ResponseBody
		public Education SaveInfo(@RequestBody Education f) {
		return edcontroller.save(f);
		}
		
		@PostMapping(value = "/addEducation/{idcand}")
		@ResponseBody
		public Education AjouterEducation(@PathVariable(value = "idcand") Long idcand,
				@RequestBody Education education)
				{
			Education p= education;
			User user = userservice.findOne(idcand);
			p.setCandedu(user);
			return edcontroller.save(p);
		}
		
		@GetMapping("/find_Education/{iduser}")
		public List<Education> findEducationByUser(@PathVariable(value = "iduser") Long iduser) {
			return educrep.findEducationByUser(iduser);
		}



}
