package zibi.robotx.autocad.common.collision.old

import zibi.robotx.autocad.common.collision.old.Math3DUtils.substract
import zibi.robotx.autocad.common.collision.old.Math3DUtils.normalize
import zibi.robotx.autocad.common.collision.old.Math3DUtils.divide
import zibi.robotx.autocad.common.collision.old.Math3DUtils.min
import zibi.robotx.autocad.common.collision.old.Math3DUtils.max
import zibi.robotx.autocad.common.collision.old.Math3DUtils.add
import zibi.robotx.autocad.common.collision.old.Math3DUtils.multiply
import zibi.robotx.autocad.common.collision.old.Math3DUtils.crossProduct
import zibi.robotx.autocad.common.collision.old.Math3DUtils.dotProduct
import android.opengl.GLU
import android.util.Log
import zibi.robotx.autocad.common.collision.old.BoundingBox
import java.util.*

/**
 * Class that encapsulates all the logic for the collision detection algorithm.
 *
 * @author andresoviedo
 */
object CollisionDetection {
    /**
     * Get the nearest object intersected by the specified window coordinates
     *
     * @param objects               the list of objects to test
     * @param height                viewport height
     * @param width                 viewport width
     * @param modelViewMatrix       model view matrix
     * @param modelProjectionMatrix model projection matrix
     * @param windowX               the window x coordinate
     * @param windowY               the window y coordinate
     * @return the nearest object intersected by the specified coordinates or null
     */
    fun getBoxIntersection(
        objects: List<Object3DData>, width: Int, height: Int, modelViewMatrix: FloatArray?,
        modelProjectionMatrix: FloatArray?, windowX: Float, windowY: Float): Object3DData? {

        val nearHit = unProject(width, height, modelViewMatrix, modelProjectionMatrix, windowX, windowY, 0f)
        val farHit = unProject(width, height, modelViewMatrix, modelProjectionMatrix, windowX, windowY, 1f)
        val direction = substract(farHit, nearHit)
        normalize(direction)
        return getBoxIntersection(objects, nearHit, direction)
    }

    /**
     * Get the nearest object intersected by the specified ray or null if no object is intersected
     *
     * @param objects   the list of objects to test
     * @param p1        the ray start point
     * @param direction the ray direction
     * @return the object intersected by the specified ray
     */
    private fun getBoxIntersection(objects: List<Object3DData>, p1: FloatArray, direction: FloatArray): Object3DData? {
        var min = Float.MAX_VALUE
        var ret: Object3DData? = null
        for (obj in objects) {
//            if ("Point" == obj.id || "Line" == obj.id) {
//                continue
//            }
            val box = obj.boundingBox
            val intersection = getBoxIntersection(p1, direction, box!!)
            if (intersection[0] > 0 && intersection[0] <= intersection[1] && intersection[0] < min) {
                min = intersection[0]
                ret = obj
            }
        }
        //        if (ret != null) {
//            Log.i("CollisionDetection", "Collision detected '" + ret.getId() + "' distance: " + min + ", dimensions: " + ret.getCurrentDimensions());
//        }
        return ret
    }
    /*
     * Get the entry and exit point of the ray intersecting the nearest object or null if no object is intersected
     *
     * @param objects list of objects to test
     * @param p1      ray start point
     * @param p2      ray end point
     * @return the entry and exit point of the ray intersecting the nearest object
     */
    /*public static float[] getBoxIntersectionPoint(List<Object3DData> objects, float[] p1, float[] p2) {
        float[] direction = Math3DUtils.substract(p2, p1);
        Math3DUtils.normalize(direction);
        float min = Float.MAX_VALUE;
        float[] intersection2 = null;
        Object3DData ret = null;
        for (Object3DData obj : objects) {
            BoundingBoxBuilder box = obj.getBoundingBox();
            float[] intersection = getBoxIntersection(p1, direction, box);
            if (intersection[0] > 0 && intersection[0] <= intersection[1] && intersection[0] < min) {
                min = intersection[0];
                ret = obj;
                intersection2 = intersection;
            }
        }
        if (ret != null) {
            Log.i("CollisionDetection", "Collision detected '" + ret.getId() + "' distance: " + min);
            return new float[]{p1[0] + direction[0] * min, p1[1] + direction[1] * min, p1[2] + direction[2] * min};
        }
        return null;
    }*/
    /**
     * Return true if the specified ray intersects the bounding box
     *
     * @param origin origin of the ray
     * @param dir    direction of the ray
     * @param b      bounding box
     * @return true if the specified ray intersects the bounding box, false otherwise
     */
    private fun isBoxIntersection(origin: FloatArray, dir: FloatArray, b: BoundingBox): Boolean {
        val intersection = getBoxIntersection(origin, dir, b)
        return intersection[0] > 0 && intersection[0] < intersection[1]
    }

    /**
     * Get the intersection points of the near and far plane for the specified ray and bounding box
     *
     * @param origin the ray origin
     * @param dir    the ray direction
     * @param b      the bounding box
     * @return the intersection points of the near and far plane
     */
    fun getBoxIntersection(origin: FloatArray?, dir: FloatArray?, b: BoundingBox): FloatArray {
        val tMin = divide(substract(b.min, origin!!), dir!!)
        val tMax = divide(substract(b.max, origin), dir)
        val t1 = min(tMin, tMax)
        val t2 = max(tMin, tMax)
        val tNear = Math.max(Math.max(t1[0], t1[1]), t1[2])
        val tFar = Math.min(Math.min(t2[0], t2[1]), t2[2])
        return floatArrayOf(tNear, tFar)
    }

//    fun getBoxIntersection(origin: FloatArray?, dir: FloatArray?, b: BoundingBox): FloatArray {
//        val tMin = divide(substract(b.min, origin!!), dir!!)
//        val tMax = divide(substract(b.max, origin), dir)
//        val t1 = min(tMin, tMax)
//        val t2 = max(tMin, tMax)
//        val tNear = Math.max(Math.max(t1[0], t1[1]), t1[2])
//        val tFar = Math.min(Math.min(t2[0], t2[1]), t2[2])
//        return floatArrayOf(tNear, tFar)
//    }

    /**
     * Map window coordinates to object coordinates.
     *
     * @param height                viewport height
     * @param width                 viewport width
     * @param modelViewMatrix       model view matrix
     * @param modelProjectionMatrix model projection matrix
     * @param rx                    x point
     * @param ry                    y point
     * @param rz                    z point
     * @return the corresponding near and far vertex for the specified window coordinates
     */
    fun unProject(
        width: Int, height: Int, modelViewMatrix: FloatArray?, modelProjectionMatrix: FloatArray?,
        rx: Float, ry: Float, rz: Float
    ): FloatArray {
        var ry = ry
        val xyzw = floatArrayOf(0f, 0f, 0f, 0f)
        ry = height.toFloat() - ry
        val viewport = intArrayOf(0, 0, width, height)
        GLU.gluUnProject(
            rx, ry, rz, modelViewMatrix, 0, modelProjectionMatrix, 0,
            viewport, 0, xyzw, 0
        )
        xyzw[0] /= xyzw[3]
        xyzw[1] /= xyzw[3]
        xyzw[2] /= xyzw[3]
        xyzw[3] = 1f
        return xyzw
    }

    /*public static float[] getTriangleIntersection(List<Object3DData> objects, ModelRenderer mRenderer, float
            windowX, float windowY) {
        float[] nearHit = unProject(mRenderer, windowX, windowY, 0);
        float[] farHit = unProject(mRenderer, windowX, windowY, 1);
        float[] direction = Math3DUtils.substract(farHit, nearHit);
        Math3DUtils.normalize(direction);
        Object3DData intersected = getBoxIntersection(objects, nearHit, direction);
        if (intersected != null) {
            Log.d("CollisionDetection", "intersected:" + intersected.getId() + ", rayOrigin:" + Arrays.toString(nearHit) + ", rayVector:" + Arrays.toString(direction));
            FloatBuffer buffer = intersected.getVertexArrayBuffer().asReadOnlyBuffer();
            float[] modelMatrix = intersected.getModelMatrix();
            buffer.position(0);
            float[] selectedv1 = null;
            float[] selectedv2 = null;
            float[] selectedv3 = null;
            float min = Float.MAX_VALUE;
            for (int i = 0; i < buffer.capacity(); i += 9) {
                float[] v1 = new float[]{buffer.get(), buffer.get(), buffer.get(), 1};
                float[] v2 = new float[]{buffer.get(), buffer.get(), buffer.get(), 1};
                float[] v3 = new float[]{buffer.get(), buffer.get(), buffer.get(), 1};
                Matrix.multiplyMV(v1, 0, modelMatrix, 0, v1, 0);
                Matrix.multiplyMV(v2, 0, modelMatrix, 0, v2, 0);
                Matrix.multiplyMV(v3, 0, modelMatrix, 0, v3, 0);
                float t = getTriangleIntersection(nearHit, direction, v1, v2, v3);
                if (t != -1 && t < min) {
                    min = t;
                    selectedv1 = v1;
                    selectedv2 = v2;
                    selectedv3 = v3;
                }
            }
            if (selectedv1 != null) {
                float[] outIntersectionPoint = Math3DUtils.add(nearHit, Math3DUtils.multiply(direction, min));
                return outIntersectionPoint;
            }
        }
        return null;
    }*/
    fun getTriangleIntersection(
        objects: List<Object3DData>,
        width: Int,
        height: Int,
        modelViewMatrix: FloatArray?,
        modelProjectionMatrix: FloatArray?,
        windowX: Float,
        windowY: Float
    ): FloatArray? {
        val nearHit =
            unProject(width, height, modelViewMatrix, modelProjectionMatrix, windowX, windowY, 0f)
        val farHit =
            unProject(width, height, modelViewMatrix, modelProjectionMatrix, windowX, windowY, 1f)
        val direction = substract(farHit, nearHit)
        normalize(direction)
        val intersected = getBoxIntersection(objects, nearHit, direction)
        return if (intersected != null) {
            getTriangleIntersection(intersected, nearHit, direction)
        } else null
    }

    fun getTriangleIntersection(
        hit: Object3DData,
        width: Int,
        height: Int,
        viewMatrix: FloatArray?,
        projectionMatrix: FloatArray?,
        windowX: Float,
        windowY: Float
    ): FloatArray? {
        val nearHit = unProject(width, height, viewMatrix, projectionMatrix, windowX, windowY, 0f)
        val farHit = unProject(width, height, viewMatrix, projectionMatrix, windowX, windowY, 1f)
        val direction = substract(farHit, nearHit)
        normalize(direction)
        return getTriangleIntersection(hit, nearHit, direction)
    }

    private fun getTriangleIntersection(
        hit: Object3DData,
        nearHit: FloatArray,
        direction: FloatArray
    ): FloatArray? {
        Log.d("CollisionDetection", "Getting triangle intersection: " + hit.id)
        var octree: Octree?
        synchronized(hit) {
            octree = hit.octree
            if (octree == null) {
                octree = Octree.build(hit)
                hit.octree = octree
            }
        }
        val intersection = getTriangleIntersectionForOctree(octree, nearHit, direction)
        return if (intersection != -1f) {
            val intersectionPoint =
                add(nearHit, multiply(direction, intersection))
            Log.d(
                "CollisionDetection",
                "Interaction point: " + Arrays.toString(intersectionPoint)
            )
            intersectionPoint
        } else {
            null
        }
    }

    private fun getTriangleIntersectionForOctree(
        octree: Octree?,
        rayOrigin: FloatArray,
        rayDirection: FloatArray
    ): Float {
        //Log.v("CollisionDetection","Testing octree "+octree);
        if (!isBoxIntersection(rayOrigin, rayDirection, octree!!.boundingBox!!)) {
            Log.d("CollisionDetection", "No octree intersection")
            return (-1).toFloat()
        }
        var selected: Octree? = null
        var min = Float.MAX_VALUE
        for (child in octree.children) {
            if (child == null) {
                continue
            }
            val intersection = getTriangleIntersectionForOctree(child, rayOrigin, rayDirection)
            if (intersection != -1f && intersection < min) {
                Log.d("CollisionDetection", "Octree intersection: $intersection")
                min = intersection
                selected = child
            }
        }
        var selectedTriangle: FloatArray? = null
        for (triangle in octree.triangles) {
            val vertex0 = floatArrayOf(triangle[0], triangle[1], triangle[2])
            val vertex1 = floatArrayOf(triangle[4], triangle[5], triangle[6])
            val vertex2 = floatArrayOf(triangle[8], triangle[9], triangle[10])
            val intersection =
                getTriangleIntersection(rayOrigin, rayDirection, vertex0, vertex1, vertex2)
            if (intersection != -1f && intersection < min) {
                min = intersection
                selectedTriangle = triangle
                selected = octree
            }
        }
        if (min != Float.MAX_VALUE) {
            Log.d("CollisionDetection", "Intersection at distance: $min")
            Log.d(
                "CollisionDetection",
                "Intersection at triangle: " + Arrays.toString(selectedTriangle)
            )
            Log.d("CollisionDetection", "Intersection at octree: $selected")
            return min
        }
        return (-1).toFloat()
    }

    private fun getTriangleIntersection(
        rayOrigin: FloatArray,
        rayVector: FloatArray,
        vertex0: FloatArray, vertex1: FloatArray, vertex2: FloatArray
    ): Float {
        val EPSILON = 0.0000001f
        val edge1: FloatArray
        val edge2: FloatArray
        val h: FloatArray
        val s: FloatArray
        val q: FloatArray
        val a: Float
        val f: Float
        val u: Float
        val v: Float
        edge1 = substract(vertex1, vertex0)
        edge2 = substract(vertex2, vertex0)
        h = crossProduct(rayVector, edge2)
        a = dotProduct(edge1, h)
        if (a > -EPSILON && a < EPSILON) return (-1).toFloat()
        f = 1 / a
        s = substract(rayOrigin, vertex0)
        u = f * dotProduct(s, h)
        if (u < 0.0 || u > 1.0) return (-1).toFloat()
        q = crossProduct(s, edge1)
        v = f * dotProduct(rayVector, q)
        if (v < 0.0 || u + v > 1.0) return (-1).toFloat()
        // At this stage we can compute t to find out where the intersection point is on the line.
        val t = f * dotProduct(edge2, q)
        return if (t > EPSILON) // ray intersection
        {
            Log.d("CollisionDetection", "Triangle intersection at: $t")
            t
        } else  // This means that there is a line intersection but not a ray intersection.
            (-1).toFloat()
    }
}