package com.example.androidplayground

import android.content.Context
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
    private var textPaint: Paint? = null
    private var dialPaint: Paint? = null
    private var radius: Float? = null
    private var activeSelection: Int? = null
    private var tempLabel = StringBuffer(8)

    init {

    }

}