package zibi.robotx.autocad.data.obj

import zibi.robotx.autocad.data.obj.error.ObjException
import java.io.IOException
import java.io.InputStream

interface IObjMaterialLoader {
    @Throws(ObjException::class, IOException::class)
    fun load(inputStream: InputStream): ObjMaterialLibrary?
}