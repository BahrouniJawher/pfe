package tn.esprit.spring.controller.Entretien;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.entity.Contact.Contact;
import tn.esprit.spring.entity.Entretien.Entretien;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.ERole;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.Role;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.repository.Entretien.EntretienRepository;
import tn.esprit.spring.service.Entretien.EntretienService;
import tn.esprit.spring.service.User.EntretienCompleteEvent;
import tn.esprit.spring.service.User.OnRegistrationCompleteEvent;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Entretien")
public class EntretienController {
	
	@Autowired
	EntretienService entretienserv;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	ApplicationEventPublisher eventPublisher;

	
	@Autowired
	EntretienRepository entretienrep;
	
	@PostMapping("/add-entretien")
	@ResponseBody
	public Long ajouterEntretien(@RequestBody Entretien ssiiConsulting) {
		entretienserv.save(ssiiConsulting);
		return ssiiConsulting.getId();
	}
	
	@GetMapping("/get-all-entretien")
	public List<Entretien> AfficherEntretien() {
		return entretienrep.findAll();
	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Optional<Entretien> entretien = entretienrep.findById(id);
		
		entretienrep.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/envoyer-entretien")
	public ResponseEntity<?> entretienOffre(HttpServletRequest request, @RequestParam(value = "entretien", required = true) String EntretienJson) throws IOException {
		
		Entretien signUpRequest = objectMapper.readValue(EntretienJson, Entretien.class);

		
		

		// Create new user's account
		Entretien entretien = new Entretien(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getLocalisation(),
				signUpRequest.getDescription(), signUpRequest.getDateAjout(), signUpRequest.getTimeStart(),
				signUpRequest.getTimeEnd());

		
		
		
		entretienrep.save(entretien);
		String appUrl = request.getContextPath();
		Entretien validation = entretien;
		
		eventPublisher.publishEvent(new EntretienCompleteEvent(validation, request.getLocale(), appUrl));

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
