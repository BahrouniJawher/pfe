package tn.esprit.spring.repository.Offre;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.Offre.CandidatureOffreUser;
import tn.esprit.spring.entity.Offre.Offre;
import tn.esprit.spring.entity.User.User;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	
	
	
	@Query(value = "SELECT * FROM candidature l JOIN offre c on l.offres_id=c.id  WHERE l.offres_id=?1 AND l.users_user_id=?2", nativeQuery = true)
	public Candidature findcandidature(Long usr,Long ofr);
	
	@Query(value = "SELECT  NEW tn.esprit.spring.entity.Offre.CandidatureOffreUser(c.id,o.id,u.id,u.nom,u.prenom,u.email,c.motivation) FROM Candidature c join c.users u  join  c.offres o  WHERE c.users.id=:idc ")
	public List<CandidatureOffreUser> CandidatureByIdUser(@Param("idc")long i);
	
	@Query(value = "SELECT  NEW tn.esprit.spring.entity.Offre.CandidatureOffreUser(c.id,o.id,u.id,u.nom,u.prenom,u.email,c.motivation) FROM Candidature c join c.users u  join  c.offres o  WHERE c.id.id=:idc ")
	public List<CandidatureOffreUser> AllCandidature(@Param("idc")long i);

	@Query(value = "SELECT  NEW tn.esprit.spring.entity.Offre.CandidatureOffreUser(c.id,o.id,o.title,u.id,u.nom,u.prenom,u.email,c.motivation,v.Cv) FROM Candidature c join c.users u  join  c.offres o join c.cvcandidats v ")
	public List<CandidatureOffreUser> AllPostulation();
	
	@Query(value = "SELECT  NEW tn.esprit.spring.entity.Offre.CandidatureOffreUser(c.id,u.id,o.title,o.description,o.nbreposte,o.dateExpiration,c.status) FROM Candidature c join c.users u  join  c.offres o  WHERE c.users.id=:idc ")
	public List<CandidatureOffreUser> AllPostulationByUser(@Param("idc")long i);
	
	@Query(value = "SELECT COUNT(*) from Candidature c WHERE c.users_user_id=?1", nativeQuery = true)
	public int countPostulationParUser(Long iduser);

	
//	@Query(value = "SELECT  NEW tn.esprit.spring.Model.lignecommandeproduit(l.id,p.id,p.nomProduit,l.quantity,p.prix,l.quantity*p.prix,c.montant) FROM LigneCommande l join l.commande c  join l.produit p   WHERE c.id.id=:idc")
//	public List<lignecommandeproduit> panierParIdCommande(@Param("idc")long i);
	

}
