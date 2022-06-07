package tn.esprit.spring.payload.response;

import java.util.Date;
import java.util.List;
import java.util.Set;

import tn.esprit.spring.entity.User.ImageUser;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String tel;
	private String entreprise;
	private String description;
	private Date dateN;
	private Set<ImageUser> images;
	private List<String> roles;
	

	public JwtResponse(String accessToken, Long id, String username, String email, String firstName, String lastName, String address, String tel, Date dateN, Set<ImageUser> images, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName= firstName;
		this.lastName=lastName;
		this.address=address;
		this.tel=tel;
		this.dateN=dateN;
		this.images=images;
		this.roles = roles;
	}
	
	
	public JwtResponse(Long id, String username, String email, String firstName, String lastName, String address, String tel, Date dateN, Set<ImageUser> images, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName= firstName;
		this.lastName=lastName;
		this.address=address;
		this.tel=tel;
		this.dateN=dateN;
		this.images=images;
		this.roles = roles;
	}
	
	
	
	
	

	





	






	public Set<ImageUser> getImages() {
		return images;
	}




	public void setImages(Set<ImageUser> images) {
		this.images = images;
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




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public Date getDateN() {
		return dateN;
	}




	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}




	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

}
