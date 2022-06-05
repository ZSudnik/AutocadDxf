package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntLWPOLYLINE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Number of vertices
	//	90	
	var NumberVertices: Int? = null

	//	 Polyline flag (bit-coded) default is 0:, 1 = Closed 128 = Plinegen
	//	70	
	var PolylineFlag: Int? = null

	//	 Constant width (optional default = 0). Not used if variable width (codes 40 and/or 41) is set
	//	43	
	var ConstantWidth: Float? = null

	//	 Elevation (optional default = 0)
	//	38	
	var ElevationDefault: Float? = null

	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Vertex coordinates (in OCS), multiple entries one entry for each vertex, DXF: X value APP: 2D pointDXF: Y value of vertex coordinates (in OCS), multiple entries one entry for each vertex
	//	10	20	
	var VertexCoordinates: Vector3f? = null

	//	 Vertex identifier
	//	91	
	var VertexIdentifier: Int? = null

	//	 Starting width (multiple entries one entry for each vertex) (optional default = 0 multiple entries). Not used if constant width (code 43) is set
	//	40	
	var StartingWidth: Float? = null

	//	 End width (multiple entries one entry for each vertex) (optional default = 0 multiple entries). Not used if constant width (code 43) is set
	//	41	
	var EndWidth: Float? = null

	//	 Bulge (multiple entries one entry for each vertex) (optional default = 0)
	//	42	
	var BulgeEntries: Float? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	////////////////////
	val listControlPoint: MutableList<Vector3f> = mutableListOf()

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				NumberVertices = getbfr.intValue()
			}
			"70" -> {
				PolylineFlag = getbfr.intValue()
			}
			"43" -> {
				ConstantWidth = getbfr.floatValue()
			}
			"38" -> {
				ElevationDefault = getbfr.floatValue()
			}
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
			}
			"10" -> {
				VertexCoordinates = Vector3f()
				listControlPoint.add( VertexCoordinates!!)
				VertexCoordinates?.x = getbfr.floatValue()
			}
			"20" -> {
				VertexCoordinates?.y = getbfr.floatValue()
			}
			"91" -> {
				VertexIdentifier = getbfr.intValue()
			}
			"40" -> {
				StartingWidth = getbfr.floatValue()
			}
			"41" -> {
				EndWidth = getbfr.floatValue()
			}
			"42" -> {
				BulgeEntries = getbfr.floatValue()
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
		if( listControlPoint.isEmpty() ) return sizeMMParent
		val sizeParent = sizeMMParent ?: SizeMinMax( listControlPoint.first() )
		for ( controlPoint in listControlPoint)
			sizeParent.findMinMax( controlPoint )
		return sizeParent
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		for ( controlPoint in listControlPoint) {
			val radiusSqr = controlPoint.lengthSquared()
			if (radiusSqr > maxRadiusSqr) {
				mRadiusSqr = radiusSqr
			}
		}
		return mRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
		collectionPoint.createNewOneLayer( LayerNamenot )
		when {
			PolylineFlag.flag70(1) -> {
				collectionPoint.collectPointConnectionNewOneL(HandlenotOmitted, listControlPoint)
			}
			else -> {
				collectionPoint.collectPointConnectionNewOneL(HandlenotOmitted, listControlPoint)
			}
		}
   	}

	override fun xdef(): Int { return 0 }

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }

	 companion object {
		//	 Subclass marker (AcDbPolyline)
		//	100
		const val AcDbPolyline: String = "AcDbPolyline"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( NumberVertices != null) sb.append( "\n 90\n"+NumberVertices )
		if( PolylineFlag != null) sb.append( "\n 70\n"+PolylineFlag )
		if( ConstantWidth != null) sb.append( "\n 43\n"+ConstantWidth )
		if( ElevationDefault != null) sb.append( "\n 38\n"+ElevationDefault )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( VertexCoordinates != null) sb.append( "\n 10\n"+VertexCoordinates?.x+"\n 20\n"+VertexCoordinates?.y )
		if( VertexIdentifier != null) sb.append( "\n 91\n"+VertexIdentifier )
		if( StartingWidth != null) sb.append( "\n 40\n"+StartingWidth )
		if( EndWidth != null) sb.append( "\n 41\n"+EndWidth )
		if( BulgeEntries != null) sb.append( "\n 42\n"+BulgeEntries )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nLWPOLYLINE")
			sbX.append( sb)
		}

		 return sbX
	}}