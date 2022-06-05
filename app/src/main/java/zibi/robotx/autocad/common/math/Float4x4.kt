package zibi.robotx.autocad.common.math

import java.io.Serializable

class Float4x4 : Serializable {
    val elements: FloatArray

    constructor() {
        elements = FloatArray(ELEMENT_COUNT)
    }

    constructor(other: Float4x4) {
        elements = FloatArray(ELEMENT_COUNT)
        setTo(other)
    }

    constructor(rotation: Float3x3?) {
        elements = FloatArray(ELEMENT_COUNT)
        elements[0] = rotation!!.elements[0]
        elements[1] = rotation.elements[1]
        elements[2] = rotation.elements[2]
        elements[3] = 0.0f
        elements[4] = rotation.elements[3]
        elements[5] = rotation.elements[4]
        elements[6] = rotation.elements[5]
        elements[7] = 0.0f
        elements[8] = rotation.elements[6]
        elements[9] = rotation.elements[7]
        elements[10] = rotation.elements[8]
        elements[11] = 0.0f
        elements[12] = 0.0f
        elements[13] = 0.0f
        elements[14] = 0.0f
        elements[15] = 1.0f
    }

    fun setTo(matrix: Float4x4) {
        System.arraycopy(matrix.elements, 0, elements, 0, ELEMENT_COUNT)
    }

    fun multiply(vector: Float3): Float3 {
        val result = Float3()
        result.x =
            elements[0] * vector.x + elements[4] * vector.y + elements[8] * vector.z + elements[12] * 1.0f
        result.y =
            elements[1] * vector.x + elements[5] * vector.y + elements[9] * vector.z + elements[13] * 1.0f
        result.z =
            elements[2] * vector.x + elements[6] * vector.y + elements[10] * vector.z + elements[14] * 1.0f
        return result
    }

    fun multiply(vector: Float4): Float4 {
        val result = Float4()
        result.f13x =
            elements[0] * vector.f13x + elements[4] * vector.f14y + elements[8] * vector.f15z + elements[12] * vector.f12w
        result.f14y =
            elements[1] * vector.f13x + elements[5] * vector.f14y + elements[9] * vector.f15z + elements[13] * vector.f12w
        result.f15z =
            elements[2] * vector.f13x + elements[6] * vector.f14y + elements[10] * vector.f15z + elements[14] * vector.f12w
        result.f12w =
            elements[3] * vector.f13x + elements[7] * vector.f14y + elements[11] * vector.f15z + elements[15] * vector.f12w
        return result
    }

    fun multiply(matrix: Float4x4?) {
        val newElements = floatArrayOf(
            elements[0] * matrix!!.elements[0] + elements[4] * matrix.elements[1] + elements[8] * matrix.elements[2] + elements[12] * matrix.elements[3],
            elements[1] * matrix.elements[0] + elements[5] * matrix.elements[1] + elements[9] * matrix.elements[2] + elements[13] * matrix.elements[3],
            elements[2] * matrix.elements[0] + elements[6] * matrix.elements[1] + elements[10] * matrix.elements[2] + elements[14] * matrix.elements[3],
            elements[3] * matrix.elements[0] + elements[7] * matrix.elements[1] + elements[11] * matrix.elements[2] + elements[15] * matrix.elements[3],
            elements[0] * matrix.elements[4] + elements[4] * matrix.elements[5] + elements[8] * matrix.elements[6] + elements[12] * matrix.elements[7],
            elements[1] * matrix.elements[4] + elements[5] * matrix.elements[5] + elements[9] * matrix.elements[6] + elements[13] * matrix.elements[7],
            elements[2] * matrix.elements[4] + elements[6] * matrix.elements[5] + elements[10] * matrix.elements[6] + elements[14] * matrix.elements[7],
            elements[3] * matrix.elements[4] + elements[7] * matrix.elements[5] + elements[11] * matrix.elements[6] + elements[15] * matrix.elements[7],
            elements[0] * matrix.elements[8] + elements[4] * matrix.elements[9] + elements[8] * matrix.elements[10] + elements[12] * matrix.elements[11],
            elements[1] * matrix.elements[8] + elements[5] * matrix.elements[9] + elements[9] * matrix.elements[10] + elements[13] * matrix.elements[11],
            elements[2] * matrix.elements[8] + elements[6] * matrix.elements[9] + elements[10] * matrix.elements[10] + elements[14] * matrix.elements[11],
            elements[3] * matrix.elements[8] + elements[7] * matrix.elements[9] + elements[11] * matrix.elements[10] + elements[15] * matrix.elements[11],
            elements[0] * matrix.elements[12] + elements[4] * matrix.elements[13] + elements[8] * matrix.elements[14] + elements[12] * matrix.elements[15],
            elements[1] * matrix.elements[12] + elements[5] * matrix.elements[13] + elements[9] * matrix.elements[14] + elements[13] * matrix.elements[15],
            elements[2] * matrix.elements[12] + elements[6] * matrix.elements[13] + elements[10] * matrix.elements[14] + elements[14] * matrix.elements[15],
            elements[3] * matrix.elements[12] + elements[7] * matrix.elements[13] + elements[11] * matrix.elements[14] + elements[15] * matrix.elements[15]
        )
        for (i in 0 until ELEMENT_COUNT) {
            elements[i] = newElements[i]
        }
    }

    var position: Float3
        get() {
            val result = Float3()
            result.x = elements[12]
            result.y = elements[13]
            result.z = elements[14]
            return result
        }
        set(position) {
            elements[12] = position.x
            elements[13] = position.y
            elements[14] = position.z
        }
    var rotation: Float3x3
        get() {
            val result = Float3x3()
            result.elements[0] = elements[0]
            result.elements[3] = elements[4]
            result.elements[6] = elements[8]
            result.elements[1] = elements[1]
            result.elements[4] = elements[5]
            result.elements[7] = elements[9]
            result.elements[2] = elements[2]
            result.elements[5] = elements[6]
            result.elements[8] = elements[10]
            return result
        }
        set(rotation) {
            elements[0] = rotation.elements[0]
            elements[4] = rotation.elements[3]
            elements[8] = rotation.elements[6]
            elements[1] = rotation.elements[1]
            elements[5] = rotation.elements[4]
            elements[9] = rotation.elements[7]
            elements[2] = rotation.elements[2]
            elements[6] = rotation.elements[5]
            elements[10] = rotation.elements[8]
        }

    fun inverse(): Float4x4 {
        val rotation = rotation.transpose()
        val position: Float3 = rotation!!.multiply(
            Float3.Companion.getInversed(
                position
            )
        )
        val result = Float4x4(rotation)
        result.position = position
        result.elements[3] = 0.0f
        result.elements[7] = 0.0f
        result.elements[11] = 0.0f
        result.elements[15] = 1.0f
        return result
    }

    fun makePerspective(width: Float, height: Float, near: Float, far: Float) {
        setTo(perspective(width, height, near, far))
    }

    companion object {
        private const val ELEMENT_COUNT = 16
        const val M_11 = 0
        const val M_12 = 4
        const val M_13 = 8
        const val M_14 = 12
        const val M_21 = 1
        const val M_22 = 5
        const val M_23 = 9
        const val M_24 = 13
        const val M_31 = 2
        const val M_32 = 6
        const val M_33 = 10
        const val M_34 = 14
        const val M_41 = 3
        const val M_42 = 7
        const val M_43 = 11
        const val M_44 = 15
        private const val serialVersionUID: Long = 1
        fun identity(): Float4x4 {
            val result = Float4x4()
            result.elements[0] = 1.0f
            result.elements[5] = 1.0f
            result.elements[10] = 1.0f
            result.elements[15] = 1.0f
            return result
        }

        fun translation(x: Float, y: Float, z: Float): Float4x4 {
            val result = identity()
            result.elements[12] = x
            result.elements[13] = y
            result.elements[14] = z
            return result
        }

        fun translation(position: Float3): Float4x4 {
            return translation(position.x, position.y, position.z)
        }

        fun scale(x: Float, y: Float, z: Float): Float4x4 {
            val result = Float4x4()
            result.elements[0] = x
            result.elements[5] = y
            result.elements[10] = z
            result.elements[15] = 1.0f
            return result
        }

        fun rotation(cs: Float, sn: Float, x: Float, y: Float, z: Float): Float4x4 {
            val result = Float4x4()
            result.elements[0] = x * x * (1.0f - cs) + cs
            result.elements[1] = y * x * (1.0f - cs) + z * sn
            result.elements[2] = x * z * (1.0f - cs) - y * sn
            result.elements[3] = 0.0f
            result.elements[4] = x * y * (1.0f - cs) - z * sn
            result.elements[5] = y * y * (1.0f - cs) + cs
            result.elements[6] = y * z * (1.0f - cs) + x * sn
            result.elements[7] = 0.0f
            result.elements[8] = x * z * (1.0f - cs) + y * sn
            result.elements[9] = y * z * (1.0f - cs) - x * sn
            result.elements[10] = z * z * (1.0f - cs) + cs
            result.elements[11] = 0.0f
            result.elements[12] = 0.0f
            result.elements[13] = 0.0f
            result.elements[14] = 0.0f
            result.elements[15] = 1.0f
            return result
        }

        fun rotation(angle: Float, x: Float, y: Float, z: Float): Float4x4 {
            var length = Math.sqrt((x * x + y * y + z * z).toDouble())
                .toFloat()
            if (length < 1.0E-5f) {
                length = 1.0f
            }
            return rotation(
                Math.cos(Math.toRadians(angle.toDouble())).toFloat(), Math.sin(
                    Math.toRadians(
                        angle.toDouble()
                    )
                ).toFloat(), x / length, y / length, z / length
            )
        }

        fun perspective(width: Float, height: Float, near: Float, far: Float): Float4x4 {
            val result = Float4x4()
            result.elements[0] = 2.0f * near / width
            result.elements[5] = 2.0f * near / height
            result.elements[10] = (far + near) / (near - far)
            result.elements[14] = 2.0f * far * near / (near - far)
            result.elements[11] = -1.0f
            return result
        }

        fun orthogonal(width: Float, height: Float, near: Float, far: Float): Float4x4 {
            return orthogonal(-width / 2.0f, width / 2.0f, -height / 2.0f, height / 2.0f, near, far)
        }

        fun orthogonal(
            left: Float,
            right: Float,
            bottom: Float,
            top: Float,
            near: Float,
            far: Float
        ): Float4x4 {
            val result = Float4x4()
            result.elements[0] = 2.0f / (right - left)
            result.elements[12] = -(right + left) / (right - left)
            result.elements[5] = 2.0f / (top - bottom)
            result.elements[13] = -(top + bottom) / (top - bottom)
            result.elements[10] = -2.0f / (far - near)
            result.elements[14] = -(far + near) / (far - near)
            result.elements[15] = 1.0f
            return result
        }
    }
}