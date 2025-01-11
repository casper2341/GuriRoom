package com.guri.guriroom.screens

import com.guri.guriroom.room.User

sealed class UserListEvents {
    data class UserNameChanged(val name: String) : UserListEvents()
    data class UserEmailChanged(val email: String) : UserListEvents()
    data class SaveUser(val name: String, val email: String) : UserListEvents()
    data class OpenDialog(val user: User) : UserListEvents()
    data class UpdateUser(val user: User) : UserListEvents()
    data class DeleteUser(val user: User) : UserListEvents()
    data class EditUserNameChanged(val name: String) : UserListEvents()
    data class EditUserEmailChanged(val email: String) : UserListEvents()
}