package zibi.robotx.autocad.common.io

import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


class SimpleInputStream(`in`: InputStream?) : InputStream() {
    /* renamed from: in */
    private var f6in: DataInputStream? = null
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
    fun readByte(): Byte {
        val result = f6in!!.readByte()
        position += StreamUtil.fromBitsToBytes(8)
        return result
    }

    @Throws(IOException::class)
    fun readBoolean(): Boolean {
        val result = f6in!!.readBoolean()
        position += StreamUtil.fromBitsToBytes(8)
        return result
    }

    @Throws(IOException::class)
    fun readChar(): Char {
        val result = f6in!!.readChar()
        position += StreamUtil.fromBitsToBytes(16)
        return if (isBigEndian) result else Character.reverseBytes(result)
    }

    @Throws(IOException::class)
    fun readShort(): Short {
        val result = f6in!!.readShort()
        position += StreamUtil.fromBitsToBytes(16)
        return if (isBigEndian) result else java.lang.Short.reverseBytes(result)
    }

    @Throws(IOException::class)
    fun readInt(): Int {
        val result = f6in!!.readInt()
        position += StreamUtil.fromBitsToBytes(32)
        return if (isBigEndian) result else Integer.reverseBytes(result)
    }

    @Throws(IOException::class)
    fun readLong(): Long {
        val value = f6in!!.readLong()
        position += StreamUtil.fromBitsToBytes(64)
        return if (isBigEndian) value else java.lang.Long.reverseBytes(value)
    }

    @Throws(IOException::class)
    fun readFloat(): Float {
        var intBits = f6in!!.readInt()
        position += StreamUtil.fromBitsToBytes(32)
        if (!isBigEndian) {
            intBits = Integer.reverseBytes(intBits)
        }
        return java.lang.Float.intBitsToFloat(intBits)
    }

    @Throws(IOException::class)
    fun readDouble(): Double {
        var longBits = f6in!!.readLong()
        position += StreamUtil.fromBitsToBytes(64)
        if (!isBigEndian) {
            longBits = java.lang.Long.reverseBytes(longBits)
        }
        return java.lang.Double.longBitsToDouble(longBits)
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun readString(charset: Charset? = StreamUtil.DEFAULT_CHARSET): String {
        return String(readBuffer(readInt())!!, charset!!)
    }

    @Throws(IOException::class)
    fun readBuffer(count: Int): ByteArray? {
        return StreamUtil.readPartially(f6in, count)
    }

    @Throws(IOException::class)  // java.io.InputStream
    override fun read(): Int {
        val result = f6in!!.read()
        if (result != -1) {
            position += StreamUtil.fromBitsToBytes(8)
        }
        return result
    }

    @Throws(IOException::class)  // java.io.InputStream
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val numberRead = f6in!!.read(b, off, len)
        if (numberRead > 0) {
            position += numberRead
        }
        return numberRead
    }

    @Throws(IOException::class)  // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    override fun close() {
        f6in!!.close()
    }

    init {
        if (`in` is DataInputStream) {
            f6in = `in`
        } else {
            f6in = DataInputStream(`in`)
        }
    }
}