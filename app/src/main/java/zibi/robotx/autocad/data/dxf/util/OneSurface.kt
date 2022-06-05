package zibi.robotx.autocad.data.dxf.util

import zibi.robotx.autocad.common.collision.Dimensions
import zibi.robotx.autocad.data.dxf.protocol.table.TblLAYER
import java.nio.*
import javax.vecmath.Vector3f

class OneSurface(val tblLAYER: TblLAYER?, val PolylineFlag: Int){

    // paint area
    val listPoint: MutableList<Float> = mutableListOf()
    var listConnect: MutableList<Int> = mutableListOf()
    var elementIndex: IntArray = intArrayOf()
    // for touch
    val triangles: MutableList<FloatArray> = mutableListOf()
    var dimensions: Dimensions? = null
        get() {
            if (field == null) {
                val dimensions = Dimensions()
                for( i in listConnect.indices step 3) {
                    val A = listConnect[i]
                    dimensions.update(arrXYZPoint[A], arrXYZPoint[A + 1], arrXYZPoint[A + 2] )
                }
                field = dimensions
            }
            return field
        }

    var vec4Color: FloatArray = FloatArray(1)
        get() {
            val transparent: Float = tblLAYER?.AcCmTransparency ?: 0f
            field = if( tblLAYER?.ColorNumber != null ) {
                convertAutoCADToRGB(tblLAYER.ColorNumber!!, transparent)
            } else{
                convertAutoCADToRGB(252, transparent)
            }
            return field
        }
        private set

    var visible: Boolean = true
        get() {
            val trans = tblLAYER?.AcCmTransparency ?: 0f
            return if(trans < 0.5f) {tblLAYER?.AcCmTransparency = 0.3f; true}
            else {tblLAYER?.AcCmTransparency = 0.7f; false}
        }
        set(value) {
            if (value) tblLAYER?.AcCmTransparency = 0.3f
            else tblLAYER?.AcCmTransparency = 0.7f
            field = value
        }

    var drawIndexBufferSurface: FloatBuffer? = null


    var arrXYZPoint: FloatArray = floatArrayOf()
    fun createDataSurface(amount: Float, centerXyZ: Vector3f, offsetIndex: Int){
        arrXYZPoint = listPoint.toFloatArray()

        for (ele in arrXYZPoint.indices step 3) {
            arrXYZPoint[ele] *= amount
            arrXYZPoint[ele] += centerXyZ.x
            arrXYZPoint[ele + 1] *= amount
            arrXYZPoint[ele + 1] += centerXyZ.y
            arrXYZPoint[ele + 2] *= amount
            arrXYZPoint[ele + 2] += centerXyZ.z
        }

        val mArrIndexPoint: MutableList<Int> = mutableListOf()
        for( ele in listConnect) mArrIndexPoint.add( ele + offsetIndex )

        elementIndex = mArrIndexPoint.toIntArray()

        //building triangles for touch

        for( i in listConnect.indices step 3) {
            val A = listConnect[i]
            val B = listConnect[i + 1]
            val C = listConnect[i + 2]
            val triangle = floatArrayOf(
                arrXYZPoint[A], arrXYZPoint[A + 1], arrXYZPoint[A + 2], 1f,
                arrXYZPoint[B], arrXYZPoint[B + 1], arrXYZPoint[B + 2], 1f,
                arrXYZPoint[C], arrXYZPoint[C + 1], arrXYZPoint[C + 2], 1f
            )
            triangles.add(triangle)
        }

        drawIndexBufferSurface =  ByteBuffer.allocateDirect(4*arrXYZPoint.size ).order(ByteOrder.nativeOrder()).asFloatBuffer()
        drawIndexBufferSurface?.put( arrXYZPoint)
        drawIndexBufferSurface?.position(0)


    }

//    init {
        // create color
//        val transparent: Float = tblLAYER?.AcCmTransparency ?: 0f
//        vec4Color = if( tblLAYER?.ColorNumber != null ) {
//            convertAutoCADToRGB(tblLAYER.ColorNumber!!, transparent)
//        } else{
//            convertAutoCADToRGB(252, transparent)
//        }
//    }
}