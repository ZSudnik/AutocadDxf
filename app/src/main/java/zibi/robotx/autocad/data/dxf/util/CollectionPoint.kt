package zibi.robotx.autocad.data.dxf.util


import zibi.robotx.autocad.data.dxf.protocol.table.TblLAYER
import javax.vecmath.Vector3f
import kotlin.math.sqrt

class CollectionPoint() {

    var last : Int = 0


    ///////////////////////// 3DFACE /////////////////////////////////////
    val listOneSurface: MutableMap<String ,OneSurface> = mutableMapOf()
    private fun findLayerSurface(LayerName: String? , tblLAYER: TblLAYER?): OneSurface {
        if( LayerName == null || LayerName == "NULL"){
            if( !listOneSurface.containsKey( "NULL") ){
                listOneSurface["NULL"] = OneSurface( tblLAYER, 32)
            }
            return listOneSurface["NULL"]!!
        }else{
            if( !listOneSurface.containsKey( LayerName ) ){
                listOneSurface[LayerName] = OneSurface( tblLAYER, 32)
            }
            return listOneSurface[LayerName]!!
        }
    }

    fun collectSurface(HandlenotOmitted: Int?, tblLAYER: TblLAYER?, FirstCorner: Vector3f?,
                       SecondCorner: Vector3f?, ThirdCorner: Vector3f?, FourthCorner: Vector3f?) {
        val oneLayer = findLayerSurface( tblLAYER?.LayerName, tblLAYER)
        val listPoint = oneLayer.listPoint
        val listConnect = oneLayer.listConnect
        val s: Int = listPoint.size/3
        if (HandlenotOmitted != null ) {
            if(FirstCorner != null && SecondCorner != null && ThirdCorner != null ){
                listPoint.add( FirstCorner.x ); listPoint.add( FirstCorner.y );  listPoint.add( FirstCorner.z )
                listPoint.add( SecondCorner.x ); listPoint.add( SecondCorner.y );  listPoint.add( SecondCorner.z )
                listPoint.add( ThirdCorner.x ); listPoint.add( ThirdCorner.y );  listPoint.add( ThirdCorner.z )
                if (FourthCorner != null){
                    if( (ThirdCorner.x == FourthCorner.x) && (ThirdCorner.y == FourthCorner.y) && (ThirdCorner.z == FourthCorner.z) ) {
                        listConnect.add(s);listConnect.add(s+1);listConnect.add(s+2)
                    }else {
                        listPoint.add(FourthCorner.x); listPoint.add(FourthCorner.y); listPoint.add(FourthCorner.z)
                        listConnect.add(s);listConnect.add(s + 1);listConnect.add(s + 3)
                        listConnect.add(s + 1);listConnect.add(s + 2);listConnect.add(s + 3)
                    }
                }else  {
                    listConnect.add(s);listConnect.add(s+1);listConnect.add(s+2)
                }
            }
        }
    }

    ///////////////////////// SPLINE /////////////////////////////////////
    val mapOnePolyline: MutableMap<String, OnePolyline> = mutableMapOf()
    private fun findLayerLine( tblLAYER: TblLAYER?): OnePolyline {
        val LayerName  = tblLAYER?.LayerName
        if( LayerName == null || LayerName == "NULL"){
            if( !mapOnePolyline.containsKey( "NULL") ){
                val onePolyline = OnePolyline( tblLAYER)
                mapOnePolyline["NULL"] = onePolyline
                listOnePolyline.add( onePolyline)
            }
            return mapOnePolyline["NULL"]!!
        }else{
            if( !mapOnePolyline.containsKey( LayerName ) ){
                val onePolyline = OnePolyline( tblLAYER)
                mapOnePolyline[LayerName] = onePolyline
                listOnePolyline.add( onePolyline)
            }
            return mapOnePolyline[LayerName]!!
        }
    }

    fun collectPointConnection( HandlenotOmitted: Int?, list: List<Vector3f>, layer: TblLAYER?) {
        val oneLayer = findLayerLine( layer )
        val mapPointList = oneLayer.mapPointList
        val mapConnect = oneLayer.mapConnect
        if (HandlenotOmitted != null) {
            var nowNumberConn: Int = oneLayer.pointerConn //mapConnect.size
            val connList = mutableListOf<Int>()
            val listVector = mutableListOf<Vector3f>()
            for ( spPoint in list ) {
                val newVec = Vector3f(spPoint)
                listVector.add( newVec)

                nowNumberConn++
                connList.add(nowNumberConn)
            }
            oneLayer.pointerConn += connList.size
            mapPointList[HandlenotOmitted] = list
            mapConnect[HandlenotOmitted] = connList
        }
    }

    fun collectPointConnectionNewOneL(HandlenotOmitted: Int?, list: List<Vector3f>) {
//        val oneLayer = findLayerLine( layer )
        val oneLayer = getLastLayer()
        val mapPointList = oneLayer.mapPointList
        val mapConnect = oneLayer.mapConnect
        if (HandlenotOmitted != null) {
            var nowNumberConn: Int = oneLayer.pointerConn //mapConnect.size
            val connList = mutableListOf<Int>()
            val listVector = mutableListOf<Vector3f>()
            for ( spPoint in list ) {
                listVector.add( Vector3f(spPoint) )
                nowNumberConn++
                connList.add(nowNumberConn)
            }
            oneLayer.pointerConn += connList.size
            mapPointList[nowNumberConn] = list
            mapConnect[nowNumberConn] = connList
        }
    }

    ///////////////////////// POLYLINE ///////////////////////////////////
    val listOnePolyline: MutableList<OnePolyline> = mutableListOf()
    private fun getLastLayer( ): OnePolyline {
        if( listOnePolyline.isEmpty() ){
            listOnePolyline.add( OnePolyline(null) )
        }
        return listOnePolyline.last()
    }

    fun createNewOneLayer(LayerNamenot: TblLAYER?){
        listOnePolyline.add( OnePolyline( LayerNamenot) )
    }

    fun collectVortex(HandlenotOmitted: Int?, LocationPoint: Vector3f?) {
        val oneLayer = getLastLayer( )
        val mapPoint = oneLayer.mapPoint
        if (HandlenotOmitted != null) {
            val newVec = Vector3f(LocationPoint)
            mapPoint[ HandlenotOmitted ] = newVec
        }
    }

    fun collectVortexOnlyConnection( HandlenotOmitted: Int?,
                                    PolyfaceMesh: Int?, PolyfaceMesh1: Int?, PolyfaceMesh2: Int?, PolyfaceMesh3: Int?) {
        val oneLayer = getLastLayer( )
        val mapConnect = oneLayer.mapConnect
            if (HandlenotOmitted != null) {
                val connList = mutableListOf<Int>()
                if( PolyfaceMesh != null)   connList.add( PolyfaceMesh)
                if( PolyfaceMesh1 != null)  connList.add( PolyfaceMesh1)
                if( PolyfaceMesh2 != null)  connList.add( PolyfaceMesh2)
                if( PolyfaceMesh3 != null)  connList.add( PolyfaceMesh3)
                mapConnect[ HandlenotOmitted ] = connList
            }
    }


    fun collectVortexOnlyConnection( HandlenotOmitted: Int?, PolyfaceList: MutableList<Int>) {
        val oneLayer = getLastLayer( )
        val mapConnect = oneLayer.mapConnect
        val index = oneLayer.pointerConn
        mapConnect[ index ] = PolyfaceList
        oneLayer.pointerConn += PolyfaceList.size

    }


    fun collectVortexLoop (HandlenotOmitted: Int?, LocationPoint: Vector3f?) {
        val oneLayer = getLastLayer( )
        val mapPoint = oneLayer.mapPoint
        val mapConnect = oneLayer.mapConnect
        if (HandlenotOmitted != null) {
            val newVec = Vector3f(LocationPoint)
            mapPoint[ HandlenotOmitted ] = newVec

            val nowNumberConn: Int = mapConnect.size
            val connList = mutableListOf<Int>()
            if(mapConnect.isNotEmpty()){ // no first later add to last
                connList.add( nowNumberConn )
            }
            connList.add( nowNumberConn +1)
            mapConnect[ HandlenotOmitted ] = connList
        }
    }

    fun closeConnVertexLoop(){
            val oneLayer = getLastLayer( )
            val mapConnect = oneLayer.mapConnect
            if (mapConnect.isNotEmpty()) {
                val lastKey = mapConnect.entries.last()
                val mConnList = mapConnect[lastKey.key]!!
                mConnList.add( 1)
            }
    }


    fun getArrElementLine(): List<OnePolyline>{
        getArrXYZLine()
        getArrBegEndLine()
        return listOnePolyline
    }

    private fun getArrXYZLine(): FloatArray{
        var count = 0
        val listXYZ: MutableList<Float> = mutableListOf()
        for(  oneLayer in listOnePolyline) {
            for ((k, v) in oneLayer.mapPoint) {
                if (v != null) {
                    listXYZ.add(v.x)
                    listXYZ.add(v.y)
                    listXYZ.add(v.z)
                    oneLayer.listPoint.add( v.x )
                    oneLayer.listPoint.add( v.y )
                    oneLayer.listPoint.add( v.z )
                    oneLayer.mapKeyToCount[k] = count
                    count++
                }
            }
            for ((k, listVec) in oneLayer.mapPointList) {
                for (v in listVec) {
                    listXYZ.add(v.x)
                    listXYZ.add(v.y)
                    listXYZ.add(v.z)
                    oneLayer.listPoint.add(v.x)
                    oneLayer.listPoint.add(v.y)
                    oneLayer.listPoint.add(v.z)
                    oneLayer.mapKeyToCount[k] = count
                    count++
                }
            }
        }
        return  listXYZ.toFloatArray()
    }

    var closeConn = 0
    private fun getArrBegEndLine(): IntArray{
        val listLines: MutableList<Int> = mutableListOf()
        var lastConn: Int = -1
        var count: Int = 0
        for(  oneLayer in listOnePolyline) {
            for ((k, mutList) in oneLayer.mapConnect) {
                if (mutList != null && mutList.isNotEmpty()) {
                    count++
                    lastConn = -1
                    for (oneConn in mutList) {
                        if (lastConn == -1) {
                            lastConn = oneConn
                        } else {
                            listLines.add((lastConn - 1))
                            listLines.add((oneConn - 1))
                            oneLayer.listConnect.add((lastConn - 1))
                            oneLayer.listConnect.add((oneConn - 1))
                            lastConn = oneConn
                        }
                    }
                }
            }
        }
//        println("      COUNT17   $count")
//        listLines.add((3-1).toShort())
//        listLines.add((5-1).toShort())
        if(count == 0 && listOnePolyline.isNotEmpty()){ //divide for singiel elements
            val listConn =  findThreeNearestPoint( listOnePolyline )
            listOnePolyline.first().listConnect += listConn.toMutableList()
            return listConn.toIntArray()
        }
        return  listLines.toIntArray()
    }

    private fun findThreeNearestPoint(listOnePolyline: MutableList<OnePolyline>? ): MutableList<Int>{
        val mapPoint: MutableMap<Int, Vector3f?> = mutableMapOf()
        var count = 1
        if (listOnePolyline != null) {
            for( oneLayer in listOnePolyline){
                for( (kk, vec) in oneLayer.mapPoint) {
                    if( vec != null) {
                        mapPoint[ count] = vec
                        count++
                    }
                }
            }
        }
        val list: List< MutableList< Float?>> = List( mapPoint.size+1 ){ MutableList(mapPoint.size+1){ null} }
        for( indA in 1..mapPoint.size){
            for( indB in (indA+1)..mapPoint.size){
                if( mapPoint[indA] == null || mapPoint[indB] == null) continue
                val distance = distanceTo(mapPoint[indA]!!, mapPoint[indB]!!   )
                list[indA][indB] = distance
                list[indB][indA] = distance
            }
        }
        // check intersection for all line and remove
        // /// // // //
        // check left 3 small value
        for( indA in 1..mapPoint.size){
            var vA = Float.MAX_VALUE
            var vB = Float.MAX_VALUE
            var vC = Float.MAX_VALUE
            var vD = Float.MAX_VALUE
            for( indB in 1..mapPoint.size){
                val distance = list[indA][indB] ?: continue
                if( vA > distance ) {
                    vD = vC
                    vC = vB
                    vB = vA
                    vA = distance
                }else if( vB > distance){
                    vD = vC
                    vC = vB
                    vB = distance
                }else if( vC > distance) {
                    vD = vC
                    vC = distance
                }else if( vD > distance) vD = distance
            }
            for( indB in 1..mapPoint.size) {
                val distance = list[indA][indB] ?: continue
                if( distance > vC) list[indA][indB] = null
            }
        }
        //remove double connection
        for( indA in 1..mapPoint.size) {
            for (indB in 1..mapPoint.size) {
                val distance = list[indA][indB] ?: continue
                if( indA >= indB )
                    list[indA][indB] = null
            }
        }
        // point to point line
        val intConn: MutableList<Int> = mutableListOf()
        for( indA in 1..mapPoint.size) {
            for (indB in 1..mapPoint.size) {
                val distance = list[indA][indB] ?: continue
                intConn.add( (indA-1) )
                intConn.add( (indB-1))
            }
        }

        return intConn
    }

    private fun distanceTo(vecA: Vector3f, vecB: Vector3f): Float {
        val deltaX = vecA.x - vecB.x
        val deltaY = vecA.y - vecB.y
        val deltaZ = vecA.z - vecB.z
        return sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ)
    }
    //////////////////////////// LINE /////////////////////////

//    val listOneLine: MutableMap<String ,OnePolyline> = mutableMapOf()
//    private fun findLayerLine(LayerName: String? , ColorNumber: Int?): OnePolyline {
//        if( LayerName == null || LayerName == "NULL"){
//            if( !listOneLine.containsKey( "NULL") ){
//                listOneLine["NULL"] = OnePolyline( ColorNumber ?: 250, 32)
//            }
//            return listOneLine["NULL"]!!
//        }else{
//            if( !listOneLine.containsKey( LayerName ) ){
//                listOneLine[LayerName] = OnePolyline( ColorNumber ?: 250, 32)
//            }
//            return listOneLine[LayerName]!!
//        }
//    }
//
//
//    fun collectLine(HandlenotOmitted: Int?, StartPoint: Vector3f?, EndpointWCS: Vector3f?) {
//        val oneLayer = findLayer( )
//        val mapPoint = oneLayer.mapPoint
//        if (HandlenotOmitted != null) {
//            val newVec = Vector3f(StartPoint)
//            mapPoint[ HandlenotOmitted ] = newVec
//        }
//    }



}
//
//    fun getArrLinesPoint(): ShortArray{
//        if( mapOneLayer.size > 1) mapOneLayer.remove("NULL")
//        val listLines: MutableList<Short> = mutableListOf()
//        var lastConn: Int = -1
//        var maxConnPoint: Int = 0
//        var maxCP: Int = 0
//        var count: Int = 0
//        for( (kL, oneLayer) in mapOneLayer) {
//            for ((k, mutList) in oneLayer.mapConnect) {
//                if (mutList != null && mutList.isNotEmpty()) {
//                    count++
//                    lastConn = -1
//                    for (oneConnPoint in mutList) {
//                        val oneConn = oneConnPoint + maxCP
//                        if (maxConnPoint < oneConn) maxConnPoint = oneConn
//                        if (lastConn == -1) {
//                            lastConn = oneConn
//                        } else {
//                            listLines.add((lastConn - 1).toShort())
//                            listLines.add((oneConn - 1).toShort())
//                            lastConn = oneConn
//                        }
//                    }
//                }
//            }
//            maxCP = maxConnPoint
//        }
////        println("      COUNT17   $count")
////        listLines.add((3-1).toShort())
////        listLines.add((5-1).toShort())
//        if(count == 0 ) return  findThreeNearestPoint( mapOneLayer ).toShortArray()
//        return  listLines.toShortArray()
//    }
//
//fun maxValConn(): Int{
//        var maxVal : Int = Int.MIN_VALUE
//        for( (k, mapOfConn) in mapConnect) {
//            if (mapOfConn != null) {
//                for (oneConn in mapOfConn)
//                    maxVal = max(oneConn, maxVal)
//            }
//        }
//        return maxVal
//    }
//
//    fun minValConn(): Int{
//        var minVal : Int = Int.MAX_VALUE
//        for( (k, mapOfConn) in mapConnect) {
//            if (mapOfConn != null) {
//                for (oneConn in mapOfConn)
//                    minVal = min(oneConn, minVal)
//            }
//        }
//        return minVal
//    }
//
//    fun howManyConn(): Int{
//        var manyConn : Int = 0
//        for( (k, mapOfConn) in mapConnect) {
//            if (mapOfConn != null) {
//                    manyConn += mapOfConn.size
//            }
//        }
//        return manyConn
//    }
//