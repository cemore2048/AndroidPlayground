package com.example.androidplayground

import android.graphics.drawable.shapes.Shape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ExecutionRepository

    private val executionViewModel: ExecutionViewModel by lazy {
        ViewModelProvider(
            this,
            createWithFactory { ExecutionViewModel(repository) }
        )[ExecutionViewModel::class.java]
    }

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.create().inject(this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

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
        TopAppBar(elevation = 30.dp) {
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
        TextField(
            value = code,
            onValueChange = {
                onCodeChanged(it)
            },
            placeholder = { Text("") },
            modifier = Modifier
                .weight(.75f)
                .fillMaxWidth(),
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
                .background(shape = AbsoluteCutCornerShape(0), color = Color.DarkGray),

            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.DarkGray,
                textColor = Color.LightGray,
                placeholderColor = Color.LightGray
            )
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