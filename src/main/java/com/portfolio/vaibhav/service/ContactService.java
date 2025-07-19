package com.portfolio.vaibhav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.vaibhav.entities.Contact;
import com.portfolio.vaibhav.entities.Prompt;
import com.portfolio.vaibhav.repository.AiRepository;
import com.portfolio.vaibhav.repository.ContactRepository;

@Service
public class ContactService {
@Autowired
private ContactRepository contactRepository;

@Autowired
private AiRepository aiRepository;

public Contact addContact(Contact contact) {
	return contactRepository.save(contact);
}

public Prompt getResponse(Prompt prompt) {
    return aiRepository.givePrompt(prompt);
}



}
