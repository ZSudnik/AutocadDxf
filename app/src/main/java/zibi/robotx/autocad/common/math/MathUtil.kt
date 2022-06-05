package zibi.robotx.autocad.common.math


object MathUtil {
    private const val FLOAT_MARGIN = 1.0E-5f
    fun isZero(value: Float): Boolean {
        return Math.abs(value) < FLOAT_MARGIN
    }

    @JvmOverloads
    fun areEqual(a: Float, b: Float, margin: Float = FLOAT_MARGIN): Boolean {
        return Math.abs(a - b) < margin
    }
}