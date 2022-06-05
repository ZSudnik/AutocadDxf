package zibi.robotx.autocad.data.off

import zibi.robotx.autocad.data.off.error.OffCorruptException
import java.io.BufferedReader
import java.io.IOException

internal object OffLoaderUtil {
    private const val COMMENT_SYMBOL = '#'
    private val WHITE_SPACE_PATTERN = "[\\s]+".toRegex() //"[\\s]+"
    @Throws(OffCorruptException::class)
    fun parseColorSegmentSafe(text: String?): Float {
        return try {
            text!!.toInt().toFloat() / 255.0f
        } catch (e: NumberFormatException) {
            parseFloatSafe(text)
        }
    }

    @Throws(OffCorruptException::class)
    fun parseIntSafe(text: String?): Int {
        return try {
            text!!.toInt()
        } catch (ex: NumberFormatException) {
            throw OffCorruptException("Expected an integer but read differently.", ex)
        }
    }

    @Throws(OffCorruptException::class)
    fun parseFloatSafe(text: String?): Float {
        return try {
            text!!.toFloat()
        } catch (ex: NumberFormatException) {
            throw OffCorruptException("Expected a float but read differently.", ex)
        }
    }

    @Throws(IOException::class)
    fun readContentLineMultiple(reader: BufferedReader): List<String> {
        return readContentLineSingle(reader).split(WHITE_SPACE_PATTERN)
    }

    @Throws(IOException::class)
    fun readContentLineSingle(reader: BufferedReader): String {
        var line = readCleanedUpLine(reader)
        while (line.isEmpty()) {
            line = readCleanedUpLine(reader)
        }
        return line
    }

    @Throws(IOException::class)
    private fun readCleanedUpLine(reader: BufferedReader): String {
        val result = reader.readLine()
        if (result != null) {
            return removeCommentFromLine(result.trim { it <= ' ' })
        }
        throw OffCorruptException("The file has ended unexpectedly.")
    }

    private fun removeCommentFromLine(line: String): String {
        val commentIndex = line.indexOf(35.toChar())
        return if (commentIndex != -1) {
            line.substring(0, commentIndex).trim { it <= ' ' }
        } else line
    }
}