package zibi.robotx.autocad.android.modelviewer.ui.render

import android.os.Bundle

interface DisplayRotation {
    fun apply( mMatrix: FloatArray)
    fun onChange(deltaX: Float, deltaY: Float)
    fun restore(bundle: Bundle)
    fun save(bundle: Bundle)
    fun reset()
    companion object {
        const val BUNDLE_ROTATION_X_KEY = "XY_ROTATION_X"
        const val BUNDLE_ROTATION_Y_KEY = "XY_ROTATION_Y"
    }
}