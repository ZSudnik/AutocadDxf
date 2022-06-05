package zibi.robotx.autocad.data.obj

import zibi.robotx.autocad.data.obj.error.ObjCorruptException

class ObjParseCommand(line: String) {

    private val line: String
    private val segments: Array<String>
    val name: Any
        get() = if (segments.isEmpty()) {
            ""
        } else segments[0]
    val remainder: String
        get() {
            val localSegments = line.split(WHITE_SPACE_PATTERN)
            return if (localSegments.size <= 1) {
                ""
            } else localSegments[1]
        }
    val isEmpty: Boolean
        get() = segments.size == 0
    val parameterCount: Int
        get() = Math.max(0, segments.size - 1)

    fun getParameter(index: Int): String {
        return segments[index + 1]
    }

    @Throws(ObjCorruptException::class)
    fun getFloat(index: Int): Float {
        return try {
            getParameter(index).toFloat()
        } catch (e: NumberFormatException) {
            throw ObjCorruptException("Could not parse float value.")
        }
    }

    companion object {
        private val WHITE_SPACE_PATTERN = "[\\s]+".toRegex() //"[\\s]+"
    }

    init {
        this.line = line.trim { it <= ' ' }
        segments = this.line.split(WHITE_SPACE_PATTERN).toTypedArray()
    }
}