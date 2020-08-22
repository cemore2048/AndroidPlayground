package com.example.androidplayground

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs

class MyCanvas(context: Context) : View(context) {

    companion object {
        private const val STROKE_WIDTH = 30f
    }

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    private lateinit var frame: Rect
    private val drawing = Path()
    private val curPath = Path()

    // Defines a tolerance that allows us to not draw every single time a users finger moves the tiniest bit
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    private val paint = Paint().apply {
        color = drawColor
        // Smooth out edges of what is drawn without affecting shape
        isAntiAlias = true
        // Dithering affects how colors with higher precision than the device are down-sampled
        isDither = false
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(backgroundColor)
        // Draw the drawing so far
        canvas.drawPath(drawing, paint)
        // Draw any current squiggle
        canvas.drawPath(curPath, paint)
        // Draw a frame around the canvas
        canvas.drawRect(frame, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }

        return true
    }

    private var currentX = 0f
    private var currentY = 0f

    private fun touchStart() {
        curPath.reset()
        curPath.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)

        // QuadTo() adds a quadratic bezier from the last point,
        // approaching control point (x1,y1), and ending at (x2,y2).
        curPath.quadTo(
            currentX,
            currentY,
            (motionTouchEventX + currentX) / 2,
            (motionTouchEventY + currentY) / 2
        )
        currentX = motionTouchEventX
        currentY = motionTouchEventY
        invalidate()
    }

    private fun touchUp() {
        // Add the current path to the drawing so far
        drawing.addPath(curPath)
        // Rewind the current path for the next touch
        curPath.reset()
    }
}