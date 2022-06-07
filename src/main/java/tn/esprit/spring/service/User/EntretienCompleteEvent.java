package tn.esprit.spring.service.User;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import tn.esprit.spring.entity.Entretien.Entretien;
import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.Offre.CandidatureOffreUser;

public class EntretienCompleteEvent extends ApplicationEvent {
	
	
	private String appUrl;
    private Locale locale;
    private CandidatureOffreUser postulation;
    private Entretien entretien;
    private Candidature candidat;

    
    
    public EntretienCompleteEvent(
    		CandidatureOffreUser postulation, Locale locale, String appUrl) {
    	        super(postulation);
    	         
    	        this.postulation = postulation;
    	        this.locale = locale;
    	        this.appUrl = appUrl;
    	    }
    	    
    	    public EntretienCompleteEvent(
    	    		CandidatureOffreUser postulation, String appUrl) {
    	    	        super(postulation);
    	    	         
    	    	        this.postulation = postulation;
    	    	        this.appUrl = appUrl;
    	    	    }

			public EntretienCompleteEvent(Entretien entretien, Locale locale, String appUrl) {
				super(entretien);
   	         
    	        this.entretien = entretien;
    	        this.locale = locale;
    	        this.appUrl = appUrl;			}

			public String getAppUrl() {
				return appUrl;
			}

			public void setAppUrl(String appUrl) {
				this.appUrl = appUrl;
			}

			public Locale getLocale() {
				return locale;
			}

			public void setLocale(Locale locale) {
				this.locale = locale;
			}

			public CandidatureOffreUser getPostulation() {
				return postulation;
			}

			public void setPostulation(CandidatureOffreUser postulation) {
				this.postulation = postulation;
			}

			public Entretien getEntretien() {
				return entretien;
			}

			public void setEntretien(Entretien entretien) {
				this.entretien = entretien;
			}
    	    
    	    
    	    

}
