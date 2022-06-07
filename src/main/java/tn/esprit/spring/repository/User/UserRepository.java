package tn.esprit.spring.repository.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	
	Optional<User> findByUsername(String username);
	
    Optional<User> findByEmail(String email);
    
    Boolean existsByUsername(String username);
    
	Boolean existsByEmail(String email);
	
	@Query("SELECT c FROM User c WHERE c.email = ?1")
    public User findByEmail1(String email);
	
	public User findByResetPasswordToken(String token);
	
	


}
