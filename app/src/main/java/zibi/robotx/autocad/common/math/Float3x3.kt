package zibi.robotx.autocad.common.math

import java.io.Serializable

class Float3x3 : Serializable {
    val elements: FloatArray

    constructor() {
        elements = FloatArray(9)
    }

    constructor(elements: FloatArray) {
        this.elements = FloatArray(9)
        require(elements.size == 9) { "Element count should be 9." }
        for (i in 0..8) {
            this.elements[i] = elements[i]
        }
    }

    constructor(copy: Float3x3) {
        elements = FloatArray(9)
        for (i in 0..8) {
            elements[i] = copy.elements[i]
        }
    }

    fun transpose(): Float3x3 {
        val result = Float3x3()
        result.elements[0] = elements[0]
        result.elements[3] = elements[1]
        result.elements[6] = elements[2]
        result.elements[1] = elements[3]
        result.elements[4] = elements[4]
        result.elements[7] = elements[5]
        result.elements[2] = elements[6]
        result.elements[5] = elements[7]
        result.elements[8] = elements[8]
        return result
    }

    fun multiply(vector: Float3): Float3 {
        val result = Float3()
        result.x =
            elements[0] * vector.x + elements[3] * vector.y + elements[6] * vector.z
        result.y =
            elements[1] * vector.x + elements[4] * vector.y + elements[7] * vector.z
        result.z =
            elements[2] * vector.x + elements[5] * vector.y + elements[8] * vector.z
        return result
    }

    fun multiply(matrix: Float3x3): Float3x3 {
        return Float3x3(
            floatArrayOf(
                elements[0] * matrix.elements[0] + elements[3] * matrix.elements[1] + elements[6] * matrix.elements[2],
                elements[1] * matrix.elements[0] + elements[4] * matrix.elements[1] + elements[7] * matrix.elements[2],
                elements[2] * matrix.elements[0] + elements[5] * matrix.elements[1] + elements[8] * matrix.elements[2],
                elements[0] * matrix.elements[3] + elements[3] * matrix.elements[4] + elements[6] * matrix.elements[5],
                elements[1] * matrix.elements[3] + elements[4] * matrix.elements[4] + elements[7] * matrix.elements[5],
                elements[2] * matrix.elements[3] + elements[5] * matrix.elements[4] + elements[8] * matrix.elements[5],
                elements[0] * matrix.elements[6] + elements[3] * matrix.elements[7] + elements[6] * matrix.elements[8],
                elements[1] * matrix.elements[6] + elements[4] * matrix.elements[7] + elements[7] * matrix.elements[8],
                elements[2] * matrix.elements[6] + elements[5] * matrix.elements[7] + elements[8] * matrix.elements[8]
            )
        )
    }

    val vectorX: Float3
        get() {
            val result = Float3()
            result.x = elements[0]
            result.y = elements[1]
            result.z = elements[2]
            return result
        }
    val vectorY: Float3
        get() {
            val result = Float3()
            result.x = elements[3]
            result.y = elements[4]
            result.z = elements[5]
            return result
        }
    val vectorZ: Float3
        get() {
            val result = Float3()
            result.x = elements[6]
            result.y = elements[7]
            result.z = elements[8]
            return result
        }

    companion object {
        private const val ELEMENT_COUNT = 9
        const val M_11 = 0
        const val M_12 = 3
        const val M_13 = 6
        const val M_21 = 1
        const val M_22 = 4
        const val M_23 = 7
        const val M_31 = 2
        const val M_32 = 5
        const val M_33 = 8
        private const val serialVersionUID: Long = 1
        fun identity(): Float3x3 {
            val result = Float3x3()
            result.elements[0] = 1.0f
            result.elements[4] = 1.0f
            result.elements[8] = 1.0f
            return result
        }

        fun translation(x: Float, y: Float): Float3x3 {
            val result = identity()
            result.elements[6] = x
            result.elements[7] = y
            return result
        }

        fun rotation(angle: Double): Float3x3 {
            val cs = Math.cos(Math.toRadians(angle)).toFloat()
            val sn = Math.sin(Math.toRadians(angle)).toFloat()
            val result = Float3x3()
            result.elements[0] = cs
            result.elements[1] = sn
            result.elements[3] = -sn
            result.elements[4] = cs
            result.elements[8] = 1.0f
            return result
        }
    }
}