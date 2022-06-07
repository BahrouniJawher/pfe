package tn.esprit.spring.service.Cv;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.entity.Cv.CvCandidat;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.repository.Cv.CvCandidatRepository;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.UserService;

@Service
public class CvCandidatServiceImpl implements CvCandidatService {
	
	@Autowired
	CvCandidatRepository cvrep;
	
	@Autowired
	UserService userservice;
	
	ObjectMapper objectMapper = new ObjectMapper();
	

	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;


	
	public CvCandidat ajouterCv(CvCandidat cv) {
		return cvrep.save(cv);
	}

	
	public CvCandidat findCvCandidat(Long idUser) {
		return (CvCandidat) cvrep.findCvCandidat(idUser);
	}

	
	public CvCandidat findOne(long id) {
		return cvrep.findById(id).get();
	}
	
	
	public CvCandidat AddCv(Long IdUser, List<MultipartFile> file)
			throws JsonMappingException, JsonProcessingException, IOException {
		User user = userservice.findOne(IdUser);
		CvCandidat cv = new CvCandidat();

		//c.setUserId(user);
		
//		for (MultipartFile i : file) {
//			String fileName = fileStorageServiceImpl.storeFile(i);
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
//			ImageUser image = new ImageUser();
//			image.setImage(fileDownloadUri);
//			image.setUserId(registered);
//			iImageUserService.ajouterImage(image);
//		}


		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			//CvCandidat cv = new CvCandidat();
			cv.setCv(fileDownloadUri);
		    cv.setUserId(user);
			//user.setCvs(cvcandidats);
		    cvrep.save(cv);

		}
		
		
		//cvrep.save(c);

		
		return cv;
	}

}
