package com.restapplication.controller;

import com.restapplication.service.ContactService;
import com.restapplication.service.dto.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    private static final String line = "******************************************************************************";

    @PostMapping("/contacts")
    public HttpStatus createContact(@RequestBody ContactDto contactDto){
        ContactDto contact = this.contactService.createContact(contactDto);
        log.info(line);
        log.info("Creation Contact : " + contact.toString());
        log.info(line);
        return HttpStatus.OK;
    }

    @PutMapping("/contacts/{id}")
    public  HttpStatus updateContact(@PathVariable long id, @RequestBody ContactDto contactDto){
        contactDto.setId(id);
        ContactDto contactDto1 = this.contactService.updateContactDto(contactDto);
        log.info(line);
        log.info("Update Contact : " + contactDto1.toString());
        log.info(line);
        return HttpStatus.OK;
    }

    @DeleteMapping("/contacts/{id}")
    public HttpStatus deleteContact(@PathVariable long id){
        this.contactService.deleteContact(id);
        log.info(line);
        log.info("delete contact success: ");
        log.info(line);
        return HttpStatus.OK;
    }

    @GetMapping("/contacts/{id}")
    public HttpStatus getContactById(@PathVariable long id){
        ContactDto contactById = contactService.getContactById(id);
        log.info(line);
        log.info("Get Contact : " + contactById.toString());
        log.info(line);
        ResponseEntity.ok().body(contactById);
        return HttpStatus.OK;
    }
}
