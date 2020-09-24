package com.example.androidplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val myCanvasView = MyCanvas(this)
//        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
//        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)

        setContent {
            Greeting("Hello, World")
        }
        val clippedView = ClippedView(this)
        setContentView(clippedView)
    }

    @Composable
    fun Greeting(name: String) {
        Text(name)
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        Greeting("This is my greeting")
    }
}
