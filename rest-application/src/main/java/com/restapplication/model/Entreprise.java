package com.restapplication.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ENTREPRISE")
public class Entreprise {

    @Id
    @Column(name = "ENTREPRISE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ADRESS")
    private String adress;

    @Column(name = "TVA")
    private String tva;

    @ManyToMany(mappedBy = "entreprises", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Contact> contacts;
}
