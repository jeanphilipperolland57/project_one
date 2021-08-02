package infra;


import domain.service.dto.ContactDto;
import domain.service.dto.EntrepriseDto;
import infra.model.Employee;
import infra.model.Entreprise;
import infra.model.Freelance;

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
                        .tva(entreprise.getTva())
                        .adress(entreprise.getAdress())
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
                        .build()).collect(Collectors.toList()))
                .build();
    }

    public static List<Entreprise> getEntreprises(ContactDto contactDto) {
        return contactDto.getEntreprises().stream().map(entreprise -> Entreprise.builder()
                .adress(entreprise.getAdress())
                .tva(entreprise.getTva()).build()).collect(Collectors.toList());
    }

}
