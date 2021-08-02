package infra.repository;

import domain.service.dto.EntrepriseDto;
import infra.MapperDtoUtil;
import domain.service.dto.ContactDto;
import infra.model.Contact;
import infra.model.Employee;
import infra.model.Entreprise;
import infra.model.Freelance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Long >,domain.repository.ContactRepository{

    @Override
    default ContactDto save(ContactDto contactDto) {
        List<Entreprise> entreprises = MapperDtoUtil.getEntreprises(contactDto);
        if(contactDto.getTva() != null){
            Freelance freelance = Freelance.builder()
                    .tva(contactDto.getTva())
                    .firstname(contactDto.getFirstname())
                    .name(contactDto.getName())
                    .adress(contactDto.getAdress())
                    .entreprises(entreprises)
                    .build();
            Freelance freelanceDao = this.save(freelance);
            freelance.getEntreprises().forEach(entreprise ->  entreprise.setContacts(Collections.singleton(freelanceDao)));
            return MapperDtoUtil.getContactDto(freelanceDao);
        } else {
            Employee employee = Employee.builder()
                    .firstname(contactDto.getFirstname())
                    .name(contactDto.getName())
                    .adress(contactDto.getAdress())
                    .entreprises(entreprises)
                    .build();
            Employee employeeDao = this.save(employee);
            entreprises.forEach(entreprise -> entreprise.setContacts(Collections.singleton(employeeDao)));
            return MapperDtoUtil.getContactDto(employeeDao);
        }
    }

    @Override
    default ContactDto findById(ContactDto contactDto){
        Optional<Contact> contact = this.findById(contactDto.getId());
        if(contact.isPresent()){
            Contact contact1 = contact.get();
            return ContactDto.builder()
                    .adress(contact1.getAdress())
                    .firstname(contact1.getFirstname()).build();
        } else {
            throw new RuntimeException("Contact not found with id : " + contactDto.getId());
        }
    }

    @Override
    default ContactDto update(ContactDto contactDto){
        Optional<Contact> contactDb = this.findById(contactDto.getId());
        if (contactDb.isPresent()) {
            if (contactDb.get() instanceof Freelance) {
                Employee employeeUpdate = (Employee) contactDb.get();
                employeeUpdate.setId(contactDto.getId());
                employeeUpdate.setName(contactDto.getName());
                employeeUpdate.setFirstname(contactDto.getFirstname());
                employeeUpdate.setAdress(contactDto.getAdress());
                employeeUpdate.setEntreprises(MapperDtoUtil.getEntreprises(contactDto));
                Employee employee = this.save(employeeUpdate);

                return MapperDtoUtil.getContactDto(employee);
            } else {
                Freelance freelanceUpdate = (Freelance) contactDb.get();
                freelanceUpdate.setId(contactDto.getId());
                freelanceUpdate.setName(contactDto.getName());
                freelanceUpdate.setFirstname(contactDto.getFirstname());
                freelanceUpdate.setAdress(contactDto.getAdress());
                freelanceUpdate.setEntreprises(MapperDtoUtil.getEntreprises(contactDto));
                freelanceUpdate.setTva(contactDto.getTva());
                Freelance freelance = this.save(freelanceUpdate);
                return MapperDtoUtil.getContactDto(freelance);
            }
        } else {
            throw new RuntimeException("Contact not found with id : " + contactDto.getId());
        }
    }

    default ContactDto getContactById(long contactId) {
        Optional<Contact> contactDb = this.findById(contactId);

        if (contactDb.isPresent()) {
            if (contactDb.get() instanceof Freelance) {
                Freelance freelance = (Freelance) contactDb.get();
                return MapperDtoUtil.getContactDto(freelance);
            } else {
                Employee employee = (Employee) contactDb.get();
                return MapperDtoUtil.getContactDto(employee);
            }
        } else {
            throw new RuntimeException("Contact not found with id : " + contactId);
        }
    }

    default void deleteContact(long contactId){
        Optional<Contact> contactDb = this.findById(contactId);
        if(contactDb.isPresent()) {
            this.delete(contactDb.get());
        } else {
            throw new RuntimeException("Contact not found with id : " + contactId);
        }
    }

}
