package zibi.robotx.autocad.android.common.listener

import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener


open class OnViewScaleGestureAdapter : OnScaleGestureListener {
    override fun onScale(detector: ScaleGestureDetector): Boolean {
        return false
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        return false
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {}
}