package zibi.robotx.autocad.data.obj

import zibi.robotx.autocad.data.obj.error.ObjException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class ObjMaterialLoader : IObjMaterialLoader {
    @Throws(
        ObjException::class,
        IOException::class
    )  // zibi.robot.autocad.data.obj.IObjMaterialLoader
    override fun load(inputStream: InputStream): ObjMaterialLibrary? {
        return try {
            ObjMaterialLoaderContext().load(BufferedReader(InputStreamReader(inputStream)))
        } finally {
            inputStream.close()
        }
    }
}