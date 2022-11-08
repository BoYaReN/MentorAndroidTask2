package com.example.mentorandroidtask2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mentorandroidtask2.databinding.ActivityMainBinding
import com.example.mentorandroidtask2.modelContact.Contact
import com.example.mentorandroidtask2.modelContact.ContactsAdapter
import com.example.mentorandroidtask2.modelContact.ContactsListener
import com.example.mentorandroidtask2.modelContact.ContactsService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: ContactsAdapter by lazy { ContactsAdapter() }

    private val contactsService: ContactsService
        get() = (applicationContext as App).contactsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        binding.ContactsRecycleView.layoutManager = layoutManager
        binding.ContactsRecycleView.adapter = adapter

        adapter.contacts = listOf(Contact(1, "Bohdan", "Yaremchak" , "" ))

        //contactsService.addListener(contactListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        contactsService.removeListener(contactListener)
    }

    private val contactListener : ContactsListener = {
        adapter.contacts = it
    }
}