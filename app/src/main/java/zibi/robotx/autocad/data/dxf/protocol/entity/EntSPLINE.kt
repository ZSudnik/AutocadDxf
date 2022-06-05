package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntSPLINE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {


	//	 Normal vector (omitted if the spline is nonplanar), DXF: X value APP: 3D vectorDXF: Y and Z values of normal vector (optional)
	//	210	220	230	
	var NormalVector: Vector3f? = null

	//	 Spline flag (bit coded):, 1 = Closed spline, 2 = Periodic spline, 4 = Rational spline, 8 = Planar, 16 = Linear (planar bit is also set)
	//	70	
	var SplineFlag: Int? = null

	//	 Degree of the spline curve
	//	71	
	var DegreeThe: Int? = null

	//	 Number of knots
	//	72	
	var NumberKnots: Int? = null

	//	 Number of control points
	//	73	
	var NumberControl: Int? = null

	//	 Number of fit points (if any)
	//	74	
	var NumberFit: Int? = null

	//	 Knot tolerance (default = 0.0000001)
	//	42	
	var KnotTolerance: Float? = null

	//	 Control-point tolerance (default = 0.0000001)
	//	43	
	var ControlpointTolerance: Float? = null

	//	 Fit tolerance (default = 0.0000000001)
	//	44	
	var FitTolerance: Float? = null

	//	 Start tangent may be omitted (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of start tangent may be omitted (in WCS)
	//	12	22	32	
	var StartTangent: Vector3f? = null

	//	 End tangent may be omitted (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of end tangent may be omitted (in WCS)
	//	13	23	33	
	var EndTangent: Vector3f? = null

	//	 Knot value (one entry per knot)
	//	40	
	var KnotValue: MutableList<Float> = mutableListOf()

	//	 Weight (if not 1) with multiple group pairs, they are present if all are not 1
	//	41	
	var WeightNot: Float? = null

	//	 Control points (in WCS) one entry per control point, DXF: X value APP: 3D pointDXF: Y and Z values of control points (in WCS) one entry per control point
	//	10	20	30	
	var ControlPoints: Vector3f? = null

	//	 Fit points (in WCS) one entry per fit point, DXF: X value APP: 3D pointDXF: Y and Z values of fit points (in WCS) one entry per fit point
	//	11	21	31	
	var FitPoints: Vector3f? = null

	////////////////////
	private val listControlPoint: MutableList<Vector3f> = mutableListOf()


	override fun read(getbfr: DxfLoaderContext) {
		var cod = getbfr.get()
		while (true) {
			if (cod == "0") break
			if (super.readX(cod)) {
				when (cod) {
					"210" -> {
						if (NormalVector == null) NormalVector = Vector3f()
						NormalVector?.x = getbfr.floatValue()
					}
					"220" -> {
						if (NormalVector == null) NormalVector = Vector3f()
						NormalVector?.y = getbfr.floatValue()
					}
					"230" -> {
						if (NormalVector == null) NormalVector = Vector3f()
						NormalVector?.z = getbfr.floatValue()
					}
					"70" -> {
						SplineFlag = getbfr.intValue()
					}
					"71" -> {
						DegreeThe = getbfr.intValue()
					}
					"72" -> {
						NumberKnots = getbfr.intValue()
					}
					"73" -> {
						NumberControl = getbfr.intValue()
					}
					"74" -> {
						NumberFit = getbfr.intValue()
					}
					"42" -> {
						KnotTolerance = getbfr.floatValue()
					}
					"43" -> {
						ControlpointTolerance = getbfr.floatValue()
					}
					"44" -> {
						FitTolerance = getbfr.floatValue()
					}
					"12" -> {
						if (StartTangent == null) StartTangent = Vector3f()
						StartTangent?.x = getbfr.floatValue()
					}
					"22" -> {
						if (StartTangent == null) StartTangent = Vector3f()
						StartTangent?.y = getbfr.floatValue()
					}
					"32" -> {
						if (StartTangent == null) StartTangent = Vector3f()
						StartTangent?.z = getbfr.floatValue()
					}
					"13" -> {
						if (EndTangent == null) EndTangent = Vector3f()
						EndTangent?.x = getbfr.floatValue()
					}
					"23" -> {
						if (EndTangent == null) EndTangent = Vector3f()
						EndTangent?.y = getbfr.floatValue()
					}
					"33" -> {
						if (EndTangent == null) EndTangent = Vector3f()
						EndTangent?.z = getbfr.floatValue()
					}
					"40" -> {
						KnotValue.add(getbfr.floatValue())
					}
					"41" -> {
						WeightNot = getbfr.floatValue()
					}
					"10" -> {
						ControlPoints = Vector3f()
						listControlPoint.add(ControlPoints!!)
						ControlPoints?.x = getbfr.floatValue()
					}
					"20" -> {
						ControlPoints?.y = getbfr.floatValue()
					}
					"30" -> {
						ControlPoints?.z = getbfr.floatValue()
					}
					"11" -> {
						if (FitPoints == null) FitPoints = Vector3f()
						FitPoints?.x = getbfr.floatValue()
					}
					"21" -> {
						if (FitPoints == null) FitPoints = Vector3f()
						FitPoints?.y = getbfr.floatValue()
					}
					"31" -> {
						if (FitPoints == null) FitPoints = Vector3f()
						FitPoints?.z = getbfr.floatValue()
					}
				}
			}
			cod = getbfr.get()
		}
	}


	private var interpolatePoints: List<Vector3f>? = null
	public fun checkInterpolate(): List<Vector3f>? {
		if( interpolatePoints == null ) {
			interpolatePoints = if (LinetypeName == null || !LinetypeName?.LinetypeName.equals("Continuous", true)) {
				getBSplinePolyline(listControlPoint, DegreeThe ?: 0, KnotValue, NumberControl, null
				) ?: listOf()
			} else listControlPoint
		}
		return interpolatePoints
	}


	override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		checkInterpolate()
		if (interpolatePoints!!.isEmpty()) return sizeMMParent
		val sizeParent = sizeMMParent ?: SizeMinMax(interpolatePoints!!.first())
		for (controlPoint in interpolatePoints!!)
			sizeParent.findMinMax(controlPoint)
		return sizeParent
	}

	override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		checkInterpolate()
		var mRadiusSqr = maxRadiusSqr
		for (controlPoint in interpolatePoints!!) {
			val radiusSqr = controlPoint.lengthSquared()
			if (radiusSqr > maxRadiusSqr) {
				mRadiusSqr = radiusSqr
			}
		}
		return mRadiusSqr
	}

	override fun collectionConnect(collectionPoint: CollectionPoint) {
//		return
		checkInterpolate()
//			 Spline flag (bit coded):, 1 = Closed spline, 2 = Periodic spline, 4 = Rational spline, 8 = Planar, 16 = Linear (planar bit is also set)
		when {
			SplineFlag.flag70(1) -> {
				collectionPoint.collectPointConnection(
					HandlenotOmitted,
					interpolatePoints!!,
					LayerNamenot
				)
			}
			else -> {
				collectionPoint.collectPointConnection(
					HandlenotOmitted,
					interpolatePoints!!, //listControlPoint,
					LayerNamenot
				)
			}
//			SplineFlag.flag70(1) -> {
//				collectionPoint.collectPointConnection(
//					HandlenotOmitted,
//					listVec,
//					LayerNamenot
//				)
//			}
//			else -> {
//				collectionPoint.collectPointConnection(
//					HandlenotOmitted,
//					listVec, //listControlPoint,
//					LayerNamenot
//				)
//			}

		}
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


	//	 Subclass marker (AcDbSpline)
	//	100	
	const val AcDbSpline: String = "AcDbSpline"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( NormalVector != null) sb.append( "\n 210\n"+NormalVector?.x+"\n 220\n"+NormalVector?.y+"\n 230\n"+NormalVector?.z )
		if( SplineFlag != null) sb.append( "\n 70\n"+SplineFlag )
		if( DegreeThe != null) sb.append( "\n 71\n"+DegreeThe )
		if( NumberKnots != null) sb.append( "\n 72\n"+NumberKnots )
		if( NumberControl != null) sb.append( "\n 73\n"+NumberControl )
		if( NumberFit != null) sb.append( "\n 74\n"+NumberFit )
		if( KnotTolerance != null) sb.append( "\n 42\n"+KnotTolerance )
		if( ControlpointTolerance != null) sb.append( "\n 43\n"+ControlpointTolerance )
		if( FitTolerance != null) sb.append( "\n 44\n"+FitTolerance )
		if( StartTangent != null) sb.append( "\n 12\n"+StartTangent?.x+"\n 22\n"+StartTangent?.y+"\n 32\n"+StartTangent?.z )
		if( EndTangent != null) sb.append( "\n 13\n"+EndTangent?.x+"\n 23\n"+EndTangent?.y+"\n 33\n"+EndTangent?.z )
		if( KnotValue != null) sb.append( "\n 40\n"+KnotValue )
		if( WeightNot != null) sb.append( "\n 41\n"+WeightNot )
		if( ControlPoints != null) sb.append( "\n 10\n"+ControlPoints?.x+"\n 20\n"+ControlPoints?.y+"\n 30\n"+ControlPoints?.z )
		if( FitPoints != null) sb.append( "\n 11\n"+FitPoints?.x+"\n 21\n"+FitPoints?.y+"\n 31\n"+FitPoints?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSPLINE")
			sbX.append( sb)
		}

		 return sbX
	}}