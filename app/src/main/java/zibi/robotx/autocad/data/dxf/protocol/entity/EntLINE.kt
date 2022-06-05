package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntLINE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Start point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of start point (in WCS)
	//	10	20	30	
	var StartPoint: Vector3f? = null

	//	 Endpoint (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of endpoint (in WCS)
	//	11	21	31	
	var EndpointWCS: Vector3f? = null

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
				if( StartPoint == null ) StartPoint = Vector3f()
				StartPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( StartPoint == null ) StartPoint = Vector3f()
				StartPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( StartPoint == null ) StartPoint = Vector3f()
				StartPoint?.z = getbfr.floatValue()
			}
			"11" -> {
				if( EndpointWCS == null ) EndpointWCS = Vector3f()
				EndpointWCS?.x = getbfr.floatValue()
			}
			"21" -> {
				if( EndpointWCS == null ) EndpointWCS = Vector3f()
				EndpointWCS?.y = getbfr.floatValue()
			}
			"31" -> {
				if( EndpointWCS == null ) EndpointWCS = Vector3f()
				EndpointWCS?.z = getbfr.floatValue()
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
		if ( StartPoint == null || EndpointWCS == null) return sizeMMParent
		val sizeParent = sizeMMParent?.findMinMax(StartPoint) ?: SizeMinMax( StartPoint!!)
		sizeParent.findMinMax(EndpointWCS)
		return sizeParent
	}

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		var radiusSqr = StartPoint?.lengthSquared() ?: Float.MIN_VALUE
		if (radiusSqr > maxRadiusSqr) {
			mRadiusSqr = radiusSqr
		}
		radiusSqr = EndpointWCS?.lengthSquared() ?: Float.MIN_VALUE
		if (radiusSqr > maxRadiusSqr) {
			mRadiusSqr = radiusSqr
		}
		return mRadiusSqr

	}

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
		if ( StartPoint == null || EndpointWCS == null) return
		val listControlPoint: List<Vector3f> = listOf(StartPoint!!,EndpointWCS!!)
		collectionPoint.collectPointConnection(
			HandlenotOmitted,
			listControlPoint,
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
		var ile = 0

	//	 Subclass marker (AcDbLine)
	//	100	
	const val AcDbLine: String = "AcDbLine"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( StartPoint != null) sb.append( "\n 10\n"+StartPoint?.x+"\n 20\n"+StartPoint?.y+"\n 30\n"+StartPoint?.z )
		if( EndpointWCS != null) sb.append( "\n 11\n"+EndpointWCS?.x+"\n 21\n"+EndpointWCS?.y+"\n 31\n"+EndpointWCS?.z )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nLINE")
			sbX.append( sb)
		}

		 return sbX
	}}