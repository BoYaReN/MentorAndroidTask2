package com.example.mentorandroidtask2.modelContact

import com.github.javafaker.Faker

typealias ContactsListener = (contacts: List<Contact>) -> Unit

class ContactsService {

    private var contacts = mutableListOf<Contact>()

    private val listeners = mutableSetOf<ContactsListener>()

    init {
        val faker = Faker.instance()
        contacts = (1..100).map { Contact(
            id = it.toLong(),
            name = faker.name().name(),
            photo = faker.avatar().image().toString(),
            career = faker.company().name()
        ) }.toMutableList()
    }

    fun getContact(): List<Contact>{
        return contacts
    }

    fun deleteContact(contact: Contact){
        val indexToDelete = contacts.indexOfFirst { it.id == contact.id }
        if (indexToDelete != -1){
            contacts.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun addListener(listener: ContactsListener){
        listeners.add(listener)
        listener.invoke(contacts)
    }

    fun removeListener(listener: ContactsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(contacts)}
    }
}