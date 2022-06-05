package zibi.robotx.autocad.data.obj

class ObjColor {

    var b: Float

    var g: Float

    var r: Float

    constructor() {
        r = 0.0f
        g = 0.0f
        b = 0.0f
    }

    constructor(r: Float, g: Float, b: Float) {
        this.r = 0.0f
        this.g = 0.0f
        this.b = 0.0f
        this.r = r
        this.g = g
        this.b = b
    }

    constructor(other: ObjColor) {
        r = 0.0f
        g = 0.0f
        b = 0.0f
        r = other.r
        g = other.g
        b = other.b
    }

    override fun equals(obj: Any?): Boolean {
        if (obj === this) {
            return true
        }
        if (obj !is ObjColor) {
            return false
        }
        val other = obj
        return (r.toRawBits() == other.r.toRawBits()) &&
                (g.toRawBits() == other.g.toRawBits()) &&
                (b.toRawBits() == other.b.toRawBits() )
    }

    override fun hashCode(): Int {
        return (r.toRawBits() * 31 + g.toRawBits()) * 31 + b.toRawBits()
    }
}