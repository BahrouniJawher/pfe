package tn.esprit.spring.service.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Contact.Contact;
import tn.esprit.spring.repository.Contact.ContactRepository;

@Service
public class ContactServicesImp implements ContactService {

	
	@Autowired
	ContactRepository contactser;
	
	@Override
	public Contact save(Contact c) {
		// TODO Auto-generated method stub
		return contactser.save(c);
	}

	

}
