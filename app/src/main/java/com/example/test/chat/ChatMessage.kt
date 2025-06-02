package com.example.test.chat

data class ChatMessage(
    val id: String = "",
    val senderId: String = "",
    val text: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
