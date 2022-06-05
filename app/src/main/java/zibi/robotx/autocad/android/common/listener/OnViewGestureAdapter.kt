package zibi.robotx.autocad.android.common.listener

import android.view.GestureDetector
import android.view.MotionEvent

open class OnViewGestureAdapter : GestureDetector.OnGestureListener {

    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {}

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent,
        distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    // android.view.GestureDetector.OnGestureListener
    override fun onLongPress(e: MotionEvent) {}

    // android.view.GestureDetector.OnGestureListener
    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }
}