package domain.service;

import domain.service.dto.ContactDto;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    ContactDto createContact(ContactDto contactDto);

    ContactDto updateContactDto(ContactDto Contact);

    ContactDto getContactById(long ContactId);

    void deleteContact(long id);
}
