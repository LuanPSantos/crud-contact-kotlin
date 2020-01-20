package com.luan.contact.contact

import org.springframework.data.repository.CrudRepository;

interface ContactRepository : CrudRepository<Contact, Long> {
}