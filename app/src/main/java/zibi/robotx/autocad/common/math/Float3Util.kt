package zibi.robotx.autocad.common.math


object Float3Util {
    fun rotateAroundX(vector: Float3, cs: Float, sn: Float) {
        vector.y = vector.y * cs - vector.z * sn
        vector.z = vector.y * sn + vector.z * cs
    }

    fun rotateAroundX(vector: Float3, angle: Double) {
        rotateAroundX(
            vector, Math.cos(Math.toRadians(angle))
                .toFloat(), Math.sin(Math.toRadians(angle)).toFloat()
        )
    }

    fun rotateAroundY(vector: Float3, cs: Float, sn: Float) {
        vector.x = vector.x * cs - vector.z * sn
        vector.z = vector.x * sn + vector.z * cs
    }

    fun rotateAroundY(vector: Float3, angle: Double) {
        rotateAroundY(
            vector, Math.cos(Math.toRadians(angle))
                .toFloat(), Math.sin(Math.toRadians(angle)).toFloat()
        )
    }

    fun rotateAroundZ(vector: Float3, cs: Float, sn: Float) {
        vector.x = vector.x * cs - vector.y * sn
        vector.y = vector.x * sn + vector.y * cs
    }

    fun rotateAroundZ(vector: Float3, angle: Double) {
        rotateAroundZ(
            vector, Math.cos(Math.toRadians(angle))
                .toFloat(), Math.sin(Math.toRadians(angle)).toFloat()
        )
    }

    fun rotateAroundVector(vector: Float3, rotationVector: Float3) {
        val length = rotationVector.length
        rotateAroundVector(
            vector, rotationVector, Math.cos(length.toDouble())
                .toFloat(), Math.sin(length.toDouble()).toFloat()
        )
    }

    fun rotateAroundVector(vector: Float3, rotationVector: Float3, cs: Float, sn: Float) {
        val horVect = Float2()
        horVect.f7x = rotationVector.x
        horVect.f8y = -rotationVector.z
        if (horVect.isNull) {
            horVect.f7x = 1.0f
            horVect.f8y = 0.0f
        } else {
            horVect.length = 1.0f
        }
        rotateAroundY(vector, horVect.f7x, horVect.f8y)
        val verVect = Float2()
        verVect.f8y = -rotationVector.y
        verVect.f7x = Math.sqrt(1.0 - (verVect.f8y * verVect.f8y).toDouble()).toFloat()
        rotateAroundZ(vector, verVect.f7x, verVect.f8y)
        rotateAroundX(vector, cs, sn)
        rotateAroundZ(vector, verVect.f7x, -verVect.f8y)
        rotateAroundY(vector, horVect.f7x, -horVect.f8y)
    }

    fun rotatePointVectors(point: Float3, vectorX: Float3, vectorY: Float3, vectorZ: Float3) {
        val result = Float3()
        result.x = point.x * vectorX.x + point.y * vectorY.x + point.z * vectorZ.x
        result.y =
            point.y * vectorY.y + point.x * vectorX.y + point.z * vectorZ.y
        result.z =
            point.z * vectorZ.z + point.y * vectorY.z + point.x * vectorX.z
        point.setTo(result)
    }

    fun placeInCoordinateSystem(vector: Float3, systemX: Float3, systemY: Float3, systemZ: Float3) {
        val result = Float3()
        result.x = vector.x * systemX.x + vector.x * systemY.x + vector.x * systemZ.x
        result.y =
            vector.y * systemX.y + vector.y * systemY.y + vector.y * systemZ.y
        result.z =
            vector.z * systemX.z + vector.z * systemY.z + vector.z * systemZ.z
        vector.setTo(result)
    }

    fun toLocalCoordinateSystem(
        vector: Float3,
        systemX: Float3,
        systemY: Float3,
        systemZ: Float3
    ): Float3 {
        val result = Float3()
        result.x =
            vector.x * systemX.x + vector.y * systemY.x + vector.z * systemZ.x
        result.y =
            vector.x * systemX.y + vector.y * systemY.y + vector.z * systemZ.y
        result.z =
            vector.x * systemX.z + vector.y * systemY.z + vector.z * systemZ.z
        return result
    }
}