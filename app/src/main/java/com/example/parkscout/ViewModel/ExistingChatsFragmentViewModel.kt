package com.example.parkscout.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.parkscout.Repository.Chat
import com.example.parkscout.Repository.ChatMessage
import com.example.parkscout.Repository.ChatWithAll
import com.example.parkscout.Repository.Model.ChatModel

class ExistingChatsFragmentViewModel: ViewModel() {
    // Data Members
    lateinit var chatList: LiveData<List<ChatWithAll>>
        private set;

    // Constructors
    init {
        this.chatList = ChatModel.instance.getAllChats();
    }
}