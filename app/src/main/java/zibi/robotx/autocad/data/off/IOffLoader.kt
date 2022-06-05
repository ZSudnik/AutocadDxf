package zibi.robotx.autocad.data.off

import zibi.robotx.autocad.data.off.error.OffException
import java.io.IOException
import java.io.InputStream

interface IOffLoader {
    var maxFaceCount: Int
    var maxVertexCount: Int

    @Throws(OffException::class, IOException::class)
    fun load(inputStream: InputStream?): OffObject?

    companion object {
        const val DEFAULT_MAX_FACE_COUNT = 65536
        const val DEFAULT_MAX_VERTEX_COUNT = 65536
    }
}