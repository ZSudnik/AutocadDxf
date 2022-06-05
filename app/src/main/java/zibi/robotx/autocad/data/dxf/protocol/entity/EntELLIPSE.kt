package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntELLIPSE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Center point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of center point (in WCS)
	//	10	20	30	
	var CenterPoint: Vector3f? = null //x

	//	 Endpoint of major axis, relative to the center (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of endpoint of major axis, relative to the center (in WCS)
	//	11	21	31	
	var EndpointMajor: Vector3f? = null //majorX

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Ratio of minor axis to major axis
	//	40	
	var RatioMinor: Float? = null //axisRatio

	//	 Start parameter (this value is 0.0 for a full ellipse)
	//	41	
	var StartParameter: Float? = null  // startAngle

	//	 End parameter (this value is 2pi for a full ellipse)
	//	42	
	var EndParameter: Float? = null // endAngle

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
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
			"11" -> {
				if( EndpointMajor == null ) EndpointMajor = Vector3f()
				EndpointMajor?.x = getbfr.floatValue()
			}
			"21" -> {
				if( EndpointMajor == null ) EndpointMajor = Vector3f()
				EndpointMajor?.y = getbfr.floatValue()
			}
			"31" -> {
				if( EndpointMajor == null ) EndpointMajor = Vector3f()
				EndpointMajor?.z = getbfr.floatValue()
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
			"40" -> {
				RatioMinor = getbfr.floatValue()
			}
			"41" -> {
				StartParameter = getbfr.floatValue()
			}
			"42" -> {
				EndParameter = getbfr.floatValue()
			}
			}
		}
			cod = getbfr.get()
	}
	}

	private var interpolatePoints: List<Vector3f>? = null
	public fun checkInterpolate(): List<Vector3f>? {
		if( interpolatePoints == null )
			interpolatePoints = interpolateEllipse(CenterPoint, EndpointMajor, ExtrusionDirection,
							RatioMinor, StartParameter, EndParameter)
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

    override fun collectionConnect(collectionPoint: CollectionPoint){
		checkInterpolate()
		collectionPoint.collectPointConnection(
			HandlenotOmitted,
			interpolatePoints!!,
			LayerNamenot
		)
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
	//	 Subclass marker (AcDbEllipse)
	//	100	
	const val AcDbEllipse: String = "AcDbEllipse"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( CenterPoint != null) sb.append( "\n 10\n"+CenterPoint?.x+"\n 20\n"+CenterPoint?.y+"\n 30\n"+CenterPoint?.z )
		if( EndpointMajor != null) sb.append( "\n 11\n"+EndpointMajor?.x+"\n 21\n"+EndpointMajor?.y+"\n 31\n"+EndpointMajor?.z )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( RatioMinor != null) sb.append( "\n 40\n"+RatioMinor )
		if( StartParameter != null) sb.append( "\n 41\n"+StartParameter )
		if( EndParameter != null) sb.append( "\n 42\n"+EndParameter )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nELLIPSE")
			sbX.append( sb)
		}

		 return sbX
	}}