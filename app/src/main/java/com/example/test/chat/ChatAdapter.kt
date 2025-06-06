package com.example.test.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ChatAdapter(private var messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val senderText: TextView = view.findViewById(R.id.senderTextView)
        val messageText: TextView = view.findViewById(R.id.messageTextView)
        val timeText: TextView = view.findViewById(R.id.timestampTextView)

    }

    class OtherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val senderText: TextView = view.findViewById(R.id.senderTextView)
        val messageText: TextView = view.findViewById(R.id.messageTextView)
        val timeText: TextView = view.findViewById(R.id.timestampTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType ==0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.my_side_msg, parent, false)
            return MyViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.other_side_msg, parent, false)
            return OtherViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]

        val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault())
        val date = Date(message.timestamp)

        if (holder is MyViewHolder) {
            holder as MyViewHolder?
            holder.senderText.text = message.senderId
            holder.messageText.text = message.text
            holder.timeText.text =dateFormat.format(date)
        }else if (holder is OtherViewHolder){
            holder as OtherViewHolder?
            holder.senderText.text = message.senderId
            holder.messageText.text = message.text
            holder.timeText.text = dateFormat.format(date)
        }

    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        val viewType = if (messages[position].senderId == "user1") 0 else 1
        return viewType
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMessages(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged() // Refresh RecyclerView
    }
}
