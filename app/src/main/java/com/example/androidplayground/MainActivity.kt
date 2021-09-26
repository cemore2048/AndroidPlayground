package com.example.androidplayground

import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import coil.bitmap.BitmapPool
import coil.load
import coil.size.Size
import coil.transform.Transformation

class MainActivity : AppCompatActivity() {
    private lateinit var coilImage: ImageView
    private lateinit var regularImage: ImageView

    val color = Color.RED
    val mode = PorterDuff.Mode.SRC_IN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coilImage = findViewById(R.id.coilImage)
        regularImage = findViewById(R.id.regularImage)

        regularImage.setImageResource(R.drawable.ic_baseline_outline_mall)
        regularImage.colorFilter = PorterDuffColorFilter(color, mode)

        coilImage.load(R.drawable.ic_baseline_outline_mall) {
            transformations(ColorTintTransformation(color, mode))
        }
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
