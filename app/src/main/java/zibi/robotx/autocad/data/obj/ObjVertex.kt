package zibi.robotx.autocad.data.obj

class ObjVertex {

    @JvmField
    var x: Float
    @JvmField
    var y: Float
    @JvmField
    var z: Float

    constructor() {
        x = 0.0f
        y = 0.0f
        z = 0.0f
    }

    constructor(x: Float, y: Float, z: Float) {
        this.x = 0.0f
        this.y = 0.0f
        this.z = 0.0f
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(other: ObjVertex) {
        x = 0.0f
        y = 0.0f
        z = 0.0f
        x = other.x
        y = other.y
        z = other.z
    }

    override fun equals(obj: Any?): Boolean {
        if (obj === this) {
            return true
        }
        if (obj !is ObjVertex) {
            return false
        }
        val other = obj
        return (x.toRawBits() == other.x.toRawBits()) &&
                (y.toRawBits() == other.y.toRawBits()) &&
                (z.toRawBits() == other.z.toRawBits() )
    }

    override fun hashCode(): Int {
        return (x.toRawBits() * 31 + y.toRawBits()) * 31 + z.toRawBits()
    }
}