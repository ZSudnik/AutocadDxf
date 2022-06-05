package zibi.robotx.autocad.data.obj

class ObjVertexReference {

    @JvmField
    var vertexIndex = -1
    @JvmField
    var normalIndex = -1
    @JvmField
    var texCoordIndex = -1
    override fun equals(obj: Any?): Boolean {
        if (obj === this) {
            return true
        }
        if (obj !is ObjVertexReference) {
            return false
        }
        val other = obj
        return vertexIndex == other.vertexIndex && texCoordIndex == other.texCoordIndex && normalIndex == other.normalIndex
    }

    override fun hashCode(): Int {
        return (vertexIndex * 31 + texCoordIndex) * 31 + normalIndex
    }

    companion object {
        const val UNDEFINED_INDEX = -1
    }
}