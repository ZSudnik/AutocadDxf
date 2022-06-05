   package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.protocol.*
import zibi.robotx.autocad.data.dxf.protocol.table.*
import zibi.robotx.autocad.data.dxf.util.*

class SecTables(lastElem: DxfChain) : DxfChain() {
    /**
     * VPORT Collection
     */
    var mapTblVPORT: MutableMap<String, TblVPORT> = mutableMapOf()
    /**
     * LTYPE Collection
     */
    var mapTblLTYPE: MutableMap<String, TblLTYPE> = mutableMapOf()
    /**
     * LAYER Collection
     */
    var mapTblLAYER: MutableMap<String, TblLAYER> = mutableMapOf()
    /**
     * STYLE Collection
     */
    var mapTblSTYLE: MutableMap<String, TblSTYLE> = mutableMapOf()
    /**
     * DIMSTYLE Collection
     */
    var mapTblDIMSTYLE: MutableMap<String,TblDIMSTYLE> = mutableMapOf()
    /**
     * UCS Collection
     */
    var mapTblUCS: MutableMap<String,TblUCS> = mutableMapOf()
    /**
     * APPID Collection
     */
    var mapTblAPPID: MutableMap<String,TblAPPID> = mutableMapOf()
    /**
     * VIEW Collection
     */
    var mapTblVIEW: MutableMap<String,TblVIEW> = mutableMapOf()
    /**
     * BLOCK_RECORD Collection
     */
    var mapTblBLOCK_RECORD: MutableMap<String,TblBLOCK_RECORD> = mutableMapOf()

                
                
    override fun read(dxfContext: DxfLoaderContext) {
        var code = dxfContext.get()
        loop@ while (true) {
            if (code == "0") {
                do{ code = dxfContext.get() }while(code == "0")
                if (code == "ENDSEC") {
                    return
                }
                if (code == "TABLE") {
                    code = dxfContext.get() //"2"
                    code = dxfContext.get()
                    when (code) {
                        "APPID" -> {
                            val locTab = TblAPPID(this, true)
                            mapTblAPPID[locTab.UsersuppliedApplicationsupplied] = locTab
                        }
                        "BLOCK_RECORD" -> {
                            val locTab = TblBLOCK_RECORD(this, true)
                            mapTblBLOCK_RECORD[locTab.BlockName] = locTab
                        }
                        "DIMSTYLE" -> {
                            val locTab = TblDIMSTYLE(this, true)
                            mapTblDIMSTYLE[locTab.DimensionStyle] = locTab
                        }
                        "LAYER" -> {
                            while(true){
                                if( code=="LAYER") {
                                    val locTab = TblLAYER(this, true)
                                    mapTblLAYER[locTab.LayerName] = locTab
                                }
                                code = dxfContext.get()
                                if (code == "ENDTAB") break
                            }
                        }
                        "LTYPE" -> {
                            val locTab = TblLTYPE(this, true)
                            mapTblLTYPE[locTab.LinetypeName] = locTab
                        }
                        "STYLE" -> {
                            val locTab = TblSTYLE(this, true)
                            mapTblSTYLE[locTab.StyleName] = locTab
                        }
                        "UCS" -> {
                            val locTab = TblUCS(this, true)
                            mapTblUCS[locTab.UCSName] = locTab
                        }
                        "VIEW" -> {
                            val locTab = TblVIEW(this, true)
                            mapTblVIEW[locTab.NameView] = locTab
                        }
                        "VPORT" -> {
                            val locTab = TblVPORT(this, true)
                            mapTblVPORT[locTab.ViewportName] = locTab
                        }
                    }
                }
                code = dxfContext.get()
            }
        }
    }

    override fun write( sbX: StringBuilder): StringBuilder {
        val sb: StringBuilder = StringBuilder()
        if( mapTblVPORT.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nVPORT")
            sb.append("\n 70\n" + mapTblVPORT.size)
            for (tblVPORT in mapTblVPORT.values) {
                tblVPORT.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblLTYPE.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nLTYPE")
            sb.append("\n 70\n" + mapTblLTYPE.size)
            for ((k,tblLTYPE) in mapTblLTYPE) {
                tblLTYPE.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblLAYER.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nLAYER")
            sb.append("\n 70\n" + mapTblLAYER.size)
            for ((k,tblLAYER) in mapTblLAYER) {
                tblLAYER.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblSTYLE.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nSTYLE")
            sb.append("\n 70\n" + mapTblSTYLE.size)
            for ((k, tblSTYLE) in mapTblSTYLE) {
                tblSTYLE.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblDIMSTYLE.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nDIMSTYLE")
            sb.append("\n 70\n" + mapTblDIMSTYLE.size)
            for ((k ,tblDIMSTYLE) in mapTblDIMSTYLE) {
                tblDIMSTYLE.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblUCS.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nUCS")
            sb.append("\n 70\n" + mapTblUCS.size)
            for ((k,tblUCS) in mapTblUCS) {
                tblUCS.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblAPPID.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nAPPID")
            sb.append("\n 70\n" + mapTblAPPID.size)
            for ((k,tblAPPID) in mapTblAPPID) {
                tblAPPID.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( mapTblVIEW.size != 0 ) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nVIEW")
            sb.append("\n 70\n" + mapTblVIEW.size)
            for ((k,tblVIEW) in mapTblVIEW) {
                tblVIEW.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if(mapTblBLOCK_RECORD.isNotEmpty()) {
            sb.append("\n 0\nTABLE")
            sb.append("\n 2\nBLOCK_RECORD")
            sb.append("\n 70\n" + mapTblBLOCK_RECORD.size)
            for ((k,tblBLOCK_RECORD) in mapTblBLOCK_RECORD) {
                tblBLOCK_RECORD.write(sb)
            }
            sb.append("\n 0\nENDTAB")
        }
        if( sb.isNotEmpty() ) {
            if( sbX.isNotEmpty()) sbX.append( "\n ")
            sbX.append( " 0\nSECTION\n 2\nTABLES")
            sbX.append( sb)
            sbX.append( "\n 0\nENDSEC")
        }

        return sbX
    }

    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {}
    override fun xdef(): Int { return 0 }


    fun isNoAPPID(UsersuppliedApplicationsupplied: String): Boolean{
        return ! mapTblAPPID.containsKey( UsersuppliedApplicationsupplied)
    }
    fun getAPPID(UsersuppliedApplicationsupplied: String?): TblAPPID {
        return when {
            UsersuppliedApplicationsupplied == null                  -> TblAPPID(this, false)
            mapTblAPPID.containsKey( UsersuppliedApplicationsupplied) -> mapTblAPPID[UsersuppliedApplicationsupplied]!!
            else                                  -> {
                val tab = TblAPPID(this, UsersuppliedApplicationsupplied)
                mapTblAPPID[UsersuppliedApplicationsupplied] = tab
                tab
            }
        }
    }

    fun isNoBLOCK_RECORD(BlockName: String): Boolean{
        return ! mapTblBLOCK_RECORD.containsKey( BlockName)
    }
    fun getBLOCK_RECORD( BlockName: String?): TblBLOCK_RECORD {
        return when {
            BlockName == null                  ->TblBLOCK_RECORD(this, false)
            mapTblBLOCK_RECORD.containsKey( BlockName) -> mapTblBLOCK_RECORD[BlockName]!!
            else                                  -> {
                val tab = TblBLOCK_RECORD(this, BlockName)
                mapTblBLOCK_RECORD[BlockName] = tab
                tab
            }
        }
    }

    fun isNoDIMSTYLE(DimensionStyle: String): Boolean{
        return ! mapTblDIMSTYLE.containsKey( DimensionStyle)
    }
    fun getDIMSTYLE( DimensionStyle: String?): TblDIMSTYLE {
        return when {
            DimensionStyle == null                  ->TblDIMSTYLE(this, false)
            mapTblDIMSTYLE.containsKey( DimensionStyle) -> mapTblDIMSTYLE[DimensionStyle]!!
            else                                  -> {
                val tab = TblDIMSTYLE(this, DimensionStyle)
                mapTblDIMSTYLE[DimensionStyle] = tab
                tab
            }
        }
    }

    fun isNoLAYER(LayerName: String): Boolean{
        return ! mapTblLAYER.containsKey( LayerName)
    }
    fun getLAYER( LayerName: String?): TblLAYER {
        return when {
            LayerName == null                  ->TblLAYER(this, false)
            mapTblLAYER.containsKey(LayerName) -> mapTblLAYER[LayerName]!!
            else                                  -> {
                val tab = TblLAYER(this, LayerName)
                mapTblLAYER[LayerName] = tab
                tab
            }
        }
    }

    fun isNoLTYPE(LinetypeName: String): Boolean{
        return ! mapTblLTYPE.containsKey( LinetypeName)
    }
    fun getLTYPE( LinetypeName: String?): TblLTYPE {
       return when {
           LinetypeName == null                  ->TblLTYPE(this, false)
           mapTblLTYPE.containsKey( LinetypeName) -> mapTblLTYPE[LinetypeName]!!
           else                                  -> {
               val tab = TblLTYPE(this, LinetypeName)
               mapTblLTYPE[LinetypeName] = tab
               tab
           }
       }
   }

    fun isNoSTYLE(StyleName: String): Boolean{
        return ! mapTblSTYLE.containsKey( StyleName)
    }
    fun getSTYLE( StyleName: String?): TblSTYLE {
       return when {
           StyleName == null                  ->TblSTYLE(this, false)
           mapTblSTYLE.containsKey( StyleName) -> mapTblSTYLE[StyleName]!!
           else                                  -> {
               val tab = TblSTYLE(this, StyleName)
               mapTblSTYLE[StyleName] = tab
               tab
           }
       }
   }

    fun isNoUCS(UCSName: String): Boolean{
        return ! mapTblUCS.containsKey( UCSName)
    }
    fun getUCS( UCSName: String?): TblUCS {
       return when {
           UCSName == null                  ->TblUCS(this, false)
           mapTblUCS.containsKey( UCSName) -> mapTblUCS[UCSName]!!
           else                                  -> {
               val tab = TblUCS(this, UCSName)
               mapTblUCS[UCSName] = tab
               tab
           }
       }
   }

    fun isNoVIEW(NameView: String): Boolean{
        return ! mapTblVIEW.containsKey( NameView)
    }
    fun getVIEW( NameView: String?): TblVIEW {
       return when {
           NameView == null                  ->TblVIEW(this, false)
           mapTblVIEW.containsKey( NameView) -> mapTblVIEW[NameView]!!
           else                                  -> {
               val tab = TblVIEW(this, NameView)
               mapTblVIEW[NameView] = tab
               tab
           }
       }
   }

    fun isNoVPORT(ViewportName: String): Boolean{
        return ! mapTblVPORT.containsKey( ViewportName)
    }
    fun getVPORT( ViewportName: String): TblVPORT {
       return when {
           mapTblVPORT.containsKey( ViewportName) -> mapTblVPORT[ViewportName]!!
           else                                  -> {
               val tab = TblVPORT(this, ViewportName)
               mapTblVPORT[ViewportName] = tab
               tab
           }
       }
   }


   init {
        last(lastElem)
        lastElem.dxfContext.secTables = this
    }



}