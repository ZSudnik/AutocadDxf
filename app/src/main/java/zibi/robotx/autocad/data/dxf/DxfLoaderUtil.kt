package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.error.DxfCorruptException
import java.io.BufferedReader
import java.io.IOException

internal object DxfLoaderUtil {

    private const val COMMENT_SYMBOL = '#'
    private val WHITE_SPACE_PATTERN = "[\\s]+".toRegex() //"[\\s]+"

    @Throws(DxfCorruptException::class)
    fun parseColorSegmentSafe(text: String): Float {
        return try {
            text.toInt().toFloat() / 255.0f
        } catch (e: NumberFormatException) {
            parseFloatSafe(text)
        }
    }

    @Throws(DxfCorruptException::class)
    fun parseIntSafe(text: String): Int {
        return try {
            if(text == "EOF") 10 else
                if(text == "") 0 else
                text.substringBefore(".") .toInt()
//                text .toInt()
        } catch (ex: NumberFormatException) {
            throw DxfCorruptException("Expected an integer but read differently.", ex)
        }
    }

    @Throws(DxfCorruptException::class)
    fun parseLongSafe(text: String): Long {
        return try {
            if(text == "EOF") 10L else
                if(text == "") 0L else
                text.substringBefore(".").toLong()
        } catch (ex: NumberFormatException) {
            throw DxfCorruptException("Expected an long but read differently.", ex)
        }
    }

    @Throws(DxfCorruptException::class)
    fun parseIntHexSafe(text: String): Int {
        return try {
            if(text == "EOF") 10 else
                if(text == "") 0 else
                text.toInt( 16 )
        } catch (ex: NumberFormatException) {
            throw DxfCorruptException("Expected an integer hex but read differently.", ex)
        }
    }

    @Throws(DxfCorruptException::class)
    fun parseFloatSafe(text: String): Float {
        return try {
            text.toFloat()
        } catch (ex: NumberFormatException) {
            throw DxfCorruptException("Expected a float but read differently.", ex)
        }
    }

    @Throws(DxfCorruptException::class)
    fun parseBooleanSafe(text: String): Boolean {
        return try {
            return if( text.equals("1")) true
            else false
        } catch (ex: NumberFormatException) {
            throw DxfCorruptException("Expected a boolean but read differently.", ex)
        }
    }

    @Throws(DxfCorruptException::class)
    fun parseDoubleSafe(text: String): Double {
        return try {
            text.toDouble()
        } catch (ex: NumberFormatException) {
            throw DxfCorruptException("Expected a double but read differently.", ex)
        }
    }

    @Throws(IOException::class)
    fun readContentLineMultiple(reader: BufferedReader): List<String> {
        return readContentLineSingle(reader).split(WHITE_SPACE_PATTERN)
    }

    @Throws(IOException::class)
    fun readContentLineSingle(reader: BufferedReader): String {
        var line = readCleanedUpLine(reader)
//        while (line.isEmpty()) {
//            line = readCleanedUpLine(reader)
//        }
        return line
    }

    @Throws(IOException::class)
    private fun readCleanedUpLine(reader: BufferedReader): String {
        val str = reader.readLine()
//        DxfLoaderContext.licznik++
//        System.out.println("${DxfLoaderContext.licznik} Line: $str")
//        if( str.equals("EOF",true))
//            DxfLoaderContext.licznik++
//        if( str == null )
//            DxfLoaderContext.licznik++
        return str?.replace(" ", "") ?:
        throw DxfCorruptException("The file has ended unexpectedly.")
    }

    private fun removeCommentFromLine(line: String): String {
        val commentIndex = line.indexOf(35.toChar())
        return if (commentIndex != -1) {
            line.substring(0, commentIndex).trim { it <= ' ' }
        } else line
    }
    private fun removeAllSpaceLine(line: String): String {
        return line.replace(" ","") // .indexOf(35.toChar())
//        return if (commentIndex != -1) {
//            line.substring(0, commentIndex).trim { it <= ' ' }
//        } else line
    }
}