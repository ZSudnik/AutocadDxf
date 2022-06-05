package zibi.robotx.autocad.android.modelviewer.data

import android.content.Context
import java.io.File
import java.io.IOException
import java.io.InputStream


class AssetResourceLocator(private val context: Context, private val path: String?) :

    IResourceLocator {
    @Throws(IOException::class)
    override fun open(): InputStream {
        return context.assets.open(File(path).absolutePath.substring(1))
    }

    @Throws(IOException::class)
    override fun open(child: String?): InputStream {
        return context.assets.open(File(File(path).parent, child).absolutePath.substring(1))
    }
}