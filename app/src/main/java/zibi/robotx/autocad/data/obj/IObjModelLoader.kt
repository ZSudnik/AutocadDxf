package zibi.robotx.autocad.data.obj


import zibi.robotx.autocad.data.obj.error.ObjException
import java.io.IOException
import java.io.InputStream


interface IObjModelLoader {
    var maxFaceCount: Int
    var maxVertexCount: Int

    @Throws(ObjException::class, IOException::class)
    fun load(inputStream: InputStream?): ObjModel?

    companion object {
        const val DEFAULT_MAX_FACE_COUNT = 65536
        const val DEFAULT_MAX_VERTEX_COUNT = 65536
    }
}