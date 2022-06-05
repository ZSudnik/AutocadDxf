package zibi.robotx.autocad.data.obj

import zibi.robotx.autocad.data.obj.error.ObjException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ObjModelLoader : IObjModelLoader {

    override var maxVertexCount = 524288//65536
        set(count) {
            assertCountNotNegative(count)
            field = count
        }

    override var maxFaceCount = 524288//65536
        set(count) {
            assertCountNotNegative(count)
            field = count
        }

    @Throws(ObjException::class, IOException::class)
    override fun load(inputStream: InputStream?): ObjModel? {
        return try {
            ObjModelLoaderContext(this, BufferedReader(InputStreamReader(inputStream)) ).load()
//        }catch (eb: ObjException){
//            eb.printStackTrace()
//            null
//        }catch (e: IOException){
//            e.printStackTrace()
//            null
        } finally {
            inputStream!!.close()
        }
    }

    companion object {
        private fun assertCountNotNegative(count: Int) {
            require(count >= 0) { "Count cannot be negative." }
        }
    }
}