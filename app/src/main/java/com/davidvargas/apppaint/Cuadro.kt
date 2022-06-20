package com.davidvargas.apppaint

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.io.path.moveTo


class Cuadro @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        setUpPintar()
    }

    lateinit var drawPath: Path
    lateinit var drawPaint: Paint
    lateinit var canvasPaint: Paint

    lateinit var drawCanvas: Canvas
    lateinit var bitmap: Bitmap

    fun setUpPintar() {
        drawPath = Path()
        drawPaint = Paint()
        drawPaint.color = resources.getColor(R.color.Black)
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 30F
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeCap = Paint.Cap.ROUND
        drawPaint.strokeJoin = Paint.Join.ROUND

        canvasPaint = Paint(Paint.DITHER_FLAG)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(bitmap)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.getX()
        var y = event.getY()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawPath.moveTo(x, y)
            MotionEvent.ACTION_MOVE -> drawPath.lineTo(x, y)
            MotionEvent.ACTION_UP -> {
                drawPath.lineTo(x, y)
                drawCanvas.drawPath(drawPath, drawPaint)
                drawPath.reset()
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0F, 0F, canvasPaint)
        canvas.drawPath(drawPath, drawPaint)
    }

    fun ponerColor(color: Int) {
        invalidate()
        drawPaint.color = color
    }

    fun ponerTamanoPincel(tamano: Float) {
        drawPaint.strokeWidth = tamano
    }

    fun ponerGomaBorrar(goma: Boolean) {
        drawPaint.color = Color.WHITE
    }

    fun ponerTamanoGomaBorrar(tamano: Float) {
        drawPaint.strokeWidth = tamano
    }

    fun nuevoCuadro(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()

    }
}