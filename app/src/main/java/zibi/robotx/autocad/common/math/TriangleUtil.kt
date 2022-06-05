package zibi.robotx.autocad.common.math

import zibi.robotx.autocad.common.memory.StaticObjectPool


object TriangleUtil {
    private val float3Pool = StaticObjectPool(32, Float3::class.java)
    fun isInTriangle(vertex: Float3, a: Float3, b: Float3, c: Float3): Boolean {
        val normal = float3Pool.allocate()
        return try {
            calculateNormal(a, b, c, normal)
            isInTriangle(vertex, a, b, c, normal)
        } finally {
            float3Pool.release(normal)
        }
    }

    fun isInTriangle(vertex: Float3, a: Float3, b: Float3, c: Float3, normal: Float3?): Boolean {
        return isCounterClockwise(a, b, vertex, normal) && isCounterClockwise(
            b,
            c,
            vertex,
            normal
        ) && isCounterClockwise(c, a, vertex, normal)
    }

    fun isCounterClockwise(a: Float3, b: Float3, c: Float3, normal: Float3?): Boolean {
        val evaluatedNormal = float3Pool.allocate()
        return try {
            calculateNormal(a, b, c, evaluatedNormal)
            Float3.Companion.getDotProduct(
                normal,
                evaluatedNormal
            ) > 0.0f
        } finally {
            float3Pool.release(evaluatedNormal)
        }
    }

    fun getNormal(a: Float3, b: Float3, c: Float3): Float3 {
        val result = Float3()
        calculateNormal(a, b, c, result)
        return result
    }

    fun calculateNormal(a: Float3, b: Float3, c: Float3, result: Float3?) {
        val normal: Float3 = Float3.Companion.getCrossProduct(
            Float3.Companion.getDec(a, c),
            Float3.Companion.getDec(c, b)
        )
        normal.length = 1.0f
        normal.invert()
        result!!.setTo(normal)
    }
}