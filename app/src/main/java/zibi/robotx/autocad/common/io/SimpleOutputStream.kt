package zibi.robotx.autocad.common.io

import java.io.DataOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.charset.Charset


class SimpleOutputStream(out: OutputStream?) : OutputStream() {
    private var out: DataOutputStream? = null
    private var isBigEndian = true
    private var position = 0
    fun setBigEndian(isBigEndian: Boolean) {
        this.isBigEndian = isBigEndian
    }

    fun isBigEndian(): Boolean {
        return isBigEndian
    }

    fun getPosition(): Int {
        return position
    }

    @Throws(IOException::class)
    fun writeByte(value: Byte) {
        out!!.writeByte(value.toInt())
        position += StreamUtil.fromBitsToBytes(8)
    }

    @Throws(IOException::class)
    fun writeBoolean(value: Boolean) {
        out!!.writeBoolean(value)
        position += StreamUtil.fromBitsToBytes(8)
    }

    @Throws(IOException::class)
    fun writeChar(value: Char) {
        var value = value
        val dataOutputStream = out
        if (!isBigEndian) {
            value = Character.reverseBytes(value)
        }
        dataOutputStream!!.writeChar(value.toInt())
        position += StreamUtil.fromBitsToBytes(16)
    }

    @Throws(IOException::class)
    fun writeShort(value: Short) {
        var value = value
        val dataOutputStream = out
        if (!isBigEndian) {
            value = java.lang.Short.reverseBytes(value)
        }
        dataOutputStream!!.writeShort(value.toInt())
        position += StreamUtil.fromBitsToBytes(16)
    }

    @Throws(IOException::class)
    fun writeInt(value: Int) {
        var value = value
        val dataOutputStream = out
        if (!isBigEndian) {
            value = Integer.reverseBytes(value)
        }
        dataOutputStream!!.writeInt(value)
        position += StreamUtil.fromBitsToBytes(32)
    }

    @Throws(IOException::class)
    fun writeLong(value: Long) {
        var value = value
        val dataOutputStream = out
        if (!isBigEndian) {
            value = java.lang.Long.reverseBytes(value)
        }
        dataOutputStream!!.writeLong(value)
        position += StreamUtil.fromBitsToBytes(64)
    }

    @Throws(IOException::class)
    fun writeFloat(value: Float) {
        var floatBits = java.lang.Float.floatToIntBits(value)
        val dataOutputStream = out
        if (!isBigEndian) {
            floatBits = Integer.reverseBytes(floatBits)
        }
        dataOutputStream!!.writeInt(floatBits)
        position += StreamUtil.fromBitsToBytes(32)
    }

    @Throws(IOException::class)
    fun writeDouble(value: Double) {
        var doubleBits = java.lang.Double.doubleToLongBits(value)
        val dataOutputStream = out
        if (!isBigEndian) {
            doubleBits = java.lang.Long.reverseBytes(doubleBits)
        }
        dataOutputStream!!.writeLong(doubleBits)
        position += StreamUtil.fromBitsToBytes(64)
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun writeString(value: String, charset: Charset? = StreamUtil.DEFAULT_CHARSET) {
        val characters = value.toByteArray(charset!!)
        writeInt(characters.size)
        write(characters)
    }

    @Throws(IOException::class)  // java.io.OutputStream
    override fun write(b: Int) {
        out!!.write(b)
        position += StreamUtil.fromBitsToBytes(8)
    }

    @Throws(IOException::class)  // java.io.OutputStream
    override fun write(b: ByteArray, off: Int, len: Int) {
        out!!.write(b, off, len)
        position += len
    }

    @Throws(IOException::class)  // java.io.OutputStream, java.io.Flushable
    override fun flush() {
        out!!.flush()
    }

    @Throws(IOException::class)  // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    override fun close() {
        out!!.close()
    }

    init {
        if (out is DataOutputStream) {
            this.out = out
        } else {
            this.out = DataOutputStream(out)
        }
    }
}