package com.guri.guriroom.screens

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.guri.guriroom.GuriRoom
import com.guri.guriroom.room.User
import com.guri.guriroom.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel constructor(
    private val application: Application
): AndroidViewModel(application) {

    var state by mutableStateOf(UiState())
        private set

    private var events = MutableSharedFlow<UserListEvents>()
    private var userDao : UserDao? = null

    init {
        handleIntent()
        userDao = (application as GuriRoom).db.userDao()
        getAllUser()
    }

    fun triggerEvent(intent: UserListEvents) {
        viewModelScope.launch {
            events.emit(intent)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    is UserListEvents.SaveUser -> {
                        insertUser(user = User(name = it.name, email = it.email))
                    }
                    is UserListEvents.UserEmailChanged -> {
                        onEmailChange(it.email)
                    }
                    is UserListEvents.UserNameChanged -> {
                        onNameChange(it.name)
                    }

                    is UserListEvents.DeleteUser -> {
                        deleteUser(it.user)
                    }

                    is UserListEvents.OpenDialog -> {
                        Log.d("GURDEEP", "open dialog: ${it.user}")
                        state = state.copy(openDialog = true, editUser = it.user)
                    }

                    is UserListEvents.UpdateUser -> {
                        Log.d("GURDEEP", "handleIntent: ${it.user}")
                        updateUser(it.user)
                        state = state.copy(openDialog = false)
                    }

                    is UserListEvents.EditUserEmailChanged -> {
                        state = state.copy(editUser = User(id = state.editUser.id, name = state.editUser.name, email = it.email))
                    }
                    is UserListEvents.EditUserNameChanged -> {
                        state = state.copy(editUser = User(id =state.editUser.id, it.name, email = state.editUser.email))
                    }
                }
            }
        }
    }

    private fun onNameChange(newName: String) {
        state = state.copy(name = newName)
    }

    fun onEmailChange(email: String) {
        state = state.copy(emailId = email)
    }

    private fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao?.insert(user)
            getAllUser()
        }
    }

    private fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("GURDEEP", "updateUser: $user")
            userDao?.update(user)
            getAllUser()
        }
    }

    private fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao?.delete(user)
            getAllUser()
        }
    }

    private fun getAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = userDao?.fetchAllUsers() ?: emptyList()
            withContext(Dispatchers.Main) {
                state = state.copy(allUsers = list)
            }
        }
    }
}