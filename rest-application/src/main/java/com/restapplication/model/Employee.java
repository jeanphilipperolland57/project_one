package com.restapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@DiscriminatorValue("Employee")
public class Employee extends Contact {
}
