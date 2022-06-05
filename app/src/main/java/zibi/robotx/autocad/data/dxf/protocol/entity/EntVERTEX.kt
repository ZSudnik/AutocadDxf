package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntVERTEX(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Location point (in OCS when 2D, and WCS when 3D), DXF: X value APP: 3D pointDXF: Y and Z values of location point (in OCS when 2D, and WCS when 3D)
	//	10	20	30	
	var LocationPoint: Vector3f? = null

	//	 Starting width (optional default is 0)
	//	40	
	var StartingWidth: Float? = null

	//	 Ending width (optional default is 0)
	//	41	
	var EndingWidth: Float? = null

	//	 Bulge (optional default is 0). The bulge is the tangent of one fourth the included angle for an arc segment, made negative if the arc goes clockwise from the start point to the endpoint. A bulge of 0 indicates a straight segment, and a bulge of 1 is a semicircle
	//	42	
	var BulgeDefault: Float? = null

	//	 Vertex flags:, 1 = Extra vertex created by curve-fitting, 2 = Curve-fit tangent defined for this vertex. A curve-fit tangent direction of 0 may be omitted from DXF output but is significant if this bit is set, 4 = Not used, 8 = Spline vertex created by spline-fitting, 16 = Spline frame control point, 32 = 3D polyline vertex, 64 = 3D polygon mesh, 128 = Polyface mesh vertex
	//	70	
	var VertexFlags: Int? = null

	//	 Curve fit tangent direction
	//	50	
	var CurveFit: Float? = null

	//	 Polyface mesh vertex index (optional present only if nonzero)
	//	71	
	var PolyfaceMesh: Int? = null

	//	 Polyface mesh vertex index (optional present only if nonzero)
	//	72	
	var PolyfaceMesh1: Int? = null

	//	 Polyface mesh vertex index (optional present only if nonzero)
	//	73	
	var PolyfaceMesh2: Int? = null

	//	 Polyface mesh vertex index (optional present only if nonzero)
	//	74	
	var PolyfaceMesh3: Int? = null

	//	 Vertex identifier
	//	91	
	var VertexIdentifier: Int? = null

	//////////////////////////////////
	private val polyFaceList: MutableList<MutableList<Int>>  = mutableListOf()


    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( LocationPoint == null ) LocationPoint = Vector3f()
				LocationPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( LocationPoint == null ) LocationPoint = Vector3f()
				LocationPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( LocationPoint == null ) LocationPoint = Vector3f()
				LocationPoint?.z = getbfr.floatValue()
			}
			"40" -> {
				StartingWidth = getbfr.floatValue()
			}
			"41" -> {
				EndingWidth = getbfr.floatValue()
			}
			"42" -> {
				BulgeDefault = getbfr.floatValue()
			}
			"70" -> {
				VertexFlags = getbfr.intValue()
			}
			"50" -> {
				CurveFit = getbfr.floatValue()
			}
			"71" -> {
				PolyfaceMesh = getbfr.intValue()
				addPoly( PolyfaceMesh!! )
			}
			"72" -> {
				PolyfaceMesh1 = getbfr.intValue()
				addPoly( PolyfaceMesh1!! )
			}
			"73" -> {
				PolyfaceMesh2 = getbfr.intValue()
				addPoly( PolyfaceMesh2!! )
			}
			"74" -> {
				PolyfaceMesh3 = getbfr.intValue()
				addPoly( PolyfaceMesh3!! )
			}
			"91" -> {
				VertexIdentifier = getbfr.intValue()
			}
			}
		}
			cod = getbfr.get()
	}
	}

	private fun addPoly( newPoly: Int){
		if( newPoly < 0 ){
			polyFaceList.last().add(  - newPoly)
			polyFaceList.add( mutableListOf() )
		}else {
			polyFaceList.last().add(newPoly)
		}
	}

    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		if( LocationPoint == null ) return sizeMMParent
        return sizeMMParent?.findMinMax( LocationPoint ) ?: SizeMinMax(LocationPoint!! )
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		val radiusSqr = LocationPoint?.lengthSquared() ?: 0f
		if (radiusSqr > maxRadiusSqr) {
			mRadiusSqr = radiusSqr
		}
		return mRadiusSqr
    }


	override fun collectionConnect(collectionPoint: CollectionPoint) {

//		if( VertexFlags != 192 && VertexFlags != 128  ){
//			println( " VertexFlag == $VertexFlags")
//
//		}
		when {
			VertexFlags.flag70( 32 ) -> {
				collectionPoint.collectVortexLoop ( HandlenotOmitted, LocationPoint )
			}
			VertexFlags.flag70( 64 ) -> {
				collectionPoint.collectVortex( HandlenotOmitted, LocationPoint )
			}
			VertexFlags.flag70( 128 ) -> {
				for( ind in polyFaceList.indices ){
					if( ind > 0 ) collectionPoint.createNewOneLayer(LayerNamenot)
					collectionPoint.collectVortexOnlyConnection(HandlenotOmitted, polyFaceList[ind] )
				}
//				if (PolyfaceMesh ?: 1 > 0 && PolyfaceMesh1 ?: 1 > 0 && PolyfaceMesh2 ?: 1 > 0 && PolyfaceMesh3 ?: 1 > 0) {
//					collectionPoint.collectVortexOnlyConnection(
//						HandlenotOmitted, PolyfaceMesh, PolyfaceMesh1, PolyfaceMesh2, PolyfaceMesh3)
//				} else if (PolyfaceMesh ?: 1 < 0)  {
//					collectionPoint.createNewOneLayer(LayerNamenot)
//					collectionPoint.collectVortexOnlyConnection(HandlenotOmitted,
//						PolyfaceMesh, PolyfaceMesh1, PolyfaceMesh2, PolyfaceMesh3)
//				} else if (PolyfaceMesh1 ?: 1 < 0)  {
//					collectionPoint.collectVortexOnlyConnection(
//						HandlenotOmitted, PolyfaceMesh, null, null, null)
//					collectionPoint.createNewOneLayer(LayerNamenot)
//					collectionPoint.collectVortexOnlyConnection(
//						HandlenotOmitted, null, null, PolyfaceMesh2, PolyfaceMesh3)
//				}else if (PolyfaceMesh2 ?: 1 < 0)  {
//					collectionPoint.collectVortexOnlyConnection(
//						HandlenotOmitted, PolyfaceMesh, PolyfaceMesh1, null, null)
//					collectionPoint.createNewOneLayer(LayerNamenot)
//					collectionPoint.collectVortexOnlyConnection(
//						HandlenotOmitted, null, null, null, PolyfaceMesh3)
//				}else if (PolyfaceMesh3 ?: 1 < 0)  {
//					collectionPoint.collectVortexOnlyConnection(
//						HandlenotOmitted, PolyfaceMesh, PolyfaceMesh1, PolyfaceMesh2, null)
//					collectionPoint.createNewOneLayer(LayerNamenot)
//				}
			}
			VertexFlags == 9 -> {
			}
			else -> {
				collectionPoint.collectVortex ( HandlenotOmitted, LocationPoint)
			}
		}
    }


	override fun xdef(): Int {
		return 0
	}

    init {
		polyFaceList.add( mutableListOf() )
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }

	companion object {
		//	 Subclass marker (AcDbVertex)
		//	100
		const val AcDbVertex: String = "AcDbVertex"
		//	 Subclass marker (AcDb2dVertex or AcDb3dPolylineVertex)
		//	100
		const val AcDb2dVertexAcDb3dPolylineVertex: String = "AcDb2dVertex or AcDb3dPolylineVertex"

	}


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( LocationPoint != null) sb.append( "\n 10\n"+LocationPoint?.x+"\n 20\n"+LocationPoint?.y+"\n 30\n"+LocationPoint?.z )
		if( StartingWidth != null) sb.append( "\n 40\n"+StartingWidth )
		if( EndingWidth != null) sb.append( "\n 41\n"+EndingWidth )
		if( BulgeDefault != null) sb.append( "\n 42\n"+BulgeDefault )
		if( VertexFlags != null) sb.append( "\n 70\n"+VertexFlags )
		if( CurveFit != null) sb.append( "\n 50\n"+CurveFit )
		if( PolyfaceMesh != null) sb.append( "\n 71\n"+PolyfaceMesh )
		if( PolyfaceMesh1 != null) sb.append( "\n 72\n"+PolyfaceMesh1 )
		if( PolyfaceMesh2 != null) sb.append( "\n 73\n"+PolyfaceMesh2 )
		if( PolyfaceMesh3 != null) sb.append( "\n 74\n"+PolyfaceMesh3 )
		if( VertexIdentifier != null) sb.append( "\n 91\n"+VertexIdentifier )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nVERTEX")
			sbX.append( sb)
		}

		 return sbX
	}}