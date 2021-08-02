package com.restapplication.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Data
@Entity
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue("Freelance")
public class Freelance extends Contact {
    @Column(name = "TVA")
    private String tva;

}
