package com.example.androidplayground

import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.bitmap.BitmapPool
import coil.load
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.Transformation
import coil.util.CoilUtils
import coil.util.DebugLogger
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {
    private lateinit var coilImage: ImageView
    private lateinit var regularImage: ImageView

    val color = Color.RED
    val mode = PorterDuff.Mode.SRC_IN

    fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
                .crossfade(true)
                .okHttpClient {
                    OkHttpClient.Builder()
                            .cache(CoilUtils.createDefaultCache(applicationContext))
                            .build()
                }
                .logger(DebugLogger())
                .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coilImage = findViewById(R.id.coilImage)
        regularImage = findViewById(R.id.regularImage)

        regularImage.setImageResource(R.drawable.ic_baseline_outline_mall)
        regularImage.colorFilter = PorterDuffColorFilter(color, mode)

        val request = ImageRequest.Builder(this)
                .data(R.drawable.ic_baseline_outline_mall)
                .transformations(ColorTintTransformation(color, mode))
                .target(coilImage)
                .build()
        newImageLoader().enqueue(request)
    }
}


class ColorTintTransformation(
        @ColorInt private val color: Int,
        private val mode: PorterDuff.Mode = PorterDuff.Mode.SRC_ATOP
) : Transformation {

    override fun key(): String = "${ColorTintTransformation::class.java.name}-$mode-$color"

    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val width = input.width
        val height = input.height

        val config = input.config
        val output = pool.get(width, height, config)

        val canvas = Canvas(output)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.colorFilter = PorterDuffColorFilter(color, mode)
        canvas.drawBitmap(input, 0f, 0f, paint)

        return output
    }
}
