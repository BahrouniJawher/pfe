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
import tn.esprit.spring.entity.Cv.CvCandidat;
import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.CvCandidatRepository;
import tn.esprit.spring.service.Cv.CvCandidatServiceImpl;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cv")
public class CvCandidatController {
	
private UserService userservice;
	
	@Autowired
	CvCandidatRepository cvrep;
	
	@Autowired
	CvCandidatServiceImpl cvservice;
	
	@GetMapping("/getCv/{IdUser}")
	public List<CvCandidat> findCvCandidat(@PathVariable(value = "IdUser") Long idUser) {
		return cvrep.findCvCandidat(idUser);
	}
	
	//{"title":"dévellopeur backend","description":"need senior bront for strides","adresse":"ariana","type":"CDI", "nbreposte" :"2", "dateAjout":"11/09/1996","dateExpiration":"04/07/1998","secteur":"info"}
	@PostMapping(value = "/addCv/{IdUser}")
	@CrossOrigin
	public CvCandidat AjouterCvCandidat(@PathVariable(value = "IdUser") Long IdUser,
		
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file)
			throws JsonParseException, JsonMappingException, IOException {
		return cvservice.AddCv(IdUser, file);
	}


}
