package zibi.robotx.autocad.android.modelviewer.data

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContentResolverCompat
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import android.content.ContentUris
import android.database.Cursor
import android.provider.BaseColumns
import android.provider.MediaStore.DownloadColumns.REFERER_URI
import android.provider.MediaStore.Files.getContentUri


class ContentResourceLocator(private val context: Context,val path: Uri) : IResourceLocator {

    var parcialUri: Uri = Uri.parse("content://media/external/audio/media")

    @Throws(IOException::class)
    override fun open(): InputStream {
            val cr = context.contentResolver
            return cr.openInputStream(path)!!
    }

    @Throws(IOException::class)
    override fun open(child: String?): InputStream {
        val cr = context.contentResolver
//        val uri = Uri.parse("content://com.android.providers.downloads.documents/document/"+child)
        //MediaStore.getMediaScannerUri() //getContentUri("external")
//        val projection = arrayOf( MediaStore.MediaColumns.RELATIVE_PATH)
//        val selection = ( MediaStore.MediaColumns.DISPLAY_NAME + "=" + child)
//        val metaCursor: Cursor? = cr.query(uri, projection, selection, null, null)
//        var uriContent: String? = null
//        if (metaCursor?.moveToFirst() == true) {
//            uriContent=  metaCursor.getString(0)
//        }
//        return cr.openInputStream(Uri.parse( uriContent ) ) !!
        val tt = Uri.fromParts("content", "media/external/file", child)
        return cr.openInputStream( tt ) !!
    }

    private fun getCorrectedPath(path: String?): String {
        return path!!.replace('\\', File.separatorChar).replace('/', File.separatorChar)
    }

}