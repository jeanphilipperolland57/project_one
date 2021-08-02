package com.restapplication.service;

import com.restapplication.service.dto.ContactDto;
import com.restapplication.service.dto.EntrepriseDto;

public interface EntrepriseService {
    EntrepriseDto createEntreprise(EntrepriseDto entrepriseDto);

    EntrepriseDto updateEntrepriseDto(EntrepriseDto Entreprise);

    EntrepriseDto addContact(long EntrepriseId, ContactDto contactDto);

    public EntrepriseDto getEntrepriseById(long entrepriseId);

    void deleteEntreprise(long id);
}
