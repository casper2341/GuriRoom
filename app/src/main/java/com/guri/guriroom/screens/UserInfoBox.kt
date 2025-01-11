package com.guri.guriroom.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.guri.guriroom.room.User

@Composable
fun UserInfoBox(user: User, triggerEvent: (UserListEvents) -> Unit) {
    Spacer(modifier = Modifier.height(2.dp))
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
            .border(shape = RoundedCornerShape(10.dp), width = 1.dp, color = Color.Red)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = Modifier.weight(1f), text = user.id.toString())
        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier.weight(7f)) {
            Text(text = user.name)
            Text(text = user.email)
        }
        Spacer(modifier = Modifier.height(4.dp))
        IconButton(onClick = {
            triggerEvent(UserListEvents.OpenDialog(user))
        }, content = {
            Icon(
                modifier = Modifier.weight(1f),
                imageVector = Icons.Default.Edit,
                contentDescription = ""
            )
        })
        Spacer(modifier = Modifier.height(4.dp))
        IconButton(onClick = {
            triggerEvent(UserListEvents.DeleteUser(user))
        }, content = {
            Icon(
                modifier = Modifier.weight(1f),
                imageVector = Icons.Default.Delete,
                contentDescription = ""
            )
        })
    }
}