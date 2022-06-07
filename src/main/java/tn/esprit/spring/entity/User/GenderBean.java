package tn.esprit.spring.entity.User;

import javax.annotation.ManagedBean;



@ManagedBean
public class GenderBean {
	 public Sexe[] getSexe() {
	        return Sexe.values();
	    }

}
