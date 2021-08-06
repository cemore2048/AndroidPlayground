package com.example.androidplayground.ignore

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.androidplayground.R
import kotlin.math.cos
import kotlin.math.sin

/**
 * This DialView is from the Advanced Practical course on Android
 * https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-5-advanced-graphics-and-views/lesson-10-custom-views/10-1b-p-using-custom-views/10-1b-p-using-custom-views.html
 */
class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val SELECTION_COUNT = 4
    }

    private var width: Float = 0f
    private var height: Float = 0f
    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var dialPaint: Paint
    private var radius: Float = 0f
    private var activeSelection: Int = 0
    private var tempLabel = StringBuffer(8)
    private val tempResult = FloatArray(2)
    private var fanOffColor = 0
    private var fanOnColor = 0

    init {
        isClickable = true

        textPaint.color = Color.BLACK
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 40f
        dialPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        activeSelection = 0

        context.withStyledAttributes(attrs, R.styleable.DialView) {
            fanOnColor = getColor(R.styleable.DialView_fanColor, 0)
            fanOffColor = getColor(R.styleable.DialView_fanOffColor, 0)
        }

        dialPaint.color = fanOffColor

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // When the size of this view changes, we want to get the size it grew to, and recalculate
        // the radius
        width = w.toFloat()
        height = h.toFloat()
        radius = ((width.coerceAtMost(height)) / 2 * 0.8).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        // Draw the dial.
        canvas.drawCircle(width / 2, height / 2, radius, dialPaint)
        // Draw the text labels.
        val labelRadius: Float = radius + 20
        val label: StringBuffer = tempLabel
        for (i in 0 until SELECTION_COUNT) {
            val xyData = computeXYForPosition(i, labelRadius)
            val x = xyData[0]
            val y = xyData[1]
            label.setLength(0)
            label.append(i)
            canvas.drawText(label, 0, label.length, x, y, textPaint)
        }
        // Draw the indicator mark.
        val markerRadius: Float = radius - 35f
        val xyData = computeXYForPosition(
            activeSelection,
            markerRadius
        )
        val x = xyData[0]
        val y = xyData[1]
        canvas.drawCircle(x, y, 20.toFloat(), textPaint)
    }

    override fun performClick(): Boolean {
        val a = hasOnClickListeners()
        if (super.performClick()) return true
        var localActiveSelecton = activeSelection
        localActiveSelecton = (localActiveSelecton + 1) % SELECTION_COUNT
        activeSelection = localActiveSelecton
        if (localActiveSelecton >= 1) {
            dialPaint.color = fanOnColor
        } else {
            dialPaint.color = fanOffColor
        }

        invalidate()
        return true

    }

    private fun computeXYForPosition(pos: Int, radius: Float): FloatArray {
        val result: FloatArray = tempResult
        val startAngle = Math.PI * (9 / 8.0) // Angles are in radians.
        val angle = startAngle + pos * (Math.PI / 4)
        result[0] = (radius * cos(angle)).toFloat() + width / 2
        result[1] = (radius * sin(angle)).toFloat() + height / 2
        return result
    }
}