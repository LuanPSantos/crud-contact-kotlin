package com.luan.contact.contact.controller

import com.luan.contact.contact.service.ContactService
import com.luan.contact.contact.model.Contact
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ContactController @Autowired constructor(
    private val contactService: ContactService
) {

    @GetMapping("contacts")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<Contact> {
        return this.contactService.findAll()
    }

    @PostMapping("contacts")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid contact: Contact) {
        this.contactService.save(contact)
    }

    @PutMapping("contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody @Valid contact: Contact) {
        contact.id = id
        this.contactService.save(contact)
    }

    @DeleteMapping("contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable id: Long) {
        this.contactService.delete(id)
    }
}