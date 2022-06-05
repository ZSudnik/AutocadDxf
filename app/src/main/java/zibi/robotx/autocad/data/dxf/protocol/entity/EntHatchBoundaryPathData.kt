package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntHatchBoundaryPathData(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Boundary path type flag (bit coded):, 0 = Default, 1 = External, 2 = Polyline, 4 = Derived, 8 = Textbox, 16 = Outermost
	//	92	
	var BoundaryPath: Int? = null

	//	 Number of edges in this boundary path (only if boundary is not a polyline)
	//	93	
	var NumberEdges: Int? = null

	//	 Edge type (only if boundary is not a polyline):, 1 = Line, 2 = Circular arc, 3 = Elliptic arc, 4 = Spline
	//	72	
	var EdgeType: Int? = null

	//	 Number of source boundary objects
	//	97	
	var NumberSource: Int? = null

	//	 Reference to source boundary objects (multiple entries)
	//	330	
	var ReferenceTo: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"92" -> {
				BoundaryPath = getbfr.intValue()
			}
			"93" -> {
				NumberEdges = getbfr.intValue()
			}
			"72" -> {
				EdgeType = getbfr.intValue()
			}
			"97" -> {
				NumberSource = getbfr.intValue()
			}
			"330" -> {
				ReferenceTo = getbfr.stringValue()
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
		if( BoundaryPath != null) sb.append( "\n 92\n"+BoundaryPath )
		if( NumberEdges != null) sb.append( "\n 93\n"+NumberEdges )
		if( EdgeType != null) sb.append( "\n 72\n"+EdgeType )
		if( NumberSource != null) sb.append( "\n 97\n"+NumberSource )
		if( ReferenceTo != null) sb.append( "\n 330\n"+ReferenceTo )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHatchBoundaryPathData")
			sbX.append( sb)
		}

		 return sbX
	}}