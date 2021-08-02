package com.restapplication.service;

import com.restapplication.service.dto.ContactDto;

public interface ContactService {
    ContactDto createContact(ContactDto contactDto);

    ContactDto updateContactDto(ContactDto contact);

    ContactDto getContactById(long contactId);

    void deleteContact(long id);
}
