package zibi.robotx.autocad.common.io

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

object StreamUtil {
    private const val DEFAULT_BUFFER_SIZE = 1024
    val DEFAULT_CHARSET = Charset.forName("UTF-8")
    fun fromBitsToBytes(count: Int): Int {
        return count / 8
    }

    @Throws(IOException::class)
    fun transferData(inputStream: InputStream, out: OutputStream) {
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        while (true) {
            val count = inputStream.read(buffer)
            if (count >= 0) {
                out.write(buffer, 0, count)
            } else {
                return
            }
        }
    }

    @Throws(IOException::class)
    fun readFully(inputStream: InputStream): ByteArray {
        val out = ByteArrayOutputStream()
        transferData(inputStream, out)
        return out.toByteArray()
    }

    @Throws(IOException::class)
    fun readFully(inputStream: InputStream, limit: Int): ByteArray {
        if (inputStream.available() > limit) {
            throw IOException("InputStream is too large.")
        }
        val out = ByteArrayOutputStream()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        var position = 0
        while (true) {
            val count = inputStream.read(buffer)
            if (count <= 0) {
                return out.toByteArray()
            }
            position += count
            if (position > limit) {
                throw IOException("InputStream is too large.")
            }
            out.write(buffer, 0, count)
        }
    }

    @Throws(IOException::class)
    fun readPartially(`in`: InputStream?, count: Int): ByteArray {
        val buffer = ByteArray(count)
        readBuffer(`in`, buffer)
        return buffer
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun readBuffer(
        `in`: InputStream?,
        buffer: ByteArray,
        offset: Int = 0,
        count: Int = buffer.size
    ) {
        var offset = offset
        var count = count
        while (count > 0) {
            val bytesRead = `in`!!.read(buffer, offset, count)
            if (bytesRead == -1) {
                throw IOException("No more data available.")
            }
            count -= bytesRead
            offset += bytesRead
        }
    }
}