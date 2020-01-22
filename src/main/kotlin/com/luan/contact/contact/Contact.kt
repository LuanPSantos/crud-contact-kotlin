package com.luan.contact.contact

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @get:NotBlank(message = "Nome vazio")
    @get:NotNull(message = "Nome nulo")
    var name: String?,
    @get:NotBlank(message = "Telefone vazio")
    @get:NotNull(message = "Telefone nulo")
    var phone: String?,
    @get:Min(value = 1, message = "Contato sem usuário")
    @get:NotNull(message = "Contato sem usuário")
    var userId: Long?) {
}