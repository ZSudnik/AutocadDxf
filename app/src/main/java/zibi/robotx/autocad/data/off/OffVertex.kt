package zibi.robotx.autocad.data.off

class OffVertex {

    var x = 0.0f
    var y = 0.0f
    var z = 0.0f

    var r = 0.0f
    var g = 0.0f
    var b = 0.0f
    var a = 0.0f

    constructor() {}
    constructor(x: Float, y: Float, z: Float) {}

    override fun equals(obj: Any?): Boolean {
        if (obj === this) {
            return true
        }
        if (obj !is OffVertex) {
            return false
        }
        return java.lang.Float.floatToRawIntBits(x) == java.lang.Float.floatToRawIntBits(obj.x) &&
                java.lang.Float.floatToRawIntBits(y) == java.lang.Float.floatToRawIntBits(obj.y) &&
                java.lang.Float.floatToRawIntBits(z) == java.lang.Float.floatToRawIntBits(obj.z) &&
                java.lang.Float.floatToRawIntBits(r) == java.lang.Float.floatToRawIntBits(obj.r) &&
                java.lang.Float.floatToRawIntBits(g) == java.lang.Float.floatToRawIntBits(obj.g) &&
                java.lang.Float.floatToRawIntBits(b) == java.lang.Float.floatToRawIntBits(obj.b) &&
                java.lang.Float.floatToRawIntBits(a) == java.lang.Float.floatToRawIntBits(obj.a)
    }

    override fun hashCode(): Int {
        val result = java.lang.Float.floatToRawIntBits(x)
        val result2 = result + result * 31 + java.lang.Float.floatToRawIntBits(y)
        val result3 = result2 + result2 * 31 + java.lang.Float.floatToRawIntBits(z)
        val result4 = result3 + result3 * 31 + java.lang.Float.floatToRawIntBits(r)
        val result5 = result4 + result4 * 31 + java.lang.Float.floatToRawIntBits(g)
        val result6 = result5 + result5 * 31 + java.lang.Float.floatToRawIntBits(b)
        return result6 + result6 * 31 + java.lang.Float.floatToRawIntBits(a)
    }
}