package com.guri.guriroom.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun EditDialog(
    state: UiState,
    modifier: Modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 16.dp)
        .fillMaxWidth(),
    triggerEvent: (UserListEvents) -> Unit
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = true
        ),
        content = {
            Column(
                modifier = modifier.background(
                    color = Color.Yellow,
                    shape = RoundedCornerShape(10.dp)
                )
            ) {
                Text(text = "Edit User", modifier = modifier)
                OutlinedTextField(
                    value = state.editUser.name,
                    onValueChange = { newName ->
                        triggerEvent(UserListEvents.EditUserNameChanged(newName))
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "") },
                    modifier = modifier,
                )
                OutlinedTextField(
                    value = state.editUser.email,
                    onValueChange = { newName ->
                        triggerEvent(UserListEvents.EditUserEmailChanged(newName))
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "") },
                    modifier = modifier,
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