package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class EntUNDERLAY(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Object name., DGNUNDERLAY - Attached DGN file, DWFUNDERLAY - Attached DWF file, PDFUNDERLAY - Attached PDF file
	//	0	
	var ObjectName: String? = null

	//	 The ID of the AcDbUnderlayDefinition object
	//	340	
	var TheID: String? = null

	//	 The X,Y, and Z coordinates of the insertion point of the underlay. These are OCS/ECS coordinates
	//	10	20	30	
	var TheXY: Vector3f? = null

	//	 DXF: X, Y, and Z scale factors
	//	41	
	var XY: Float? = null

	//	 Rotation Angle (in OCS/ECS. CCW from the coordinate system X axis and around the Z axis)
	//	50	
	var RotationAngle: Float? = null

	//	 Normal vector (in WCS)
	//	210	220	230	
	var NormalVector: Vector3f? = null

	//	 Flags, 1 = Clipping is on, 2 = Underlay is on, 4 = Monochrome, 8 = Adjust for background, 16 = Clip is inside mode
	//	280	
	var FlagsClipping: Int? = null

	//	 Contrast (value between 20 and 100)
	//	281	
	var ContrastBetween: Int? = null

	//	 Fade (value between 0 and 80)
	//	282	
	var FadeBetween: Int? = null

	//	 Repeating: 2d points in OCS/ECS. If only two, then they are the lower left and upper right corner points of a clip rectangle. If more than two, then they are the vertices of a clipping polygon
	//	11	21	
	var RepeatingPoints: Vector2f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"0" -> {
				ObjectName = getbfr.stringValue()
			}
			"340" -> {
				TheID = getbfr.stringValue()
			}
			"10" -> {
				if( TheXY == null ) TheXY = Vector3f()
				TheXY?.x = getbfr.floatValue()
			}
			"20" -> {
				if( TheXY == null ) TheXY = Vector3f()
				TheXY?.y = getbfr.floatValue()
			}
			"30" -> {
				if( TheXY == null ) TheXY = Vector3f()
				TheXY?.z = getbfr.floatValue()
			}
			"41" -> {
				XY = getbfr.floatValue()
			}
			"50" -> {
				RotationAngle = getbfr.floatValue()
			}
			"210" -> {
				if( NormalVector == null ) NormalVector = Vector3f()
				NormalVector?.x = getbfr.floatValue()
			}
			"220" -> {
				if( NormalVector == null ) NormalVector = Vector3f()
				NormalVector?.y = getbfr.floatValue()
			}
			"230" -> {
				if( NormalVector == null ) NormalVector = Vector3f()
				NormalVector?.z = getbfr.floatValue()
			}
			"280" -> {
				FlagsClipping = getbfr.intValue()
			}
			"281" -> {
				ContrastBetween = getbfr.intValue()
			}
			"282" -> {
				FadeBetween = getbfr.intValue()
			}
			"11" -> {
				if( RepeatingPoints == null ) RepeatingPoints = Vector2f()
				RepeatingPoints?.x = getbfr.floatValue()
			}
			"21" -> {
				if( RepeatingPoints == null ) RepeatingPoints = Vector2f()
				RepeatingPoints?.y = getbfr.floatValue()
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


	//	 Subclass marker (AcDbUnderlayReference)
	//	100	
	const val AcDbUnderlayReference: String = "AcDbUnderlayReference"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ObjectName != null) sb.append( "\n 0\n"+ObjectName )
		if( TheID != null) sb.append( "\n 340\n"+TheID )
		if( TheXY != null) sb.append( "\n 10\n"+TheXY?.x+"\n 20\n"+TheXY?.y+"\n 30\n"+TheXY?.z )
		if( XY != null) sb.append( "\n 41\n"+XY )
		if( RotationAngle != null) sb.append( "\n 50\n"+RotationAngle )
		if( NormalVector != null) sb.append( "\n 210\n"+NormalVector?.x+"\n 220\n"+NormalVector?.y+"\n 230\n"+NormalVector?.z )
		if( FlagsClipping != null) sb.append( "\n 280\n"+FlagsClipping )
		if( ContrastBetween != null) sb.append( "\n 281\n"+ContrastBetween )
		if( FadeBetween != null) sb.append( "\n 282\n"+FadeBetween )
		if( RepeatingPoints != null) sb.append( "\n 11\n"+RepeatingPoints?.x+"\n 21\n"+RepeatingPoints?.y )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nUNDERLAY")
			sbX.append( sb)
		}

		 return sbX
	}}