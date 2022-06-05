package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.protocol.*
import zibi.robotx.autocad.data.dxf.protocol.table.TblLAYER
import zibi.robotx.autocad.data.dxf.protocol.table.TblLTYPE
import zibi.robotx.autocad.data.dxf.protocol.table.TblSTYLE
import java.io.BufferedReader


class DxfLoaderContext(private val loader: IDxfLoader,private val reader: BufferedReader) {


    var idChain: Int = 0
        get(){
            field++
            return field
        }
        private set

    private var code: String = ""

    var secTables: SecTables? = null
    var secBlocks: SecBlocks? = null


    fun get(): String {
        code = DxfLoaderUtil.readContentLineSingle(reader)
        return code
    }

    fun lastCode(): String {
        return code
    }

    fun codEquals( checkVal: String): Boolean{
        return code == checkVal
    }

    fun doubleValue(): Double{
        return DxfLoaderUtil.parseDoubleSafe(DxfLoaderUtil.readContentLineSingle(reader) )
    }

    fun intValue(): Int{
        return DxfLoaderUtil.parseIntSafe(DxfLoaderUtil.readContentLineSingle(reader) )
    }

    fun longValue(): Long{
        return DxfLoaderUtil.parseLongSafe(DxfLoaderUtil.readContentLineSingle(reader) )
    }

    fun intHexValue(): Int{
        return DxfLoaderUtil.parseIntHexSafe(DxfLoaderUtil.readContentLineSingle(reader) )
    }

    fun floatValue(): Float{
        return DxfLoaderUtil.parseFloatSafe(DxfLoaderUtil.readContentLineSingle(reader) )
    }
    fun booleanValue(): Boolean{
        return DxfLoaderUtil.parseBooleanSafe(DxfLoaderUtil.readContentLineSingle(reader) )
    }

    fun stringValue(): String{
        return DxfLoaderUtil.readContentLineSingle(reader)
    }

    fun styleValue( parentElem: DxfChain): TblSTYLE {
        val name = DxfLoaderUtil.readContentLineSingle(reader)
        var found =  secTables?.mapTblSTYLE?.get( name)
         if( found != null) return found
         else {
             found = TblSTYLE(parentElem, name)
             secTables?.mapTblSTYLE?.set(name, found)
             return found
        }
    }

    fun ltypeValue(parentElem: DxfChain): TblLTYPE {
        val name = DxfLoaderUtil.readContentLineSingle(reader)
        var found =  secTables?.mapTblLTYPE?.get( name)
        if( found != null) return found
        else {
            found = TblLTYPE(parentElem, name)
            secTables?.mapTblLTYPE?.set(name, found)
            return found
        }
    }

    fun layerValue(parentElem: DxfChain): TblLAYER {
        val name = DxfLoaderUtil.readContentLineSingle(reader)
        var found =  secTables?.mapTblLAYER?.get( name)
        if( found != null) return found
        else {
            found = TblLAYER(parentElem, name, 140)
            secTables?.mapTblLAYER?.set(name, found)
            return found
        }
    }

    var emptyLayerName: String? = null
    fun layerValueEmpty(parentElem: DxfChain): TblLAYER {
        return if( secTables?.mapTblLAYER?.get(emptyLayerName) == null ){
            var nameInt: Int = 0
            while( secTables?.mapTblLAYER?.get( nameInt.toString()) != null){
                nameInt++
            }
            emptyLayerName = nameInt.toString()
            val found = TblLAYER(parentElem, emptyLayerName!!, 166)
            secTables?.mapTblLAYER?.set(emptyLayerName!!, found)
            found
        }else {
            secTables?.mapTblLAYER?.get(emptyLayerName)!!
        }

    }

    fun blockValue(parentElem: DxfChain): BlkBlock {
        val name = DxfLoaderUtil.readContentLineSingle(reader)
        var found =  secBlocks?.mapBlocks?.get( name)
        return if( found != null) found
        else {
            found = BlkBlock(parentElem, name)
            secBlocks?.mapBlocks?.set(name, found)
            found
        }
    }

    companion object {
        var licznik = 0
    }
}