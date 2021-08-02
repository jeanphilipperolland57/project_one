package com.restapplication.controller;

import com.restapplication.service.EntrepriseService;
import com.restapplication.service.dto.ContactDto;
import com.restapplication.service.dto.EntrepriseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class EntrepriseController {
    @Autowired
    private EntrepriseService entrepriseService;
    private static final String line = "******************************************************************************";

    @PostMapping("/entreprises")
    public HttpStatus createEntreprise(@RequestBody EntrepriseDto entrepriseDto){
        EntrepriseDto entreprise = this.entrepriseService.createEntreprise(entrepriseDto);
        log.info(line);
        log.info("Creation Entreprise : " + entreprise.toString());
        log.info(line);
        return HttpStatus.OK;
    }

    @PostMapping("/addContact/{entrepriseId}")
    public HttpStatus addContact(@PathVariable long entrepriseId, @RequestBody ContactDto contactDto){
        EntrepriseDto entreprise = this.entrepriseService.addContact(entrepriseId,contactDto);
        log.info(line);
        log.info("add Contact : " + entreprise.toString());
        log.info(line);
        return HttpStatus.OK;
    }

    @PutMapping("/entreprises/{id}")
    public  HttpStatus updateEntreprise(@PathVariable long id, @RequestBody EntrepriseDto entrepriseDto){
        entrepriseDto.setId(id);
        EntrepriseDto entrepriseDto1 = this.entrepriseService.updateEntrepriseDto(entrepriseDto);
        log.info(line);
        log.info("Update Entreprise : " + entrepriseDto1.toString());
        log.info(line);
        return HttpStatus.OK;
    }

    @DeleteMapping("/entreprises/{id}")
    public HttpStatus deleteEntreprise(@PathVariable long id){
        this.entrepriseService.deleteEntreprise(id);
        return HttpStatus.OK;
    }

    @GetMapping("/entreprises/{id}")
    public HttpStatus getEntrepriseById(@PathVariable long id){
        EntrepriseDto entrepriseById = entrepriseService.getEntrepriseById(id);
        log.info(line);
        log.info("Get Entreprise : " + entrepriseById.toString());
        log.info(line);
        ResponseEntity.ok().body(entrepriseById);
        return HttpStatus.OK;
    }
}
