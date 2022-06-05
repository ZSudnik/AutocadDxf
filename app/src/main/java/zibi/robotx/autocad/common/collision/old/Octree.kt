package zibi.robotx.autocad.common.collision.old

import android.opengl.Matrix
import android.util.Log
import zibi.robotx.autocad.common.collision.old.Math3DUtils.divide
import zibi.robotx.autocad.common.collision.old.Math3DUtils.add
import java.util.ArrayList
/**
 * Created by Andres on 21/12/2017.
 */
class Octree private constructor(val boundingBox: BoundingBox?) {
    private val pending: MutableList<FloatArray> = ArrayList()
    val triangles: MutableList<FloatArray> = ArrayList()
    val children = arrayOfNulls<Octree>(8)
    private fun addChild(octant: Int, boundingBox: BoundingBox?, triangle: FloatArray) {
        if (children[octant] == null) {
            children[octant] = Octree(boundingBox)
        }
        children[octant]!!.pending.add(triangle)
    }

//    fun getTriangles(): List<FloatArray> {
//        return triangles
//    }

    private fun subdivide() {
        Log.v("Octree", "Subdividing octree...")
        for (child in children) {
            if (child != null) {
                subdivide(child)
            }
        }
    }

    companion object {
        // The minimum size of the 3D space for individual boxes
        // if the model is 100 size, then 10 for boxes is OK
        const val BOX_SIZE = 10.0
        fun build(object3DData: Object3DData): Octree {
//        Log.i("Octree", "Building octree for "+object.getId());
            val ret = Octree(object3DData.boundingBox)
            if (object3DData.drawOrder == null) {
                // vertex array contains vertex in sequence
                val buffer = object3DData.vertexBuffer!!.asReadOnlyBuffer()
                val triangles: MutableList<FloatArray> = ArrayList(buffer.capacity() / 3 * 4)
                val modelMatrix = object3DData.newModelMatrix
                buffer.position(0)
                for (n in object3DData.triangleIndexPoint!!.indices) {
                    val trian = object3DData.triangleIndexPoint!![n]
                    var i = 0
                    while (i < trian.size / 3) {
                        val A = 3 * trian[i]
                        val B = 3 * trian[i + 1]
                        val C = 3 * trian[i + 2]
                        val triangle = floatArrayOf(
                            buffer[A], buffer[A + 1], buffer[A + 2], 1f,
                            buffer[B], buffer[B + 1], buffer[B + 2], 1f,
                            buffer[C], buffer[C + 1], buffer[C + 2], 1f
                        )
                        Matrix.multiplyMV(triangle, 0, modelMatrix, 0, triangle, 0)
                        Matrix.multiplyMV(triangle, 4, modelMatrix, 0, triangle, 4)
                        Matrix.multiplyMV(triangle, 8, modelMatrix, 0, triangle, 8)
                        triangles.add(triangle)
                        i += 3
                    }
                }
                ret.pending.addAll(triangles)
            } else {
                // faces are built
                val drawOrder = object3DData.drawOrder!!.asReadOnlyBuffer()
                val buffer = object3DData.vertexBuffer!!.asReadOnlyBuffer()
                val triangles: MutableList<FloatArray> = ArrayList(drawOrder.capacity() / 3 * 4)
                val modelMatrix = object3DData.newModelMatrix
                var i = 0
                while (i < drawOrder.capacity()) {
                    val triangle = floatArrayOf(
                        buffer[drawOrder[i]],
                        buffer[drawOrder[i] + 1],
                        buffer[drawOrder[i] + 2],
                        1f,
                        buffer[drawOrder[i + 1]],
                        buffer[drawOrder[i + 1] + 1],
                        buffer[drawOrder[i + 1] + 2],
                        1f,
                        buffer[drawOrder[i + 2]],
                        buffer[drawOrder[i + 2] + 1],
                        buffer[drawOrder[i + 2] + 2],
                        1f
                    )
                    Matrix.multiplyMV(triangle, 0, modelMatrix, 0, triangle, 0)
                    Matrix.multiplyMV(triangle, 4, modelMatrix, 0, triangle, 4)
                    Matrix.multiplyMV(triangle, 8, modelMatrix, 0, triangle, 8)
                    triangles.add(triangle)
                    i += 3
                }
                ret.pending.addAll(triangles)
            }
            subdivide(ret)
            return ret
        }

        fun build_Orginal(`object`: Object3DData): Octree {
//        Log.i("Octree", "Building octree for "+object.getId());
            val ret = Octree(`object`.boundingBox)
            if (`object`.drawOrder == null) {
                // vertex array contains vertex in sequence
                val buffer = `object`.vertexBuffer!!.asReadOnlyBuffer()
                val triangles: MutableList<FloatArray> = ArrayList(buffer.capacity() / 3 * 4)
                val modelMatrix = `object`.newModelMatrix
                buffer.position(0)
                var i = 0
                while (i < buffer.capacity()) {
                    val triangle = floatArrayOf(
                        buffer.get(), buffer.get(), buffer.get(), 1f,
                        buffer.get(), buffer.get(), buffer.get(), 1f,
                        buffer.get(), buffer.get(), buffer.get(), 1f
                    )
                    Matrix.multiplyMV(triangle, 0, modelMatrix, 0, triangle, 0)
                    Matrix.multiplyMV(triangle, 4, modelMatrix, 0, triangle, 4)
                    Matrix.multiplyMV(triangle, 8, modelMatrix, 0, triangle, 8)
                    triangles.add(triangle)
                    i += 9
                }
                ret.pending.addAll(triangles)
            } else {
                // faces are built
                val drawOrder = `object`.drawOrder!!.asReadOnlyBuffer()
                val buffer = `object`.vertexBuffer!!.asReadOnlyBuffer()
                val triangles: MutableList<FloatArray> = ArrayList(drawOrder.capacity() / 3 * 4)
                val modelMatrix = `object`.newModelMatrix
                var i = 0
                while (i < drawOrder.capacity()) {
                    val triangle = floatArrayOf(
                        buffer[drawOrder[i]],
                        buffer[drawOrder[i] + 1],
                        buffer[drawOrder[i] + 2],
                        1f,
                        buffer[drawOrder[i + 1]],
                        buffer[drawOrder[i + 1] + 1],
                        buffer[drawOrder[i + 1] + 2],
                        1f,
                        buffer[drawOrder[i + 2]],
                        buffer[drawOrder[i + 2] + 1],
                        buffer[drawOrder[i + 2] + 2],
                        1f
                    )
                    Matrix.multiplyMV(triangle, 0, modelMatrix, 0, triangle, 0)
                    Matrix.multiplyMV(triangle, 4, modelMatrix, 0, triangle, 4)
                    Matrix.multiplyMV(triangle, 8, modelMatrix, 0, triangle, 8)
                    triangles.add(triangle)
                    i += 3
                }
                ret.pending.addAll(triangles)
            }
            subdivide(ret)
            return ret
        }

        private fun subdivide(octree: Octree) {
            Log.d(
                "Octree",
                "Subdividing octree (" + octree.boundingBox + "): " + octree.pending.size
            )
            val min = octree.boundingBox!!.min
            val max = octree.boundingBox.max
            val mid = divide(add(max, min), 2f)
            val octant = arrayOfNulls<BoundingBox>(8)
            var xMin: Float
            var yMin: Float
            var zMin: Float
            var xMax: Float
            var yMax: Float
            var zMax: Float
            xMin = min[0]
            yMin = min[1]
            zMin = min[2]
            xMax = mid[0]
            yMax = mid[1]
            zMax = mid[2]
            octant[0] = BoundingBox(
                "octree0",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = mid[0]
            yMin = min[1]
            zMin = min[2]
            xMax = max[0]
            yMax = mid[1]
            zMax = mid[2]
            octant[1] = BoundingBox(
                "octree1",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = min[0]
            yMin = mid[1]
            zMin = min[2]
            xMax = mid[0]
            yMax = max[1]
            zMax = mid[2]
            octant[2] = BoundingBox(
                "octree2",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = mid[0]
            yMin = mid[1]
            zMin = min[2]
            xMax = max[0]
            yMax = max[1]
            zMax = mid[2]
            octant[3] = BoundingBox(
                "octree3",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = min[0]
            yMin = min[1]
            zMin = mid[2]
            xMax = mid[0]
            yMax = mid[1]
            zMax = max[2]
            octant[4] = BoundingBox(
                "octree4",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = mid[0]
            yMin = min[1]
            zMin = mid[2]
            xMax = max[0]
            yMax = mid[1]
            zMax = max[2]
            octant[5] = BoundingBox(
                "octree5",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = min[0]
            yMin = mid[1]
            zMin = mid[2]
            xMax = mid[0]
            yMax = max[1]
            zMax = max[2]
            octant[6] = BoundingBox(
                "octree6",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            xMin = mid[0]
            yMin = mid[1]
            zMin = mid[2]
            xMax = max[0]
            yMax = max[1]
            zMax = max[2]
            octant[7] = BoundingBox(
                "octree7",
                xMin,
                xMax,
                yMin,
                yMax,
                zMin,
                zMax
            )
            var anyInOctant = false
            val it = octree.pending.iterator()
            while (it.hasNext()) {
                val triangle = it.next()
                var inOctant = false
                for (i in 0..7) {
                    var inside = if (octant[i]!!
                            .insideBounds(triangle[0], triangle[1], triangle[2])
                    ) 1 else 0
                    inside += if (octant[i]!!
                            .insideBounds(triangle[4], triangle[5], triangle[6])
                    ) 1 else 0
                    inside += if (octant[i]!!
                            .insideBounds(triangle[8], triangle[9], triangle[10])
                    ) 1 else 0
                    if (inside == 3) {
                        inOctant = true
                        octree.addChild(i, octant[i], triangle)
                        anyInOctant = true
                    }
                }
                if (!inOctant) {
                    octree.triangles.add(triangle)
                }
                it.remove()
            }
            if (anyInOctant) {
                // subdivide if big enough (>=0.02)
                if ((mid[0] + min[0]) / 2 > BOX_SIZE && (mid[1] + min[1]) / 2 > BOX_SIZE && (mid[2] + min[2]) / 2 > BOX_SIZE) {
                    octree.subdivide()
                } else {
                    for (child in octree.children) {
                        if (child == null) continue
                        child.triangles.addAll(child.pending)
                    }
                }
            }
        }
    }
}