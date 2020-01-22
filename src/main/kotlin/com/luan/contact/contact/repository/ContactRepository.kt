package com.luan.contact.contact.repository

import com.luan.contact.contact.model.Contact
import org.springframework.data.repository.CrudRepository;

interface ContactRepository : CrudRepository<Contact, Long> {
}