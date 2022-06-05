package zibi.robotx.autocad.data.off.util

import zibi.robotx.autocad.data.off.OffObject

object OffObjectUtil {
    fun centerObject(offObject: OffObject) {
        var minX = 0.0f
        var maxX = 0.0f
        var minY = 0.0f
        var maxY = 0.0f
        var minZ = 0.0f
        var maxZ = 0.0f
        for (vertex in offObject.vertices) {
            minX = Math.min(minX, vertex!!.x)
            maxX = Math.max(maxX, vertex.x)
            minY = Math.min(minY, vertex.y)
            maxY = Math.max(maxY, vertex.y)
            minZ = Math.min(minZ, vertex.z)
            maxZ = Math.max(maxZ, vertex.z)
        }
        offsetObject(
            offObject,
            -((maxX + minX) / 2.0f),
            -((maxY + minY) / 2.0f),
            -((maxZ + minZ) / 2.0f)
        )
    }

    fun offsetObject(offObject: OffObject, x: Float, y: Float, z: Float) {
        for (vertex in offObject.vertices) {
            vertex!!.x += x
            vertex!!.y += y
            vertex!!.z += z
        }
    }

    fun scaleObjectToFit(offObject: OffObject, radius: Float) {
        var maxRadiusSqr = 0.0f
        for (vertex in offObject.vertices) {
            val radiusSqr =
                vertex!!.x * vertex.x + vertex.y * vertex.y + vertex.z * vertex.z
            if (radiusSqr > maxRadiusSqr) {
                maxRadiusSqr = radiusSqr
            }
        }
        val maxRadius = Math.sqrt(maxRadiusSqr.toDouble()).toFloat()
        if (maxRadius > 1.0E-4f) {
            scaleObject(offObject, radius / maxRadius)
        }
    }

    fun scaleObject(offObject: OffObject, amount: Float) {
        for (vertex in offObject.vertices) {
            vertex!!.x *= amount
            vertex!!.y *= amount
            vertex!!.z *= amount
        }
    }
}