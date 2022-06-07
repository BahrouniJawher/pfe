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

import tn.esprit.spring.entity.Contact.Contact;
import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Cv.Experience;
import tn.esprit.spring.entity.Cv.InformationsProfessionnelles;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.InformationProffessionellesRepository;
import tn.esprit.spring.service.Cv.InfoProffServiceImp;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Info_Prof")
public class InformationProffController {
	
	@Autowired
	InfoProffServiceImp infocontroller;
	
	@Autowired
	InformationProffessionellesRepository inforep;
	
	@Autowired
	private UserService userservice;
	
	
	// Ajouter info : http://localhost:8084/contacter
	//{"competence":"java","langue":"Français"}

@PostMapping("/save_competence")
@ResponseBody
public InformationsProfessionnelles SaveInfo(@RequestBody InformationsProfessionnelles f) {
return infocontroller.save(f);
}

@GetMapping("/findCompetence")
public List<InformationsProfessionnelles> AfficherCompetence() {
	return inforep.findAll();

}

@PostMapping(value = "/addCompetence/{iduser}")
@ResponseBody
public InformationsProfessionnelles AjouterComptence(@PathVariable(value = "iduser") Long iduser,
		@RequestBody InformationsProfessionnelles competence)
		{
	InformationsProfessionnelles c= competence;
	User userinfo = userservice.findOne(iduser);
	c.setUserinfo(userinfo);
	return infocontroller.save(c);
}

@GetMapping("/find_Comptence/{iduser}")
public List<InformationsProfessionnelles> findCompetenceByUser(@PathVariable(value = "iduser") Long iduser) {
	return inforep.findCompetenceByUser(iduser);
}


}
