package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class TblLAYER(lastElem: DxfChain, isRead: Boolean): TblCommon() {

	//	 Layer name
	//	2	
	var LayerName: String = ""
		private set

	//	 Standard flags (bit-coded values):,1 = Layer is frozen otherwise layer is thawed,2 = Layer is frozen by default in new viewports,4 = Layer is locked,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and need not be set by programs that write DXF files)
	//	70	
	var StandardFlags: Int? = null

	//	 Color number (if negative, layer is off)
	//	62	
	var ColorNumber: Int? = null

	//	 Linetype name
	//	6	
	var LinetypeName: String? = null

	//	 Plotting flag. If set to 0, do not plot this layer
	//	290	
	var PlottingFlag: Boolean? = null

	//	 Lineweight enum value
	//	370	
	var LineweightEnum: Int? = null

	//	 Hard-pointer ID/handle of PlotStyleName object
	//	390	
	var HardpointerIDhandle: String? = null

	//	 Hard-pointer ID/handle to Material object,
	//	347	
	var HardpointerIDhandle1: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
		if (cod == "0") return
		if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				LayerName = getbfr.stringValue()
			}
			"70" -> {
				StandardFlags = getbfr.intValue()
			}
			"62" -> {
				ColorNumber = getbfr.intValue()
			}
			"6" -> {
				LinetypeName = getbfr.stringValue()
			}
			"290" -> {
				PlottingFlag = getbfr.booleanValue()
			}
			"370" -> {
				LineweightEnum = getbfr.intValue()
			}
			"390" -> {
				HardpointerIDhandle = getbfr.stringValue()
			}
			"347" -> {
				HardpointerIDhandle1 = getbfr.stringValue()
			}else -> {
				cod = getbfr.get()
			}
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
    //    return sizeMMParent?.findMinMax( begpnt, endpnt, xtruDir )
    //            ?: SizeMinMax.findMinMax( begpnt, endpnt, xtruDir )
                  return sizeMMParent
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
        return maxRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
        // collectionPoint
   }

 	override fun xdef(): Int {
	
	 return 0
	
}

//    init {
//        last(lastElem)
//        if (isRead) {
//            read(lastElem.dxfContext)
//        }
//    }
	 companion object {


	//	 Subclass marker (AcDbLayerTableRecord)
	//	100	
	const val AcDbLayerTableRecord: String = "AcDbLayerTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( LayerName != null) sb.append( "\n 2\n"+LayerName )
		if( StandardFlags != null) sb.append( "\n 70\n"+StandardFlags )
		if( ColorNumber != null) sb.append( "\n 62\n"+ColorNumber )
		if( LinetypeName != null) sb.append( "\n 6\n"+LinetypeName )
		if( PlottingFlag != null) sb.append( "\n 290\n"+PlottingFlag )
		if( LineweightEnum != null) sb.append( "\n 370\n"+LineweightEnum )
		if( HardpointerIDhandle != null) sb.append( "\n 390\n"+HardpointerIDhandle )
		if( HardpointerIDhandle1 != null) sb.append( "\n 347\n"+HardpointerIDhandle1 )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nLAYER")
			sbX.append( sb)
		}

		 return sbX
	}


	init {
		last(lastElem)
		if (isRead) read(lastElem.dxfContext)
	}


	constructor(lastElem: DxfChain, LayerName: String ): this(lastElem, false){
		this.LayerName = LayerName
	}

	constructor(lastElem: DxfChain, LayerName: String, ColorNumber: Int?) : this(lastElem, LayerName){
		this.LayerName = LayerName
		this.ColorNumber = ColorNumber
	}

}