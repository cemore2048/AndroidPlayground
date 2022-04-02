package com.example.androidplayground

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hello: Observable<String> = Observable.just("")

        Observable.just("hello").withLatestFrom(Observable.just("second string"))
            .fl

            Icon(
                Icons.Rounded.Menu,
            //    contentDescription = null,
            //    modifier = Modifier.height(100.dp),
            //    tint = Color.White
            //)
            Box(
                modifier = Modifier.background(Color.Blue).size(100.dp),
            content = {
                Text("Hello")
            })
        }

    }
}
