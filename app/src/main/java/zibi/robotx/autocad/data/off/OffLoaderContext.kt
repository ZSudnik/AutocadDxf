package zibi.robotx.autocad.data.off

import zibi.robotx.autocad.data.off.error.OffCorruptException
import zibi.robotx.autocad.data.off.error.OffSizeException
import zibi.robotx.autocad.data.off.error.OffUnsupportedException
import java.io.BufferedReader
import java.io.IOException


internal class OffLoaderContext(private val loader: IOffLoader) {
    val offObject = OffObject()
    private var vertexCount = 0
    private var faceCount = 0
    @Throws(IOException::class)
    fun readHeader(reader: BufferedReader) {
        val header = OffLoaderUtil.readContentLineSingle(reader)
        if (OFF_BINARY_HEADER_SUFFIX.equals(header, ignoreCase = true)) {
            throw OffUnsupportedException("Binary OFF not supported.")
        } else if (!header.endsWith(OFF_HEADER)) {
            throw OffCorruptException("Missing header.")
        } else if (!OFF_HEADER.equals(header, ignoreCase = true)) {
            if (OFF_COLOR_HEADER.equals(header, ignoreCase = true)) {
                offObject.hasVertexColors = true
                return
            }
            throw OffUnsupportedException("Unsupported header.")
        }
    }

    @Throws(IOException::class)
    fun readDimensions(reader: BufferedReader) {
        val segments = OffLoaderUtil.readContentLineMultiple(reader)
        if (segments.size < 3) {
            throw OffCorruptException("Incomplete count indicators.")
        }
        vertexCount = OffLoaderUtil.parseIntSafe(segments[0])
        if (vertexCount > loader.maxVertexCount) {
            throw OffSizeException("Number of vertices exceeds max range.")
        }
        faceCount = OffLoaderUtil.parseIntSafe(segments[1])
        if (faceCount > loader.maxFaceCount) {
            throw OffSizeException("Number of faces exceeds max range.")
        }
    }

    @Throws(IOException::class)
    fun readVertices(reader: BufferedReader) {
        for (i in 0 until vertexCount) {
            offObject.vertices.add(readVertex(reader))
        }
    }

    @Throws(IOException::class)
    private fun readVertex(reader: BufferedReader): OffVertex {
        val segments = OffLoaderUtil.readContentLineMultiple(reader)
        if (segments?.size < 3) {
            throw OffCorruptException("Insufficient coordinates.")
        }
        val vertex = OffVertex()
        vertex.x = OffLoaderUtil.parseFloatSafe(segments[0])
        vertex.y = OffLoaderUtil.parseFloatSafe(segments[1])
        vertex.z = OffLoaderUtil.parseFloatSafe(segments[2])
        if (offObject.hasVertexColors) {
            if (segments.size < 7) {
                throw OffCorruptException("Missing vertex color.")
            }
            vertex.r = OffLoaderUtil.parseColorSegmentSafe(segments[3])
            vertex.g = OffLoaderUtil.parseColorSegmentSafe(segments[4])
            vertex.b = OffLoaderUtil.parseColorSegmentSafe(segments[5])
            vertex.a = OffLoaderUtil.parseColorSegmentSafe(segments[6])
        }
        return vertex
    }

    @Throws(IOException::class)
    fun readFaces(reader: BufferedReader) {
        for (i in 0 until faceCount) {
            offObject.faces.add(readFace(reader))
        }
    }

    @Throws(IOException::class)
    private fun readFace(reader: BufferedReader): OffFace {
        val segments = OffLoaderUtil.readContentLineMultiple(reader)
        if (segments!!.size == 0) {
            throw OffCorruptException("Missing face information")
        }
        val vertexRefCount = OffLoaderUtil.parseIntSafe(segments[0])
        if (vertexRefCount > segments.size - 1) {
            throw OffCorruptException("Insufficient face data.")
        }
        val face = OffFace()
        for (i in 0 until vertexRefCount) {
            face.vertexReferences.add(
                Integer.valueOf(
                    OffLoaderUtil.parseIntSafe(
                        segments[i + 1]
                    )
                )
            )
        }
        if (segments.size - 1 >= vertexRefCount + 3) {
            face.hasColor = true
            face.r = OffLoaderUtil.parseColorSegmentSafe(segments[vertexRefCount + 1])
            face.g = OffLoaderUtil.parseColorSegmentSafe(segments[vertexRefCount + 2])
            face.b = OffLoaderUtil.parseColorSegmentSafe(segments[vertexRefCount + 3])
            if (segments.size - 1 >= vertexRefCount + 4) {
                face.a = OffLoaderUtil.parseColorSegmentSafe(segments[vertexRefCount + 4])
            } else {
                face.a = 1.0f
            }
        }
        return face
    }

    companion object {
        private const val OFF_BINARY_HEADER_SUFFIX = "OFF BINARY"
        private const val OFF_COLOR_HEADER = "COFF"
        private const val OFF_HEADER = "OFF"
    }
}