package zibi.robotx.autocad.android.modelviewer.util

import javax.vecmath.Vector3f

class PointInsideTri {


    fun ptInTriangle(p: Vector3f, p0: Vector3f, p1:Vector3f, p2: Vector3f): Boolean {
        val A = 1/2 * (-p1.y * p2.x + p0.y * (-p1.x + p2.x) + p0.x * (p1.y - p2.y) + p1.x * p2.y);
        val sign = if(A < 0)  -1 else 1;
        val s = (p0.y * p2.x - p0.x * p2.y + (p2.y - p0.y) * p.x + (p0.x - p2.x) * p.y) * sign;
        val t = (p0.x * p1.y - p0.y * p1.x + (p0.y - p1.y) * p.x + (p1.x - p0.x) * p.y) * sign;

        return s > 0 && t > 0 && (s + t) < (2 * A * sign)
    }



}