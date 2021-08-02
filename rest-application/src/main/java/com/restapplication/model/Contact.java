package com.restapplication.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "CONTACT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="contact_type")
public abstract class Contact {

    @Id
    @Column(name = "CONTACT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ADRESS")
    private String adress;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CONTACT_ENTREPRISE",
            joinColumns = @JoinColumn(name = "CONTACT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ENTREPRISE_ID"))
    private List<Entreprise> entreprises;
}
