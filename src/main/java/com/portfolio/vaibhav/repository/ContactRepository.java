package com.portfolio.vaibhav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.vaibhav.entities.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
