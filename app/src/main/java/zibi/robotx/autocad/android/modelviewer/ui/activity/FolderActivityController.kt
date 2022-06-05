package zibi.robotx.autocad.android.modelviewer.ui.activity

import android.content.ContextWrapper
import zibi.robotx.arplan.R
import android.os.Bundle
import zibi.robotx.autocad.android.modelviewer.file.StorageEntity
import zibi.robotx.autocad.android.modelviewer.file.StorageFile
import zibi.robotx.autocad.android.modelviewer.file.StorageFolder
import android.content.Intent
import android.os.Environment
import android.view.View
//import zibi.robotx.autocad.android.modelviewer.file.Storage.getRootFolder
import zibi.robotx.autocad.android.modelviewer.file.StorageException

class FolderActivityController(private val activity: FolderActivity) {

    private var currentDir: StorageFolder? = null

    fun onCreate() {
        try {
            openFolder(StorageFolder.fromWorkSpace( activity ))
        } catch (e: StorageException) {
            handleStorageError()
        }
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        var path: String? = null
//        if (!isInErrorState() && savedInstanceState.getString(STATE_FOLDER_KEY)
//                .also { path = it } != null) {
//            try {
//                openFolder(StorageFolder.fromPath(path))
//            } catch (e: StorageException) {
//                handleStorageError()
//            }
//        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        if (currentDir != null && !isInErrorState()) {
//            outState.putSerializable(STATE_FOLDER_KEY, currentDir!!.path)
        }
    }

//    fun onHomeClicked() {
//        val intent = Intent(activity, HomeActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
//        activity.startActivity(intent)
//    }

//    fun onBackClicked() {
//        if (isActionUpEnabled()) {
//            openParentFolder()
//        } else {
//            activity.finish()
//        }
//    }

//    fun onUpClicked() {
//        if (isActionUpEnabled()) {
//            openParentFolder()
//        }
//    }

    fun onFileClicked(entity: StorageEntity?) {
//        if (entity is StorageFolder) {
//            openFolder(entity as StorageFolder?)
//        }
        if (entity is StorageFile) {
            openFile(entity)
        }
    }

//    private fun openParentFolder() {
//        try {
//            openFolder(currentDir!!.parent)
//        } catch (e: StorageException) {
//            handleStorageError()
//        }
//    }

    private fun openFolder(folder: StorageFolder?) {
        try {
//            currentDir = folder
            activity.showFiles(folder!!.children)
//            if (folder.isRoot) {
//                activity.setTitle(R.string.storage_folder)
//            } else {
//                activity.title = folder.name
//            }
//            activity.invalidateOptionsMenu()
        } catch (e: StorageException) {
            handleStorageError()
        }
    }

    private fun openFile(storage: StorageFile) {
        val intent = Intent(activity, DisplayActivity::class.java)
        intent.data = storage.uri
        activity.startActivity(intent)
    }

    private fun isInErrorState(): Boolean {
        return currentDir == null
    }

//    fun getCurrentDir(): String {
//        val pathRoot = getRootFolder().absolutePath
//        return currentDir?.path?.replace(pathRoot, ".") ?: "./"
//    }

    fun isActionUpEnabled(): Boolean {
        return currentDir != null && !currentDir!!.isRoot
    }

    fun makeActionUpEnabled(): Int {
        return if( currentDir != null && !currentDir!!.isRoot)
            View.VISIBLE
        else View.GONE
    }
    private fun handleStorageError() {
        activity.showStorageError()
        activity.invalidateOptionsMenu()
    }

    companion object {
        private const val STATE_FOLDER_KEY = "folder"
    }
}