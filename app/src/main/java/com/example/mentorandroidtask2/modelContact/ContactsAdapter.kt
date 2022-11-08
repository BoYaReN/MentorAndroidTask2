package com.example.mentorandroidtask2.modelContact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mentorandroidtask2.R
import com.example.mentorandroidtask2.databinding.ItemContactBinding

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    var contacts: List<Contact> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = contacts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        with(holder.binding){
            contactNameTextView.text = contact.name
            contactCareerTextView.text = contact.career
            if(contact.photo.isNotBlank()){
                Glide.with(avatarImageView.context)
                    .load(contact.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_contact_avatar)
                    .error(R.drawable.ic_contact_avatar)
                    .into(avatarImageView)
            } else {
                avatarImageView.setImageResource(R.drawable.ic_contact_avatar)
            }

        }
    }

    class ContactsViewHolder(
        val binding: ItemContactBinding
    ): RecyclerView.ViewHolder(binding.root)
}