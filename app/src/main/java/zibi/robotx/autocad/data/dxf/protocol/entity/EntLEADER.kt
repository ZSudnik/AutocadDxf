package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntLEADER(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Dimension style name
	//	3	
	var DimensionStyle: String? = null

	//	 Arrowhead flag: 0 = Disabled 1 = Enabled
	//	71	
	var ArrowheadFlag: Int? = null

	//	 Leader path type: 0 = Straight line segments 1 = Spline
	//	72	
	var LeaderPath: Int? = null

	//	 Leader creation flag (default = 3):, 0 = Created with text annotation, 1 = Created with tolerance annotation, 2 = Created with block reference annotation, 3 = Created without any annotation
	//	73	
	var LeaderCreation: Int? = null

	//	 Hookline direction flag:, 0 = Hookline (or end of tangent for a splined leader) is the opposite direction from the horizontal vector, 1 = Hookline (or end of tangent for a splined leader) is the same direction as horizontal vector (see code 75)
	//	74	
	var HooklineDirection: Int? = null

	//	 Hookline flag:, 0 = No hookline, 1 = Has a hookline
	//	75	
	var HooklineFlag: Int? = null

	//	 Text annotation height
	//	40	
	var TextAnnotation: Float? = null

	//	 Text annotation width
	//	41	
	var TextAnnotation1: Float? = null

	//	 Number of vertices in leader (ignored for OPEN)
	//	76	
	var NumberVertices: Int? = null

	//	 Vertex coordinates (one entry for each vertex), DXF: X value APP: 3D pointDXF: Y and Z values of vertex coordinates
	//	10	20	30	
	var VertexCoordinates: Vector3f? = null

	//	 Color to use if leader's DIMCLRD = BYBLOCK
	//	77	
	var ColorTo: Int? = null

	//	 Hard reference to associated annotation (mtext, tolerance, or insert entity)
	//	340	
	var HardReference: String? = null

	//	 Normal vector, DXF: X value APP: 3D vectorDXF: Y and Z values of normal vector
	//	210	220	230	
	var NormalVector: Vector3f? = null

	//	 Horizontal direction for leader, DXF: X value APP: 3D vectorDXF: Y and Z values of horizontal direction for leader
	//	211	221	231	
	var HorizontalDirection: Vector3f? = null

	//	 Offset of last leader vertex from block reference insertion point, DXF: X value APP: 3D vectorDXF: Y and Z values of offset
	//	212	222	232	
	var OffsetLast: Vector3f? = null

	//	 Offset of last leader vertex from annotation placement point, DXF: X value APP: 3D vectorDXF: Y and Z values of offset
	//	213	223	233	
	var OffsetLast1: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"3" -> {
				DimensionStyle = getbfr.stringValue()
			}
			"71" -> {
				ArrowheadFlag = getbfr.intValue()
			}
			"72" -> {
				LeaderPath = getbfr.intValue()
			}
			"73" -> {
				LeaderCreation = getbfr.intValue()
			}
			"74" -> {
				HooklineDirection = getbfr.intValue()
			}
			"75" -> {
				HooklineFlag = getbfr.intValue()
			}
			"40" -> {
				TextAnnotation = getbfr.floatValue()
			}
			"41" -> {
				TextAnnotation1 = getbfr.floatValue()
			}
			"76" -> {
				NumberVertices = getbfr.intValue()
			}
			"10" -> {
				if( VertexCoordinates == null ) VertexCoordinates = Vector3f()
				VertexCoordinates?.x = getbfr.floatValue()
			}
			"20" -> {
				if( VertexCoordinates == null ) VertexCoordinates = Vector3f()
				VertexCoordinates?.y = getbfr.floatValue()
			}
			"30" -> {
				if( VertexCoordinates == null ) VertexCoordinates = Vector3f()
				VertexCoordinates?.z = getbfr.floatValue()
			}
			"77" -> {
				ColorTo = getbfr.intValue()
			}
			"340" -> {
				HardReference = getbfr.stringValue()
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
			"211" -> {
				if( HorizontalDirection == null ) HorizontalDirection = Vector3f()
				HorizontalDirection?.x = getbfr.floatValue()
			}
			"221" -> {
				if( HorizontalDirection == null ) HorizontalDirection = Vector3f()
				HorizontalDirection?.y = getbfr.floatValue()
			}
			"231" -> {
				if( HorizontalDirection == null ) HorizontalDirection = Vector3f()
				HorizontalDirection?.z = getbfr.floatValue()
			}
			"212" -> {
				if( OffsetLast == null ) OffsetLast = Vector3f()
				OffsetLast?.x = getbfr.floatValue()
			}
			"222" -> {
				if( OffsetLast == null ) OffsetLast = Vector3f()
				OffsetLast?.y = getbfr.floatValue()
			}
			"232" -> {
				if( OffsetLast == null ) OffsetLast = Vector3f()
				OffsetLast?.z = getbfr.floatValue()
			}
			"213" -> {
				if( OffsetLast1 == null ) OffsetLast1 = Vector3f()
				OffsetLast1?.x = getbfr.floatValue()
			}
			"223" -> {
				if( OffsetLast1 == null ) OffsetLast1 = Vector3f()
				OffsetLast1?.y = getbfr.floatValue()
			}
			"233" -> {
				if( OffsetLast1 == null ) OffsetLast1 = Vector3f()
				OffsetLast1?.z = getbfr.floatValue()
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


	//	 Subclass marker (AcDbLeader)
	//	100	
	const val AcDbLeader: String = "AcDbLeader"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( DimensionStyle != null) sb.append( "\n 3\n"+DimensionStyle )
		if( ArrowheadFlag != null) sb.append( "\n 71\n"+ArrowheadFlag )
		if( LeaderPath != null) sb.append( "\n 72\n"+LeaderPath )
		if( LeaderCreation != null) sb.append( "\n 73\n"+LeaderCreation )
		if( HooklineDirection != null) sb.append( "\n 74\n"+HooklineDirection )
		if( HooklineFlag != null) sb.append( "\n 75\n"+HooklineFlag )
		if( TextAnnotation != null) sb.append( "\n 40\n"+TextAnnotation )
		if( TextAnnotation1 != null) sb.append( "\n 41\n"+TextAnnotation1 )
		if( NumberVertices != null) sb.append( "\n 76\n"+NumberVertices )
		if( VertexCoordinates != null) sb.append( "\n 10\n"+VertexCoordinates?.x+"\n 20\n"+VertexCoordinates?.y+"\n 30\n"+VertexCoordinates?.z )
		if( ColorTo != null) sb.append( "\n 77\n"+ColorTo )
		if( HardReference != null) sb.append( "\n 340\n"+HardReference )
		if( NormalVector != null) sb.append( "\n 210\n"+NormalVector?.x+"\n 220\n"+NormalVector?.y+"\n 230\n"+NormalVector?.z )
		if( HorizontalDirection != null) sb.append( "\n 211\n"+HorizontalDirection?.x+"\n 221\n"+HorizontalDirection?.y+"\n 231\n"+HorizontalDirection?.z )
		if( OffsetLast != null) sb.append( "\n 212\n"+OffsetLast?.x+"\n 222\n"+OffsetLast?.y+"\n 232\n"+OffsetLast?.z )
		if( OffsetLast1 != null) sb.append( "\n 213\n"+OffsetLast1?.x+"\n 223\n"+OffsetLast1?.y+"\n 233\n"+OffsetLast1?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nLEADER")
			sbX.append( sb)
		}

		 return sbX
	}}