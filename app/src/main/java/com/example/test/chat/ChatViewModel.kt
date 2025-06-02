package com.example.test.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatViewModel : ViewModel() {
    private val dbRef = FirebaseDatabase.getInstance().getReference("chats/chatId1/messages")

    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> = _messages

    init {
        loadMessages()
    }

    private fun loadMessages() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messagesList = snapshot.children.mapNotNull { it.getValue(ChatMessage::class.java) }
                _messages.value = messagesList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching messages", error.toException())
            }
        })
    }

    fun sendMessage(senderId: String, text: String) {
        val messageId = dbRef.push().key ?: return
        val message = ChatMessage(id = messageId, senderId = senderId, text = text)
        dbRef.child(messageId).setValue(message)
    }
}
