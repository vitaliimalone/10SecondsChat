package com.vitaliimalone.a10secondschat.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vitaliimalone.a10secondschat.domain.interactors.HomeInteractor
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.MainRouter
import com.vitaliimalone.a10secondschat.presentation.base.BaseViewModel

class HomeViewModel(
        private val mainRouter: MainRouter,
        private val homeInteractor: HomeInteractor
) : BaseViewModel() {
    val showDialog by lazy { MutableLiveData<Boolean>() }
    val allChats by lazy { MutableLiveData<List<Chat>>() }

    init {
        getAllChats()
    }

    private fun getAllChats() {
        autoDispose(homeInteractor.getAllChats()
                .subscribe({
                    allChats.value = it
                }, {
                    Log.e(HomeViewModel::class.java.simpleName, it.message, it)
                }))
    }

    fun onFabClick() {
        showDialog.value = true
    }

    fun onCancelDialogClick() {
        showDialog.value = false
    }

    fun onCreateDialogClick(title: String) {
        autoDispose(homeInteractor.saveChat(Chat(title = title))
                .subscribe({
                    showDialog.value = false
                }, {
                    Log.e(HomeViewModel::class.java.simpleName, it.message, it)
                }))
    }
}