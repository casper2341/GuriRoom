package com.guri.guriroom.screens

import com.guri.guriroom.room.User

data class UiState(
    val name: String = "",
    val emailId: String = "",
    val isLoading: Boolean = false,
    val allUsers: List<User> = emptyList(),
    val openDialog: Boolean = false,
    val editUser: User = User(-1, "", "")
)