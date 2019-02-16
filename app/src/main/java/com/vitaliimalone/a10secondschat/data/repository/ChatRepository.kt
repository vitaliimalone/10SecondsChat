package com.vitaliimalone.a10secondschat.data.repository

import com.vitaliimalone.a10secondschat.data.repository.local.ChatLocalDataSource
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class ChatRepository(
        private val chatLocalDataSource: ChatLocalDataSource
) {
    fun getAllChats(): Flowable<List<ChatEntity>> {
        return chatLocalDataSource.getAllChats()
    }

    fun saveChat(chat: ChatEntity): Completable {
        return chatLocalDataSource.saveChat(chat)
    }
}