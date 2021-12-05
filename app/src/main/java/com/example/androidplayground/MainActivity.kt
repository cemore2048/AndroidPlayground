package com.example.androidplayground

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val repository =
        ExecutionRepository(executionEndpoint = ExecutionEndpoint.createEndpoint())
    private val executionViewModel: ExecutionViewModel by lazy {
        ViewModelProvider(
            this,
            createWithFactory { ExecutionViewModel(repository) }
        )[ExecutionViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var text by rememberSaveable { mutableStateOf("") }
            Column {
                AppTopBar {
                    executionViewModel.execute(text)
                }
                Editor(text) {
                    text = it
                }
            }
        }
    }

    @Composable
    fun AppTopBar(onClick: () -> Unit) {
        TopAppBar(elevation = 25.dp) {
            Text(text = "Repl.it mobile")
            IconButton(
                content = {
                    Icon(Icons.Filled.PlayArrow, "Run")
                },
                onClick = {
                    onClick()
                }
            )
        }
    }

    @Composable
    fun Editor(code: String, onCodeChanged: (String) -> Unit) {
        TextField(
            value = code,
            onValueChange = {
                onCodeChanged(it)
            },
            placeholder = { Text("Write your code here") },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }

    private fun createWithFactory(
        create: () -> ViewModel
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return create.invoke() as T
            }
        }
    }

}