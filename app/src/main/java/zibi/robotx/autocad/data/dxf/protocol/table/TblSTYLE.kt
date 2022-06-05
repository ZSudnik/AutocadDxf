package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class TblSTYLE(lastElem: DxfChain, isRead: Boolean): TblCommon() {




	//	 Style name
	//	2	
	var StyleName: String = ""
		private set

	//	 Standard flag values (bit-coded values):,1 = If set, this entry describes a shape,4 = Vertical text,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and need not be set by programs that write DXF files)
	//	70	
	var StandardFlag: Int? = null

	//	 Fixed text height 0 if not fixed
	//	40	
	var FixedText: Float? = null

	//	 Width factor
	//	41	
	var WidthFactor: Float? = null

	//	 Oblique angle
	//	50	
	var ObliqueAngle: Float? = null

	//	 Text generation flags:,2 = Text is backward (mirrored in X),4 = Text is upside down (mirrored in Y)
	//	71	
	var TextGeneration: Int? = null

	//	 Last height used
	//	42	
	var LastHeight: Float? = null

	//	 Primary font file name
	//	3	
	var PrimaryFont: String? = null

	//	 Bigfont file name blank if none
	//	4	
	var BigfontFile: String? = null

	//	 A long value which contains a truetype fonts pitch and family, character set, and italic and bold flags,
	//	1071	
	var ALong: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				StyleName = getbfr.stringValue()
			}
			"70" -> {
				StandardFlag = getbfr.intValue()
			}
			"40" -> {
				FixedText = getbfr.floatValue()
			}
			"41" -> {
				WidthFactor = getbfr.floatValue()
			}
			"50" -> {
				ObliqueAngle = getbfr.floatValue()
			}
			"71" -> {
				TextGeneration = getbfr.intValue()
			}
			"42" -> {
				LastHeight = getbfr.floatValue()
			}
			"3" -> {
				PrimaryFont = getbfr.stringValue()
			}
			"4" -> {
				BigfontFile = getbfr.stringValue()
			}
			"1071" -> {
				ALong = getbfr.intValue()
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


	//	 Subclass marker (AcDbTextStyleTableRecord)
	//	100	
	const val AcDbTextStyleTableRecord: String = "AcDbTextStyleTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( StyleName != null) sb.append( "\n 2\n"+StyleName )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( FixedText != null) sb.append( "\n 40\n"+FixedText )
		if( WidthFactor != null) sb.append( "\n 41\n"+WidthFactor )
		if( ObliqueAngle != null) sb.append( "\n 50\n"+ObliqueAngle )
		if( TextGeneration != null) sb.append( "\n 71\n"+TextGeneration )
		if( LastHeight != null) sb.append( "\n 42\n"+LastHeight )
		if( PrimaryFont != null) sb.append( "\n 3\n"+PrimaryFont )
		if( BigfontFile != null) sb.append( "\n 4\n"+BigfontFile )
		if( ALong != null) sb.append( "\n 1071\n"+ALong )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSTYLE")
			sbX.append( sb)
		}

		 return sbX
	}
	constructor(lastElem: DxfChain, StyleName: String ): this(lastElem, false){
		this.StyleName = StyleName
	}

	init {
		last(lastElem)
		if (isRead) read(lastElem.dxfContext)
	}

}