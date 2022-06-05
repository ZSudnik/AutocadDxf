package zibi.robotx.autocad.data.obj

class ObjTexCoord {

    @JvmField
    var u: Float
    @JvmField
    var v: Float
    @JvmField
    var w: Float

    constructor() {
        u = 0.0f
        v = 0.0f
        w = 0.0f
    }

    constructor(u: Float, v: Float, w: Float) {
        this.u = 0.0f
        this.v = 0.0f
        this.w = 0.0f
        this.u = u
        this.v = v
        this.w = w
    }

    constructor(other: ObjTexCoord) {
        u = 0.0f
        v = 0.0f
        w = 0.0f
        u = other.u
        v = other.v
        w = other.w
    }

    override fun equals(obj: Any?): Boolean {
        if (obj === this) {
            return true
        }
        if (obj !is ObjTexCoord) {
            return false
        }
        val other = obj
        return return (u.toRawBits() == other.u.toRawBits()) &&
                (v.toRawBits() == other.v.toRawBits()) &&
                (w.toRawBits() == other.w.toRawBits() )
    }

    override fun hashCode(): Int {
        return return (u.toRawBits() * 31 + v.toRawBits()) * 31 + w.toRawBits()
    }
}