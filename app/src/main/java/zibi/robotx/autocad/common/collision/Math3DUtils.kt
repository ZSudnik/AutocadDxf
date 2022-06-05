package zibi.robotx.autocad.common.collision

import android.opengl.Matrix
import java.lang.StringBuilder
import java.math.BigDecimal
import java.util.*

object Math3DUtils {

    val IDENTITY_MATRIX = FloatArray(16)

    /**
     * Substract 2 vertex: a-b
     *
     * @param a
     * @param b
     * @return a-b
     */
    
    fun substract(a: FloatArray, b: FloatArray): FloatArray {
        return floatArrayOf(a[0] - b[0], a[1] - b[1], a[2] - b[2])
    }

    /**
     * Divide 2 vertex: a/b
     *
     * @param a
     * @param b
     * @return a/b
     */
    fun divide(a: FloatArray, b: FloatArray): FloatArray {
        return floatArrayOf(a[0] / b[0], a[1] / b[1], a[2] / b[2])
    }

    /**
     * Divide vertex: a/b
     *
     * @param a
     * @param b
     * @return a/b
     */
    
    fun divide(a: FloatArray, b: Float): FloatArray {
        return floatArrayOf(a[0] / b, a[1] / b, a[2] / b)
    }

    /**
     * Get the min of both vertex
     *
     * @param a
     * @param b
     * @return min of both vertex
     */
    
    fun min(a: FloatArray, b: FloatArray): FloatArray {
        return floatArrayOf(
            Math.min(a[0], b[0]), Math.min(a[1], b[1]), Math.min(
                a[2], b[2]
            )
        )
    }

    /**
     * Get the max of both vertex
     *
     * @param a
     * @param b
     * @return max of both vertex
     */
    
    fun max(a: FloatArray, b: FloatArray): FloatArray {
        return floatArrayOf(
            Math.max(a[0], b[0]), Math.max(a[1], b[1]), Math.max(
                a[2], b[2]
            )
        )
    }

    /**
     * Normalize the specified vector
     *
     * @param a
     */
    
    fun normalize(a: FloatArray) {
        val length = length(a)
        require(length != 0f) { "vector length is zero" }
        a[0] = a[0] / length
        a[1] = a[1] / length
        a[2] = a[2] / length
    }


    fun add(a: FloatArray, b: FloatArray): FloatArray {
        return floatArrayOf(a[0] + b[0], a[1] + b[1], a[2] + b[2])
    }

    /**
     * [Matrix]
     */
    fun length(vector: FloatArray): Float {
        return length(vector[0], vector[1], vector[2])
    }

    /**
     * [Matrix]
     */
    fun length(x: Float, y: Float, z: Float): Float {
        return Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
    }


    init {
        Matrix.setIdentityM(IDENTITY_MATRIX, 0)
    }
}