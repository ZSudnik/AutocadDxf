package zibi.robotx.autocad.common.collision

import zibi.robotx.autocad.common.collision.old.Math3DUtils.substract
import zibi.robotx.autocad.common.collision.old.Math3DUtils.divide
import zibi.robotx.autocad.common.collision.old.Math3DUtils.min
import zibi.robotx.autocad.common.collision.old.Math3DUtils.max
import android.opengl.GLU

/**
 * Class that encapsulates all the logic for the collision detection algorithm.
 *
 * @author andresoviedo
 */
object CollisionDetection {



    fun getBoxIntersection(origin: FloatArray?, dir: FloatArray?, b: zibi.robotx.autocad.common.collision.BoundingBox): FloatArray {
        val tMin = divide(substract(b.min, origin!!), dir!!)
        val tMax = divide(substract(b.max, origin), dir)
        val t1 = min(tMin, tMax)
        val t2 = max(tMin, tMax)
        val tNear = Math.max(Math.max(t1[0], t1[1]), t1[2])
        val tFar = Math.min(Math.min(t2[0], t2[1]), t2[2])
        return floatArrayOf(tNear, tFar)
    }

    /**
     * Map window coordinates to object coordinates.
     *
     * @param height                viewport height
     * @param width                 viewport width
     * @param modelViewMatrix       model view matrix
     * @param modelProjectionMatrix model projection matrix
     * @param rx                    x point
     * @param ry                    y point
     * @param rz                    z point
     * @return the corresponding near and far vertex for the specified window coordinates
     */
    fun unProject(
        width: Int, height: Int, modelViewMatrix: FloatArray?, modelProjectionMatrix: FloatArray?,
        rx: Float, ry: Float, rz: Float
    ): FloatArray {
        var ry = ry
        val xyzw = floatArrayOf(0f, 0f, 0f, 0f)
        ry = height.toFloat() - ry
        val viewport = intArrayOf(0, 0, width, height)
        GLU.gluUnProject(
            rx, ry, rz, modelViewMatrix, 0, modelProjectionMatrix, 0,
            viewport, 0, xyzw, 0
        )
        xyzw[0] /= xyzw[3]
        xyzw[1] /= xyzw[3]
        xyzw[2] /= xyzw[3]
        xyzw[3] = 1f
        return xyzw
    }


}