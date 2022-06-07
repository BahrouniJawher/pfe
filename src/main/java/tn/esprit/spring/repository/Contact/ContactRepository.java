package tn.esprit.spring.repository.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Contact.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>{

}
