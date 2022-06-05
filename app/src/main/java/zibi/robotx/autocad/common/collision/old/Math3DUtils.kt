package zibi.robotx.autocad.common.collision.old

import android.opengl.Matrix
import java.lang.StringBuilder
import java.math.BigDecimal
import java.util.*

//import org.andresoviedo.android_3d_model_engine.animation.JointTransform;
/**
 * Utility class to calculate 3D stuff
 *
 * @author andresoviedo
 */
object Math3DUtils {
    @JvmField
    val IDENTITY_MATRIX = FloatArray(16)
    fun initMatrixIfNull(matrix: FloatArray?): FloatArray {
        var matrix = matrix
        if (matrix != null) return matrix
        matrix = FloatArray(16)
        Matrix.setIdentityM(matrix, 0)
        return matrix
    }

    /**
     * Calculate face normal
     *
     *
     * So for a triangle p1, p2, p3, if the vector U = p2 - p1 and the vector V = p3 - p1 then the normal N = U X V and can be calculated by:
     *
     * <pre>
     * Nx = UyVz - UzVy
     * Ny = UzVx - UxVz
     * Nz = UxVy - UyVx
    </pre> *
     *
     * [Calculating_a_Surface_Normal](https://www.khronos.org/opengl/wiki/Calculating_a_Surface_Normal)
     *
     * @param v0
     * @param v1
     * @param v2
     * @return
     */
    fun calculateNormal(v0: FloatArray, v1: FloatArray, v2: FloatArray): FloatArray {

        // calculate perpendicular vector to the face. That is to calculate the cross product of v1-v0 x v2-v0
        val va = doubleArrayOf(
            (v1[0] - v0[0]).toDouble(),
            (v1[1] - v0[1]).toDouble(),
            (v1[2] - v0[2]).toDouble()
        )
        val vb = doubleArrayOf(
            (v2[0] - v0[0]).toDouble(),
            (v2[1] - v0[1]).toDouble(),
            (v2[2] - v0[2]).toDouble()
        )
        val vn = floatArrayOf(
            (va[1] * vb[2] - va[2] * vb[1]).toFloat(), (va[2] * vb[0] - va[0] * vb[2]).toFloat(),
            (va[0] * vb[1] - va[1] * vb[0]).toFloat()
        )
        return if (length(vn) != 0f) {
            vn
        } else {
            calculateNormal_highPrecision(v0, v1, v2)
        }
    }

    /**
     * Calculate face normal using high precision maths
     *
     *
     * So for a triangle p1, p2, p3, if the vector U = p2 - p1 and the vector V = p3 - p1 then the normal N = U X V and can be calculated by:
     *
     * <pre>
     * Nx = UyVz - UzVy
     * Ny = UzVx - UxVz
     * Nz = UxVy - UyVx
    </pre> *
     *
     * [Calculating_a_Surface_Normal](https://www.khronos.org/opengl/wiki/Calculating_a_Surface_Normal)
     *
     * @param v0
     * @param v1
     * @param v2
     * @return
     */
    fun calculateNormal_highPrecision(v0: FloatArray, v1: FloatArray, v2: FloatArray): FloatArray {

        // calculate the 2 vectors
        val u = substract(v1, v0)
        val v = substract(v2, v0)
        val u_ = arrayOf(
            BigDecimal(java.lang.Float.toString(u[0])),
            BigDecimal(java.lang.Float.toString(u[1])),
            BigDecimal(java.lang.Float.toString(u[2]))
        )
        val v_ = arrayOf(
            BigDecimal(java.lang.Float.toString(v[0])),
            BigDecimal(java.lang.Float.toString(v[1])),
            BigDecimal(java.lang.Float.toString(v[2]))
        )
        val n_ = arrayOf(
            u_[1].multiply(v_[2]).subtract(u_[2].multiply(v_[1])),
            u_[2].multiply(v_[0]).subtract(u_[0].multiply(v_[2])),
            u_[0].multiply(v_[1]).subtract(u_[1].multiply(v_[0]))
        )
        return floatArrayOf(
            n_[0].toFloat(),
            n_[1].toFloat(),
            n_[2].toFloat()
        )
    }

    /**
     * Calculate the 2 vectors, that is a line (x1,y1,z1-x2,y2,z2} corresponding to the normal of the specified face.
     * The calculated line will be positioned exactly in the middle of the face
     *
     * @param v0    the first vector of the face
     * @param v1    the second vector of the face
     * @param v2    the third vector of the face
     * @param scale if `true` scale normal line according to triangle size (bigger triangle bigger line)
     * @return the 2 vectors (line) corresponding to the face normal
     */
    fun calculateNormalLine(
        v0: FloatArray,
        v1: FloatArray,
        v2: FloatArray,
        scale: Boolean
    ): Array<FloatArray> {

        // calculate perpendicular vector to the face. That is to calculate the cross product of v1-v0 x v2-v0
        val va = floatArrayOf(v1[0] - v0[0], v1[1] - v0[1], v1[2] - v0[2])
        val vb = floatArrayOf(v2[0] - v0[0], v2[1] - v0[1], v2[2] - v0[2])
        val n = floatArrayOf(
            va[1] * vb[2] - va[2] * vb[1], va[2] * vb[0] - va[0] * vb[2],
            va[0] * vb[1] - va[1] * vb[0]
        )
        val modul = Matrix.length(n[0], n[1], n[2])
        val vn = floatArrayOf(n[0] / modul, n[1] / modul, n[2] / modul)
        return getNormalLine(v0, v1, v2, vn, scale, 1f)
    }

    /**
     * Calculate the 2 vectors, that is a line (x1,y1,z1-x2,y2,z2} corresponding to the normal of the specified face.
     * The calculated line will be positioned exactly in the middle of the face
     *
     * @param v0     the first vector of the face
     * @param v1     the second vector of the face
     * @param v2     the third vector of the face
     * @param normal the normal vector
     * @param scale  if `true` scale normal line according to triangle size (bigger triangle bigger line)
     * @return the 2 vectors (line) corresponding to the face normal
     */
    fun getNormalLine(
        v0: FloatArray,
        v1: FloatArray,
        v2: FloatArray,
        normal: FloatArray,
        scale: Boolean,
        rescale: Float
    ): Array<FloatArray> {

        // calculate center of the face
        val faceCenter = calculateFaceCenter(v0, v1, v2)
        val va = floatArrayOf(v1[0] - v0[0], v1[1] - v0[1], v1[2] - v0[2])
        val vb = floatArrayOf(v2[0] - v0[0], v2[1] - v0[1], v2[2] - v0[2])
        val vc = floatArrayOf(v2[0] - v1[0], v2[1] - v1[1], v2[2] - v1[2])

        // scale normal proportional to triangle perimeter (or area)
        val scaleFactor: Float = if (scale) (length(va[0], va[1], va[2])
                + length(vb[0], vb[1], vb[2])
                + length(vc[0], vc[1], vc[2])) / 3 else 1f

        // calculate 2nd vertex position
        val vn2 = floatArrayOf(
            faceCenter[0] + normal[0] * scaleFactor * rescale,
            faceCenter[1] + normal[1] * scaleFactor * rescale,
            faceCenter[2] + normal[2] * scaleFactor * rescale
        )
        return arrayOf(faceCenter, vn2)
    }

    /**
     * Calculate the 3 lines, that is a line (x1,y1,z1-x2,y2,z2} corresponding to the normal of the specified face.
     *
     * @param v0     the first vector of the face
     * @param v1     the second vector of the face
     * @param v2     the third vector of the face
     * @param normal the normal vector
     * @param scale  if `true` scale normal line according to triangle size (bigger triangle bigger line)
     * @return the 2 vectors (line) corresponding to the face normal
     */
    fun getNormalLines(
        v0: FloatArray,
        v1: FloatArray,
        v2: FloatArray,
        normal: FloatArray,
        scale: Boolean,
        rescale: Float
    ): Array<FloatArray> {
        val va = floatArrayOf(v1[0] - v0[0], v1[1] - v0[1], v1[2] - v0[2])
        val vb = floatArrayOf(v2[0] - v0[0], v2[1] - v0[1], v2[2] - v0[2])
        val vc = floatArrayOf(v2[0] - v1[0], v2[1] - v1[1], v2[2] - v1[2])

        // scale normal proportional to triangle perimeter (or area)
        val scaleFactor: Float = if (scale) (length(va[0], va[1], va[2])
                + length(vb[0], vb[1], vb[2])
                + length(vc[0], vc[1], vc[2])) / 3 else 1f

        // calculate 2nd vertex position
        val vn0 = floatArrayOf(
            v0[0] + normal[0] * scaleFactor * rescale,
            v0[1] + normal[1] * scaleFactor * rescale,
            v0[2] + normal[2] * scaleFactor * rescale
        )
        val vn1 = floatArrayOf(
            v1[0] + normal[0] * scaleFactor * rescale,
            v1[1] + normal[1] * scaleFactor * rescale,
            v1[2] + normal[2] * scaleFactor * rescale
        )
        val vn2 = floatArrayOf(
            v2[0] + normal[0] * scaleFactor * rescale,
            v2[1] + normal[1] * scaleFactor * rescale,
            v2[2] + normal[2] * scaleFactor * rescale
        )
        return arrayOf(v0, vn0, v1, vn1, v2, vn2)
    }

    fun calculateFaceCenter(v0: FloatArray, v1: FloatArray, v2: FloatArray): FloatArray {
        return floatArrayOf(
            (v0[0] + v1[0] + v2[0]) / 3,
            (v0[1] + v1[1] + v2[1]) / 3,
            (v0[2] + v1[2] + v2[2]) / 3
        )
    }

    /**
     * Calculates the distance of the intersection between the specified ray and the target, or return -1 if the ray
     * doesn't intersect the target
     *
     * @param rayPoint1 where the ray starts
     * @param rayPoint2 where the ray ends
     * @param target    where is the object to intersect
     * @param precision the radius to test for intersection
     * @return the distance of intersection
     */
    @Deprecated("")
    fun calculateDistanceOfIntersection(
        rayPoint1: FloatArray, rayPoint2: FloatArray, target: FloatArray,
        precision: Float
    ): Float {
        val raySteps = 100f
        val objHalfWidth = precision / 2
        val length = Matrix.length(
            rayPoint2[0] - rayPoint1[0], rayPoint2[1] - rayPoint1[1],
            rayPoint2[2] - rayPoint1[2]
        )
        val lengthDiff = length / raySteps
        val xDif = (rayPoint2[0] - rayPoint1[0]) / raySteps
        val yDif = (rayPoint2[1] - rayPoint1[1]) / raySteps
        val zDif = (rayPoint2[2] - rayPoint1[2]) / raySteps
        var i = 0
        while (i < raySteps) {

            // @formatter:off
            if (rayPoint1[0] + xDif * i > target[0] - objHalfWidth && rayPoint1[0] + xDif * i < target[0] + objHalfWidth && rayPoint1[1] + yDif * i > target[1] - objHalfWidth && rayPoint1[1] + yDif * i < target[1] + objHalfWidth && rayPoint1[2] + zDif * i > target[2] - objHalfWidth && rayPoint1[2] + zDif * i < target[2] + objHalfWidth) {
                // @formatter:on
                // Log.v(TouchController.TAG, "HIT: i[" + i + "] wz[" + (rayPoint1[2] + (zDif * i)) + "]");
                // return new Object[] { i * lengthDiff, new float[] { rayPoint1[0] + (xDif * i),
                // rayPoint1[1] + (yDif * i), rayPoint1[2] + (zDif * i) } };
                return i * lengthDiff
            }
            i++
        }
        return (-1).toFloat()
    }

    /**
     * Substract 2 vertex: a-b
     *
     * @param a
     * @param b
     * @return a-b
     */
    @JvmStatic
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
    @JvmStatic
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
    @JvmStatic
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
    @JvmStatic
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
    @JvmStatic
    fun normalize(a: FloatArray) {
        val length = length(a)
        require(length != 0f) { "vector length is zero" }
        a[0] = a[0] / length
        a[1] = a[1] / length
        a[2] = a[2] / length
    }

    fun crossProduct(a: FloatArray, b: FloatArray): FloatArray {
        // AxB = (AyBz − AzBy, AzBx − AxBz, AxBy − AyBx)
        //(r)[0] = (a)[1] * (b)[2] - (b)[1] * (a)[2]; \
        //(r)[1] = (a)[2] * (b)[0] - (b)[2] * (a)[0]; \
        //(r)[2] = (a)[0] * (b)[1] - (b)[0] * (a)[1];
        val x = a[1] * b[2] - a[2] * b[1]
        val y = a[2] * b[0] - a[0] * b[2]
        val z = a[0] * b[1] - a[1] * b[0]
        return floatArrayOf(x, y, z)
    }

    fun crossProduct(ax: Float, ay: Float, az: Float, bx: Float, by: Float, bz: Float): FloatArray {
        // AxB = (AyBz − AzBy, AzBx − AxBz, AxBy − AyBx)
        //(r)[0] = (a)[1] * (b)[2] - (b)[1] * (a)[2]; \
        //(r)[1] = (a)[2] * (b)[0] - (b)[2] * (a)[0]; \
        //(r)[2] = (a)[0] * (b)[1] - (b)[0] * (a)[1];
        val x = ay * bz - az * by
        val y = az * bx - ax * bz
        val z = ax * by - ay * bx
        return floatArrayOf(x, y, z)
    }

    @JvmStatic
    fun dotProduct(a: FloatArray, b: FloatArray): Float {
        // a1b1+a2b2+a3b3
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2]
    }

    @JvmStatic
    fun multiply(a: FloatArray, t: Float): FloatArray {
        return floatArrayOf(a[0] * t, a[1] * t, a[2] * t)
    }

    @JvmStatic
    fun add(a: FloatArray, b: FloatArray): FloatArray {
        return floatArrayOf(a[0] + b[0], a[1] + b[1], a[2] + b[2])
    }

    fun mean(a: FloatArray, b: FloatArray): FloatArray {
        val add = add(a, b)
        add[0] /= 2f
        add[1] /= 2f
        add[2] /= 2f
        return add
    }

    /**
     * Matrices are 4 x 4 column-vector matrices stored in column-major order:
     * m[offset +  0] m[offset +  4] m[offset +  8] m[offset + 12]
     * m[offset +  1] m[offset +  5] m[offset +  9] m[offset + 13]
     * m[offset +  2] m[offset +  6] m[offset + 10] m[offset + 14]
     * m[offset +  3] m[offset +  7] m[offset + 11] m[offset + 15]
     *
     * @param matrix the matrix to stringify
     * @param indent the spaces to add at beginning
     * @return the string representation of the matrix
     */
    fun toString(matrix: FloatArray, indent: Int): String {
        val ret = StringBuilder()
        for (i in 0..3) {
            ret.append("\n")
            for (k in 0 until indent) {
                ret.append(" ")
            }
            for (j in 0..3) {
                if (matrix[j * 4 + i] >= 0) {
                    ret.append("+")
                }
                ret.append(String.format(Locale.getDefault(), "%+.3f", matrix[j * 4 + i]))
                ret.append("  ")
            }
        }
        return ret.toString()
    }

    fun toString(array: FloatArray): String {
        val ret = StringBuilder("[")
        for (i in array.indices) {
            if (i > 0) ret.append(" ")
            ret.append(String.format(Locale.getDefault(), "%+.4f", array[i]))
        }
        ret.append("]")
        return ret.toString()
    }

    fun parseFloat(rawData: Array<String>): FloatArray {
        val matrixData = FloatArray(rawData.size)
        for (i in rawData.indices) {
            matrixData[i] = rawData[i].toFloat()
        }
        return matrixData
    }

    /**
     * [Matrix]
     */
    fun setRotateM(
        rm: FloatArray, rmOffset: Int,
        a: Float, x: Float, y: Float, z: Float
    ) {
        var a = a
        var x = x
        var y = y
        var z = z
        rm[rmOffset + 3] = 0f
        rm[rmOffset + 7] = 0f
        rm[rmOffset + 11] = 0f
        rm[rmOffset + 12] = 0f
        rm[rmOffset + 13] = 0f
        rm[rmOffset + 14] = 0f
        rm[rmOffset + 15] = 1f
        a *= (Math.PI / 180.0f).toFloat()
        val s = Math.sin(a.toDouble()).toFloat()
        val c = Math.cos(a.toDouble()).toFloat()
        if (1.0f == x && 0.0f == y && 0.0f == z) {
            rm[rmOffset + 5] = c
            rm[rmOffset + 10] = c
            rm[rmOffset + 6] = s
            rm[rmOffset + 9] = -s
            rm[rmOffset + 1] = 0f
            rm[rmOffset + 2] = 0f
            rm[rmOffset + 4] = 0f
            rm[rmOffset + 8] = 0f
            rm[rmOffset + 0] = 1f
        } else if (0.0f == x && 1.0f == y && 0.0f == z) {
            rm[rmOffset + 0] = c
            rm[rmOffset + 10] = c
            rm[rmOffset + 8] = s
            rm[rmOffset + 2] = -s
            rm[rmOffset + 1] = 0f
            rm[rmOffset + 4] = 0f
            rm[rmOffset + 6] = 0f
            rm[rmOffset + 9] = 0f
            rm[rmOffset + 5] = 1f
        } else if (0.0f == x && 0.0f == y && 1.0f == z) {
            rm[rmOffset + 0] = c
            rm[rmOffset + 5] = c
            rm[rmOffset + 1] = s
            rm[rmOffset + 4] = -s
            rm[rmOffset + 2] = 0f
            rm[rmOffset + 6] = 0f
            rm[rmOffset + 8] = 0f
            rm[rmOffset + 9] = 0f
            rm[rmOffset + 10] = 1f
        } else {
            val len = length(x, y, z)
            if (1.0f != len) {
                val recipLen = 1.0f / len
                x *= recipLen
                y *= recipLen
                z *= recipLen
            }
            val nc = 1.0f - c
            val xy = x * y
            val yz = y * z
            val zx = z * x
            val xs = x * s
            val ys = y * s
            val zs = z * s
            rm[rmOffset + 0] = x * x * nc + c
            rm[rmOffset + 4] = xy * nc - zs
            rm[rmOffset + 8] = zx * nc + ys
            rm[rmOffset + 1] = xy * nc + zs
            rm[rmOffset + 5] = y * y * nc + c
            rm[rmOffset + 9] = yz * nc - xs
            rm[rmOffset + 2] = zx * nc - ys
            rm[rmOffset + 6] = yz * nc + xs
            rm[rmOffset + 10] = z * z * nc + c
        }
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

//    fun interpolate(result: JointTransform, start: JointTransform, end: JointTransform, progression: Float) {
//        interpolate(result.getScale(), start.getScale(), end.getScale(), progression)
//        interpolate(result.getLocation(), start.getLocation(), end.getLocation(), progression)
//        interpolate(result.getRotation1(), start.getRotation1(), end.getRotation1(), progression)
//        interpolate(result.getRotation2(), start.getRotation2(), end.getRotation2(), progression)
//        interpolate(result.getRotation2Location(), start.getRotation2Location(), end.getRotation2Location(), progression)
//        Quaternion.interpolate(result.getQRotation(), start.getQRotation(), end.getQRotation(), progression)
//    }

    /**
     * Linearly interpolates between two translations based on a "progression"
     * value.
     *
     * @param start       - the start translation.
     * @param end         - the end translation.
     * @param progression - a value between 0 and 1 indicating how far to interpolate
     * between the two translations.
     * @return
     */
    fun interpolate(
        result: Array<Float?>,
        start: Array<Float>,
        end: Array<Float>,
        progression: Float
    ) {
        for (i in result.indices) {
            result[i] = start[i] + (end[i] - start[i]) * progression
        }
    }

    fun negate(vector: FloatArray): FloatArray {
        val ret = FloatArray(vector.size)
        for (i in vector.indices) ret[i] = -vector[i]
        return ret
    }

    fun mult(vector1: FloatArray, vector2: FloatArray): FloatArray {
        val ret = FloatArray(vector1.size)
        for (i in vector1.indices) ret[i] = vector1[i] * vector2[i]
        return ret
    }

    fun scaleFromMatrix(matrix: FloatArray): Array<Float?> {
        // |A| = a(ei − fh) − b(di − fg) + c(dh − eg)
        val ret = arrayOfNulls<Float>(3)
        ret[0] = Math.sqrt(
            Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(
                matrix[1].toDouble(),
                2.0
            ) + Math.pow(matrix[2].toDouble(), 2.0)
        ).toFloat()
        ret[1] = Math.sqrt(
            Math.pow(matrix[4].toDouble(), 2.0) + Math.pow(
                matrix[5].toDouble(),
                2.0
            ) + Math.pow(matrix[6].toDouble(), 2.0)
        ).toFloat()
        ret[2] = Math.sqrt(
            Math.pow(matrix[8].toDouble(), 2.0) + Math.pow(
                matrix[9].toDouble(),
                2.0
            ) + Math.pow(matrix[10].toDouble(), 2.0)
        ).toFloat()
        if (determinant(matrix) < 0) {
            ret[1] = -ret[1]!!
        }
        return ret
    }

    fun scaleFromMatrixf(matrix: FloatArray): FloatArray {
        // |A| = a(ei − fh) − b(di − fg) + c(dh − eg)
        val ret = FloatArray(3)
        ret[0] = Math.sqrt(
            Math.pow(matrix[0].toDouble(), 2.0) + Math.pow(
                matrix[1].toDouble(),
                2.0
            ) + Math.pow(matrix[2].toDouble(), 2.0)
        ).toFloat()
        ret[1] = Math.sqrt(
            Math.pow(matrix[4].toDouble(), 2.0) + Math.pow(
                matrix[5].toDouble(),
                2.0
            ) + Math.pow(matrix[6].toDouble(), 2.0)
        ).toFloat()
        ret[2] = Math.sqrt(
            Math.pow(matrix[8].toDouble(), 2.0) + Math.pow(
                matrix[9].toDouble(),
                2.0
            ) + Math.pow(matrix[10].toDouble(), 2.0)
        ).toFloat()
        if (determinant(matrix) < 0) {
            ret[1] = -ret[1]
        }
        return ret
    }

    fun determinant(matrix: FloatArray): Float {
        var ret = 0f
        ret += matrix[0] * (matrix[5] * (matrix[10] * matrix[15] - matrix[11] * matrix[14]))
        ret -= matrix[1] * (matrix[6] * (matrix[11] * matrix[12] - matrix[8] * matrix[15]))
        ret += matrix[2] * (matrix[7] * (matrix[8] * matrix[13] - matrix[9] * matrix[12]))
        ret -= matrix[3] * (matrix[4] * (matrix[9] * matrix[14] - matrix[10] * matrix[13]))
        return ret
    }

    fun createRotationMatrixAroundVector(angle: Float, x: Float, y: Float, z: Float): FloatArray {
        val matrix = FloatArray(16)
        val offset = 0
        val cos = Math.cos(angle.toDouble()).toFloat()
        val sin = Math.sin(angle.toDouble()).toFloat()
        val cos_1 = 1 - cos

        // @formatter:off
        matrix[offset] = cos_1 * x * x + cos
        matrix[offset + 1] = cos_1 * x * y - z * sin
        matrix[offset + 2] = cos_1 * z * x + y * sin
        matrix[offset + 3] = 0f
        matrix[offset + 4] = cos_1 * x * y + z * sin
        matrix[offset + 5] = cos_1 * y * y + cos
        matrix[offset + 6] = cos_1 * y * z - x * sin
        matrix[offset + 7] = 0f
        matrix[offset + 8] = cos_1 * z * x - y * sin
        matrix[offset + 9] = cos_1 * y * z + x * sin
        matrix[offset + 10] = cos_1 * z * z + cos
        matrix[offset + 11] = 0f
        matrix[offset + 12] = 0f
        matrix[offset + 13] = 0f
        matrix[offset + 14] = 0f
        matrix[offset + 15] = 1f

        // @formatter:on
        return matrix
    }

    init {
        Matrix.setIdentityM(IDENTITY_MATRIX, 0)
    }
}