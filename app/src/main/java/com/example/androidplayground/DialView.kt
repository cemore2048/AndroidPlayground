package com.example.androidplayground

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

/**
 * This DialView is from the Advanced Practical course on Android
 * https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-10-custom-views/10-1b-p-using-custom-views/10-1b-p-using-custom-views.html
 *
 */
class DialView: View {
    companion object {
        private const val SELECTION_COUNT = 4
    }

    constructor(context: Context): super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyleAttr: Int): super(context,attributes, defStyleAttr)

    private var width: Float? = null
    private var height: Float? = null
    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var dialPaint: Paint
    private var radius: Float? = null
    private var activeSelection: Int? = null
    private var tempLabel = StringBuffer(8)

    init {
        textPaint.color = Color.BLACK
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 40f
        dialPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        dialPaint.color = Color.GRAY

        activeSelection = 0

    }


}