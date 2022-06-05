package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntMLINE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 String of up to 32 characters. The name of the style used for this mline. An entry for this style must exist in the MLINESTYLE dictionary., Do not modify this field without also updating the associated entry in the MLINESTYLE dictionary
	//	2	
	var UpTo: String? = null

	//	 Pointer-handle/ID of MLINESTYLE object
	//	340	
	var PointerhandleIDMLINESTYLE: String? = null

	//	 Scale factor
	//	40	
	var ScaleFactor: Float? = null

	//	 Justification:, 0 = Top, 1 = Zero, 2 = Bottom
	//	70	
	var JustificationTop: Int? = null

	//	 Flags (bit-coded values):, 1 = Has at least one vertex (code 72 is greater than 0), 2 = Closed, 4 = Suppress start caps, 8 = Suppress end caps
	//	71	
	var FlagsValues: Int? = null

	//	 Number of vertices
	//	72	
	var NumberVertices: Int? = null

	//	 Number of elements in MLINESTYLE definition
	//	73	
	var NumberElements: Int? = null

	//	 Start point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of start point (in WCS)
	//	10	20	30	
	var StartPoint: Vector3f? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Vertex coordinates (multiple entries one entry for each vertex) DXF: X value APP: 3D pointDXF: Y and Z values of vertex coordinates
	//	11	21	31	
	var VertexCoordinates: Vector3f? = null

	//	 Direction vector of segment starting at this vertex (multiple entries one for each vertex), DXF: X value APP: 3D vectorDXF: Y and Z values of direction vector of segment starting at this vertex
	//	12	22	32	
	var DirectionVector: Vector3f? = null

	//	 Direction vector of miter at this vertex (multiple entries: one for each vertex), DXF: X value APP: 3D vectorDXF: Y and Z values of direction vector of miter
	//	13	23	33	
	var DirectionVector1: Vector3f? = null

	//	 Number of parameters for this element (repeats for each element in segment)
	//	74	
	var NumberParameters: Int? = null

	//	 Element parameters (repeats based on previous code 74)
	//	41	
	var ElementParameters: Float? = null

	//	 Number of area fill parameters for this element (repeats for each element in segment)
	//	75	
	var NumberArea: Int? = null

	//	 Area fill parameters (repeats based on previous code 75)
	//	42	
	var AreaFill: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				UpTo = getbfr.stringValue()
			}
			"340" -> {
				PointerhandleIDMLINESTYLE = getbfr.stringValue()
			}
			"40" -> {
				ScaleFactor = getbfr.floatValue()
			}
			"70" -> {
				JustificationTop = getbfr.intValue()
			}
			"71" -> {
				FlagsValues = getbfr.intValue()
			}
			"72" -> {
				NumberVertices = getbfr.intValue()
			}
			"73" -> {
				NumberElements = getbfr.intValue()
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
			"11" -> {
				if( VertexCoordinates == null ) VertexCoordinates = Vector3f()
				VertexCoordinates?.x = getbfr.floatValue()
			}
			"21" -> {
				if( VertexCoordinates == null ) VertexCoordinates = Vector3f()
				VertexCoordinates?.y = getbfr.floatValue()
			}
			"31" -> {
				if( VertexCoordinates == null ) VertexCoordinates = Vector3f()
				VertexCoordinates?.z = getbfr.floatValue()
			}
			"12" -> {
				if( DirectionVector == null ) DirectionVector = Vector3f()
				DirectionVector?.x = getbfr.floatValue()
			}
			"22" -> {
				if( DirectionVector == null ) DirectionVector = Vector3f()
				DirectionVector?.y = getbfr.floatValue()
			}
			"32" -> {
				if( DirectionVector == null ) DirectionVector = Vector3f()
				DirectionVector?.z = getbfr.floatValue()
			}
			"13" -> {
				if( DirectionVector1 == null ) DirectionVector1 = Vector3f()
				DirectionVector1?.x = getbfr.floatValue()
			}
			"23" -> {
				if( DirectionVector1 == null ) DirectionVector1 = Vector3f()
				DirectionVector1?.y = getbfr.floatValue()
			}
			"33" -> {
				if( DirectionVector1 == null ) DirectionVector1 = Vector3f()
				DirectionVector1?.z = getbfr.floatValue()
			}
			"74" -> {
				NumberParameters = getbfr.intValue()
			}
			"41" -> {
				ElementParameters = getbfr.floatValue()
			}
			"75" -> {
				NumberArea = getbfr.intValue()
			}
			"42" -> {
				AreaFill = getbfr.floatValue()
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


	//	 Subclass marker (AcDbMline)
	//	100	
	const val AcDbMline: String = "AcDbMline"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( UpTo != null) sb.append( "\n 2\n"+UpTo )
		if( PointerhandleIDMLINESTYLE != null) sb.append( "\n 340\n"+PointerhandleIDMLINESTYLE )
		if( ScaleFactor != null) sb.append( "\n 40\n"+ScaleFactor )
		if( JustificationTop != null) sb.append( "\n 70\n"+JustificationTop )
		if( FlagsValues != null) sb.append( "\n 71\n"+FlagsValues )
		if( NumberVertices != null) sb.append( "\n 72\n"+NumberVertices )
		if( NumberElements != null) sb.append( "\n 73\n"+NumberElements )
		if( StartPoint != null) sb.append( "\n 10\n"+StartPoint?.x+"\n 20\n"+StartPoint?.y+"\n 30\n"+StartPoint?.z )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( VertexCoordinates != null) sb.append( "\n 11\n"+VertexCoordinates?.x+"\n 21\n"+VertexCoordinates?.y+"\n 31\n"+VertexCoordinates?.z )
		if( DirectionVector != null) sb.append( "\n 12\n"+DirectionVector?.x+"\n 22\n"+DirectionVector?.y+"\n 32\n"+DirectionVector?.z )
		if( DirectionVector1 != null) sb.append( "\n 13\n"+DirectionVector1?.x+"\n 23\n"+DirectionVector1?.y+"\n 33\n"+DirectionVector1?.z )
		if( NumberParameters != null) sb.append( "\n 74\n"+NumberParameters )
		if( ElementParameters != null) sb.append( "\n 41\n"+ElementParameters )
		if( NumberArea != null) sb.append( "\n 75\n"+NumberArea )
		if( AreaFill != null) sb.append( "\n 42\n"+AreaFill )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMLINE")
			sbX.append( sb)
		}

		 return sbX
	}}