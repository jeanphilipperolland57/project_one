package com.restapplication.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class ContactDto {
    private Long id;
    private String adress;
    private String name;
    private String firstname;
    private String tva;
    private List<EntrepriseDto> entreprises;
}
