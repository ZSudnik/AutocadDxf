package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntTOLERANCE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Dimension style name
	//	3	
	var DimensionStyle: String? = null

	//	 Insertion point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of insertion point (in WCS)
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 String representing the visual representation of the tolerance
	//	1	
	var RepresentingThe: String? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 X-axis direction vector (in WCS), DXF: X value APP: 3D vectorDXF: Y and Z values of X-axis direction vector (in WCS)
	//	11	21	31	
	var XaxisDirection: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"3" -> {
				DimensionStyle = getbfr.stringValue()
			}
			"10" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.z = getbfr.floatValue()
			}
			"1" -> {
				RepresentingThe = getbfr.stringValue()
			}
			"210" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.x = getbfr.floatValue()
			}
			"220" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.y = getbfr.floatValue()
			}
			"230" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.z = getbfr.floatValue()
			}
			"11" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.x = getbfr.floatValue()
			}
			"21" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.y = getbfr.floatValue()
			}
			"31" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.z = getbfr.floatValue()
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


	//	 Subclass marker (AcDbFcf)
	//	100	
	const val AcDbFcf: String = "AcDbFcf"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( DimensionStyle != null) sb.append( "\n 3\n"+DimensionStyle )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( RepresentingThe != null) sb.append( "\n 1\n"+RepresentingThe )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( XaxisDirection != null) sb.append( "\n 11\n"+XaxisDirection?.x+"\n 21\n"+XaxisDirection?.y+"\n 31\n"+XaxisDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nTOLERANCE")
			sbX.append( sb)
		}

		 return sbX
	}}