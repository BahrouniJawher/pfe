package tn.esprit.spring.controller.Cv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Cv.Experience;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.ExperienceRepository;
import tn.esprit.spring.service.Cv.ExperienceServiceImp;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Experience")
public class ExperienceController {
	
	@Autowired
	ExperienceServiceImp expercont;
	
	
	private UserService userservice;
	
	@Autowired
	ExperienceRepository exprep;
	
	
	 
	
	// Ajouter info : http://localhost:8084/contacter
		//{"competence":"java","langue":"Français"}

	public UserService getUserservice() {
		return userservice;
	}
	
	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	@PostMapping("/save-experience")
	@ResponseBody
	public Experience SaveInfo(@RequestBody Experience f) {
	return expercont.save(f);
	}
	
	@PostMapping(value = "/addExperience/{iduser}")
	@ResponseBody
	public Experience AjouterExperience(@PathVariable(value = "iduser") Long iduser,
			@RequestBody Experience experience)
			{
		Experience e= experience;
		User candexp = userservice.findOne(iduser);
		e.setCandexp(candexp);
		return expercont.save(e);
	}
	
	@GetMapping("/find_Experience/{iduser}")
	public List<Experience> findEducationByUser(@PathVariable(value = "iduser") Long iduser) {
		return exprep.findExperienceByUser(iduser);
	}


}
