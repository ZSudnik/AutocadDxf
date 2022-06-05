package zibi.robotx.autocad.data.obj


class ObjModel {

    val vertices: MutableList<ObjVertex> = mutableListOf()
    val texCoords: MutableList<ObjTexCoord> = mutableListOf()
    val normals: MutableList<ObjNormal> = mutableListOf()
    val objects: MutableList<ObjObject> = mutableListOf()

    val materialLibraryRefs: MutableList<String> = mutableListOf()
    fun getVertex(reference: ObjVertexReference): ObjVertex {
        return vertices[reference.vertexIndex]
    }

    fun getTexCoord(reference: ObjVertexReference): ObjTexCoord {
        return texCoords[reference.texCoordIndex]
    }

    fun getNormal(reference: ObjVertexReference): ObjNormal {
        return normals[reference.normalIndex]
    }

    fun getObject(name: String): ObjObject? {
        for (itObj in objects) {
            if (itObj.name == name) {
                return itObj
            }
        }
        return null
    }
}