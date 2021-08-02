package domain.repository;

import domain.service.dto.ContactDto;

public interface ContactRepository {

    ContactDto save(ContactDto contactDto);

    ContactDto findById(ContactDto contactDto);

    ContactDto update(ContactDto contactDto);

    ContactDto getContactById(long contactId);

    void deleteContact(long contactId);

}
