package tn.esprit.spring.service.Offre;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.file.UploadedFiles;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.entity.Offre.Offre;

public interface OffreService {
	public Offre save(Offre o);
	public List<Offre> findAll();
	public Offre Add(String OffreJson,Long IdUser,List<MultipartFile> file) throws JsonMappingException, JsonProcessingException, IOException;
	public void addOffreWithImage(Offre o, UploadedFiles files);
	public Offre Update(Long idoffre,String OffreJson,Long IdUser) throws JsonMappingException, JsonProcessingException, IOException;
	public Offre findOne(Long id);
	public List<Offre> findByTitle(String title);
	public List<Offre> findByAdresse(String adresse);






}
