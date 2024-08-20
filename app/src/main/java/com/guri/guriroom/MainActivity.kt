package com.guri.guriroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guri.guriroom.screens.UserListScreen
import com.guri.guriroom.screens.UserListViewModel
import com.guri.guriroom.ui.theme.GuriRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuriRoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel : UserListViewModel = ViewModelProvider(this)[UserListViewModel::class.java]
                    Box(modifier = Modifier.padding(innerPadding)) {
                        UserListScreen(viewModel)
                    }
                }
            }
        }
    }
}