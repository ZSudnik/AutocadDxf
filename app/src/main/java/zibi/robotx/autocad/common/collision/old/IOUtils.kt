package zibi.robotx.autocad.common.collision

import java.io.*
import java.nio.*
import kotlin.Throws

//object IOUtils {

    @Throws(IOException::class)
    fun read(file: File?): ByteArray {
        val bos = ByteArrayOutputStream()
        val fis = FileInputStream(file)
        val data = read(fis)
        fis.close()
        return data
    }

    /**
     * Read fully the input stream and return the bytes.
     *
     * @param `is` input stream
     * @return the bytes
     * @throws IOException if there is an error reading from the stream
     */
    @Throws(IOException::class)
    fun read(inputStreams: InputStream): ByteArray {
        val isData = ByteArray(512)
        val buffer = ByteArrayOutputStream()
        var nRead: Int
        while (inputStreams.read(isData, 0, isData.size).also { nRead = it } != -1) {
            buffer.write(isData, 0, nRead)
        }
        return buffer.toByteArray()
    }

    fun createFloatBuffer(floats: Int): FloatBuffer {
        return createNativeByteBuffer(floats * 4).asFloatBuffer()
    }

    fun createFloatBuffer(vectorArray: List<FloatArray?>, stride: Int): FloatBuffer {
        val floatBuffer = createFloatBuffer(vectorArray.size * stride)
        for (i in vectorArray.indices) {
            floatBuffer.put(vectorArray[i])
        }
        return floatBuffer
    }

    fun createIntBuffer(integers: Int): IntBuffer {
        return createNativeByteBuffer(integers * 4).asIntBuffer()
    }

    fun createShortBuffer(shorts: Int): ShortBuffer {
        return createNativeByteBuffer(shorts * 2).asShortBuffer()
    }

    fun createNativeByteBuffer(length: Int): ByteBuffer {
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect(length)
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder())
        return bb
    }
//}