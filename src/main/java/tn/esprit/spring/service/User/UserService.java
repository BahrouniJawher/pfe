package tn.esprit.spring.service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import Exception.UserNotFoundException;
import tn.esprit.spring.entity.User.User;
import tn.esprit.spring.entity.User.VerificationToken;
import tn.esprit.spring.repository.User.UserRepository;
import tn.esprit.spring.repository.User.VerificationTokenRepository;


@Service
public class UserService {
	@Autowired
	UserRepository  UserRepository;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	/*Chercher un utilisateur*/
	public User findOne(long id){
	return UserRepository.findById(id).get();
	}
	public User save(User u) {
		return UserRepository.save(u);
	}
	public List<User> findAll() {
		return UserRepository.findAll();
	}
	
	/*Update d'un user*/
	public  User updateUser(User user)	{
		return UserRepository.save(user);
		
	}
	
	public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
	
	public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
	
	public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
	
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		User user = UserRepository.findByEmail1(email);
        if (user != null) {
        	user.setResetPasswordToken(token);
        	UserRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }
	
	
	   public User getByResetPasswordToken(String token) {
	        return UserRepository.findByResetPasswordToken(token);
	    }
	
	   public void updatePassword(User user, String newPassword) {
		   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedPassword);
	         
	        user.setResetPasswordToken(null);
	        UserRepository.save(user);
	    }


}
