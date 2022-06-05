package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.protocol.*
import zibi.robotx.autocad.data.dxf.util.contextBlocks
import zibi.robotx.autocad.data.dxf.util.contextClasses
import zibi.robotx.autocad.data.dxf.util.contextHeader
import zibi.robotx.autocad.data.dxf.util.contextTables


class DxfDocument {//} : IDxfLoader {

    val dxfObject = DxfObject()
    private var isHeader: Boolean = true
    var secHeader: SecHeader = SecHeader( dxfObject )
        get() { isHeader = false; return field }
        private set
    private var isClasses: Boolean = true
    var secClasses: SecClasses = SecClasses(dxfObject)
        get() { isClasses = false; return field }
        private set
    private var isTables: Boolean = true
    var secTables: SecTables = SecTables( dxfObject )
        get() { isTables = false; return field }
        private set
    private var isBlocks: Boolean = true
    var secBlocks: SecBlocks = SecBlocks(dxfObject)
        get() { isBlocks = false; return field }
        private set
    private var isEntities: Boolean = true
    var secEntities: SecEntities = SecEntities(dxfObject)
        get() { isEntities = false; return field }
        private set
//        val secObject: SecObject = SecObject(dxfObject) TODO


    fun write() {
        val sb = StringBuilder()
        if( isHeader) contextHeader( secHeader)
        secHeader.write( sb)
        if( isClasses) contextClasses( secClasses , false)
        secClasses.write( sb )
        if( isTables) contextTables( secTables , false)
        secTables.write( sb)
        if( isBlocks) contextBlocks( secBlocks , false)
        secBlocks.write( sb)
//        if( isEntities)
        secEntities.write( sb)
    }

}



//    @Throws(DxfException::class, IOException::class)
//    override fun load(inputStream: InputStream?): DxfObject? {
//        return try {
//            load(BufferedReader(InputStreamReader(inputStream)))
//        } finally {
//            inputStream!!.close()
//        }
//    }

//    @Throws(IOException::class)
//    private fun load(reader: BufferedReader): DxfObject {
////        dxfObject.dxfContext = DxfLoaderContext(this, reader)
//        val secHeader: SecHeader = SecHeader(dxfObject)
//        val secClasses: SecClasses = SecClasses(dxfObject)
//        val secTables: SecTables = SecTables( dxfObject)
//        val secBlocks: SecBlocks = SecBlocks( dxfObject)
//        val secEntities: SecEntities = SecEntities(dxfObject)
////        val secObject: SecObject = SecObject(dxfObject) TODO
//
//        code = dxfObject.dxfContext.get()
//        loop@ while (true) {
//            if ( code  == "0" ) {
//                when( dxfObject.dxfContext.get().also { code = it }){
//                    "EOF" -> break
//                    "SECTION" -> {
//                        code = dxfObject.dxfContext.get()
//                        when (dxfObject.dxfContext.get().also { code = it }) {
//                            "HEADER" -> {
//                                secHeader.read( dxfObject.dxfContext)
//                                continue@loop
//                            }
//                            "TABLES" -> {
//                                secTables.read(dxfObject.dxfContext)
//                                continue@loop
//                            }
//                            "CLASSES" -> {
//                                secClasses.read(dxfObject.dxfContext)
//                                continue@loop
//                            }
//                            "BLOCKS" -> {
//                                secBlocks.read( dxfObject.dxfContext)
//                                continue@loop
//                            }
//                            "ENTITIES" -> {
//                                secEntities.read(dxfObject.dxfContext)
//                                continue@loop
//                            }
//                        }
//                    }
//                }
//            }
//            code = dxfObject.dxfContext.get()
//        }
//
//        return dxfObject
//    }

