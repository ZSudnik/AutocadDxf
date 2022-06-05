package zibi.robotx.autocad.data.obj

class ObjMesh {
    val faces: MutableList<ObjFace> = mutableListOf<ObjFace>()
    var materialName: String? = null
}