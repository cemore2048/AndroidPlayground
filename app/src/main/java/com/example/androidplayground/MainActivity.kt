package com.example.androidplayground

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.contextaware.withContextAvailable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import java.net.URL


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //8000x8000
        val url8000x8000 = "https://images.wallpapersden.com/image/download/radical-raphael-loki-tmnt-x-smite_bGhuamiUmZqaraWkpJRtZWVlrW1lZWU.jpg"
        //4096x4096
        val url4096x4096 = "https://wallpaperaccess.com/full/721158.jpg"


        val imageView = findViewById<ImageView>(R.id.image)
        imageView.load(url8000x8000) {
            transformations(CircleCropTransformation())
        }
        //GlobalScope.launch(Dispatchers.IO) {
            val input = URL(url8000x8000).openStream()
            val bitmap = BitmapFactory.decodeStream(input)
            withContext(Dispatchers.Main) {
                imageView.setImageBitmap(bitmap)
            }
        //}

        GlobalScope.launch(Dispatchers.IO) {
            delay(10000)


            withContext(Dispatchers.Main) {
                val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                val imageUrlList = List(100) { "https://wallpaperaccess.com/full/721158.jpg" }  // Replace with actual image URLs.
                val imageAdapter = ImageAdapter(imageUrlList)
                recyclerView.adapter = imageAdapter
            }

        }

    }
}

