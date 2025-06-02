package com.example.test.chat

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatMainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatAdapter: ChatAdapter
    private val messagesList = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_main)

        recyclerView = findViewById(R.id.recyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(messagesList)
        recyclerView.adapter = chatAdapter

        val dbRef = FirebaseDatabase.getInstance().getReference("chats/chatId1/messages")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messagesList.clear()
                snapshot.children.forEach {
                    it.getValue(ChatMessage::class.java)?.let { message -> messagesList.add(message) }
                }
                chatAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching messages", error.toException())
            }
        })

        sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val dbRef = FirebaseDatabase.getInstance().getReference("chats/chatId1/messages")
        val messageId = dbRef.push().key ?: return
        val message = ChatMessage(id = messageId, senderId = "user1", text = messageInput.text.toString())
        dbRef.child(messageId).setValue(message)
        messageInput.setText("")
    }
}