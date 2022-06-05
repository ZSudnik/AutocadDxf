package zibi.robotx.autocad.data.obj

class ObjFace {

    val references: MutableList<ObjVertexReference> = mutableListOf()//ArrayList<ObjVertexReference?>(4)
    private var hasTexCoords = false
    private var hasNormals = false

    fun setHasTexCoords(hasTexCoords: Boolean) {
        this.hasTexCoords = hasTexCoords
    }

    fun hasTexCoords(): Boolean {
        return hasTexCoords
    }

    fun setHasNormals(hasNormals: Boolean) {
        this.hasNormals = hasNormals
    }

    fun hasNormals(): Boolean {
        return hasNormals
    }
}