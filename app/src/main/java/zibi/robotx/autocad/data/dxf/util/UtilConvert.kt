package zibi.robotx.autocad.data.dxf.util

import android.graphics.Color

//	 Polyline flag (bit-coded default = 0):, 1 = This is a closed polyline (or a polygon mesh closed in the M direction), 2 = Curve-fit vertices have been added, 4 = Spline-fit vertices have been added, 8 = This is a 3D polyline, 16 = This is a 3D polygon mesh, 32 = The polygon mesh is closed in the N direction, 64 = The polyline is a polyface mesh, 128 = The linetype pattern is generated continuously around the vertices of this polyline
//	 Vertex flags:,                          1 = Extra vertex created by curve-fitting,                            2 = Curve-fit tangent defined for this vertex. A curve-fit tangent direction of 0 may be omitted from DXF output but is significant if this bit is set, 4 = Not used, 8 = Spline vertex created by spline-fitting, 16 = Spline frame control point, 32 = 3D polyline vertex, 64 = 3D polygon mesh, 128 = Polyface mesh vertex

fun Int?.flag70( vararg  typesFlag: Int): Boolean{
    if( this == null ) return false
    var result: Boolean = true
    for( type in typesFlag) {
        val mResult = this.and(type) != 0
         result  = result && mResult
    }
    return result
}

fun Boolean?.to01( ): String{
    if( this == null ) return "0"
    return if(this){
        "1"
    }else{
        "0"
    }
}


fun Int?.toHex( ): String{
    if(this == null) return "0"
    return Integer.toHexString( this)
}


fun convertRGBToAutoCAD(rgbColor: Int): Int {
    // run through the list of colors and return the one that's closest in the sense of RGB distance
    val r1 = Color.red(rgbColor)
    val g1 = Color.green(rgbColor)
    val b1 = Color.blue(rgbColor)
    var bestMatch = 0
    var closestDistance = 3 * 255 * 255

    // start at index 1 since 0 not used
    for (i in 1 until rgbTable.size) {
        val r2 = rgbTable[i][0]
        val g2 = rgbTable[i][1]
        val b2 = rgbTable[i][2]
        val distance = (r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2)
        if (distance < closestDistance) {
            bestMatch = i
            closestDistance = distance
        }
        if (distance == 0) {
            // found exact match
            break
        }
    }
    return bestMatch
}

fun convertAutoCADToRGB( AutoCadColor: Int, transparent: Float): FloatArray {
    val color: IntArray = if(AutoCadColor>255 || AutoCadColor<0) rgbTable[250] else rgbTable[AutoCadColor]
    val r = color[0] / 255.0f
    val g = color[1] / 255.0f
    val b = color[2] / 255.0f
//    val a = (color shr 24 and 0xff) / 255.0f
    return floatArrayOf( r, g, b, transparent )
}

/**
 * The standard DXF color table rgb values.
 */
private val rgbTable = arrayOf(
    intArrayOf(0, 0, 0),
    intArrayOf(255, 0, 0),
    intArrayOf(255, 255, 0),
    intArrayOf(0, 255, 0),
    intArrayOf(0, 255, 255),
    intArrayOf(0, 0, 255),
    intArrayOf(255, 0, 255),
    intArrayOf(255, 255, 255),
    intArrayOf(128, 128, 128),
    intArrayOf(192, 192, 192),
    intArrayOf(255, 0, 0),
    intArrayOf(255, 127, 127),
    intArrayOf(165, 0, 0),
    intArrayOf(165, 82, 82),
    intArrayOf(127, 0, 0),
    intArrayOf(127, 63, 63),
    intArrayOf(76, 0, 0),
    intArrayOf(76, 38, 38),
    intArrayOf(38, 0, 0),
    intArrayOf(38, 19, 19),
    intArrayOf(255, 63, 0),
    intArrayOf(255, 159, 127),
    intArrayOf(165, 41, 0),
    intArrayOf(165, 103, 82),
    intArrayOf(127, 31, 0),
    intArrayOf(127, 79, 63),
    intArrayOf(76, 19, 0),
    intArrayOf(76, 47, 38),
    intArrayOf(38, 9, 0),
    intArrayOf(38, 23, 19),
    intArrayOf(255, 127, 0),
    intArrayOf(255, 191, 127),
    intArrayOf(165, 82, 0),
    intArrayOf(165, 124, 82),
    intArrayOf(127, 63, 0),
    intArrayOf(127, 95, 63),
    intArrayOf(76, 38, 0),
    intArrayOf(76, 57, 38),
    intArrayOf(38, 19, 0),
    intArrayOf(38, 28, 19),
    intArrayOf(255, 191, 0),
    intArrayOf(255, 223, 127),
    intArrayOf(165, 124, 0),
    intArrayOf(165, 145, 82),
    intArrayOf(127, 95, 0),
    intArrayOf(127, 111, 63),
    intArrayOf(76, 57, 0),
    intArrayOf(76, 66, 38),
    intArrayOf(38, 28, 0),
    intArrayOf(38, 33, 19),
    intArrayOf(255, 255, 0),
    intArrayOf(255, 255, 127),
    intArrayOf(165, 165, 0),
    intArrayOf(165, 165, 82),
    intArrayOf(127, 127, 0),
    intArrayOf(127, 127, 63),
    intArrayOf(76, 76, 0),
    intArrayOf(76, 76, 38),
    intArrayOf(38, 38, 0),
    intArrayOf(38, 38, 19),
    intArrayOf(191, 255, 0),
    intArrayOf(223, 255, 127),
    intArrayOf(124, 165, 0),
    intArrayOf(145, 165, 82),
    intArrayOf(95, 127, 0),
    intArrayOf(111, 127, 63),
    intArrayOf(57, 76, 0),
    intArrayOf(66, 76, 38),
    intArrayOf(28, 38, 0),
    intArrayOf(33, 38, 19),
    intArrayOf(127, 255, 0),
    intArrayOf(191, 255, 127),
    intArrayOf(82, 165, 0),
    intArrayOf(124, 165, 82),
    intArrayOf(63, 127, 0),
    intArrayOf(95, 127, 63),
    intArrayOf(38, 76, 0),
    intArrayOf(57, 76, 38),
    intArrayOf(19, 38, 0),
    intArrayOf(28, 38, 19),
    intArrayOf(63, 255, 0),
    intArrayOf(159, 255, 127),
    intArrayOf(41, 165, 0),
    intArrayOf(103, 165, 82),
    intArrayOf(31, 127, 0),
    intArrayOf(79, 127, 63),
    intArrayOf(19, 76, 0),
    intArrayOf(47, 76, 38),
    intArrayOf(9, 38, 0),
    intArrayOf(23, 38, 19),
    intArrayOf(0, 255, 0),
    intArrayOf(127, 255, 127),
    intArrayOf(0, 165, 0),
    intArrayOf(82, 165, 82),
    intArrayOf(0, 127, 0),
    intArrayOf(63, 127, 63),
    intArrayOf(0, 76, 0),
    intArrayOf(38, 76, 38),
    intArrayOf(0, 38, 0),
    intArrayOf(19, 38, 19),
    intArrayOf(0, 255, 63),
    intArrayOf(127, 255, 159),
    intArrayOf(0, 165, 41),
    intArrayOf(82, 165, 103),
    intArrayOf(0, 127, 31),
    intArrayOf(63, 127, 79),
    intArrayOf(0, 76, 19),
    intArrayOf(38, 76, 47),
    intArrayOf(0, 38, 9),
    intArrayOf(19, 38, 23),
    intArrayOf(0, 255, 127),
    intArrayOf(127, 255, 191),
    intArrayOf(0, 165, 82),
    intArrayOf(82, 165, 124),
    intArrayOf(0, 127, 63),
    intArrayOf(63, 127, 95),
    intArrayOf(0, 76, 38),
    intArrayOf(38, 76, 57),
    intArrayOf(0, 38, 19),
    intArrayOf(19, 38, 28),
    intArrayOf(0, 255, 191),
    intArrayOf(127, 255, 223),
    intArrayOf(0, 165, 124),
    intArrayOf(82, 165, 145),
    intArrayOf(0, 127, 95),
    intArrayOf(63, 127, 111),
    intArrayOf(0, 76, 57),
    intArrayOf(38, 76, 66),
    intArrayOf(0, 38, 28),
    intArrayOf(19, 38, 33),
    intArrayOf(0, 255, 255),
    intArrayOf(127, 255, 255),
    intArrayOf(0, 165, 165),
    intArrayOf(82, 165, 165),
    intArrayOf(0, 127, 127),
    intArrayOf(63, 127, 127),
    intArrayOf(0, 76, 76),
    intArrayOf(38, 76, 76),
    intArrayOf(0, 38, 38),
    intArrayOf(19, 38, 38),
    intArrayOf(0, 191, 255),
    intArrayOf(127, 223, 255),
    intArrayOf(0, 124, 165),
    intArrayOf(82, 145, 165),
    intArrayOf(0, 95, 127),
    intArrayOf(63, 111, 127),
    intArrayOf(0, 57, 76),
    intArrayOf(38, 66, 76),
    intArrayOf(0, 28, 38),
    intArrayOf(19, 33, 38),
    intArrayOf(0, 127, 255),
    intArrayOf(127, 191, 255),
    intArrayOf(0, 82, 165),
    intArrayOf(82, 124, 165),
    intArrayOf(0, 63, 127),
    intArrayOf(63, 95, 127),
    intArrayOf(0, 38, 76),
    intArrayOf(38, 57, 76),
    intArrayOf(0, 19, 38),
    intArrayOf(19, 28, 38),
    intArrayOf(0, 63, 255),
    intArrayOf(127, 159, 255),
    intArrayOf(0, 41, 165),
    intArrayOf(82, 103, 165),
    intArrayOf(0, 31, 127),
    intArrayOf(63, 79, 127),
    intArrayOf(0, 19, 76),
    intArrayOf(38, 47, 76),
    intArrayOf(0, 9, 38),
    intArrayOf(19, 23, 38),
    intArrayOf(0, 0, 255),
    intArrayOf(127, 127, 255),
    intArrayOf(0, 0, 165),
    intArrayOf(82, 82, 165),
    intArrayOf(0, 0, 127),
    intArrayOf(63, 63, 127),
    intArrayOf(0, 0, 76),
    intArrayOf(38, 38, 76),
    intArrayOf(0, 0, 38),
    intArrayOf(19, 19, 38),
    intArrayOf(63, 0, 255),
    intArrayOf(159, 127, 255),
    intArrayOf(41, 0, 165),
    intArrayOf(103, 82, 165),
    intArrayOf(31, 0, 127),
    intArrayOf(79, 63, 127),
    intArrayOf(19, 0, 76),
    intArrayOf(47, 38, 76),
    intArrayOf(9, 0, 38),
    intArrayOf(23, 19, 38),
    intArrayOf(127, 0, 255),
    intArrayOf(191, 127, 255),
    intArrayOf(82, 0, 165),
    intArrayOf(124, 82, 165),
    intArrayOf(63, 0, 127),
    intArrayOf(95, 63, 127),
    intArrayOf(38, 0, 76),
    intArrayOf(57, 38, 76),
    intArrayOf(19, 0, 38),
    intArrayOf(28, 19, 38),
    intArrayOf(191, 0, 255),
    intArrayOf(223, 127, 255),
    intArrayOf(124, 0, 165),
    intArrayOf(145, 82, 165),
    intArrayOf(95, 0, 127),
    intArrayOf(111, 63, 127),
    intArrayOf(57, 0, 76),
    intArrayOf(66, 38, 76),
    intArrayOf(28, 0, 38),
    intArrayOf(33, 19, 38),
    intArrayOf(255, 0, 255),
    intArrayOf(255, 127, 255),
    intArrayOf(165, 0, 165),
    intArrayOf(165, 82, 165),
    intArrayOf(127, 0, 127),
    intArrayOf(127, 63, 127),
    intArrayOf(76, 0, 76),
    intArrayOf(76, 38, 76),
    intArrayOf(38, 0, 38),
    intArrayOf(38, 19, 38),
    intArrayOf(255, 0, 191),
    intArrayOf(255, 127, 223),
    intArrayOf(165, 0, 124),
    intArrayOf(165, 82, 145),
    intArrayOf(127, 0, 95),
    intArrayOf(127, 63, 111),
    intArrayOf(76, 0, 57),
    intArrayOf(76, 38, 66),
    intArrayOf(38, 0, 28),
    intArrayOf(38, 19, 33),
    intArrayOf(255, 0, 127),
    intArrayOf(255, 127, 191),
    intArrayOf(165, 0, 82),
    intArrayOf(165, 82, 124),
    intArrayOf(127, 0, 63),
    intArrayOf(127, 63, 95),
    intArrayOf(76, 0, 38),
    intArrayOf(76, 38, 57),
    intArrayOf(38, 0, 19),
    intArrayOf(38, 19, 28),
    intArrayOf(255, 0, 63),
    intArrayOf(255, 127, 159),
    intArrayOf(165, 0, 41),
    intArrayOf(165, 82, 103),
    intArrayOf(127, 0, 31),
    intArrayOf(127, 63, 79),
    intArrayOf(76, 0, 19),
    intArrayOf(76, 38, 47),
    intArrayOf(38, 0, 9),
    intArrayOf(38, 19, 23),
    intArrayOf(84, 84, 84),
    intArrayOf(118, 118, 118),
    intArrayOf(160, 160, 160),
    intArrayOf(192, 192, 192),
    intArrayOf(224, 224, 224),
    intArrayOf(0, 0, 0)
)
