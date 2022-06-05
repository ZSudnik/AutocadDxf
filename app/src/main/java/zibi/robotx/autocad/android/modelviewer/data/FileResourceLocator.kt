package zibi.robotx.autocad.android.modelviewer.data

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

class FileResourceLocator(path: String) : IResourceLocator {

    private val file: File = File(path)

    @Throws(IOException::class)
    override fun open(): InputStream {
        return FileInputStream(file)
    }

    @Throws(IOException::class)
    override fun open(child: String?): InputStream {
        val directoryFile = file.parentFile
            ?: throw IOException("Could not open child file due to missing directory.")
        var childFile = File(directoryFile, getCorrectedPath(child))
        if (!childFile.exists()) {
            childFile = File(directoryFile, childFile.name)
        }
        return FileInputStream(childFile)
    }

    private fun getCorrectedPath(path: String?): String {
        return path!!.replace('\\', File.separatorChar).replace('/', File.separatorChar)
    }

}