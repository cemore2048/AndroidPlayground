package com.example.androidplayground

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {
    
    private lateinit var catImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catImage = findViewById(R.id.image)

        Thread(object : Runnable {
            var bitmap: Bitmap? = null
            override fun run() {
                val input: InputStream = URL("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.RGO2_JeImrSM658MPIASrwHaEo%26pid%3DApi&f=1").openStream()
                bitmap = BitmapFactory.decodeStream(input)

                Handler(Looper.getMainLooper()).post { catImage.setImageBitmap(bitmap) }
            }
        }).start()
    }
}
