package com.restapplication.service;

import com.restapplication.model.Employee;
import com.restapplication.model.Entreprise;
import com.restapplication.model.Freelance;
import com.restapplication.service.dto.ContactDto;
import com.restapplication.service.dto.EntrepriseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MapperDtoUtil {
    public static ContactDto getContactDto(Freelance freelance) {
        return ContactDto.builder()
                .id(freelance.getId())
                .adress(freelance.getAdress())
                .firstname(freelance.getFirstname())
                .name(freelance.getName())
                .tva(freelance.getTva())
                .entreprises(freelance.getEntreprises().stream().map(entreprise -> EntrepriseDto.builder()
                        .id(entreprise.getId())
                        .tva(entreprise.getTva())
                        .adress(entreprise.getAdress())
                        .contact(entreprise.getContacts())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    public static ContactDto getContactDto(Employee employee) {
        return ContactDto.builder()
                .id(employee.getId())
                .adress(employee.getAdress())
                .firstname(employee.getFirstname())
                .name(employee.getName())
                .entreprises(employee.getEntreprises().stream().map(entreprise -> EntrepriseDto.builder()
                        .tva(entreprise.getTva())
                        .adress(entreprise.getAdress())
                        .contact(entreprise.getContacts())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    public static List<Entreprise> getEntreprises(ContactDto contactDto) {
        return contactDto.getEntreprises().stream().map(entreprise -> Entreprise.builder()
                .adress(entreprise.getAdress())
                .tva(entreprise.getTva()).build()).collect(Collectors.toList());
    }

    public static EntrepriseDto getEntrepriseDto(Entreprise entreprise) {
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .tva(entreprise.getTva())
                .adress(entreprise.getAdress())
                .contact(entreprise.getContacts())
                .build();
    }
}
