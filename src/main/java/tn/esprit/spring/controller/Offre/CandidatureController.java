package tn.esprit.spring.controller.Offre;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Qrcode.ZXingHelper;
import Utils.AppConstants;
import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Entretien.Entretien;
import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.Offre.CandidatureOffreUser;
import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Offre.CandidatureRepository;
import tn.esprit.spring.service.Offre.CandidatureService;

@CrossOrigin
@RestController
@RequestMapping("/Candidature")
public class CandidatureController {
	
	@Autowired
	CandidatureService candser;
	@Autowired
	CandidatureRepository candrep;
	
	
	ObjectMapper objectMapper = new ObjectMapper();

	
	// save candid : http://localhost:8084/contacter
				//{"motivation":"je suis bahrouni jawher","status":"en cour","cv": "cv.bahrouni"}

			@PostMapping("/valider")
			@ResponseBody
			public Candidature ValiderCand(@RequestBody Candidature c) {
			return candser.save(c);
			}
			
			@GetMapping("/getCandidatures")
			public List<Candidature> AfficherCand() {
				return candrep.findAll();
			
			}
			
			//{"title":"dévellopeur backend","description":"need senior bront for strides","adresse":"ariana","type":"CDI", "nbreposte" :"2", "dateAjout":"11/09/1996","dateExpiration":"04/07/1998","secteur":"info"}
			@PostMapping(value = "/add-candidature/{IdUser}/{IdOffre}")
			@CrossOrigin
			public Candidature AjouterCandidature(@PathVariable(value = "IdUser") Long IdUser,@PathVariable(value = "IdOffre") Long IdOffre,
					@RequestParam(value = "candidature", required = true) String CandidatureJson,
					@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file)
					throws JsonParseException, JsonMappingException, IOException {
				return candser.Add(CandidatureJson, IdUser, IdOffre, file);
			}
			
			
			@RequestMapping(value = "qrcode/{id}", method = RequestMethod.GET)
			public void qrcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
				response.setContentType("image/png");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(ZXingHelper.getQRCodeImage(id, 200, 200));
				outputStream.flush();
				outputStream.close();
			}
			
			@GetMapping("/{usr}/{ofr}")
			public Candidature findCandidatureByUserAndOffre(@PathVariable(value = "usr") long users,@PathVariable(value = "ofr") long offres) {
			
				return candrep.findcandidature(users, offres);
			}
			
			@GetMapping("/{idUser}")
			public List<CandidatureOffreUser> CandidatureByIdUser(@PathVariable(value = "idUser") long id) {
			
				return candrep.CandidatureByIdUser(id);
			}
			
			@GetMapping("/all-candidature/{idCandidature}")
			public List<CandidatureOffreUser> AllCandidature(@PathVariable(value = "idCandidature") long id) {
			
				return candrep.AllCandidature(id);
			}
	
			@GetMapping("/getPostulation")
			public List<CandidatureOffreUser> AfficherPostulation() {
				return candrep.AllPostulation();
			
			}
			
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
				Optional<Candidature> candidature = candrep.findById(id);
				
				candrep.deleteById(id);
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.ok(response);
			}
			
			
			@GetMapping("/getOffrePostule/{idUser}")
			public List<CandidatureOffreUser> PostulationByIdUser(@PathVariable(value = "idUser") long id) {
			
				return candrep.AllPostulationByUser(id);
			}
			
			@GetMapping("/nombrePostulation/{iduser}")
			 public int NombrePostulation(@PathVariable(value = "iduser")Long iduser)
			 {
				 int a=candrep.countPostulationParUser(iduser);
				 System.out.println(candrep.countPostulationParUser(iduser));
				 return a;
			 }
			
			@PutMapping(value = "/valider/{idCandidature}/{etat}") 
			public Candidature validerCandidature(@PathVariable(value = "idCandidature") long id, @PathVariable("etat") String etat) {
				return candser.ValiderCandidature(id, etat);
			}
			
			@PutMapping("/valider/{idCandidature}")
			public ResponseEntity<Candidature> EditOffre(@RequestParam(value = "candidature", required = true) String CandidatureJson,
					@PathVariable(value = "idCandidature") Long idCandidature
					) 
					throws JsonParseException, JsonMappingException,IOException {
				Candidature candidature = candrep.findById(idCandidature).orElse(null);
				Candidature p = objectMapper.readValue(CandidatureJson, Candidature.class);
				candidature.setStatus(p.getStatus());
				
				
				Candidature OffreModifier = candrep.save(candidature);
				return ResponseEntity.ok().body(OffreModifier);

			}
	

}
