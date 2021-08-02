package infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Table(name = "EMPLOYEE")
@DiscriminatorValue("Employee")
public class Employee extends Contact {
}
