package zibi.robotx.autocad.android.common.view

import kotlin.jvm.Synchronized
import android.graphics.*

internal class EnhancedSurfaceViewBackBuffer {

    private var bufferCanvas: Canvas? = null
    private val bufferSize = Rect()
    private var buffer = createBitmapBuffer(1, 1)

    @Synchronized
    fun resize(width: Int, height: Int) {
        buffer.recycle()
        buffer = createBitmapBuffer(width, height)
        bufferCanvas = null
        bufferSize[0, 0, buffer.width] = buffer.height
    }

    @Synchronized
    fun getCanvas(): Canvas? {
        if (bufferCanvas == null) {
            bufferCanvas = Canvas(buffer)
        }
        return bufferCanvas
    }

    @Synchronized
    fun swap(canvas: Canvas) {
        canvas.drawBitmap(buffer, bufferSize, canvas.clipBounds, null as Paint?)
    }

    private fun createBitmapBuffer(preferredWidth: Int, preferredHeight: Int): Bitmap {
        return Bitmap.createBitmap(
            Math.max(1, preferredWidth),
            Math.max(1, preferredHeight),
            Bitmap.Config.ARGB_8888
        )
    }
}