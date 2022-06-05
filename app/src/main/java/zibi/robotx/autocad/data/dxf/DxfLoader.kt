package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.error.DxfException
import zibi.robotx.autocad.data.dxf.protocol.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class DxfLoader : IDxfLoader {

    override var maxVertexCount = 165536
    override var maxFaceCount = 165536
    val dxfObject = DxfObject()
    var code:String = ""


    @Throws(DxfException::class, IOException::class)
    override fun load(inputStream: InputStream?): DxfObject? {
        return try {
            load(BufferedReader(InputStreamReader(inputStream)))
        } finally {
            inputStream!!.close()
        }
    }

    @Throws(IOException::class)
    private fun load(reader: BufferedReader): DxfObject {
        dxfObject.dxfContext = DxfLoaderContext(this, reader)
        val secHeader: SecHeader = SecHeader(dxfObject)
        val secClasses: SecClasses = SecClasses(dxfObject)
        val secTables: SecTables = SecTables( dxfObject)
        val secBlocks: SecBlocks = SecBlocks( dxfObject)
        val secEntities: SecEntities = SecEntities(dxfObject)
//        val secObject: SecObject = SecObject(dxfObject) TODO

        code = dxfObject.dxfContext.get()
        loop@ while (true) {
            if ( code  == "0" ) {
                when( dxfObject.dxfContext.get().also { code = it }){
                    "EOF" -> break
                    "SECTION" -> {
                        code = dxfObject.dxfContext.get()
                        when (dxfObject.dxfContext.get().also { code = it }) {
                            "HEADER" -> {
                                secHeader.read( dxfObject.dxfContext)
                                continue@loop
                            }
                            "TABLES" -> {
                                secTables.read(dxfObject.dxfContext)
                                continue@loop
                            }
                            "CLASSES" -> {
                                secClasses.read(dxfObject.dxfContext)
                                continue@loop
                            }
                            "BLOCKS" -> {
                                secBlocks.read( dxfObject.dxfContext)
                                continue@loop
                            }
                            "ENTITIES" -> {
                                secEntities.read(dxfObject.dxfContext)
                                continue@loop
                            }
                        }
                    }
                }
            }
            code = dxfObject.dxfContext.get()
        }

        val sb = StringBuilder()
        secHeader.write(sb)
        println( sb.toString() )
        return dxfObject
    }

}