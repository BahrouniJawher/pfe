package tn.esprit.spring.service.Offre;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.entity.Cv.CvCandidat;
import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Offre.CandidatureRepository;
import tn.esprit.spring.service.Cv.CvCandidatService;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.UserService;

@Service
public class CandidatureServiceImpl implements CandidatureService {
	
	
	@Autowired
	CandidatureRepository candrep;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	CvCandidatService cvservice;
	
	@Autowired
	OffreService offreservice;
	
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	ObjectMapper objectMapper = new ObjectMapper();


	
	public Candidature findOne(Long id) {
		return candrep.getOne(id);
	}


	
	public Candidature save(Candidature c) {
		return candrep.save(c);
	}



	
	public Candidature Add(String CandidatureJson, Long IdUser,Long IdOffre, List<MultipartFile> file)
			throws JsonMappingException, JsonProcessingException, IOException {
		Candidature c = objectMapper.readValue(CandidatureJson, Candidature.class);
		User user = userservice.findOne(IdUser);
		Offre offre = offreservice.findOne(IdOffre);
		c.setUsers(user);
		c.setOffres(offre);

		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			CvCandidat cv = new CvCandidat();
			Set<CvCandidat> cvcandidats= new HashSet<CvCandidat>();
			cv.setCv(fileDownloadUri);
		//	image.setIdoffre(o);
			//imageOffreService.save(image);
			cvcandidats.add(cv);
			c.setCvcandidats(cvcandidats);
		}
		
		candrep.save(c);

		
		return c;
	}




	
	public Candidature ValiderCandidature(Long idCandidature, String etat) {
		//User user = userservice.findOne(userId);
				Candidature candidature = candrep.findById(idCandidature).orElse(null);
			    etat = "";

				if(etat=="En attente") {
					candidature.setStatus("En attente");
					candrep.save(candidature);

					}
				
				if(etat=="Accepte") {
					
					candidature.setStatus("Accepte");
					candrep.save(candidature);


				}
				if(etat=="Refuse"){
					candidature.setStatus("Refuse");

				}
				candrep.save(candidature);

				return candidature;
				
	}
	
	

}
