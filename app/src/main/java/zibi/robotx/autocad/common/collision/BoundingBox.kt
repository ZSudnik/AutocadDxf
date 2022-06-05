package zibi.robotx.autocad.common.collision

import android.opengl.Matrix

class BoundingBox private constructor(
    l_min: FloatArray,
    l_max: FloatArray,
    l_modelMatrix: FloatArray
) {
    private val lmin: FloatArray
    private val lmax: FloatArray

    // dynamic bounding box
    private val modelMatrix: FloatArray
    val min: FloatArray
    val max: FloatArray

    fun getxMin(): Float {
        return min[0]
    }

    fun getxMax(): Float {
        return max[0]
    }

    fun getyMin(): Float {
        return min[1]
    }

    fun getyMax(): Float {
        return max[1]
    }

    fun getzMin(): Float {
        return min[2]
    }

    fun getzMax(): Float {
        return max[2]
    }

    fun insideBounds(x: Float, y: Float, z: Float): Boolean {
        return !outOfBound(x, y, z)
    }

    fun outOfBound(x: Float, y: Float, z: Float): Boolean {
        return x > getxMax() || x < getxMin() || y < getyMin() || y > getyMax() || z < getzMin() || z > getzMax()
    }

    companion object {
        fun create(d: zibi.robotx.autocad.common.collision.Dimensions, modelMatrix: FloatArray): BoundingBox {
            return BoundingBox(d.min, d.max, modelMatrix)
        }
    }

    init {
        lmin = floatArrayOf(l_min[0], l_min[1], l_min[2], 1f)
        lmax = floatArrayOf(l_max[0], l_max[1], l_max[2], 1f)
        modelMatrix = l_modelMatrix
        min = FloatArray(4)
        max = FloatArray(4)
        Matrix.multiplyMV(min, 0, l_modelMatrix, 0, lmin, 0)
        Matrix.multiplyMV(max, 0, l_modelMatrix, 0, lmax, 0)
    }
}