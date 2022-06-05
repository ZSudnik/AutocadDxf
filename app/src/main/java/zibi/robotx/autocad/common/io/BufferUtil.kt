package zibi.robotx.autocad.common.io

import java.nio.*


object BufferUtil {
    fun createByteBuffer(capacity: Int): ByteBuffer {
        val result = ByteBuffer.allocateDirect(capacity)
        result.order(ByteOrder.nativeOrder())
        return result
    }

    fun createByteBuffer(data: ByteArray): ByteBuffer {
        val result = createByteBuffer(data.size)
        result.put(data)
        result.rewind()
        return result
    }

    fun createShortBuffer(capacity: Int): ShortBuffer {
        return createByteBuffer(capacity * 2).asShortBuffer()
    }

    fun createShortBuffer(data: ShortArray): ShortBuffer {
        val result = createShortBuffer(data.size)
        result.put(data)
        result.rewind()
        return result
    }

    fun createIntBuffer(capacity: Int): IntBuffer {
        return createByteBuffer(capacity * 4).asIntBuffer()
    }

    fun createIntBuffer(data: IntArray): IntBuffer {
        val result = createIntBuffer(data.size)
        result.put(data)
        result.rewind()
        return result
    }

    fun createFloatBuffer(capacity: Int): FloatBuffer {
        return createByteBuffer(capacity * 4).asFloatBuffer()
    }

    fun createFloatBuffer(data: FloatArray): FloatBuffer {
        val result = createFloatBuffer(data.size)
        result.put(data)
        result.rewind()
        return result
    }
}