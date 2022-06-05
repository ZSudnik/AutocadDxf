package zibi.robotx.autocad.android.modelviewer.ui.render


import android.opengl.Matrix
import android.os.Bundle
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRotation.Companion.BUNDLE_ROTATION_X_KEY
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRotation.Companion.BUNDLE_ROTATION_Y_KEY

class DisplayRotationMoveXY : DisplayRotation {

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
        angleY = deltaX
        angleX = deltaY
        //https://stackoverflow.com/questions/46641995/opengl-move-object-and-keep-transformation
        //
//        if (angleX < MIN_X_ROTATION) {
//            angleX = MIN_X_ROTATION
//        }
//        if (angleX > MAX_X_ROTATION) {
//            angleX = MAX_X_ROTATION
//        }
//        println(" BBB x: $angleX  y: $angleY ")
//        angleX /= 360.0f
//        angleY /= 360.0f
//        println(" NNN x: $angleX  y: $angleY ")
    }

    override fun apply(mMatrix: FloatArray) {
        Matrix.translateM(mMatrix,0,  angleX, angleY,0f)
    }

    override fun reset() {
        angleX = 0f
        angleY = 0f
    }


    companion object {
        private const val MAX_X_ROTATION = 60.0f
        private const val MIN_X_ROTATION = -60.0f
    }

}