package zibi.robotx.autocad.android.modelviewer.data

import java.io.IOException
import java.io.InputStream


interface IResourceLocator {
    @Throws(IOException::class)
    fun open(): InputStream

    @Throws(IOException::class)
    fun open(str: String?): InputStream
}