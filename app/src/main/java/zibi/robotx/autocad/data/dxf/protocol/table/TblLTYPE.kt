package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class TblLTYPE(lastElem: DxfChain, isRead: Boolean) : TblCommon() {


	//	 Linetype name
	//	2	
	var LinetypeName: String = ""
		private set

	//	 Standard flag values (bit-coded values):,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and need not be set by programs that write DXF files)
	//	70	
	var StandardFlag: Int? = null

	//	 Descriptive text for linetype
	//	3	
	var DescriptiveText: String? = null

	//	 Alignment code value is always 65, the ASCII code for A
	//	72	
	var AlignmentCode: Int? = null

	//	 The number of linetype elements
	//	73	
	var TheNumber: Int? = null

	//	 Total pattern length
	//	40	
	var TotalPattern: Float? = null

	//	 Dash, dot or space length (one entry per element)
	//	49	
	var DashDot: Float? = null

	//	 Complex linetype element type (one per element). Default is 0 (no embedded shape/text),The following codes are bit values:,1 = If set, code 50 specifies an absolute rotation if not set, code 50 specifies a relative rotation,2 = Embedded element is a text string,4 = Embedded element is a shape
	//	74	
	var ComplexLinetype: Int? = null

	//	 Shape number (one per element) if code 74 specifies an embedded shape,If code 74 specifies an embedded text string, this value is set to 0,If code 74 is set to 0, code 75 is omitted
	//	75	
	var ShapeNumber: Int? = null

	//	 Pointer to STYLE object (one per element if code 74 > 0)
	//	340	
	var PointerTo: String? = null

	//	 S = Scale value (optional) multiple entries can exist
	//	46	
	var SScale: Float? = null

	//	 R = (relative) or A = (absolute) rotation value in radians of embedded shape or text one per element if code 74 specifies an embedded shape or text string
	//	50	
	var RA: Float? = null

	//	 X = X offset value (optional) multiple entries can exist
	//	44	
	var XX: Float? = null

	//	 Y = Y offset value (optional) multiple entries can exist
	//	45	
	var YY: Float? = null

	//	 Text string (one per element if code 74 = 2),
	//	9	
	var TextPer: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				LinetypeName = getbfr.stringValue()
			}
			"70" -> {
				StandardFlag = getbfr.intValue()
			}
			"3" -> {
				DescriptiveText = getbfr.stringValue()
			}
			"72" -> {
				AlignmentCode = getbfr.intValue()
			}
			"73" -> {
				TheNumber = getbfr.intValue()
			}
			"40" -> {
				TotalPattern = getbfr.floatValue()
			}
			"49" -> {
				DashDot = getbfr.floatValue()
			}
			"74" -> {
				ComplexLinetype = getbfr.intValue()
			}
			"75" -> {
				ShapeNumber = getbfr.intValue()
			}
			"340" -> {
				PointerTo = getbfr.stringValue()
			}
			"46" -> {
				SScale = getbfr.floatValue()
			}
			"50" -> {
				RA = getbfr.floatValue()
			}
			"44" -> {
				XX = getbfr.floatValue()
			}
			"45" -> {
				YY = getbfr.floatValue()
			}
			"9" -> {
				TextPer = getbfr.stringValue()
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


	//	 Subclass marker (AcDbLinetypeTableRecord)
	//	100	
	const val AcDbLinetypeTableRecord: String = "AcDbLinetypeTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( LinetypeName != null) sb.append( "\n 2\n"+LinetypeName )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( DescriptiveText != null) sb.append( "\n 3\n"+DescriptiveText )
		if( AlignmentCode != null) sb.append( "\n 72\n"+AlignmentCode )
		if( TheNumber != null) sb.append( "\n 73\n"+TheNumber )
		if( TotalPattern != null) sb.append( "\n 40\n"+TotalPattern )
		if( DashDot != null) sb.append( "\n 49\n"+DashDot )
		if( ComplexLinetype != null) sb.append( "\n 74\n"+ComplexLinetype )
		if( ShapeNumber != null) sb.append( "\n 75\n"+ShapeNumber )
		if( PointerTo != null) sb.append( "\n 340\n"+PointerTo )
		if( SScale != null) sb.append( "\n 46\n"+SScale )
		if( RA != null) sb.append( "\n 50\n"+RA )
		if( XX != null) sb.append( "\n 44\n"+XX )
		if( YY != null) sb.append( "\n 45\n"+YY )
		if( TextPer != null) sb.append( "\n 9\n"+TextPer )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nLTYPE")
			sbX.append( sb)
		}

		 return sbX
	}

	init{
		last(lastElem)
		if (isRead) read(lastElem.dxfContext)
	}

	constructor(lastElem: DxfChain, LinetypeName: String ): this(lastElem, false){
		this.LinetypeName = LinetypeName
	}
}