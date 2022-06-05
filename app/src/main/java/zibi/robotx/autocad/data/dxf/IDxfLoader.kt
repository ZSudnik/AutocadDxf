package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.error.DxfException
import java.io.IOException
import java.io.InputStream

interface IDxfLoader {
    var maxFaceCount: Int
    var maxVertexCount: Int


    @Throws(DxfException::class, IOException::class)
    fun load(inputStream: InputStream?): DxfObject?

    companion object {
        const val DEFAULT_MAX_FACE_COUNT = 65536
        const val DEFAULT_MAX_VERTEX_COUNT = 65536
    }
}