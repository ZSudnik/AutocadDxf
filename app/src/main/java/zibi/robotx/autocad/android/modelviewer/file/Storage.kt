package zibi.robotx.autocad.android.modelviewer.file

import kotlin.Throws
import android.os.Environment
import java.io.File

object Storage {

//    private fun isExternalStorageAvailable(): Boolean {
//        val state = Environment.getExternalStorageState()
//        return "mounted" == state || "mounted_ro" == state
//    }

//    @Throws(StorageException::class)
//    fun assureAvailable() {
//        if (!isExternalStorageAvailable()) {
//            throw StorageException()
//        }
//    }

//    @Throws(StorageException::class)
//    fun getRootFolder(): File {
//        val file =  File(Environment.
//        getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
//        if (file.isDirectory) {
//            return file
//        }
//        throw StorageException("File is not a directory")
//    }

//    @Throws(StorageException::class)
//    fun isRootFolder(folder: File): Boolean {
//        return folder.parent == null || folder == getRootFolder()
//    }
}