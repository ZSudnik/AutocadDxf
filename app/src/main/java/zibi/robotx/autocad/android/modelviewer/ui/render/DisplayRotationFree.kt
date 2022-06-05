package zibi.robotx.autocad.android.modelviewer.ui.render

import android.opengl.Matrix
import zibi.robotx.autocad.common.math.Float4x4
import kotlin.jvm.Volatile
import android.os.Bundle

class DisplayRotationFree : DisplayRotation {
    @Volatile
    private var rotation: Float4x4? = Float4x4.identity()


    override fun save(bundle: Bundle) {
        bundle.putSerializable(BUNDLE_FREE_ROTATION_KEY, rotation)
    }


    override fun restore(bundle: Bundle) {
        rotation = bundle.getSerializable(BUNDLE_FREE_ROTATION_KEY) as Float4x4?
    }


    override fun onChange(deltaX: Float, deltaY: Float) {
        val newRotation: Float4x4 = Float4x4.rotation(-deltaX, 0.0f, 1.0f, 0.0f)
        newRotation.multiply(Float4x4.rotation(-deltaY, 1.0f, 0.0f, 0.0f))
        newRotation.multiply(rotation)
        rotation = newRotation
    }

    override fun apply(mMatrix: FloatArray) {
        Matrix.multiplyMM(mMatrix,0,mMatrix,0,rotation!!.elements,0)
    }

    override fun reset() {
        rotation= Float4x4.identity()
    }


    companion object {
        private const val BUNDLE_FREE_ROTATION_KEY = "FREE_ROTATION"
    }
}