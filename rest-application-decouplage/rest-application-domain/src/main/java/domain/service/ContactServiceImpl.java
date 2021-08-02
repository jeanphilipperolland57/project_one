package domain.service;

import domain.repository.ContactRepository;
import domain.service.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        return contactRepository.save(contactDto);
    }

    @Override
    public ContactDto updateContactDto(ContactDto contact) {
        return contactRepository.update(contact);
    }

    @Override
    public ContactDto getContactById(long contactId){
        return contactRepository.getContactById(contactId);
    }

    @Override
    public void deleteContact(long contactId) {
        contactRepository.deleteContact(contactId);
    }
}
