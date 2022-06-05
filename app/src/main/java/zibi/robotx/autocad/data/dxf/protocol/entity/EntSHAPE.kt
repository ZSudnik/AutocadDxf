package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntSHAPE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Insertion point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of insertion point (in WCS)
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 Size
	//	40	
	var Size: Float? = null

	//	 Shape name
	//	2	
	var ShapeName: String? = null

	//	 Rotation angle (optional default = 0)
	//	50	
	var RotationAngle: Float? = null

	//	 Relative X scale factor (optional default = 1)
	//	41	
	var RelativeX: Float? = null

	//	 Oblique angle (optional default = 0)
	//	51	
	var ObliqueAngle: Float? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
			}
			"10" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.z = getbfr.floatValue()
			}
			"40" -> {
				Size = getbfr.floatValue()
			}
			"2" -> {
				ShapeName = getbfr.stringValue()
			}
			"50" -> {
				RotationAngle = getbfr.floatValue()
			}
			"41" -> {
				RelativeX = getbfr.floatValue()
			}
			"51" -> {
				ObliqueAngle = getbfr.floatValue()
			}
			"210" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.x = getbfr.floatValue()
			}
			"220" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.y = getbfr.floatValue()
			}
			"230" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.z = getbfr.floatValue()
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


	//	 Subclass marker (AcDbShape)
	//	100	
	const val AcDbShape: String = "AcDbShape"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( Size != null) sb.append( "\n 40\n"+Size )
		if( ShapeName != null) sb.append( "\n 2\n"+ShapeName )
		if( RotationAngle != null) sb.append( "\n 50\n"+RotationAngle )
		if( RelativeX != null) sb.append( "\n 41\n"+RelativeX )
		if( ObliqueAngle != null) sb.append( "\n 51\n"+ObliqueAngle )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSHAPE")
			sbX.append( sb)
		}

		 return sbX
	}}