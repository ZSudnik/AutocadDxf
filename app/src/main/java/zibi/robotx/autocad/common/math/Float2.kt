package zibi.robotx.autocad.common.math

import java.io.Serializable
import java.text.MessageFormat

class Float2 @JvmOverloads constructor(/* renamed from: x */
    var f7x: Float = 0.0f, /* renamed from: y */
    var f8y: Float = 0.0f
) : Serializable {
    constructor(other: Float2) : this(other.f7x, other.f8y) {}

    fun setTo(x: Float, y: Float) {
        f7x = x
        f8y = y
    }

    fun setTo(other: Float2) {
        setTo(other.f7x, other.f8y)
    }

    fun nullify() {
        setTo(0.0f, 0.0f)
    }

    val isNull: Boolean
        get() = MathUtil.isZero(f7x) && MathUtil.isZero(
            f8y
        )

    fun invert() {
        setTo(-f7x, -f8y)
    }

    fun inc(x: Float, y: Float) {
        f7x += x
        f8y += y
    }

    fun inc(other: Float2) {
        inc(other.f7x, other.f8y)
    }

    fun dec(x: Float, y: Float) {
        f7x -= x
        f8y -= y
    }

    fun dec(other: Float2) {
        dec(other.f7x, other.f8y)
    }

    fun mul(value: Float) {
        f7x *= value
        f8y *= value
    }

    operator fun div(value: Float) {
        f7x /= value
        f8y /= value
    }

    val lengthSquared: Float
        get() = f7x * f7x + f8y * f8y
    var length: Float
        get() = Math.sqrt(lengthSquared.toDouble()).toFloat()
        set(value) {
            mul(value / length)
        }

    fun distanceTo(x: Float, y: Float): Float {
        val deltaX = f7x - x
        val deltaY = f8y - y
        return Math.sqrt((deltaX * deltaX + deltaY * deltaY).toDouble()).toFloat()
    }

    fun distanceTo(other: Float2): Float {
        return distanceTo(other.f7x, other.f8y)
    }

    // java.lang.Object
    override fun hashCode(): Int {
        return (java.lang.Float.floatToRawIntBits(f7x) + 31) * 31 + java.lang.Float.floatToRawIntBits(
            f8y
        )
    }

    // java.lang.Object
    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj !is Float2) {
            return false
        }
        val other = obj
        return java.lang.Float.floatToRawIntBits(f7x) == java.lang.Float.floatToRawIntBits(other.f7x) && java.lang.Float.floatToRawIntBits(
            f8y
        ) == java.lang.Float.floatToRawIntBits(other.f8y)
    }

    // java.lang.Object
    override fun toString(): String {
        return MessageFormat.format(
            "({0}; {1})", java.lang.Float.valueOf(f7x), java.lang.Float.valueOf(
                f8y
            )
        )
    }

    companion object {
        val VECTOR_X = baseVectorX()
        val VECTOR_Y = baseVectorY()
        private const val serialVersionUID: Long = 1
        fun baseVectorX(): Float2 {
            return Float2(1.0f, 0.0f)
        }

        fun baseVectorY(): Float2 {
            return Float2(0.0f, 1.0f)
        }

        fun getInversed(vector: Float2): Float2 {
            return Float2(-vector.f7x, -vector.f8y)
        }

        fun calculateInversed(vector: Float2, result: Float2) {
            result.f7x = -vector.f7x
            result.f8y = -vector.f8y
        }

        fun getInc(a: Float2, b: Float2): Float2 {
            val result = Float2(a)
            result.inc(b)
            return result
        }

        fun calculateInc(a: Float2, b: Float2, result: Float2) {
            result.setTo(a)
            result.inc(b)
        }

        fun getDec(a: Float2, b: Float2): Float2 {
            val result = Float2(a)
            result.dec(b)
            return result
        }

        fun calculateDec(a: Float2, b: Float2, result: Float2) {
            result.setTo(a)
            result.dec(b)
        }

        fun getMul(vector: Float2, value: Float): Float2 {
            val result = Float2(vector)
            result.mul(value)
            return result
        }

        fun calculateMul(vector: Float2, value: Float, result: Float2) {
            result.setTo(vector)
            result.mul(value)
        }

        fun getDiv(vector: Float2, value: Float): Float2 {
            val result = Float2(vector)
            result.div(value)
            return result
        }

        fun calculateDiv(vector: Float2, value: Float, result: Float2) {
            result.setTo(vector)
            result.div(value)
        }

        fun getResized(vector: Float2, length: Float): Float2 {
            val result = Float2(vector)
            result.length = length
            return result
        }

        fun calculateResized(vector: Float2, length: Float, result: Float2) {
            result.setTo(vector)
            result.length = length
        }

        fun getDotProduct(a: Float2, b: Float2): Float {
            return a.f7x * b.f7x + a.f8y * b.f8y
        }

        @Throws(RuntimeException::class)
        fun assertNotNanOrInfinite(vector: Float2) {
            if (java.lang.Float.isNaN(vector.f7x)) {
                throw RuntimeException("X component is NAN.")
            } else if (java.lang.Float.isNaN(vector.f8y)) {
                throw RuntimeException("Y component is NAN.")
            } else if (java.lang.Float.isInfinite(vector.f7x)) {
                throw RuntimeException("X component is Infinite.")
            } else if (java.lang.Float.isInfinite(vector.f8y)) {
                throw RuntimeException("Y component is Infinite.")
            }
        }
    }
}