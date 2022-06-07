package tn.esprit.spring.service.Offre;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.entity.Offre.Candidature;

public interface CandidatureService {
	
	public Candidature findOne(Long id);
	
	public Candidature save(Candidature c);
	public Candidature Add(String CandidatureJson,Long IdUser,Long IdOffre, List<MultipartFile> file) throws JsonMappingException, JsonProcessingException, IOException;

	public Candidature ValiderCandidature(Long idCandidature, String etat);



}
