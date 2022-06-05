package zibi.robotx.autocad.android.modelviewer.file

import java.io.File
import java.io.FileFilter


class ModelFileFilter : FileFilter {

    override fun accept(file: File): Boolean {
        if (file.isHidden) {
            return false
        }
        if (file.isDirectory) {
            return true
        }
        val extension = getFileExtension(file)
        return extension.equals("obj", ignoreCase = true) ||
                extension.equals("off", ignoreCase = true) ||
                extension.equals("dxf", ignoreCase = true)
    }

    private fun getFileExtension(file: File): String {
        val name = file.name
        val separatorIndex = name.indexOf(46.toChar())
        return if (separatorIndex == -1) {
            ""
        } else name.substring(separatorIndex + 1)
    }
}