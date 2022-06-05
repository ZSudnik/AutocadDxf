package zibi.robotx.autocad.android.modelviewer.util

import android.opengl.Matrix
import java.lang.StringBuilder
import javax.vecmath.Vector3f
import kotlin.math.abs
import kotlin.math.acos


//fun pointInTriangle(p: Vector3f, tp0: Vector3f, tp1: Vector3f, tp2: Vector3f): bool {
//    // Lets define some local variables, we can change these
//    // without affecting the references passed in
//    var a: Vector3f = tp0
//    var b: Vector3f = tp1
//    var c: Vector3f = tp2
//
//    // Move the triangle so that the point becomes the
//    // triangles origin
//    a -= p
//    b -= p
//    c -= p
//
//    // The point should be moved too, so they are both
//    // relative, but because we don't use p in the
//    // equation anymore, we don't need it!
//    // p -= p;
//
//    // Compute the normal vectors for triangles:
//    // u = normal of PBC
//    // v = normal of PCA
//    // w = normal of PAB
//    val u: Vector3f = Cross(b, c)
//    val v: Vector3f = Cross(c, a)
//    val w: Vector3f = Cross(a, b)
//
//    // Test to see if the normals are facing
//    // the same direction, return false if not
//    if (Dot(u, v) < 0f) {
//        return false
//    }
//    return if (dot(u, w) < 0.0f) {
//        false
//    } else true
//
//    // All normals facing the same way, return true
//}
/////////////////////////////////////////////////////



fun checkPointInTriangle_2(P: Vector3f, A: Vector3f, B: Vector3f, C: Vector3f): Boolean {

// Compute vectors
    val v0: Vector3f = Vector3f()
    v0.sub(C , A)
    val v1: Vector3f = Vector3f()
    v1.sub(B , A)
    val v2: Vector3f = Vector3f()
    v2.sub(P , A)

// Compute dot products
    val dot00 = dot(v0, v0)
    val dot01 = dot(v0, v1)
    val dot02 = dot(v0, v2)
    val dot11 = dot(v1, v1)
    val dot12 = dot(v1, v2)

// Compute barycentric coordinates
    val invDenom = 1 / (dot00 * dot11 - dot01 * dot01)
    val u = (dot11 * dot02 - dot01 * dot12) * invDenom
    val v = (dot00 * dot12 - dot01 * dot02) * invDenom

// Check if point is in triangle
    return (u >= 0) && (v >= 0) && (u + v < 1)
}

fun dot(v0: Vector3f, v1: Vector3f): Float {
    return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z
}

///////////////////////////////////////////////////

fun checkPointInTriangle(point: Vector3f, p1: Vector3f, p2: Vector3f, p3: Vector3f): Boolean {
    var angles = 0f
    val v1: Vector3f = vecMin(point, p1)
    v1.normalize()
    val v2: Vector3f = vecMin(point, p2)
    v2.normalize()
    val v3: Vector3f = vecMin(point, p3)
    v3.normalize()
    angles += acos(dot(v1, v2))
    angles += acos(dot(v2, v3))
    angles += acos(dot(v3, v1))
    return abs(angles - 2 * Math.PI) <= 0.005
}

fun vecMin(one: Vector3f, two: Vector3f): Vector3f {
    return Vector3f(one.x - two.x, one.y - two.y, one.z - two.z)
}

fun getCameraPos(modelView: FloatArray?): FloatArray {
    val modelviewInverse = FloatArray(16)
    Matrix.invertM(modelviewInverse, 0, modelView, 0)
    val cameraPos = FloatArray(4)
    cameraPos[0] = modelviewInverse[12]
    cameraPos[1] = modelviewInverse[13]
    cameraPos[2] = modelviewInverse[14]
    cameraPos[3] = modelviewInverse[15]
    return cameraPos
}

fun floatArrayAsString(array: FloatArray): String {
    val sb = StringBuilder()
    sb.append("[")
    for (f in array) {
        sb.append("$f, ")
    }
    sb.deleteCharAt(sb.length - 1)
    sb.deleteCharAt(sb.length - 1)
    sb.append("]")
    return sb.toString()
}

fun floatArrayAsString(vec3f: Vector3f): String {
    val sb = StringBuilder()
    sb.append("[")
    sb.append("${vec3f.x}, ")
    sb.append("${vec3f.y}, ")
    sb.append("${vec3f.z}, ")
    sb.deleteCharAt(sb.length - 1)
    sb.deleteCharAt(sb.length - 1)
    sb.append("]")
    return sb.toString()
}

fun getInverseMatrix(originalMatrix: FloatArray?): FloatArray {
    val inverseMatrix = FloatArray(16)
    Matrix.invertM(inverseMatrix, 0, originalMatrix, 0)
    return inverseMatrix
}

///////////////// ver 1

fun getMouseRayProjection(touchX: Float, touchY: Float, windowWidth: Int, windowHeight: Int,
                          view: FloatArray?, projection: FloatArray?): Vector3f {

    val normalizedX = 2f * touchX / windowWidth - 1f
    val normalizedY = 1f - 2f * touchY / windowHeight
    val normalizedZ = 1.0f
    val rayNDC = floatArrayOf(normalizedX, normalizedY, normalizedZ)
    var rayClip = floatArrayOf(rayNDC[0], rayNDC[1], -1f, 1f)
    val inverseProjection = FloatArray(16)
    Matrix.invertM(inverseProjection, 0, projection, 0)
    val rayEye = multiplyMat4ByVec4(inverseProjection, rayClip)
    rayClip = floatArrayOf(rayClip[0], rayClip[1], -1f, 0f)
    val inverseView = FloatArray(16)
    Matrix.invertM(inverseView, 0, view, 0)
    val rayWorld4D = multiplyMat4ByVec4(inverseView, rayEye)
    val rayWorld = Vector3f(rayWorld4D[0], rayWorld4D[1], rayWorld4D[2])
    rayWorld.normalize()
    val rayDirection = rayWorld
    return rayDirection
}

fun multiplyMat4ByVec4(matrix4: FloatArray, vector4: FloatArray): FloatArray {
    val returnMatrix = FloatArray(4)
    returnMatrix[0] = matrix4[0] * vector4[0] + matrix4[1] * vector4[1] + matrix4[2] * vector4[2] + matrix4[3] * vector4[3]
    returnMatrix[1] = matrix4[4] * vector4[0] + matrix4[5] * vector4[1] + matrix4[6] * vector4[2] + matrix4[7] * vector4[3]
    returnMatrix[2] = matrix4[8] * vector4[0] + matrix4[9] * vector4[1] + matrix4[10] * vector4[2] + matrix4[11] * vector4[3]
    returnMatrix[3] = matrix4[12] * vector4[0] + matrix4[13] * vector4[1] + matrix4[14] * vector4[2] + matrix4[15] * vector4[3]
    return returnMatrix
}


/////////ver 2
fun  getMouseRayProjection_2( touchX: Float, touchY: Float, windowWidth: Int, windowHeight: Int, mViewMatrix: FloatArray,  mProjectionMatrix: FloatArray): Vector3f {
//    fun  getMouseRayProjection_2( touchX: Float, touchY: Float, windowWidth: Int, windowHeight: Int, mViewMatrix: FloatArray,  mProjectionMatrix: FloatArray): Vector3f {
    val rayDirection = FloatArray(4)

    val normalizedX: Float = 2f * touchX/windowWidth-1f;
    val normalizedY: Float = 1f-2f*touchY/windowHeight;
    val normalizedZ: Float = 1.0f;

    val rayClip1 = floatArrayOf( normalizedX, normalizedY, -1f, 1f )
    val rayClip2 = floatArrayOf( normalizedX, normalizedY, 1f, 1f )

    val mVPMatrix = FloatArray(16)
    val invertVPMatrix = FloatArray(16)

    Matrix.multiplyMM(mVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    Matrix.invertM(invertVPMatrix, 0, mVPMatrix, 0);


    val rayWorld1 = FloatArray(4)
    Matrix.multiplyMV(rayWorld1, 0, invertVPMatrix, 0, rayClip1, 0);

    val rayWorld2 = FloatArray(4)
    Matrix.multiplyMV(rayWorld2, 0, invertVPMatrix, 0, rayClip2, 0);


    if (rayWorld1[3] != 0f && rayWorld2[3] != 0f) {
        rayWorld1[0] = rayWorld1[0] / rayWorld1[3]
        rayWorld1[1] = rayWorld1[1] / rayWorld1[3]
        rayWorld1[2] = rayWorld1[2] / rayWorld1[3]
        rayWorld1[3] = 1f
        rayWorld2[0] = rayWorld2[0] / rayWorld2[3]
        rayWorld2[1] = rayWorld2[1] / rayWorld2[3]
        rayWorld2[2] = rayWorld2[2] / rayWorld2[3]
        rayWorld2[3] = 1f
    }

    return Vector3f( rayWorld1[0], rayWorld1[1], rayWorld1[2])
}

fun crossProduct(vec1: Vector3f, vec2: Vector3f): Vector3f {
    return Vector3f(
        vec1.y * vec2.z - vec1.z * vec2.y,
        vec1.z * vec2.x - vec1.x * vec2.z,
        vec1.x * vec2.y - vec1.y * vec2.x
    )
}

fun vectorBetween(from: Vector3f, to: Vector3f): Vector3f {
    return Vector3f(
        to.x - from.x,
        to.y - from.y,
        to.z - from.z
    )
}

fun sub(vec1: Vector3f, vec2: Vector3f): Vector3f {
    return Vector3f(vec1.x - vec2.x, vec1.y - vec2.y, vec1.z - vec2.z)
}


fun checkPointInTriangle_3(point: Vector3f, pa: Vector3f, pb: Vector3f, pc: Vector3f):Boolean {
//    pb.sub(pa)
    val e10: Vector3f= sub( pb , pa)
    val e20: Vector3f= sub( pc, pa)

    val a = e10.dot(e10);
    val b = e10.dot(e20);
    val c = e20.dot(e20);
    val ac_bb=(a*c)-(b*b);
    val vp: Vector3f = Vector3f(point.x-pa.x, point.y-pa.y, point.z-pa.z);

    val d = vp.dot(e10);
    val e = vp.dot(e20);
    val x = (d*c)-(e*b);
    val y = (e*a)-(d*b);
    val z = x+y-ac_bb;
//    return (( in(z)& ~(in(x)|in(y)) ) & 0x80000000);
    return z < 0 && x >= 0 && y >= 0;
}