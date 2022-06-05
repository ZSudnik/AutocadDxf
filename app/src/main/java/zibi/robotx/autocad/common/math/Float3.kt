package zibi.robotx.autocad.common.math


import java.io.Serializable
import java.text.MessageFormat

class Float3 @JvmOverloads constructor(/* renamed from: x */
    var x: Float = 0.0f, /* renamed from: y */
    var y: Float = 0.0f, /* renamed from: z */
    var z: Float = 0.0f
) : Serializable {
    constructor(other: Float3) : this(other.x, other.y, other.z) {}

    fun setTo(x: Float, y: Float, z: Float) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun setTo(other: Float3) {
        setTo(other.x, other.y, other.z)
    }

    fun nullify() {
        setTo(0.0f, 0.0f, 0.0f)
    }

    val isNull: Boolean
        get() = MathUtil.isZero(x) && MathUtil.isZero(
            y
        ) && MathUtil.isZero(z)

    fun invert() {
        setTo(-x, -y, -z)
    }

    fun inc(x: Float, y: Float, z: Float) {
        this.x += x
        this.y += y
        this.z += z
    }

    fun inc(other: Float3) {
        inc(other.x, other.y, other.z)
    }

    fun dec(x: Float, y: Float, z: Float) {
        this.x -= x
        this.y -= y
        this.z -= z
    }

    fun dec(other: Float3) {
        dec(other.x, other.y, other.z)
    }

    fun mul(value: Float) {
        x *= value
        y *= value
        z *= value
    }

    operator fun div(value: Float) {
        x /= value
        y /= value
        z /= value
    }

    val lengthSquared: Float
        get() = x * x + y * y + z * z
    var length: Float
        get() = Math.sqrt(lengthSquared.toDouble()).toFloat()
        set(value) {
            mul(value / length)
        }

    fun distanceTo(x: Float, y: Float, z: Float): Float {
        val deltaX = this.x - x
        val deltaY = this.y - y
        val deltaZ = this.z - z
        return Math.sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ).toDouble())
            .toFloat()
    }

    fun distanceTo(other: Float3): Float {
        return distanceTo(other.x, other.y, other.z)
    }

    // java.lang.Object
    override fun hashCode(): Int {
        return ((java.lang.Float.floatToRawIntBits(x) + 31) * 31 + java.lang.Float.floatToRawIntBits(
            y
        )) * 31 + java.lang.Float.floatToRawIntBits(z)
    }

    // java.lang.Object
    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj !is Float3) {
            return false
        }
        val other = obj
        return java.lang.Float.floatToRawIntBits(x) == java.lang.Float.floatToRawIntBits(other.x) && java.lang.Float.floatToRawIntBits(
            y
        ) == java.lang.Float.floatToRawIntBits(other.y) && java.lang.Float.floatToRawIntBits(z) == java.lang.Float.floatToRawIntBits(
            other.z
        )
    }

    // java.lang.Object
    override fun toString(): String {
        return MessageFormat.format(
            "({0}; {1}; {2})", java.lang.Float.valueOf(x), java.lang.Float.valueOf(
                y
            ), java.lang.Float.valueOf(z)
        )
    }

    companion object {
        val VECTOR_X = baseVectorX()
        val VECTOR_Y = baseVectorY()
        val VECTOR_Z = baseVectorZ()
        private const val serialVersionUID: Long = 1
        fun baseVectorX(): Float3 {
            return Float3(1.0f, 0.0f, 0.0f)
        }

        fun baseVectorY(): Float3 {
            return Float3(0.0f, 1.0f, 0.0f)
        }

        fun baseVectorZ(): Float3 {
            return Float3(0.0f, 0.0f, 1.0f)
        }

        fun getInversed(vector: Float3): Float3 {
            return Float3(-vector.x, -vector.y, -vector.z)
        }

        fun calculateInversed(vector: Float3, result: Float3) {
            result.x = -vector.x
            result.y = -vector.y
            result.z = -vector.z
        }

        fun getInc(a: Float3, b: Float3): Float3 {
            val result = Float3(a)
            result.inc(b)
            return result
        }

        fun calculateInc(a: Float3, b: Float3, result: Float3) {
            result.setTo(a)
            result.inc(b)
        }

        fun getDec(a: Float3, b: Float3): Float3 {
            val result = Float3(a)
            result.dec(b)
            return result
        }

        fun calculateDec(a: Float3, b: Float3, result: Float3) {
            result.setTo(a)
            result.dec(b)
        }

        fun getMul(vector: Float3, value: Float): Float3 {
            val result = Float3(vector)
            result.mul(value)
            return result
        }

        fun calculateMul(vector: Float3, value: Float, result: Float3) {
            result.setTo(vector)
            result.mul(value)
        }

        fun getDiv(vector: Float3, value: Float): Float3 {
            val result = Float3(vector)
            result.div(value)
            return result
        }

        fun calculateDiv(vector: Float3, value: Float, result: Float3) {
            result.setTo(vector)
            result.div(value)
        }

        fun getResized(vector: Float3, length: Float): Float3 {
            val result = Float3(vector)
            result.length = length
            return result
        }

        fun calculateResized(vector: Float3, length: Float, result: Float3) {
            result.setTo(vector)
            result.length = length
        }

        fun getDotProduct(a: Float3?, b: Float3?): Float {
            return a!!.x * b!!.x + a.y * b.y + a.z * b.z
        }

        fun getCrossProduct(a: Float3, b: Float3): Float3 {
            val result = Float3()
            calculateCrossProduct(a, b, result)
            return result
        }

        fun calculateCrossProduct(a: Float3, b: Float3, result: Float3) {
            result.x = a.y * b.z - a.z * b.y
            result.y = a.z * b.x - a.x * b.z
            result.z = a.x * b.y - a.y * b.x
        }

        @Throws(RuntimeException::class)
        fun assertNotNanOrInfinite(vector: Float3) {
            if (java.lang.Float.isNaN(vector.x)) {
                throw RuntimeException("X component is NAN.")
            } else if (java.lang.Float.isNaN(vector.y)) {
                throw RuntimeException("Y component is NAN.")
            } else if (java.lang.Float.isNaN(vector.z)) {
                throw RuntimeException("Z component is NAN.")
            } else if (java.lang.Float.isInfinite(vector.x)) {
                throw RuntimeException("X component is Infinite.")
            } else if (java.lang.Float.isInfinite(vector.y)) {
                throw RuntimeException("Y component is Infinite.")
            } else if (java.lang.Float.isInfinite(vector.z)) {
                throw RuntimeException("Z component is Infinite.")
            }
        }
    }
}