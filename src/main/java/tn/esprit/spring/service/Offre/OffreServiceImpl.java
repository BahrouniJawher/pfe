package tn.esprit.spring.service.Offre;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Offre.OffreRepository;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.UserService;

@Service
public class OffreServiceImpl implements OffreService {

	@Autowired
	OffreRepository offrerep;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	@Autowired
	ImageOffreServices imageOffreService;
	
	@Autowired
	UserService userservice;
	
	ObjectMapper objectMapper = new ObjectMapper();


	public Offre save(Offre o) {
		return offrerep.save(o);
	}

	public List<Offre> findAll() {
		return offrerep.findAll();
	}
    
	@Transactional
	public void addOffreWithImage(Offre o, UploadedFiles files) {
		if(files.getSize()!=0){
			for (UploadedFile f : files.getFiles()) {
	         	String newFileName = fileStorageServiceImpl.UploadImages(f);
	         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
	         	ImageOffre image = new ImageOffre();
				image.setImage(fileDownloadUri);
				//image.setIdoffre(o);
				imageOffreService.save(image);}
			}
			else{
				ImageOffre image = new ImageOffre();
				image.setImage("http://localhost:8084/downloadFile/1590066364642_no-image-available.jpg");
			//	image.setIdoffre(o);
				imageOffreService.save(image);
				
			}
		offrerep.save(o);
	}
	
	public Offre Update(Long idoffre,String OffreJson,Long IdUser) 
			throws JsonMappingException, JsonProcessingException, IOException {
		Offre p2 = findOne(idoffre);
		Offre p = objectMapper.readValue(OffreJson, Offre.class);
		User user = userservice.findOne(IdUser);

			p2.setTitle(p.getTitle());
			p2.setDescription(p.getDescription());
			p2.setType(p.getType());
			p2.setRenumeration(p.getRenumeration());
			p2.setNbreposte(p.getNbreposte());
			p2.setCategorie(p.getCategorie());
			p2.setAdresse(p.getAdresse());
			p2.setOffreusers(user);
			offrerep.save(p2);
		
		return p2;
	}

	
	public Offre Add(String OffreJson,Long IdUser, List<MultipartFile> file)
			throws JsonMappingException, JsonProcessingException, IOException {
		Offre o = objectMapper.readValue(OffreJson, Offre.class);
		User user = userservice.findOne(IdUser);
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
		
		offrerep.save(o);

		
		return o;
	}

	public Offre findOne(Long id) {
		return offrerep.getOne(id);
	}

	
	public List<Offre> findByTitle(String title) {
		return offrerep.findByTitle(title);
	}
	
	public List<Offre> findByAdresse(String adresse) {
		return offrerep.findByAdresse(adresse);
	}


	

}
