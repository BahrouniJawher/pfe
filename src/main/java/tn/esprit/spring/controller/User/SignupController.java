package tn.esprit.spring.controller.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.entity.User.ERole;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.Role;
import tn.esprit.spring.entity.User.Sexe;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.repository.User.RoleRepository;
import tn.esprit.spring.repository.User.UserRepository;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.ImageService;
import tn.esprit.spring.service.User.OnRegistrationCompleteEvent;

@Controller(value = "signupController")
@ELBeanName(value = "signupController")
@Component
public class SignupController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	@Autowired
	ImageService iImageUserService;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String address;
    private Date dateN;
	private String tel;
 	private Sexe sexe;
 	private String entreprise;
 	private String description;
 	private UploadedFiles file;

 	

	public SignupController() {
		try
		{
		System.out.println(LoginController.userDetails.getFirstName());
		} catch(Exception e){
			System.out.println("Error, please connect to get acces to user details");
		}
	}


	public String doSignup() {
		String navigateTo = "null";
		if (userRepository.existsByUsername(username)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Username is already taken!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}

		else if (userRepository.existsByEmail(email)) {
			FacesMessage facesMessage =

					new FacesMessage("Error: Email is already in use!");

					FacesContext.getCurrentInstance().addMessage("form1:btn",facesMessage);
		}
		else
		{
			User user = new User(username, 
					 email,
					 encoder.encode(password),firstName
					 ,lastName,address,dateN,tel
					 ,sexe);
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			user.setRoles(roles);
			userRepository.save(user);
			
			for (UploadedFile f : file.getFiles()) {
	         	String newFileName = fileStorageServiceImpl.UploadImages(f);
	         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
				
				ImageUser image = new ImageUser();
				image.setImage(fileDownloadUri);
				image.setUserId(user);
				iImageUserService.ajouterImage(image);
			}
			String appUrl = "";
			User registered= user;
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, appUrl));
			navigateTo="login.xhtml?faces-redirect=true";
			FacesMessage facesMessage =

					new FacesMessage("Registered successfully, please verify your account!");

					FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
		
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDateN() {
		return dateN;
	}


	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Sexe getSexe() {
		return sexe;
	}


	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}


	public String getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public UploadedFiles getFile() {
		return file;
	}


	public void setFile(UploadedFiles file) {
		this.file = file;
	}
	
	
	
	

}
