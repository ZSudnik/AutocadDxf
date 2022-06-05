package zibi.robotx.autocad.data.dxf.util

import zibi.robotx.autocad.data.dxf.protocol.table.TblLAYER
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import javax.vecmath.Vector3f

class OneElement(val tblLAYER: TblLAYER, val PolylineFlag: Int){

    //data from file
    val mapPoint: MutableMap<Int, Vector3f?> = mutableMapOf()
    val mapConnect: MutableMap<Int,MutableList<Int>?> = mutableMapOf()
    val mapKeyToCount: MutableMap<Int, Int> = mutableMapOf()
//    var ColorNumber: Int? = null
    var vec4Color: FloatArray = floatArrayOf()
    // paint area
    val listPoint: MutableList<Float> = mutableListOf()
    val listTriangleConnect: MutableList<Short> = mutableListOf()
    var listConnect: MutableList<Short> = mutableListOf()
    var arrIndexPoint: ShortArray = shortArrayOf()
    var vertexBuffer: FloatBuffer? = null
    var drawIndexBufferLine: ShortBuffer? = null


    fun closeConnVertexLoop(){
        if( PolylineFlag == 9 || PolylineFlag == 1) {
            if (mapConnect.isNotEmpty()) {
//                val mConnList = mapConnect[mapConnect.keys.maxOrNull()]!!
//                mConnList.add( 1)
                val lastKey = mapConnect.entries.last()
                val mConnList = mapConnect[lastKey.key]!!
                mConnList.add( 1)
            }
        }
    }

    var arrXYZPoint: FloatArray = floatArrayOf()
    fun createVertexLineBuffers( amount: Float, centeXyZ: Vector3f, offsetIndex: Int){
        arrXYZPoint = listPoint.toFloatArray()
//        arrIndexPoint = listConnect.toShortArray()

        for (ele in arrXYZPoint.indices step 3) {
            arrXYZPoint[ele] *= amount
            arrXYZPoint[ele] += centeXyZ.x
            arrXYZPoint[ele + 1] *= amount
            arrXYZPoint[ele + 1] += centeXyZ.y
            arrXYZPoint[ele + 2] *= amount
            arrXYZPoint[ele + 2] += centeXyZ.z
        }

        val mArrIndexPoint: MutableList<Short> = mutableListOf()
        for( ele in listConnect) mArrIndexPoint.add( (ele + offsetIndex).toShort() )
        arrIndexPoint = mArrIndexPoint.toShortArray()

        val bb = ByteBuffer.allocateDirect(arrXYZPoint.size * 4)
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer?.put(arrXYZPoint)
        vertexBuffer?.position(0)
        // initialize byte buffer for the draw list
        val dlbLine = ByteBuffer.allocateDirect(arrIndexPoint.size * 2)
        dlbLine.order(ByteOrder.nativeOrder())
        drawIndexBufferLine = dlbLine.asShortBuffer()
        drawIndexBufferLine?.put(arrIndexPoint)
        drawIndexBufferLine?.position(0)
    }

    init {
//        val transparent: Float = tblLAYER.AcCmTransparency ?: 0f
        if( tblLAYER.ColorNumber != null ) {
            vec4Color = convertAutoCADToRGB(tblLAYER.ColorNumber!!, 0f)
        }else{
            vec4Color = convertAutoCADToRGB(252, 0f)
        }
    }

    fun findTriangle(){
//        for( ind in 0 until listConnect.size step 2){
//            val first:Int = listConnect[ind]
//            val second:Int = listConnect[ind+1]
//            val three = if( ind+3 < listConnect.size ) listConnect[ind+3] else return
//            for( ind2 in ind+2 until listConnect.size step 2){
//                if( (first == listConnect[ind2] && three == listConnect[ind2+1] ) ||
//                    (three == listConnect[ind2] && first == listConnect[ind2+1] ) ){
//                    listTriangleConnect.add(first.toShort())
//                    listTriangleConnect.add(second.toShort())
//                    listTriangleConnect.add(three.toShort())
//                }
//            }
//        }
    }
}