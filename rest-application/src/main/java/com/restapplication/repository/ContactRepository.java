package com.restapplication.repository;

import com.restapplication.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long > {
}
