package com.restapplication.service;

import com.restapplication.model.*;
import com.restapplication.model.Entreprise;
import com.restapplication.repository.EntrepriseRepository;
import com.restapplication.service.dto.ContactDto;
import com.restapplication.service.dto.EntrepriseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.restapplication.service.MapperDtoUtil.getEntrepriseDto;

@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService{

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public EntrepriseDto createEntreprise(EntrepriseDto entrepriseDto) {
        Entreprise entreprise1 = Entreprise.builder()
                .adress(entrepriseDto.getAdress())
                .tva(entrepriseDto.getTva())
                .build();
        Entreprise entrepriseDb = entrepriseRepository.save(entreprise1);
        return getEntrepriseDto(entrepriseDb);
    }

    @Override
    public EntrepriseDto updateEntrepriseDto(EntrepriseDto entrepriseDto) {
        Optional<Entreprise> entrepriseDb = this.entrepriseRepository.findById(entrepriseDto.getId());
        if (entrepriseDb.isPresent()) {
            Entreprise entreprise = entrepriseDb.get();
            entreprise.setAdress(entrepriseDto.getAdress());
            entreprise.setTva(entrepriseDto.getTva());
            Entreprise entreprise1 = entrepriseRepository.save(entreprise);
            return getEntrepriseDto(entreprise1);
        } else {
            throw new RuntimeException("Entreprise not found with id : " + entrepriseDto.getId());
        }
    }

    @Override
    public EntrepriseDto addContact(long entrepriseId, ContactDto contactDto) {
        Optional<Entreprise> entreprise = entrepriseRepository.findById(entrepriseId);
        if(entreprise.isPresent()){
            Entreprise entrepriseDb = entreprise.get();
            Entreprise entreprise1 = null;
            if(contactDto.getTva() != null) {
                Freelance freelance = Freelance.builder()
                        .entreprises(Collections.singletonList(entrepriseDb))
                        .name(contactDto.getName())
                        .firstname(contactDto.getFirstname())
                        .adress(contactDto.getAdress())
                        .tva(contactDto.getTva())
                        .build();
                Set<Contact> contacts = new HashSet<>();
                contacts.add(freelance);
                entrepriseDb.setContacts(contacts);
                entreprise1 = entrepriseRepository.save(entrepriseDb);
            } else {
                Employee employee = Employee.builder()
                        .entreprises(Collections.singletonList(entrepriseDb))
                        .name(contactDto.getName())
                        .firstname(contactDto.getFirstname())
                        .adress(contactDto.getAdress())
                        .build();
                Set<Contact> contacts = new HashSet<>();
                contacts.add(employee);
                entrepriseDb.setContacts(contacts);
                entreprise1 = entrepriseRepository.save(entrepriseDb);
            }

            return getEntrepriseDto(entreprise1);
        } else {
            throw new RuntimeException("Entreprise not found with id : " + entrepriseId);
        }
    }

    @Override
    public EntrepriseDto getEntrepriseById(long entrepriseId){

        Optional<Entreprise> entrepriseDb = this.entrepriseRepository.findById(entrepriseId);

        if(entrepriseDb.isPresent()) {
            return getEntrepriseDto(entrepriseDb.get());
        } else {
            throw new RuntimeException("Entreprise not found with id : " + entrepriseId);
        }
    }

    @Override
    public void deleteEntreprise(long entrepriseId) {
        Optional<Entreprise> entrepriseDb = entrepriseRepository.findById(entrepriseId);
        if(entrepriseDb.isPresent()) {
            entrepriseRepository.delete(entrepriseDb.get());
        } else {
            throw new RuntimeException("Entreprise not found with id : " + entrepriseId);
        }
    }
}
