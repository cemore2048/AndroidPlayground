package com.example.androidplayground

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
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

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var text by rememberSaveable { mutableStateOf("") }
            val output: String? by executionViewModel.executionLiveData.observeAsState()

            Column {
                AppTopBar {
                    executionViewModel.execute(text)
                }
                Editor(text) { text = it }
                Console(output = output ?: "")
            }
        }
    }

    @ExperimentalComposeUiApi
    @Composable
    fun AppTopBar(onClick: () -> Unit) {
        val keyboardController = LocalSoftwareKeyboardController.current
        TopAppBar(elevation = 25.dp) {
            Text(
                text = "Repl.it mobile",
                Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
            IconButton(
                content = {
                    Icon(Icons.Filled.PlayArrow, "Run")
                },
                onClick = {
                    keyboardController?.hide()
                    onClick()
                }
            )
        }
    }

    @Composable
    fun ColumnScope.Editor(code: String, onCodeChanged: (String) -> Unit) {
        val focusManager = LocalFocusManager.current
        TextField(
            value = code,
            onValueChange = {
                onCodeChanged(it)
            },
            placeholder = { Text("") },
            modifier = Modifier
                .weight(.75f)
                .fillMaxWidth()

        )
    }

    @Composable
    fun ColumnScope.Console(output: String) {
        TextField(
            value = output,
            onValueChange = {},
            placeholder = { Text("Your output will load here") },
            modifier = Modifier
                .weight(.25f)
                .fillMaxWidth()
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