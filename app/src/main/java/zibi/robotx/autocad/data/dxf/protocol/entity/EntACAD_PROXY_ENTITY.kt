package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntACAD_PROXY_ENTITY(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 DXF: Proxy entity class ID (always 498)
	//	90	
	var ProxyEntity: Int? = null

	//	 DXF: Application entity's class ID. Class IDs are based on the order of the class in the CLASSES section. The first class is given the ID of 500, the next is 501, and so on
	//	91	
	var ApplicationEntitys: Int? = null

	//	 DXF: Size of graphics data in bytes
	//	92	
	var SizeGraphics: Int? = null

	//	 DXF: Binary graphics data (multiple entries can appear) (optional)
	//	310	
	var BinaryGraphics: String? = null

	//	 DXF: Size of entity data in bits
	//	93	
	var SizeEntity: Int? = null

	//	 DXF: Binary entity data (multiple entries can appear) (optional)
	//	310	
	var BinaryEntity: String? = null

	//	 DXF: An object ID (multiple entries can appear) (optional) (330 or 340 or 350 or 360)
	//	330	
	var AnObject: String? = null

	//	 DXF: 0 (indicates end of object ID section)
	//	94	
	var IndicatesEnd: Int? = null

	//	 DXF: Object drawing format when it becomes a proxy (a 32-bit unsigned integer):, Low word is AcDbDwgVersion, High word is MaintenanceReleaseVersion
	//	95	
	var ObjectDrawing: Int? = null

	//	 DXF: Original custom object data format:, 0 = DWG format, 1 = DXF format
	//	70	
	var OriginalCustom: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				ProxyEntity = getbfr.intValue()
			}
			"91" -> {
				ApplicationEntitys = getbfr.intValue()
			}
			"92" -> {
				SizeGraphics = getbfr.intValue()
			}
			"310" -> {
				BinaryGraphics = getbfr.stringValue()
			}
			"93" -> {
				SizeEntity = getbfr.intValue()
			}
			"310" -> {
				BinaryEntity = getbfr.stringValue()
			}
			"330" -> {
				AnObject = getbfr.stringValue()
			}
			"94" -> {
				IndicatesEnd = getbfr.intValue()
			}
			"95" -> {
				ObjectDrawing = getbfr.intValue()
			}
			"70" -> {
				OriginalCustom = getbfr.intValue()
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

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }
	 companion object {


	//	 DXF: AcDbProxyEntity
	//	100	
	const val AcDbProxyEntity: String = ""
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ProxyEntity != null) sb.append( "\n 90\n"+ProxyEntity )
		if( ApplicationEntitys != null) sb.append( "\n 91\n"+ApplicationEntitys )
		if( SizeGraphics != null) sb.append( "\n 92\n"+SizeGraphics )
		if( BinaryGraphics != null) sb.append( "\n 310\n"+BinaryGraphics )
		if( SizeEntity != null) sb.append( "\n 93\n"+SizeEntity )
		if( BinaryEntity != null) sb.append( "\n 310\n"+BinaryEntity )
		if( AnObject != null) sb.append( "\n 330\n"+AnObject )
		if( IndicatesEnd != null) sb.append( "\n 94\n"+IndicatesEnd )
		if( ObjectDrawing != null) sb.append( "\n 95\n"+ObjectDrawing )
		if( OriginalCustom != null) sb.append( "\n 70\n"+OriginalCustom )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nACAD_PROXY_ENTITY")
			sbX.append( sb)
		}

		 return sbX
	}}