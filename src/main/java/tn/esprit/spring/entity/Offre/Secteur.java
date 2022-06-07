package tn.esprit.spring.entity.Offre;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Secteur {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id ;
	    private String name;

	    @OneToMany(mappedBy = "secteur")
	   private Collection<Offre> offres;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Collection<Offre> getOffres() {
			return offres;
		
		}

		public void setOffres(Collection<Offre> offres) {
			this.offres = offres;
		}

		public Secteur() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Secteur(Long id, String name, Collection<Offre> offres) {
			super();
			this.id = id;
			this.name = name;
			this.offres = offres;
		}

		@Override
		public String toString() {
			return "Secteur [id=" + id + ", name=" + name + ", offres=" + offres + "]";
		}
	    
	    
	    

}
