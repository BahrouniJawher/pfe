package tn.esprit.spring.controller.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Contact.Contact;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.payload.request.SignupRequest;
import tn.esprit.spring.payload.response.JwtResponse;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.security.services.UserDetailsImpl;
import tn.esprit.spring.service.User.UserService;
import tn.esprit.spring.repository.User.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class TestController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository UserRepository;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {

		return "User Content.";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
   @ResponseBody
    public ResponseEntity<?> currentUserName(Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse( userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(),userDetails.getAddress(), userDetails.getTel(), userDetails.getDateN(), null, roles));
       // return userDetails.getAddress();
		// return userDetails.getId(); //LONG
	  //return userDetails;

    }

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	@GetMapping("/afficherUsers")
	public List<User> getAllUser() {
		return userService.findAll();
	}
	
	@PutMapping("/disableUser/{username}")
	public ResponseEntity<?> DisableUser(@PathVariable(value = "username") String username,@Valid @RequestBody SignupRequest signUpRequest) {
		
		if (!UserRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username not found!"));
		}
		
		User U = UserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		U.setEtatAcc(false);
		
		userService.updateUser(U);
		
		return ResponseEntity.ok(new MessageResponse("User Disabled successfully!"));
	}
	
	
	@DeleteMapping("/DeleteUser/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		Optional<User> usr = UserRepository.findById(id);
		
		UserRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	

}
