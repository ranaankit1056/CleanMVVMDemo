package com.example.test.chat

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatMainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_main)

        recyclerView = findViewById(R.id.recyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(emptyList()) // Initialize adapter
        recyclerView.adapter = chatAdapter

        // Initialize ViewModel
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        // Observe LiveData
        chatViewModel.messages.observe(this) { messages ->
            chatAdapter.updateMessages(messages) // Update RecyclerView when data changes
        }

        sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        chatViewModel.sendMessage("user1", messageInput.text.toString())
        messageInput.setText("")
    }
}