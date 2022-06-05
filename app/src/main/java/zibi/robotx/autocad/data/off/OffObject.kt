package zibi.robotx.autocad.data.off

class OffObject {

    val vertices: MutableList<OffVertex?> = mutableListOf()
    val faces: MutableList<OffFace?> = mutableListOf()
    var hasVertexColors = false
    fun getVertex(index: Int): OffVertex? {
        return vertices[index]
    }
}