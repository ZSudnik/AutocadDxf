package zibi.robotx.autocad.data.dxf.util

import java.lang.Float.min
import javax.vecmath.Vector3f

class SizeMinMax() {

    var minX = 0.0f
    var maxX = 0.0f
    var minY = 0.0f
    var maxY = 0.0f
    var minZ = 0.0f
    var maxZ = 0.0f

    fun findMinMax(vararg pointW: Vector3f?): SizeMinMax {
        val resultMM: SizeMinMax = SizeMinMax( minX, maxX, minY, maxY, minZ, maxZ)
        for (point in pointW) {
            if( point != null) {
                    resultMM.minX = min(resultMM.minX, point.x)
                    resultMM.maxX = Math.max(resultMM.maxX, point.x)
                    resultMM.minY = Math.min(resultMM.minY, point.y)
                    resultMM.maxY = Math.max(resultMM.maxY, point.y)
                    resultMM.minZ = Math.min(resultMM.minZ, point.z)
                    resultMM.maxZ = Math.max(resultMM.maxZ, point.z)
            }
        }
        return resultMM
    }

    fun findMinMax( pointW: List<Vector3f?>): SizeMinMax {
        val resultMM: SizeMinMax = SizeMinMax( minX, maxX, minY, maxY, minZ, maxZ)
        for (point in pointW) {
            if( point != null) {
                resultMM.minX = min(resultMM.minX, point.x)
                resultMM.maxX = Math.max(resultMM.maxX, point.x)
                resultMM.minY = Math.min(resultMM.minY, point.y)
                resultMM.maxY = Math.max(resultMM.maxY, point.y)
                resultMM.minZ = Math.min(resultMM.minZ, point.z)
                resultMM.maxZ = Math.max(resultMM.maxZ, point.z)
            }
        }
        return resultMM
    }

    companion object{

        fun findMinMax(vararg pointW: Vector3f?): SizeMinMax? {
            var resultMM: SizeMinMax? = null
            for (point in pointW) {
                if( point != null) {
                    if (resultMM == null){
                        resultMM = SizeMinMax( point )
                    } else {
                        resultMM.minX = Math.min(resultMM.minX, point.x)
                        resultMM.maxX = Math.max(resultMM.maxX, point.x)
                        resultMM.minY = Math.min(resultMM.minY, point.y)
                        resultMM.maxY = Math.max(resultMM.maxY, point.y)
                        resultMM.minZ = Math.min(resultMM.minZ, point.z)
                        resultMM.maxZ = Math.max(resultMM.maxZ, point.z)
                    }
                }
            }
            return resultMM
        }

        fun findMinMax( pointW: List<Vector3f?>): SizeMinMax? {
            var resultMM: SizeMinMax? = null
            for (point in pointW) {
                if( point != null) {
                    if (resultMM == null){
                        resultMM = SizeMinMax( point )
                    } else {
                        resultMM.minX = Math.min(resultMM.minX, point.x)
                        resultMM.maxX = Math.max(resultMM.maxX, point.x)
                        resultMM.minY = Math.min(resultMM.minY, point.y)
                        resultMM.maxY = Math.max(resultMM.maxY, point.y)
                        resultMM.minZ = Math.min(resultMM.minZ, point.z)
                        resultMM.maxZ = Math.max(resultMM.maxZ, point.z)
                    }
                }
            }
            return resultMM
        }
    }

    constructor( minX: Float, maxX:Float, minY:Float, maxY:Float, minZ:Float, maxZ:Float): this() {
        this.minX = minX
        this.maxX = maxX
        this.minY = minY
        this.maxY = maxY
        this.minZ = minZ
        this.maxZ = maxZ
    }

    constructor( vararg pointW: Vector3f) : this() {
        var resultMM: SizeMinMax? = null
        for (point in pointW) {
            if (resultMM == null){
                resultMM = SizeMinMax( point )
            } else {
                resultMM.minX = Math.min(resultMM.minX, point.x)
                resultMM.maxX = Math.max(resultMM.maxX, point.x)
                resultMM.minY = Math.min(resultMM.minY, point.y)
                resultMM.maxY = Math.max(resultMM.maxY, point.y)
                resultMM.minZ = Math.min(resultMM.minZ, point.z)
                resultMM.maxZ = Math.max(resultMM.maxZ, point.z)
            }
        }
        this.minX = resultMM!!.minX
        this.maxX = resultMM.maxX
        this.minY = resultMM.minY
        this.maxY = resultMM.maxY
        this.minZ = resultMM.minZ
        this.maxZ = resultMM.maxZ

    }

    constructor( point: Vector3f) : this() {
        this.minX = point.x
        this.maxX = point.x
        this.minY = point.y
        this.maxY = point.y
        this.minZ = point.z
        this.maxZ = point.z
    }
}