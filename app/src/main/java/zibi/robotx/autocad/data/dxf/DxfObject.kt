package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.protocol.SecBlocks
import zibi.robotx.autocad.data.dxf.protocol.SecEntities
import zibi.robotx.autocad.data.dxf.protocol.SecTables
import zibi.robotx.autocad.data.dxf.util.*
import java.nio.FloatBuffer
import javax.vecmath.Vector3f
import kotlin.math.sqrt

class DxfObject : DxfChain() {

    var centerXYZ: Vector3f? = null
    var amount: Float = 1f

    var listOnePolyline: List<OnePolyline> = listOf()
    var vertexArrayLine: FloatArray = floatArrayOf()
    var elementArrayLine: IntArray = intArrayOf()

    var listOneSurface: List<OneSurface> = listOf()
    var vertexArraySurface: FloatArray? = null
    val elementArraySurface: IntArray
        get() {
            var oneArr = IntArray(0)
            for (oneSurface in listOneSurface) {
                oneArr +=  oneSurface.elementIndex
            }
            return oneArr
        }


    override fun read(dxfContext: DxfLoaderContext) {
    }

    override fun write(sb: StringBuilder): StringBuilder {
        return sb
    }


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
        var sizeMM: SizeMinMax? = sizeMMParent
        val iter = this.iterator()
        while( iter.hasNext() ){
            val element = iter.next()
                sizeMM = element.centerObject( sizeMM )
        }
        centerXYZ = if( sizeMM == null){
            Vector3f()
        }else {
            Vector3f(-((sizeMM.maxX + sizeMM.minX) / 2.0f), -((sizeMM.maxY + sizeMM.minY) / 2.0f), -((sizeMM.maxZ + sizeMM.minZ) / 2.0f))
        }
        return null
    }


    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
        var mRadiusSqr = maxRadiusSqr
        val iter = this.iterator()
        while( iter.hasNext() ){
            val element = iter.next()
            mRadiusSqr = element.scaleObjectToFit( mRadiusSqr )
        }
        val maxRadius = sqrt(mRadiusSqr)
        if (maxRadius > 1.0E-4f) {
            amount =  5f / maxRadius
        }
        return amount
    }

    override fun collectionConnect(collectionPoint: CollectionPoint) {
        /////////////////////////////////////
        this.reCountCommand( CounterCommand( 0))
        /////////////////////////////////////
        val iter = this.iterator()
        while( iter.hasNext() ){
            val element = iter.next()
            element.collectionConnect( collectionPoint )
        }


        //////////////////////// Scale
        centerXYZ?.scale( amount )
        /////////////////////// Line
        listOnePolyline = collectionPoint.getArrElementLine()
        val arrXYZPointAll: MutableList<Float> = mutableListOf()


        for( oneElement in listOnePolyline) {
            oneElement.createDataLine( amount, centerXYZ!!, vertexArrayLine.size/3)
            vertexArrayLine += oneElement.arrXYZPoint
            elementArrayLine += oneElement.elementIndex
        }

        ///////////////////////////// Surface
        arrXYZPointAll.clear()
        listOneSurface = collectionPoint.listOneSurface.values.toList()

        for( oneSurface in listOneSurface) {
            oneSurface.createDataSurface( amount, centerXYZ!!, arrXYZPointAll.size/3)
            arrXYZPointAll += oneSurface.arrXYZPoint.toList()
        }

        vertexArraySurface = arrXYZPointAll.toFloatArray()

    }

    override fun xdef(): Int {
        return 0
    }


    override fun reCountCommand(counterCommand: CounterCommand) {
        val iter = this.iterator()
        while( iter.hasNext() ){
            val element = iter.next()
            if( element is SecTables) element.reCountCommand( counterCommand )
            if( element is SecBlocks) element.reCountCommand( counterCommand )
            if( element is SecEntities) element.reCountCommand( counterCommand )
//            if( element is SecObject) element.reCountCommand( counterCommand ) TODO
        }
    }
}