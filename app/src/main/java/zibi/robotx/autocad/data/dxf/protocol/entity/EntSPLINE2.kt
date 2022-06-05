package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.error.DxfException
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntSPLINE2(lastElem: DxfChain, isRead: Boolean) : EntCommon() {


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


	override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		if (listControlPoint.isEmpty()) return sizeMMParent
		val sizeParent = sizeMMParent ?: SizeMinMax(listControlPoint.first())
		for (controlPoint in listControlPoint)
			sizeParent.findMinMax(controlPoint)
		return sizeParent
	}

	override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		for (controlPoint in listControlPoint) {
			val radiusSqr = controlPoint.lengthSquared()
			if (radiusSqr > maxRadiusSqr) {
				mRadiusSqr = radiusSqr
			}
		}
		return mRadiusSqr
	}

	override fun collectionConnect(collectionPoint: CollectionPoint) {
//		getBSplinePolyline( listControlPoint, DegreeThe ?: 0, KnotValue.toFloatArray(),
//		InterpolationsPerSplineSegment: Int, WeightNot )
		//	 Spline flag (bit coded):, 1 = Closed spline, 2 = Periodic spline, 4 = Rational spline, 8 = Planar, 16 = Linear (planar bit is also set)
		when {
			SplineFlag.flag70(1) -> {
				collectionPoint.collectPointConnection(
					HandlenotOmitted,
					listControlPoint,
					LayerNamenot
				)
			}
			else -> {
				collectionPoint.collectPointConnection(
					HandlenotOmitted,
					listControlPoint,
					LayerNamenot
				)
			}
		}
	}



	fun round10_(value: Float, exp: Int): Float {
		// If the exp is undefined or zero...
//		if (typeof exp == 'undefined' || +exp === 0) {
//			return Math.round(value)
//		}
//		value = +value
//		exp = +exp
//		// If the value is not a number or the exp is not an integer...
//		if (isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0)) {
//			return NaN
//		}
//		// Shift
//		value = value.toString().split('e')
//		value = Math.round(+(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp)))
//		// Shift back
//		value = value.toString().split('e')
//		return +(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp))
		return value
	}
	fun round10(x: Float, position: Int): Float {
		var a = x
		val temp = Math.pow(10.0, position.toDouble())
		a *= temp.toFloat()
		a = Math.round(a).toFloat()
		return a / temp.toFloat()
	}

//	@Throws(DxfException::class)
	private fun bSpline(T: Float, degree: Int, points: List<Vector3f>, Knots: FloatArray, Weights:FloatArray): Vector3f  {
		val n = points.size // points count
				val d = 3 //points[0].x // point dimensionality

		if ((T < 0) || (T > 1)) {
			throw DxfException(" new Error('t out of bounds [0,1]: " + T)
		}
		if (degree < 1) throw DxfException("degree must be at least 1 (linear)")
		if (degree > (n - 1)) throw DxfException("degree must be less than or equal to point count - 1")


		val weights =  if (Weights.isEmpty()) {
			// build weight vector of length [n]
			FloatArray(n){1f}
//			for (let i = 0; i < n; i++) {
//				weights[i] = 1
//			}
		}else Weights

//		var knots = FloatArray(n + degree.toInt() + 1){ 0f }
		val knots = if (Knots.isEmpty()) {
			// build knot vector of length [n + degree + 1]
			val mknots = FloatArray(n + degree + 1){ 0f }
			for (i in 0 until (n + degree + 1) ) {
				mknots[i] = i.toFloat()
			}
			mknots
		} else if (Knots.size != n + degree + 1) {
			throw DxfException("bad knot vector length")
		}else{
			Knots
		}

		val domain0 = degree
		val domain1 = knots.size - 1 - degree


		// remap t to the domain where the spline is defined
		val  low = knots[domain0]
		val  high = knots[domain1]
		var t = T * (high - low) + low

		// Clamp to the upper &  lower bounds instead of
		// throwing an error like in the original lib
		// https://github.com/bjnortier/dxf/issues/28
		t = Math.max(t, low)
		t = Math.min(t, high)

		// find s (the spline segment) for the [t] value provided
//		let s
//				for (s = domain0; s < domain1; s++) {
//			if (t >= knots[s] && t <= knots[s + 1]) {
//				break
//			}
//		}
		var s = domain0
		for( i in domain0 until domain1 ){
			if (t >= knots[i] && t <= knots[i + 1]){
				s = i
				break
			}
		}

		// convert points to homogeneous coordinates
		val v: MutableList<MutableList<Float>> = mutableListOf()
		for ( i in 0 until n) {
			v[i] = mutableListOf()
			for ( j in 0 until d) {
				v[i][j] = when( j ){
					0-> points[i].x * weights[i]
					1-> points[i].y * weights[i]
					2-> points[i].z * weights[i]
					else -> 0f
				}
//			v[i][j] = points[i][j] * weights[i]
			}
			v[i][d] = weights[i]
		}

		// l (level) goes from 1 to the curve degree + 1
//		let alpha
//				for (let l = 1; l <= degree + 1; l++) {
			 //build level l of the pyramid
//			for (let i = s; i > s - degree - 1 + l; i--) {
//			alpha = (T - knots[i]) / (knots[i + degree + 1 - l] - knots[i])
//
			 //interpolate each component
//			for (let j = 0; j < d + 1; j++) {
//			v[i][j] = (1 - alpha) * v[i - 1][j] + alpha * v[i][j]
//		}
//		}
//		}
//
		var alpha = 0f
		for ( l in 1 until degree + 1) {
			// build level l of the pyramid
			for ( i in  s downTo (s - degree - 1 + l) ) {//}; i--) {
				alpha = (T - knots[i]) / (knots[i + degree + 1 - l] - knots[i])
				// interpolate each component
				for ( j in 0 until ( d + 1) ) {
					v[i][j] = (1 - alpha) * v[i - 1][j] + alpha * v[i][j]
				}
			}
		}

		// convert back to cartesian and return
//		const result = []
//		for (let i = 0; i < d; i++) {
//			result[i] = round10(v[s][i] / v[s][d], -9)
//		}
		val result: MutableList<Float> = mutableListOf()
		for (i in 0 until d) {
			result[i] = Math.round((v[s][i] / v[s][d]) * 0.00001) / 0.00001f
//			result[i] = round10(v[s][i] / v[s][d], -9)
		}
		val vec: Vector3f = Vector3f()
		vec.x = result[0]
		vec.y = result[1]
		vec.z = result[2]
		return vec
	}

	/**
	 * Interpolate a b-spline. The algorithm examins the knot vector
	 * to create segments for interpolation. The parameterisation value
	 * is re-normalised back to [0,1] as that is what the lib expects (
	 * and t i de-normalised in the b-spline library)
	 *
	 * @param controlPoints the control points
	 * @param degree the b-spline degree
	 * @param knots the knot vector
	 * @returns the polyline
	 */
	fun getBSplinePolyline(controlPoints: List<Vector3f>, degree: Int, knots: FloatArray,
						   InterpolationsPerSplineSegment: Int, weights: FloatArray): MutableList<Vector3f> {
		val polyline: MutableList<Vector3f> = mutableListOf()
//		val controlPointsForLib = controlPoints.map(function (p) {
//			return [p.x, p.y]
//		})

		val  segmentTs = arrayListOf<Float>(knots[degree] )
		val domain0 = knots[degree]
		val domain1 = knots[knots.size - 1 - degree]

		for ( k in (degree + 1) until (knots.size - degree) ) {
			if (segmentTs[segmentTs.size - 1] != knots[k]) {
				segmentTs.add(knots[k])
			}
		}
//		val interpolationsPerSplineSegment: Int = InterpolationsPerSplineSegment || 25
		val interpolationsPerSplineSegment: Int = InterpolationsPerSplineSegment.or( 25 )
		for ( i in 1 until segmentTs.size ) {
			val uMin = segmentTs[i - 1]
			val uMax = segmentTs[i]
			for ( k in 0..interpolationsPerSplineSegment) {
				val u = k / interpolationsPerSplineSegment * (uMax - uMin) + uMin
				// Clamp t to 0, 1 to handle numerical precision issues
				var t = (u - domain0) / (domain1 - domain0)
				t = Math.max(t, 0f)
				t = Math.min(t, 1f)
//				val p = bSpline(t, degree, controlPointsForLib, knots, weights)
				val p = bSpline(t, degree, controlPoints, knots, weights)
				polyline.add( p )    //push(new THREE.Vector2(p[0], p[1]));
			}
		}
		return polyline
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