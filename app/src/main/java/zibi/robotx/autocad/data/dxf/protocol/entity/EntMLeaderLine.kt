package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntMLeaderLine(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 VertexVertexVertex
	//	10	20	30	
	var VertexVertexVertex: Vector3f? = null

	//	 Break Point Index
	//	90	
	var BreakPoint: Int? = null

	//	 Break Start Point
	//	11	21	31	
	var BreakStart: Vector3f? = null

	//	 Break End Point
	//	12	22	32	
	var BreakEnd: Vector3f? = null

	//	 Leader Line Index
	//	91	
	var LeaderLine: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( VertexVertexVertex == null ) VertexVertexVertex = Vector3f()
				VertexVertexVertex?.x = getbfr.floatValue()
			}
			"20" -> {
				if( VertexVertexVertex == null ) VertexVertexVertex = Vector3f()
				VertexVertexVertex?.y = getbfr.floatValue()
			}
			"30" -> {
				if( VertexVertexVertex == null ) VertexVertexVertex = Vector3f()
				VertexVertexVertex?.z = getbfr.floatValue()
			}
			"90" -> {
				BreakPoint = getbfr.intValue()
			}
			"11" -> {
				if( BreakStart == null ) BreakStart = Vector3f()
				BreakStart?.x = getbfr.floatValue()
			}
			"21" -> {
				if( BreakStart == null ) BreakStart = Vector3f()
				BreakStart?.y = getbfr.floatValue()
			}
			"31" -> {
				if( BreakStart == null ) BreakStart = Vector3f()
				BreakStart?.z = getbfr.floatValue()
			}
			"12" -> {
				if( BreakEnd == null ) BreakEnd = Vector3f()
				BreakEnd?.x = getbfr.floatValue()
			}
			"22" -> {
				if( BreakEnd == null ) BreakEnd = Vector3f()
				BreakEnd?.y = getbfr.floatValue()
			}
			"32" -> {
				if( BreakEnd == null ) BreakEnd = Vector3f()
				BreakEnd?.z = getbfr.floatValue()
			}
			"91" -> {
				LeaderLine = getbfr.intValue()
			}
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
    //    return sizeMMParent?.findMinMax( begpnt, endpnt, xtruDir )
    //            ?: SizeMinMax.findMinMax( begpnt, endpnt, xtruDir )
                  return sizeMMParent
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
        return maxRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
        // collectionPoint
   }

 	override fun xdef(): Int {
	
	 return 0
	
}

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }
	 companion object {

	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( VertexVertexVertex != null) sb.append( "\n 10\n"+VertexVertexVertex?.x+"\n 20\n"+VertexVertexVertex?.y+"\n 30\n"+VertexVertexVertex?.z )
		if( BreakPoint != null) sb.append( "\n 90\n"+BreakPoint )
		if( BreakStart != null) sb.append( "\n 11\n"+BreakStart?.x+"\n 21\n"+BreakStart?.y+"\n 31\n"+BreakStart?.z )
		if( BreakEnd != null) sb.append( "\n 12\n"+BreakEnd?.x+"\n 22\n"+BreakEnd?.y+"\n 32\n"+BreakEnd?.z )
		if( LeaderLine != null) sb.append( "\n 91\n"+LeaderLine )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMLeaderLine")
			sbX.append( sb)
		}

		 return sbX
	}}