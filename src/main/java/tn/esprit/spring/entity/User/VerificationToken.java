package tn.esprit.spring.entity.User;

import javax.persistence.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;


@Entity
@JsonIgnoreProperties
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    
	    private String token;
	    
	    @JsonIgnore
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn(nullable = false, name = "user_id")
	    private User user;
	    
	    private Date expiryDate;
	    
	    private Date calculateExpiryDate(int expiryTimeInMinutes) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date(cal.getTime().getTime()));
	        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
	        return new Date(cal.getTime().getTime());
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}

		public static int getExpiration() {
			return EXPIRATION;
		}

		public VerificationToken() {
			super();
			// TODO Auto-generated constructor stub
		}

	
		public VerificationToken(String token, User user) {
			super();
			this.expiryDate=calculateExpiryDate(EXPIRATION);
			this.token = token;
			this.user = user;
		}

		@Override
		public String toString() {
			return "VerificationToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
					+ "]";
		}
	    
	    


}
