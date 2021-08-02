package com.restapplication.service;

import com.restapplication.model.Contact;
import com.restapplication.model.Employee;
import com.restapplication.model.Entreprise;
import com.restapplication.model.Freelance;
import com.restapplication.repository.ContactRepository;
import com.restapplication.service.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.restapplication.service.MapperDtoUtil.getContactDto;
import static com.restapplication.service.MapperDtoUtil.getEntreprises;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    private static final String RESSOURCE_NOT_FOUND = "Contact not found with id : ";
    
    @Override
    public ContactDto createContact(ContactDto contactDto) {
        List<Entreprise> entreprises = getEntreprises(contactDto);
        if(contactDto.getTva() != null){
            Freelance freelance = Freelance.builder()
                    .tva(contactDto.getTva())
                    .firstname(contactDto.getFirstname())
                    .name(contactDto.getName())
                    .adress(contactDto.getAdress())
                    .entreprises(entreprises)
                    .build();
            Freelance freelanceDao = contactRepository.save(freelance);
            freelance.getEntreprises().forEach(entreprise ->  entreprise.setContacts(Collections.singleton(freelanceDao)));
            return getContactDto(freelanceDao);
        } else {
            Employee employee = Employee.builder()
                    .firstname(contactDto.getFirstname())
                    .name(contactDto.getName())
                    .adress(contactDto.getAdress())
                    .entreprises(entreprises)
                    .build();
            Employee employeeDao = contactRepository.save(employee);
            entreprises.forEach(entreprise -> entreprise.setContacts(Collections.singleton(employeeDao)));
            return getContactDto(employeeDao);
        }
    }

    @Override
    public ContactDto updateContactDto(ContactDto contact) {
        Optional<Contact> contactDb = this.contactRepository.findById(contact.getId());
        if (contactDb.isPresent()) {
            if (contactDb.get() instanceof Freelance) {
                Employee employeeUpdate = (Employee) contactDb.get();
                employeeUpdate.setId(contact.getId());
                employeeUpdate.setName(contact.getName());
                employeeUpdate.setFirstname(contact.getFirstname());
                employeeUpdate.setAdress(contact.getAdress());
                employeeUpdate.setEntreprises(getEntreprises(contact));
                Employee employee = contactRepository.save(employeeUpdate);

                return getContactDto(employee);
            } else {
                Freelance freelanceUpdate = (Freelance) contactDb.get();
                freelanceUpdate.setId(contact.getId());
                freelanceUpdate.setName(contact.getName());
                freelanceUpdate.setFirstname(contact.getFirstname());
                freelanceUpdate.setAdress(contact.getAdress());
                freelanceUpdate.setEntreprises(getEntreprises(contact));
                freelanceUpdate.setTva(contact.getTva());
                Freelance freelance = contactRepository.save(freelanceUpdate);
                return getContactDto(freelance);
            }
        } else {
            
            throw new RuntimeException(RESSOURCE_NOT_FOUND + contact.getId());
        }
    }

    @Override
    public ContactDto getContactById(long contactId){

        Optional<Contact> contactDb = this.contactRepository.findById(contactId);

        if(contactDb.isPresent()) {
            if(contactDb.get() instanceof Freelance){
                Freelance freelance = (Freelance) contactDb.get();
                return getContactDto(freelance);
            } else {
                Employee employee = (Employee) contactDb.get();
                return getContactDto(employee);
            }
        } else {
            throw new RuntimeException(RESSOURCE_NOT_FOUND + contactId);
        }
    }

    @Override
    public void deleteContact(long contactId) {
        Optional<Contact> contactDb = contactRepository.findById(contactId);
        if(contactDb.isPresent()) {
            contactRepository.delete(contactDb.get());
        } else {
            throw new RuntimeException(RESSOURCE_NOT_FOUND + contactId);
        }
    }
}
