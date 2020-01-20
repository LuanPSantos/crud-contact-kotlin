package com.luan.contact.contact

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContactService @Autowired constructor(
    private val contactRepository: ContactRepository
) {

    fun findAll(): List<Contact> {
        return contactRepository.findAll().toList();
    }

    fun save(contact: Contact) {
        contactRepository.save(contact);
    }

    fun delete(id: Long) {
        contactRepository.deleteById(id);
    }
}