package zibi.robotx.autocad.common.math

import java.io.Serializable
import java.text.MessageFormat

class Float4 @JvmOverloads constructor(/* renamed from: x */
    var f13x: Float = 0.0f, /* renamed from: y */
    var f14y: Float = 0.0f, /* renamed from: z */
    var f15z: Float = 0.0f, /* renamed from: w */
    var f12w: Float = 0.0f
) : Serializable {
    constructor(other: Float4) : this(other.f13x, other.f14y, other.f15z, other.f12w) {}

    fun setTo(x: Float, y: Float, z: Float, w: Float) {
        f13x = x
        f14y = y
        f15z = z
        f12w = w
    }

    fun setTo(other: Float4) {
        setTo(other.f13x, other.f14y, other.f15z, other.f12w)
    }

    fun nullify() {
        setTo(0.0f, 0.0f, 0.0f, 0.0f)
    }

    val isNull: Boolean
        get() = MathUtil.isZero(f13x) && MathUtil.isZero(
            f14y
        ) && MathUtil.isZero(f15z) && MathUtil.isZero(
            f12w
        )

    fun invert() {
        setTo(-f13x, -f14y, -f15z, -f12w)
    }

    fun inc(x: Float, y: Float, z: Float, w: Float) {
        f13x += x
        f14y += y
        f15z += z
        f12w += w
    }

    fun inc(other: Float4) {
        inc(other.f13x, other.f14y, other.f15z, other.f12w)
    }

    fun dec(x: Float, y: Float, z: Float, w: Float) {
        f13x -= x
        f14y -= y
        f15z -= z
        f12w -= w
    }

    fun dec(other: Float4) {
        dec(other.f13x, other.f14y, other.f15z, other.f12w)
    }

    fun mul(value: Float) {
        f13x *= value
        f14y *= value
        f15z *= value
        f12w *= value
    }

    operator fun div(value: Float) {
        f13x /= value
        f14y /= value
        f15z /= value
        f12w /= value
    }

    val lengthSquared: Float
        get() = f13x * f13x + f14y * f14y + f15z * f15z + f12w * f12w
    var length: Float
        get() = Math.sqrt(lengthSquared.toDouble()).toFloat()
        set(value) {
            mul(value / length)
        }

    fun distanceTo(x: Float, y: Float, z: Float, w: Float): Float {
        val deltaX = f13x - x
        val deltaY = f14y - y
        val deltaZ = f15z - z
        val deltaW = f12w - w
        return Math.sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ + deltaW * deltaW).toDouble())
            .toFloat()
    }

    fun distanceTo(other: Float4): Float {
        return distanceTo(other.f13x, other.f14y, other.f15z, other.f12w)
    }

    // java.lang.Object
    override fun hashCode(): Int {
        return (((java.lang.Float.floatToRawIntBits(f13x) + 31) * 31 + java.lang.Float.floatToRawIntBits(
            f14y
        )) * 31 + java.lang.Float.floatToRawIntBits(f15z)) * 31 + java.lang.Float.floatToRawIntBits(
            f12w
        )
    }

    // java.lang.Object
    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj !is Float4) {
            return false
        }
        val other = obj
        return java.lang.Float.floatToRawIntBits(f13x) == java.lang.Float.floatToRawIntBits(other.f13x) && java.lang.Float.floatToRawIntBits(
            f14y
        ) == java.lang.Float.floatToRawIntBits(other.f14y) && java.lang.Float.floatToRawIntBits(f15z) == java.lang.Float.floatToRawIntBits(
            other.f15z
        ) && java.lang.Float.floatToRawIntBits(
            f12w
        ) == java.lang.Float.floatToRawIntBits(other.f12w)
    }

    // java.lang.Object
    override fun toString(): String {
        return MessageFormat.format(
            "({0}; {1}; {2}; {3})", java.lang.Float.valueOf(f13x), java.lang.Float.valueOf(
                f14y
            ), java.lang.Float.valueOf(f15z), java.lang.Float.valueOf(f12w)
        )
    }

    companion object {
        private const val serialVersionUID: Long = 1
    }
}