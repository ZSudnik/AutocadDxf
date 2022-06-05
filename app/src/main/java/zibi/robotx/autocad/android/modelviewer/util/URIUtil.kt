package zibi.robotx.autocad.android.modelviewer.util

import java.util.*

object URIUtil {

    const val SCHEMA_ASSET = "asset"
    const val SCHEMA_FILE = "file"
    const val SCHEMA_CONTENT = "content"

    fun toRelativePath(path: String?): String {
        return if (path!!.startsWith("/")) {
            path.substring(1)
        } else path
    }

    fun getExtension(path: String): String {
        val dotPosition = path.lastIndexOf(46.toChar())
        return if (dotPosition == -1) {
            ""
        } else path.substring(dotPosition + 1).lowercase(Locale.US)
    }
}