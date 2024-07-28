package com.guri.guriroom.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.guri.guriroom.room.User

@Composable
fun EditDialog(state: UiState, modifier: Modifier = Modifier, triggerEvent: (UserListEvents) -> Unit) {
    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties(dismissOnBackPress = true, usePlatformDefaultWidth = false, dismissOnClickOutside = true),
        content = {
            Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(color = Color.Yellow)) {
                OutlinedTextField(
                    value = state.editUser.name,
                    onValueChange = { newName ->
                        triggerEvent(UserListEvents.EditUserNameChanged(newName))
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "") },
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = state.editUser.email,
                    onValueChange = { newName ->
                        triggerEvent(UserListEvents.EditUserEmailChanged(newName))
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            triggerEvent(UserListEvents.UpdateUser(state.editUser))
                        }
                    ) {
                        Text(text = "Edit")
                    }
                }
            }
        }
    )
}