package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntOLE2FRAME(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 OLE version number
	//	70	
	var OLEVersion: Int? = null

	//	 Length of binary data
	//	3	
	var LengthBinary: String? = null

	//	 Upper-left corner (WCS), DXF: X value APP: 3D pointDXF: Y and Z values of upper-left corner (in WCS)
	//	10	20	30	
	var UpperleftCorner: Vector3f? = null

	//	 Lower-right corner (WCS), DXF: X value APP: 3D pointDXF: Y and Z values of lower-right corner (in WCS)
	//	11	21	31	
	var LowerrightCorner: Vector3f? = null

	//	 OLE object type, 1 = Link 2 = Embedded 3 = Static
	//	71	
	var OLEObject: Int? = null

	//	 Tile mode descriptor:, 0 = Object resides in model space, 1 = Object resides in paper space
	//	72	
	var TileMode: Int? = null

	//	 Length of binary data
	//	90	
	var LengthBinary1: Int? = null

	//	 Binary data (multiple lines)
	//	310	
	var BinaryData: String? = null

	//	 End of OLE data (the string OLE)
	//	1	
	var EndOLE: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"70" -> {
				OLEVersion = getbfr.intValue()
			}
			"3" -> {
				LengthBinary = getbfr.stringValue()
			}
			"10" -> {
				if( UpperleftCorner == null ) UpperleftCorner = Vector3f()
				UpperleftCorner?.x = getbfr.floatValue()
			}
			"20" -> {
				if( UpperleftCorner == null ) UpperleftCorner = Vector3f()
				UpperleftCorner?.y = getbfr.floatValue()
			}
			"30" -> {
				if( UpperleftCorner == null ) UpperleftCorner = Vector3f()
				UpperleftCorner?.z = getbfr.floatValue()
			}
			"11" -> {
				if( LowerrightCorner == null ) LowerrightCorner = Vector3f()
				LowerrightCorner?.x = getbfr.floatValue()
			}
			"21" -> {
				if( LowerrightCorner == null ) LowerrightCorner = Vector3f()
				LowerrightCorner?.y = getbfr.floatValue()
			}
			"31" -> {
				if( LowerrightCorner == null ) LowerrightCorner = Vector3f()
				LowerrightCorner?.z = getbfr.floatValue()
			}
			"71" -> {
				OLEObject = getbfr.intValue()
			}
			"72" -> {
				TileMode = getbfr.intValue()
			}
			"90" -> {
				LengthBinary1 = getbfr.intValue()
			}
			"310" -> {
				BinaryData = getbfr.stringValue()
			}
			"1" -> {
				EndOLE = getbfr.stringValue()
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


	//	 Subclass marker (AcDbOle2Frame)
	//	100	
	const val AcDbOle2Frame: String = "AcDbOle2Frame"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( OLEVersion != null) sb.append( "\n 70\n"+OLEVersion )
		if( LengthBinary != null) sb.append( "\n 3\n"+LengthBinary )
		if( UpperleftCorner != null) sb.append( "\n 10\n"+UpperleftCorner?.x+"\n 20\n"+UpperleftCorner?.y+"\n 30\n"+UpperleftCorner?.z )
		if( LowerrightCorner != null) sb.append( "\n 11\n"+LowerrightCorner?.x+"\n 21\n"+LowerrightCorner?.y+"\n 31\n"+LowerrightCorner?.z )
		if( OLEObject != null) sb.append( "\n 71\n"+OLEObject )
		if( TileMode != null) sb.append( "\n 72\n"+TileMode )
		if( LengthBinary1 != null) sb.append( "\n 90\n"+LengthBinary1 )
		if( BinaryData != null) sb.append( "\n 310\n"+BinaryData )
		if( EndOLE != null) sb.append( "\n 1\n"+EndOLE )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nOLE2FRAME")
			sbX.append( sb)
		}

		 return sbX
	}}