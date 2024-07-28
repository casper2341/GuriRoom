package com.guri.guriroom.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserListScreen(viewModel: UserListViewModel) {

   val state = viewModel.state

    if (state.openDialog) {
        EditDialog(state = state, triggerEvent = viewModel::triggerEvent)
    }
    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = state.name,
            onValueChange = { newName ->
                viewModel.triggerEvent(UserListEvents.UserNameChanged(newName))
            },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = state.emailId,
            onValueChange = { newName ->
                viewModel.onEmailChange(newName)
            },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    viewModel.triggerEvent(UserListEvents.SaveUser(state.name, state.emailId))
                }
            ) {
                Text(text = "Save")
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn {
            items(state.allUsers) {
                UserInfoBox(it, triggerEvent = viewModel::triggerEvent)
            }
        }
    }
}