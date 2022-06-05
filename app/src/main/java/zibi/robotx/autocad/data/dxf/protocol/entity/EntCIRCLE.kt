package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntCIRCLE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Center point (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of center point (in OCS)
	//	10	20	30	
	var CenterPoint: Vector3f? = null

	//	 Radius
	//	40	
	var Radius: Float? = null

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
				if( CenterPoint == null ) CenterPoint = Vector3f()
				CenterPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( CenterPoint == null ) CenterPoint = Vector3f()
				CenterPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( CenterPoint == null ) CenterPoint = Vector3f()
				CenterPoint?.z = getbfr.floatValue()
			}
			"40" -> {
				Radius = getbfr.floatValue()
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

	private var interpolatePoints: List<Vector3f>? = null
	public fun checkInterpolate(): List<Vector3f>?{
		if( interpolatePoints == null )
			interpolatePoints = interpolateCircle(CenterPoint, Radius, ExtrusionDirection )
		return interpolatePoints
	}

    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		checkInterpolate()
		return sizeMMParent?.findMinMax( interpolatePoints!! )
			?: SizeMinMax.findMinMax( interpolatePoints!! )
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		checkInterpolate()
		var mRadiusSqr = maxRadiusSqr
		for( point in interpolatePoints!!) {
			val radiusSqr = point.lengthSquared()
			if (radiusSqr > maxRadiusSqr) mRadiusSqr = radiusSqr
		}
		return mRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
		checkInterpolate()
		collectionPoint.collectPointConnection(
			HandlenotOmitted,
			interpolatePoints!!,
			LayerNamenot)
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


	//	 Subclass marker (AcDbCircle)
	//	100	
	const val AcDbCircle: String = "AcDbCircle"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( CenterPoint != null) sb.append( "\n 10\n"+CenterPoint?.x+"\n 20\n"+CenterPoint?.y+"\n 30\n"+CenterPoint?.z )
		if( Radius != null) sb.append( "\n 40\n"+Radius )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nCIRCLE")
			sbX.append( sb)
		}

		 return sbX
	}}