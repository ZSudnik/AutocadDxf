package zibi.robotx.autocad.data.dxf.util

import zibi.robotx.autocad.data.dxf.protocol.table.TblLAYER
import java.nio.*
import javax.vecmath.Vector3f

class OnePolyline(val LayerNamenot: TblLAYER?){

    //data from file
    val mapPoint: MutableMap<Int, Vector3f?> = mutableMapOf()
    val mapPointList: MutableMap<Int, List<Vector3f>> = mutableMapOf()
    val mapConnect: MutableMap<Int,MutableList<Int>> = mutableMapOf()
    val mapKeyToCount: MutableMap<Int, Int> = mutableMapOf()

    var vec4Color: FloatArray = floatArrayOf()
    // paint area
    val listPoint: MutableList<Float> = mutableListOf()
    var listConnect: MutableList<Int> = mutableListOf()
    var elementIndex: IntArray = intArrayOf()
    var pointerConn = 0


    var arrXYZPoint: FloatArray = floatArrayOf()
    fun createDataLine(amount: Float, centeXyZ: Vector3f, offsetIndex: Int){
        arrXYZPoint = listPoint.toFloatArray()

        for (ele in arrXYZPoint.indices step 3) {
            arrXYZPoint[ele] *= amount
            arrXYZPoint[ele] += centeXyZ.x
            arrXYZPoint[ele + 1] *= amount
            arrXYZPoint[ele + 1] += centeXyZ.y
            arrXYZPoint[ele + 2] *= amount
            arrXYZPoint[ele + 2] += centeXyZ.z
        }

        val mArrIndexPoint: MutableList<Int> = mutableListOf()
        for( ele in listConnect) mArrIndexPoint.add( (ele + offsetIndex) )

        elementIndex = mArrIndexPoint.toIntArray()

    }

    init {
        vec4Color = if( LayerNamenot?.ColorNumber != null ) {
            convertAutoCADToRGB(LayerNamenot.ColorNumber!!, 1f)
        }else{
            convertAutoCADToRGB(252, 1f)
        }
    }


}