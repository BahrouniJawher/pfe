package tn.esprit.spring.controller.User;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import Exception.ResourceNotFoundException;
import Utils.AppConstants;
import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.Offre.ImageOffre;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.ERole;
import tn.esprit.spring.entity.User.ImageUser;
import tn.esprit.spring.entity.User.Role;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.entity.User.UserPrincipal;
import tn.esprit.spring.entity.User.VerificationToken;
import tn.esprit.spring.payload.request.LoginRequest;
import tn.esprit.spring.payload.request.PasswordRequest;
import tn.esprit.spring.payload.request.ProfileRequest;
import tn.esprit.spring.payload.request.SignupRequest;
import tn.esprit.spring.payload.response.JwtResponse;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.repository.User.RoleRepository;
import tn.esprit.spring.repository.User.UserRepository;
import tn.esprit.spring.repository.User.VerificationTokenRepository;
import tn.esprit.spring.security.Oauth.CurrentUser;
import tn.esprit.spring.security.jwt.JwtUtils;
import tn.esprit.spring.security.services.UserDetailsImpl;
import tn.esprit.spring.service.User.FileStorageServiceImpl;
import tn.esprit.spring.service.User.ImageService;
import tn.esprit.spring.service.User.OnRegistrationCompleteEvent;
import tn.esprit.spring.service.User.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	
	@Autowired
	ImageService iImageUserService;
	
	@Autowired
	VerificationTokenRepository verificationrep;
	
	private UploadedFiles file;
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User U = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));

		if (!userDetails.getEtatAcc()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Your account is Disabled by Admin!"));
		}

		if (!U.isEnabled()) {
			return ResponseEntity.badRequest().body(
					new MessageResponse("Error: Please Confirm your Account, We've sent you a confirmation email"));
		}
		System.out.println(userDetails.getEtatAcc());
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(),userDetails.getAddress(), userDetails.getTel(), userDetails.getDateN(), U.getImages(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(HttpServletRequest request, @RequestParam(value = "candidat", required = true) String UserJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) throws IOException {
		
		User signUpRequest = objectMapper.readValue(UserJson, User.class);

		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNom(), signUpRequest.getPrenom(),
				signUpRequest.getAddress(), signUpRequest.getDateN(), signUpRequest.getTel(), signUpRequest.getSexe());

		//Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "cand":
//					Role candRole = roleRepository.findByName(ERole.ROLE_CANDIDATURE)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(candRole);
//
//					break;
//				case "mod":
//					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				case "ent":
//					Role entRole = roleRepository.findByName(ERole.ROLE_ENTREPRISE)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(entRole);
//
//					break;
//				case "rec":
//					Role recRole = roleRepository.findByName(ERole.ROLE_RECRETEUR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(recRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_CANDIDATURE)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}
		
		Role candRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(candRole);
		
		user.setRoles(roles);
		userRepository.save(user);
		String appUrl = request.getContextPath();
		User registered = user;
		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			ImageUser image = new ImageUser();
			image.setImage(fileDownloadUri);
			image.setUserId(registered);
			iImageUserService.ajouterImage(image);
		}
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/entreprise")
	public ResponseEntity<?> registerentreprise(HttpServletRequest request, @RequestParam(value = "client", required = true) String UserJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) throws IOException {
		User signUpRequest = objectMapper.readValue(UserJson, User.class);

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),signUpRequest.getNom(), signUpRequest.getPrenom(),signUpRequest.getEntreprise(),
				signUpRequest.getAddress(), signUpRequest.getTel(), signUpRequest.getDescripssion(), signUpRequest.getSecteur());

		Set<Role> roles = new HashSet<>();

		Role entRole = roleRepository.findByName(ERole.ROLE_ENTREPRISE)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(entRole);

		user.setRoles(roles);
		
	
		
		
		userRepository.save(user);
		
		String appUrl = request.getContextPath();
		User registered = user;
		for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			ImageUser image = new ImageUser();
			image.setImage(fileDownloadUri);
			image.setUserId(registered);
			iImageUserService.ajouterImage(image);
		}
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	@PutMapping("/updateUser")
	public ResponseEntity<?> UpdateUser(@Valid @RequestBody ProfileRequest profileRequest,
			Authentication authentication) throws IOException {

		// update user's account
		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		User U = userRepository.findByUsername(U1.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));
		U.setNom(profileRequest.getFirstName());
		U.setPrenom(profileRequest.getLastName());
		U.setAddress(profileRequest.getAddress());
		U.setDateN(profileRequest.getDateN());
		U.setTel(profileRequest.getTel());
		U.setEmail(profileRequest.getEmail());
		
		
		
		/*for (MultipartFile i : file) {
			String fileName = fileStorageServiceImpl.storeFile(i);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			ImageUser image = new ImageUser();
			Set<ImageUser> images= new HashSet<ImageUser>();
			image.setImage(fileDownloadUri);
			//image.setUserId(U);
			//iImageUserService.ajouterImage(image);
			//images.add(image);
			//U.setImages(images);
		}*/
		userService.updateUser(U);


		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}
	
	
	// get employee by id rest api
		@GetMapping("/user/{id}")
		public ResponseEntity<User> getEmployeeById(@PathVariable Long id) {
			User employee = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			return ResponseEntity.ok(employee);
		}
		
		
		@PutMapping("/update-profile/{id}")
		public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody User profileRequest){
			User U = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			U.setUsername(profileRequest.getUsername());
			U.setNom(profileRequest.getNom());
			U.setPrenom(profileRequest.getPrenom());
			U.setAddress(profileRequest.getAddress());
			U.setDateN(profileRequest.getDateN());
			U.setTel(profileRequest.getTel());
			U.setEmail(profileRequest.getEmail());
			//U.setImages(profileRequest.getImages());
		
//			for (MultipartFile i : file) {
//				String fileName = fileStorageServiceImpl.storeFile(i);
//				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//						.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
//				ImageUser image = new ImageUser();
//				image.setImage(fileDownloadUri);
//				image.setUserId(U);
//				iImageUserService.ajouterImage(image);
//			}
			User updatedEmployee = userRepository.save(U);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		@DeleteMapping("/Delete-User/{token}")
		public ResponseEntity<String> DeleteUser(@PathVariable(value = "token") String token){
			
			
			VerificationToken verificationToken = userService.getVerificationToken(token);
			User user = userService.getUser(token);
			verificationrep.delete(verificationToken);
			userRepository.delete(user);
			return ResponseEntity.ok("user deleted");
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			Optional<User> users = userRepository.findById(id);
			
			userRepository.deleteById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

	@PutMapping("/updatepassword")
	public ResponseEntity<?> UpdatePassword(@Valid @RequestBody PasswordRequest PasswordRequest,
			Authentication authentication) {

		UserDetailsImpl U1 = (UserDetailsImpl) authentication.getPrincipal();
		User U = userRepository.findByUsername(U1.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + U1.getUsername()));

		if (PasswordRequest.getPassword().equals(PasswordRequest.getPasswordConfirm())) {
			U.setPassword(encoder.encode(PasswordRequest.getPassword()));
			userService.updateUser(U);

			return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
		} else
			return ResponseEntity.ok(new MessageResponse("password and confirm password does not match!"));

	}

	@GetMapping("/regitrationConfirm/token/{token}")
	public ResponseEntity<?> confirmRegistration(WebRequest request, Model model,
			@PathVariable(value = "token") String token) {

		Locale locale = request.getLocale();

		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			return ResponseEntity.ok(new MessageResponse("InvaLID vERFICATION Token"));

		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return ResponseEntity.ok(new MessageResponse("Link Expired!"));

		}

		user.setEnabled(true);
		userService.updateUser(user);
		return ResponseEntity.ok(new MessageResponse("Account Confirmed with succes"));
	}
	
	
	  @GetMapping("/candidat")
	    @PreAuthorize("hasRole('USER')")
	    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
	        return userRepository.findById(userPrincipal.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	    }

	public UploadedFiles getFile() {
		return file;
	}

	public void setFile(UploadedFiles file) {
		this.file = file;
	}
	
	

}
