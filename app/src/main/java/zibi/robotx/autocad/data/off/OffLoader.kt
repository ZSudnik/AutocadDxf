package zibi.robotx.autocad.data.off

import zibi.robotx.autocad.data.off.error.OffException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class OffLoader : IOffLoader {

    override var maxVertexCount = 65536
    override var maxFaceCount = 65536

    @Throws(OffException::class, IOException::class)
    override fun load(inputStream: InputStream?): OffObject? {
        return try {
            load(BufferedReader(InputStreamReader(inputStream)))
        } finally {
            inputStream!!.close()
        }
    }

    @Throws(IOException::class)
    private fun load(reader: BufferedReader): OffObject? {
        val context = OffLoaderContext(this)
        context.readHeader(reader)
        context.readDimensions(reader)
        context.readVertices(reader)
        context.readFaces(reader)
        return context.offObject
    }
}