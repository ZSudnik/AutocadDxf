package zibi.robotx.autocad.android.common.view


import android.view.SurfaceView
import kotlin.jvm.Volatile
import android.view.SurfaceHolder
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock


class EnhancedSurfaceView : SurfaceView {
    private val backBuffer = EnhancedSurfaceViewBackBuffer()
    private val surfaceLock: Lock = ReentrantLock()

    @Volatile
    private var isAvailable = false

    constructor(context: Context?) : super(context) {
        registerStateHandler()
        resizeBackBuffer(width, height)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        registerStateHandler()
        resizeBackBuffer(width, height)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        registerStateHandler()
        resizeBackBuffer(width, height)
    }

    fun redraw() {
        draw(backBuffer.getCanvas()) //backBuffer.canvas
        surfaceLock.lock()
        try {
            swapBuffer()
        } finally {
            surfaceLock.unlock()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w != oldw || h != oldh) {
            resizeBackBuffer(w, h)
        }
    }

    private fun registerStateHandler() {
        holder.addCallback(SurfaceStateHandler(this, null))
    }

    /* access modifiers changed from: private */
    fun handleSurfaceCreated() {
        surfaceLock.lock()
        isAvailable = true
        surfaceLock.unlock()
    }

    /* access modifiers changed from: private */
    fun handleSurfaceDestroyed() {
        surfaceLock.lock()
        isAvailable = false
        surfaceLock.unlock()
    }

    private fun resizeBackBuffer(width: Int, height: Int) {
        backBuffer.resize(getWidth(), getHeight())
    }

    private fun swapBuffer() {
        var holder: SurfaceHolder? = null
        var canvas: Canvas? = null
        if (isAvailable && getHolder().also { holder = it }.lockCanvas()
                .also { canvas = it } != null
        ) {
            try {
                backBuffer.swap(canvas!!)
            } finally {
                holder!!.unlockCanvasAndPost(canvas)
            }
        }
    }

    /* loaded from: classes.dex */
    inner class SurfaceStateHandler private constructor() : SurfaceHolderCallbackAdapter() {
        /* synthetic */
        internal constructor(
            enhancedSurfaceView: EnhancedSurfaceView?,
            surfaceStateHandler: SurfaceStateHandler?
        ) : this() {
        }

        // zibi.robot.autocad.android.common.view.SurfaceHolderCallbackAdapter, android.view.SurfaceHolder.Callback
        override fun surfaceCreated(holder: SurfaceHolder) {
            handleSurfaceCreated()
        }

        // zibi.robot.autocad.android.common.view.SurfaceHolderCallbackAdapter, android.view.SurfaceHolder.Callback
        override fun surfaceDestroyed(holder: SurfaceHolder) {
            handleSurfaceDestroyed()
        }
    }
}