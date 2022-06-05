package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntPOINT(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Point location (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of point location (in WCS)
	//	10	20	30	
	var PointLocation: Vector3f? = null

	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Angle of the X axis for the UCS in effect when the point was drawn (optional, default = 0) used when PDMODE is nonzero
	//	50	
	var AngleThe: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( PointLocation == null ) PointLocation = Vector3f()
				PointLocation?.x = getbfr.floatValue()
			}
			"20" -> {
				if( PointLocation == null ) PointLocation = Vector3f()
				PointLocation?.y = getbfr.floatValue()
			}
			"30" -> {
				if( PointLocation == null ) PointLocation = Vector3f()
				PointLocation?.z = getbfr.floatValue()
			}
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
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
			"50" -> {
				AngleThe = getbfr.floatValue()
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


	//	 Subclass marker (AcDbPoint)
	//	100	
	const val AcDbPoint: String = "AcDbPoint"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( PointLocation != null) sb.append( "\n 10\n"+PointLocation?.x+"\n 20\n"+PointLocation?.y+"\n 30\n"+PointLocation?.z )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( AngleThe != null) sb.append( "\n 50\n"+AngleThe )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nPOINT")
			sbX.append( sb)
		}

		 return sbX
	}}