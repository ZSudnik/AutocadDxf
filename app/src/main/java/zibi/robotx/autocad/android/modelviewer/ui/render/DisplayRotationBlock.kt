package zibi.robotx.autocad.android.modelviewer.ui.render


import android.os.Bundle
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRotation.Companion.BUNDLE_ROTATION_X_KEY
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRotation.Companion.BUNDLE_ROTATION_Y_KEY

class DisplayRotationBlock : DisplayRotation {

    @Volatile
    private var angleX = 0f
    @Volatile
    private var angleY = 0f

    override fun save(bundle: Bundle) {}

    override fun restore(bundle: Bundle) {
        angleX = bundle.getFloat(BUNDLE_ROTATION_X_KEY, 0.0f)
        angleY = bundle.getFloat(BUNDLE_ROTATION_Y_KEY, 0.0f)
    }

    override fun onChange(deltaX: Float, deltaY: Float) {
    }

    override fun apply(mMatrix: FloatArray) {
//        Matrix.rotateM(mMatrix, 0, angleX, 1f, 0f, 0f)
//        Matrix.rotateM(mMatrix, 0, angleY, 0f, 1f, 0f)

    }

    override fun reset() {
        angleX = 0f
        angleY = 0f
    }

}