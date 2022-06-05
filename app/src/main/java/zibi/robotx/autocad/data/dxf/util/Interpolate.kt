package zibi.robotx.autocad.data.dxf.util

import zibi.robotx.autocad.data.dxf.error.DxfException
import javax.vecmath.Vector3f
import kotlin.math.*

const val PI: Float = Math.PI.toFloat()



fun interpolateEllipse(CenterPoint: Vector3f?, EndpointMajor: Vector3f?, ExtrusionDirection: Vector3f?,
                       RatioMinor: Float?, StartParameter: Float?, EndParameter: Float?) : List<Vector3f> {

    if( CenterPoint == null && EndpointMajor == null && StartParameter == null ) return mutableListOf()

    val rx: Float = EndpointMajor!!.length()
    val ry: Float = RatioMinor!! * rx
    val start: Float = StartParameter!!
    var end: Float = EndParameter ?: 0f
    if (end < start) {
        end += PI * 2
    }
    var extrusionZ = 1
    if (ExtrusionDirection != null && ExtrusionDirection.z == -1f) {
        extrusionZ = -1
    }

    // ----- Relative points -----

    // Start point
    val points: MutableList<Vector3f> = mutableListOf()
    val dTheta = PI * 2 / 72
    val EPS = 1e-6.toFloat()
    var theta = start
    while( theta < end - EPS){
        points.add( Vector3f( cos(theta) * rx, sin(theta) * ry, 0f)  )
        theta += dTheta
    }
    points.add( Vector3f( cos(end) * rx, sin(end) * ry, 0f)  )

    val mPoints: MutableList<Vector3f> = mutableListOf()
    // ----- Rotate -----
    val rotationAngle: Float = -atan2(-EndpointMajor.y, EndpointMajor.x)
    if (rotationAngle > 0 ) {
        for (point in points) {
            mPoints.add(Vector3f(
                extrusionZ * (CenterPoint!!.x + point.x * cos(rotationAngle) - point.y * sin(rotationAngle)),
                CenterPoint.y + point.y * cos(rotationAngle) + point.x * sin(rotationAngle), point.z))
        }
    }else{
        for (point in points) {
            mPoints.add(Vector3f(
                extrusionZ * (CenterPoint!!.x + point.x ),
                CenterPoint.y + point.y , point.z))
        }
    }
    return mPoints.toList()
}


fun interpolateArc(CenterPoint: Vector3f?, Radius: Float?, ExtrusionDirection: Vector3f?,
                   StartAngle: Float?, EndAngle: Float?) : List<Vector3f> {

    if( CenterPoint == null && Radius == null && StartAngle == null && EndAngle == null ) return mutableListOf()

    val start: Float = (PI * StartAngle!!)/ 180f
    val end: Float = (PI * EndAngle!!)/ 180f
//    if (end < start) {
//        val tmp = end
//        end = start
//        start = tmp
//    }
    var extrusionZ = 1
    if (ExtrusionDirection != null && ExtrusionDirection.z == -1f) {
        extrusionZ = -1
    }
    // ----- Relative points -----
    // Start point
    val points: MutableList<Vector3f> = mutableListOf()
    val dTheta = PI * 2 / 72
    val EPS = 1e-6.toFloat()
    var theta = start
    while( theta < end - EPS){
        points.add( Vector3f( cos(theta) * Radius!!, sin(theta) * Radius, 0f)  )
        theta += dTheta
    }
    points.add( Vector3f( cos(end) * Radius!!, sin(end) * Radius, 0f)  )

    val mPoints: MutableList<Vector3f> = mutableListOf()
    for (point in points) {
        mPoints.add(Vector3f(
            extrusionZ * (CenterPoint!!.x + point.x ),
            CenterPoint.y + point.y , point.z))
    }
    return mPoints.toList()
}

fun interpolateCircle(CenterPoint: Vector3f?, Radius: Float?, ExtrusionDirection: Vector3f?) : List<Vector3f> {

    if( CenterPoint == null && Radius == null ) return mutableListOf()

    val start = 0f
    val end: Float = PI * 2

    var extrusionZ = 1
    if (ExtrusionDirection != null && ExtrusionDirection.z == -1f) {
        extrusionZ = -1
    }

    // ----- Relative points -----

    // Start point
    val points: MutableList<Vector3f> = mutableListOf()
    val dTheta = PI * 2 / 72
    val EPS = 1e-6.toFloat()
    var theta = start
    while( theta < end - EPS){
        points.add( Vector3f( cos(theta) * Radius!!, sin(theta) * Radius, 0f)  )
        theta += dTheta
    }
    points.add( Vector3f( cos(end) * Radius!!, sin(end) * Radius, 0f)  )

    val mPoints: MutableList<Vector3f> = mutableListOf()
    for (point in points) {
        mPoints.add(Vector3f(
            extrusionZ * (CenterPoint!!.x + point.x ),
            CenterPoint.y + point.y , point.z))
    }
    return mPoints.toList()
}

///////////////////// SPLINE /////////////////////////

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

private fun bSpline(T: Float, degree: Int, points: List<Vector3f>, knots: List<Float>, Weights: FloatArray?): Vector3f  {
    val n = points.size // points count
    val d = 3 //points[0].x // point dimensionality

    if ((T < 0) || (T > 1)) {
        throw DxfException(" new Error('t out of bounds [0,1]: " + T)
    }
    if (degree < 1) throw DxfException("degree must be at least 1 (linear)")
    if (degree > (n - 1)) throw DxfException("degree must be less than or equal to point count - 1")


    // build weight vector of length [n]
    val weights =  if (Weights == null || Weights.isEmpty()) {
        FloatArray(n){1f}
    }else Weights

//		val knots: List<Float> = if (Knots == null || Knots.isEmpty()) {
//			// build knot vector of length [n + degree + 1]
//			val mknots = MutableList(n + degree + 1){ 0f }
//			for (i in 0 until (n + degree + 1) ) {
//				mknots[i] = i.toFloat()
//			}
//			mknots
//		} else if (Knots.size != n + degree + 1) {
//			throw DxfException("bad knot vector length")
//		}else{
//			Knots
//		}

    val domain0 = degree
    val domain1 = knots.size - 1 - degree


    // remap t to the domain where the spline is defined
    val  low = knots[domain0]
    val  high = knots[domain1]
    var t = T * (high - low) + low

    // Clamp to the upper &  lower bounds instead of
    // throwing an error like in the original lib
    // https://github.com/bjnortier/dxf/issues/28
    t = max(t, low)
    t = min(t, high)

    // find s (the spline segment) for the [t] value provided
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
        v.add( MutableList( d+1 ){1f} )
        for ( j in 0 until d) {
            val wartosc = when( j ){
                0-> points[i].x * weights[i]
                1-> points[i].y * weights[i]
                2-> points[i].z * weights[i]
                else -> 0f
            }
            v[i][j] = wartosc
//			v[i][j] = points[i][j] * weights[i]
        }
        v[i][d] = weights[i]
    }

    // l (level) goes from 1 to the curve degree + 1
    for ( l in 1 until degree + 1) {
        // build level l of the pyramid
//			for ( i in  s downTo (s - degree - 1 + l) ) {
        for ( i in  s downTo (s - degree + l) ) {
            val alpha = (T - knots[i]) / (knots[i + degree + 1 - l] - knots[i])
            // interpolate each component
            for ( j in 0 until ( d + 1) ) {
                v[i][j] = (1 - alpha) * v[i - 1][j] + alpha * v[i][j]
            }
        }
    }

    // convert back to cartesian and return
    val result: MutableList<Float> = mutableListOf()
    for (i in 0 until d) {
        result.add( v[s][i] / v[s][d])
//			result.add( Math.round((v[s][i] / v[s][d]) * 0.00001) / 0.00001f  )
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
fun getBSplinePolyline(controlPoints: List<Vector3f>, degree: Int, Knots: List<Float>?,
                       InterpolationsPerSplineSegment: Int?, weights: FloatArray?): MutableList<Vector3f>? {
    val polyline: MutableList<Vector3f> = mutableListOf()
//		val controlPointsForLib = controlPoints.map(function (p) {
//			return [p.x, p.y]
//		})

    val n = controlPoints.size
    val knots: List<Float> = if (Knots == null || Knots.isEmpty()) {
        // build knot vector of length [n + degree + 1]
        val mknots = MutableList(n + degree + 1){ 0f }
        for (i in 0 until (n + degree + 1) ) {
            mknots[i] = i.toFloat()
        }
        mknots
    } else if (Knots.size != n + degree + 1) {
        return  null//throw DxfException("bad knot vector length")
    }else{
        Knots
    }


    val segmentTs = arrayListOf<Float>(knots[degree] )
    val domain0 = knots[degree]
    val domain1 = knots[knots.size - 1 - degree]

    for ( k in (degree + 1) until (knots.size - degree) ) {
        if (segmentTs[segmentTs.size - 1] != knots[k]) {
            segmentTs.add(knots[k])
        }
    }
//		val interpolationsPerSplineSegment: Int = InterpolationsPerSplineSegment || 25
    val interPerSegment = InterpolationsPerSplineSegment ?: 1
    val interpolationsPerSplineSegment: Int = interPerSegment.or( 25 )
    for ( i in 1 until segmentTs.size ) {
        val uMin = segmentTs[i - 1]
        val uMax = segmentTs[i]
        for ( k in 0..interpolationsPerSplineSegment) {
            val u = k.toFloat() / interpolationsPerSplineSegment * (uMax - uMin) + uMin
            // Clamp t to 0, 1 to handle numerical precision issues
            var t = (u - domain0) / (domain1 - domain0)
            t = max(t, 0f)
            t = min(t, 1f)
//				val p = bSpline(t, degree, controlPointsForLib, knots, weights)
            val p = bSpline(t, degree, controlPoints, knots, weights)
            polyline.add( p )    //push(new THREE.Vector2(p[0], p[1]));
        }
    }
    return polyline
}

//////////////////////////// INSERT /////////////////////////

fun Vector3f.scaleXYZ( scaleX: Float?, scaleY: Float?, scaleZ: Float? ){
    this.x = this.x * (scaleX ?: 1f)
    this.y = this.y * (scaleY ?: 1f)
    this.z = this.z * (scaleZ ?: 1f)
}

fun rotate2Dpoint( angle: Float, interpolatePoints: MutableList<List<Vector3f>>) {
    val s = sin(angle)
    val c = cos(angle)

    // rotate point
//    val xnew: Float = p.x * c - p.y * s
//    val ynew: Float = p.x * s + p.y * c
    for( list in interpolatePoints) {
        for (points in list) {
            val px = points.x;
            val py = points.y;

            points.x = c * px - s * py
            points.y = s * px + c * py
        }
    }
}

fun rotate2Dpoint(pivot: Vector3f, angle: Float,  interpolatePoints: MutableList<List<Vector3f>>) {
    val s = sin(angle * PI / 180f)
    val c = cos(angle * PI / 180f)
    for( list in interpolatePoints) {
        for (points in list) {
            // translate point back to origin:
            points.x -= pivot.x
            points.y -= pivot.y

            // rotate point
            val xnew: Float = points.x * c - points.y * s
            val ynew: Float = points.x * s + points.y * c

            // translate point back:
            points.x = xnew + pivot.x
            points.y = ynew + pivot.y
        }
    }
}
//bad working
fun rotate(pitch: Float?, roll: Float?, yaw: Float?, interpolatePoints: MutableList<List<Vector3f>>) {
    val cosa = if( yaw!=null) cos(yaw) else 1f
    val sina = if( yaw!=null) sin(yaw) else 1f

    val cosb = if( pitch!=null) cos(pitch) else 1f
    val sinb = if( pitch!=null) sin(pitch) else 1f

    val cosc = if( roll!=null) cos(roll) else 1f
    val sinc = if( roll!=null) sin(roll) else 1f

    val Axx = cosa*cosb;
    val Axy = cosa*sinb*sinc - sina*cosc;
    val Axz = cosa*sinb*cosc + sina*sinc;

    val Ayx = sina*cosb;
    val Ayy = sina*sinb*sinc + cosa*cosc;
    val Ayz = sina*sinb*cosc - cosa*sinc;

    val Azx = -sinb;
    val Azy = cosb*sinc;
    val Azz = cosb*cosc;

    for( list in interpolatePoints) {
        for (points in list) {
            val px = points.x;
            val py = points.y;
            val pz = points.z;

            points.x = Axx * px + Axy * py + Axz * pz;
            points.y = Ayx * px + Ayy * py + Ayz * pz;
            points.z = Azx * px + Azy * py + Azz * pz;
        }
    }
}
//var InsertionPoint: Vector3f? = null
//var XScale: Float? = null
//var YScale: Float? = null
//var ZScale: Float? = null
//var RotationAngle: Float? = null
//var ColumnCount: Int? = null
//var RowCount: Int? = null
//var ColumnSpacing: Float? = null
//var RowSpacing: Float? = null
//var ExtrusionDirection: Vector3f? = null


//export default (parseResult) => {
//    const blocksByName = parseResult.blocks.reduce((acc, b) => {
//        acc[b.name] = b
//        return acc
//    }, {})
//
    fun  interpolateInsert(  InsertionPoint: Vector3f?,
                             XScale: Float?, YScale: Float?, ZScale: Float?,
                             RotationAngle: Float?, ColumnCount: Int?, RowCount: Int?,
                             ColumnSpacing: Float?, RowSpacing: Float?, ExtrusionDirection: Vector3f?) : MutableList<Vector3f> {
//        let current = []
//        entities.forEach((e) => {
//            if (e.type === 'INSERT') {
//                const insert = e
//                        const block = blocksByName[insert.block]
//                if (!block) {
//                    logger.error('no block found for insert. block:', insert.block)
//                    return
//                }

                val rowCount = RowCount ?: 1
                val columnCount = ColumnCount ?: 1
                val rowSpacing = RowSpacing ?: 0
                val columnSpacing = ColumnSpacing ?: 0
                val rotation = RotationAngle ?: 0f

                // It appears that the rectangular array is affected by rotation, but NOT by scale.
                var rowVec: Vector3f? = null
                var colVec: Vector3f? = null
                if (rowCount > 1 || columnCount > 1) {
                    val cos = cos(rotation * PI / 180f)
                    val sin = sin(rotation * PI / 180f)
                    rowVec = Vector3f( -sin * rowSpacing.toFloat(),  cos * rowSpacing.toFloat() , 0f)
                    colVec = Vector3f( cos * columnSpacing.toFloat(), sin * columnSpacing.toFloat() ,0f )
                } else {
                    rowVec = Vector3f(0f,0f,0f)
                    colVec = Vector3f(0f,0f,0f)
                }

                val t: MutableList<Vector3f> = mutableListOf()
                // For rectangular arrays, add the block entities for each location in the array
                for ( r  in 0 until  rowCount) {
                    for ( c in 0 until columnCount) {
                    // Adjust insert transform by row and column for rectangular arrays
                    t.add(  Vector3f(InsertionPoint!!.x + rowVec.x * r + colVec.x * c,
                        InsertionPoint.y + rowVec.y * r + colVec.y * c, InsertionPoint.z) )
//                        scaleX: insert.scaleX,
//                        scaleY: insert.scaleY,
//                        scaleZ: insert.scaleZ,
//                        extrusionX: insert.extrusionX,
//                        extrusionY: insert.extrusionY,
//                        extrusionZ: insert.extrusionZ,
//                        rotation: insert.rotation
                    } }
    return t }
                    // Add the insert transform and recursively add entities
//                    val transforms2 = transforms.slice(0)
//                    transforms2.push(t)

                    // Use the insert layer
//                    val blockEntities = block.entities.map((be) => {
//                    val be2 = cloneDeep(be)
//                    be2.layer = insert.layer
                    // https://github.com/bjnortier/dxf/issues/52
                    // See Issue 52. If we don't modify the
                    // entity coordinates here it creates an issue with the
                    // transformation matrices (which are only applied AFTER
                    // block insertion modifications has been applied).
//                    switch (be2.type) {
//                        case 'LINE': {
//                        be2.start.x -= block.x
//                        be2.start.y -= block.y
//                        be2.end.x -= block.x
//                        be2.end.y -= block.y
//                        break
//                    }
//                        case 'LWPOLYLINE':
//                        case 'POLYLINE': {
//                        be2.vertices.forEach(v => {
//                            v.x -= block.x
//                            v.y -= block.y
//                        })
//                        break
//                    }
//                        case 'CIRCLE':
//                        case 'ELLIPSE':
//                        case 'ARC': {
//                        be2.x -= block.x
//                        be2.y -= block.y
//                        break
//                    }
//                        case 'SPLINE': {
//                        be2.controlPoints.forEach(cp => {
//                            cp.x -= block.x
//                            cp.y -= block.y
//                        })
//                        break
//                    }
//                    }
//                    return be2
//                })
//                    current = current.concat(gatherEntities(blockEntities, transforms2))
//                }
//                }
//            } else {
//                // Top-level entity. Clone and add the transforms
//                // The transforms are reversed so they occur in
//                // order of application - i.e. the transform of the
//                // top-level insert is applied last
//                val e2 = cloneDeep(e)
//                e2.transforms = transforms.slice().reverse()
//                current.push(e2)
//            }
//        })
//        return current
//    }
//
//    return gatherEntities(parseResult.entities, [])
//}

