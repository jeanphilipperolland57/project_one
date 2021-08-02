package domain.service.dto;

import com.infra.model.Contact;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EntrepriseDto {
    private Long id;
    private String adress;
    private String tva;
    private Set<Contact> contact;
}
