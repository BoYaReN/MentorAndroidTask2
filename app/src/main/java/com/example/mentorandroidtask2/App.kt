package com.example.mentorandroidtask2

import android.app.Application
import com.example.mentorandroidtask2.modelContact.ContactsService

class App: Application() {

    val contactsService = ContactsService()
}