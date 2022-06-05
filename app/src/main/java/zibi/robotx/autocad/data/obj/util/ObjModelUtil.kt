package zibi.robotx.autocad.data.obj.util

import zibi.robotx.autocad.data.obj.ObjModel
import zibi.robotx.autocad.data.obj.ObjNormal


object ObjModelUtil {
    fun centerModel(model: ObjModel) {
        var minX = 0.0f
        var maxX = 0.0f
        var minY = 0.0f
        var maxY = 0.0f
        var minZ = 0.0f
        var maxZ = 0.0f
        for (vertex in model.vertices) {
            minX = Math.min(minX, vertex.x)
            maxX = Math.max(maxX, vertex.x)
            minY = Math.min(minY, vertex.y)
            maxY = Math.max(maxY, vertex.y)
            minZ = Math.min(minZ, vertex.z)
            maxZ = Math.max(maxZ, vertex.z)
        }
        offsetModel(
            model,
            -((maxX + minX) / 2.0f),
            -((maxY + minY) / 2.0f),
            -((maxZ + minZ) / 2.0f)
        )
    }

    fun offsetModel(model: ObjModel, x: Float, y: Float, z: Float) {
        for (vertex in model.vertices) {
            vertex.x += x
            vertex.y += y
            vertex.z += z
        }
    }

    fun scaleModelToFit(model: ObjModel, radius: Float) {
        var maxRadiusSqr = 0.0f
        for (vertex in model.vertices) {
            val radiusSqr =
                vertex.x * vertex.x + vertex.y * vertex.y + vertex.z * vertex.z
            if (radiusSqr > maxRadiusSqr) {
                maxRadiusSqr = radiusSqr
            }
        }
        val maxRadius = Math.sqrt(maxRadiusSqr.toDouble()).toFloat()
        if (maxRadius > 1.0E-4f) {
            scaleModel(model, radius / maxRadius)
        }
    }

    fun scaleModel(model: ObjModel, amount: Float) {
        for (vertex in model.vertices) {
            vertex.x *= amount
            vertex.y *= amount
            vertex.z *= amount
        }
    }

    @JvmStatic
    fun normalize(normal: ObjNormal) {
        val length =
            Math.sqrt((normal.x * normal.x + normal.y * normal.y + normal.z * normal.z).toDouble())
                .toFloat()
        normal.x /= length
        normal.y /= length
        normal.z /= length
    }
}