package tn.esprit.spring.controller.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Contact.Contact;
import tn.esprit.spring.repository.Contact.ContactRepository;
import tn.esprit.spring.service.Contact.ContactService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class RestControllerContact {
	
	@Autowired
	ContactService contactser;
	@Autowired
	ContactRepository contactrep;
	
	
	
	// Ajouter Contact : http://localhost:8084/contacter
				//{"nom":"jawher","email":"jawherbahrouni@esprit.tn","num_tel":"51823592","description":"concernant mon cv"}
	
	@PostMapping("/contacter")
	@ResponseBody
	public Contact EnvoyerContact(@RequestBody Contact c) {
		return contactser.save(c);
	}
	
	@GetMapping("/afficherContacts")
	public List<Contact> getAllContact() {
		return contactrep.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Optional<Contact> contact = contactrep.findById(id);
		
		contactrep.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


	

}
