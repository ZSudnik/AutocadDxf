package zibi.robotx.autocad.android.modelviewer.ui.render


import kotlin.jvm.Volatile
import android.opengl.Matrix
import android.os.Bundle
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRotation.Companion.BUNDLE_ROTATION_X_KEY
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRotation.Companion.BUNDLE_ROTATION_Y_KEY

class DisplayRotationXY : DisplayRotation {

    @Volatile
    private var angleX = 0f
    @Volatile
    private var angleY = 0f

    override fun save(bundle: Bundle) {
        bundle.putFloat(BUNDLE_ROTATION_X_KEY, angleX)
        bundle.putFloat(BUNDLE_ROTATION_Y_KEY, angleY)
    }

    override fun restore(bundle: Bundle) {
        angleX = bundle.getFloat(BUNDLE_ROTATION_X_KEY, 0.0f)
        angleY = bundle.getFloat(BUNDLE_ROTATION_Y_KEY, 0.0f)
    }

    override fun onChange(deltaX: Float, deltaY: Float) {
        angleY -= deltaX
        angleX -= deltaY
        if (angleX < MIN_X_ROTATION) {
            angleX = MIN_X_ROTATION
        }
        if (angleX > MAX_X_ROTATION) {
            angleX = MAX_X_ROTATION
        }
    }

    override fun apply(mMatrix: FloatArray) {
        Matrix.rotateM(mMatrix, 0, angleX, 1f, 0f, 0f)
        Matrix.rotateM(mMatrix, 0, angleY, 0f, 1f, 0f)
//        Matrix.setRotateM(mRotationMatrixX, 0, angleX, 1f, 0f, 0f)
//        Matrix.setRotateM(mRotationMatrixY, 0, angleY, 0f, 1f, 0f)
//        mRotationMatrix = add2Matrix(mRotationMatrixX, mRotationMatrixY )
//        Matrix.multiplyMM(mMatrix, 0, mMatrix, 0, mRotationMatrix, 0)

    //        GLES10.glRotatef(angleX, 1.0f, 0.0f, 0.0f)
//        GLES10.glRotatef(angleY, 0.0f, 1.0f, 0.0f)
    }

    override fun reset() {
        angleX = 0f
        angleY = 0f
    }

    companion object {
        private const val MAX_X_ROTATION = 60.0f
        private const val MIN_X_ROTATION = -60.0f
    }


    private fun add2Matrix( A: FloatArray, B: FloatArray): FloatArray{
        val C = FloatArray(16)
        for( i in A.indices){
            C[i] = A[i] +B[i]
        }
        return C
    }

}