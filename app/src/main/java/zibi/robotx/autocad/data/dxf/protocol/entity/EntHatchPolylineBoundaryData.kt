package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f

class EntHatchPolylineBoundaryData(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Has bulge flag
	//	72	
	var HasBulge: Int? = null

	//	 Is closed flag
	//	73	
	var IsClosed: Int? = null

	//	 Number of polyline vertices
	//	93	
	var NumberPolyline: Int? = null

	//	 Vertex location (in OCS), DXF: X value APP: 2D point (multiple entries)DXF: Y value of vertex location (in OCS) (multiple entries)
	//	10	20	
	var VertexLocation: Vector2f? = null

	//	 Bulge (optional, default = 0)
	//	42	
	var BulgeDefault: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"72" -> {
				HasBulge = getbfr.intValue()
			}
			"73" -> {
				IsClosed = getbfr.intValue()
			}
			"93" -> {
				NumberPolyline = getbfr.intValue()
			}
			"10" -> {
				if( VertexLocation == null ) VertexLocation = Vector2f()
				VertexLocation?.x = getbfr.floatValue()
			}
			"20" -> {
				if( VertexLocation == null ) VertexLocation = Vector2f()
				VertexLocation?.y = getbfr.floatValue()
			}
			"42" -> {
				BulgeDefault = getbfr.floatValue()
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
		if( HasBulge != null) sb.append( "\n 72\n"+HasBulge )
		if( IsClosed != null) sb.append( "\n 73\n"+IsClosed )
		if( NumberPolyline != null) sb.append( "\n 93\n"+NumberPolyline )
		if( VertexLocation != null) sb.append( "\n 10\n"+VertexLocation?.x+"\n 20\n"+VertexLocation?.y )
		if( BulgeDefault != null) sb.append( "\n 42\n"+BulgeDefault )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHatchPolylineBoundaryData")
			sbX.append( sb)
		}

		 return sbX
	}}