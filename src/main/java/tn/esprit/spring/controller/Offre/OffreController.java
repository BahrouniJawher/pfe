package tn.esprit.spring.controller.Offre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.entity.Contact.Contact;
import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.payload.request.ProfileRequest;
import tn.esprit.spring.repository.Offre.OffreRepository;
import tn.esprit.spring.security.services.UserDetailsImpl;
import tn.esprit.spring.service.Offre.OffreService;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/offres")
public class OffreController {
	
	@Autowired
	OffreService offreser;
	@Autowired
	OffreRepository offrerep;
	
	
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	@Autowired
	UserService userservice;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private Sort.Direction getSortDirection(String direction) {
	    if (direction.equals("asc")) {
	      return Sort.Direction.ASC;
	    } else if (direction.equals("desc")) {
	      return Sort.Direction.DESC;
	    }

	    return Sort.Direction.ASC;
	  }
	
	 @GetMapping("/sortedoffres")
	  public ResponseEntity<List<Offre>> getAllOffres(@RequestParam(defaultValue = "id,desc") String[] sort) {
		 try {
		      List<Order> orders = new ArrayList<Order>();

		      if (sort[0].contains(",")) {
		        // will sort more than 2 fields
		        // sortOrder="field, direction"
		        for (String sortOrder : sort) {
		          String[] _sort = sortOrder.split(",");
		          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
		        }
		      } else {
		        // sort=[field, direction]
		        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		      }

		      List<Offre> offres = offrerep.findAll(Sort.by(orders));

		      if (offres.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(offres, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }
	 
	
	
	//http://localhost:8084/offre/afficher
		@GetMapping("/findoffre")
		public List<Offre> AfficherOffres() {
			return offreser.findAll();
		
		}
		
		//{"title":"dévellopeur backend","description":"need senior bront for strides","adresse":"ariana","type":"CDI", "nbreposte" :"2", "dateAjout":"11/09/1996","dateExpiration":"04/07/1998","secteur":"info"}
		@PostMapping(value = "/add-offre/{IdUser}")
		public Offre AjouterOffer(@PathVariable(value = "IdUser") Long IdUser,
				@RequestParam(value = "offre", required = true) String OffreJson,
				@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file)
				throws JsonParseException, JsonMappingException, IOException {
			return offreser.Add(OffreJson, IdUser, file);
		}
		
		public Offre findOne(Long id) {
			return offrerep.getOne(id);
		}
		
		@PutMapping("/edit/{IdUser}/{id}")
		public ResponseEntity<Offre> EditOffre(@RequestParam(value = "offre", required = true) String OffreJson,
				@PathVariable(value = "id") Long id,
				@PathVariable(value = "IdUser") Long IdUser,
				@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) 
				throws JsonParseException, JsonMappingException,IOException {
			Offre o = findOne(id);
			Offre p = objectMapper.readValue(OffreJson, Offre.class);
			User user = userservice.findOne(IdUser);
			o.setTitle(p.getTitle());
			o.setDescription(p.getDescription());
			o.setType(p.getType());
			o.setRenumeration(p.getRenumeration());
			o.setNbreposte(p.getNbreposte());
			o.setCategorie(p.getCategorie());
			o.setAdresse(p.getAdresse());
			o.setOffreusers(user);
			for (MultipartFile i : file) {
				String fileName = fileStorageServiceImpl.storeFile(i);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
				ImageOffre image = new ImageOffre();
				Set<ImageOffre> images= new HashSet<ImageOffre>();
				image.setImage(fileDownloadUri);
			//	image.setIdoffre(o);
				//imageOffreService.save(image);
				images.add(image);
				o.setImages(images);
			}
			Offre OffreModifier = offreser.save(o);
			return ResponseEntity.ok().body(OffreModifier);

		}
		
		

		//http://
		
		// Ajouter Categorie : http://localhost:8084/AjouterOffre
		//{"title":"dévellopeur front","description":"need senior bront for strides","adresse":"ariana","type":"CDI", "nbreposte" :"2", "dateAjout":"11/09/1996","dateExpiration":"04/07/1998","secteur":"info"}

@PostMapping("/AjouterOffre")
@ResponseBody
public Offre AjouterOffre(@RequestBody Offre o) {
return offreser.save(o);
}

@DeleteMapping("/deleteOffre/{id}")
public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
  try {
	  offrerep.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@DeleteMapping("/deleteAllOffres")
public ResponseEntity<HttpStatus> deleteAllTutorials() {
  try {
	  offrerep.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}



@GetMapping("/offre/{id}")
public Optional<Offre> getOffreById(@PathVariable Long id) {
	//Offre offre = offreser.findOne(id);
	return offrerep.findById(id);
}

@GetMapping("/detailoffre/{id}")
public ResponseEntity<Offre> getoffreById(@PathVariable("id") Long id) {
  Optional<Offre> offreData = offrerep.findById(id);

  if (offreData.isPresent()) {
    return new ResponseEntity<>(offreData.get(), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

@GetMapping("/find")
public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
    @RequestParam(required = false) String title,
    @RequestParam(required = false) String adresse,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "2") int size,
    @RequestParam(defaultValue = "id,desc") String[] sort) {

	 try {
	      List<Order> orders = new ArrayList<Order>();

	      if (sort[0].contains(",")) {
	        // will sort more than 2 fields
	        // sortOrder="field, direction"
	        for (String sortOrder : sort) {
	          String[] _sort = sortOrder.split(",");
	          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
	        }
	      } else {
	        // sort=[field, direction]
	        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
	      }
    

    List<Offre> offres = new ArrayList<Offre>();
    Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

    Page<Offre> pageTuts;
    if (title == null || adresse==null)
      pageTuts = offrerep.findAll(pagingSort);
    else
      pageTuts = offrerep.findByTitleContaining(title, adresse, pagingSort);

    offres = pageTuts.getContent();

    Map<String, Object> response = new HashMap<>();
    response.put("offres", offres);
   response.put("currentPage", pageTuts.getNumber());
   response.put("totalItems", pageTuts.getTotalElements());
    response.put("totalCount", pageTuts.getTotalPages());

    return new ResponseEntity<>(response, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@GetMapping("/recherche/{title}")
public List<Offre> findLikeNameM(@PathVariable(value = "title") String title) {
	return offreser.findByTitle(title);
}

@GetMapping("/recherche-adresse/{adresse}")
public List<Offre> findLikeadresse(@PathVariable(value = "adresse") String adresse) {
	return offreser.findByAdresse(adresse);
}

@GetMapping("/Offre-Entreprise/{iduser}")
public List<Offre> findLikeNameM(@PathVariable(value = "iduser") Long iduser) {
	return offrerep.findOfrreByUser(iduser);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
	Optional<Offre> offre = offrerep.findById(id);
	
	offrerep.deleteById(id);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
}

@GetMapping("/NombreOffre")
public Long Nombreoffre()
{
	 Long a=offrerep.countemp();
	 System.out.println(offrerep.countemp());
	 return a;
}

}
