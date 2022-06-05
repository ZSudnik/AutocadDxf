package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f

class EntHatchArcEdgeData(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Center point (in OCS), DXF: X value APP: 2D pointDXF: Y value of center point (in OCS)
	//	10	20	
	var CenterPoint: Vector2f? = null

	//	 Radius
	//	40	
	var Radius: Float? = null

	//	 Start angle
	//	50	
	var StartAngle: Float? = null

	//	 End angle
	//	51	
	var EndAngle: Float? = null

	//	 Is counterclockwise flag
	//	73	
	var IsCounterclockwise: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( CenterPoint == null ) CenterPoint = Vector2f()
				CenterPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( CenterPoint == null ) CenterPoint = Vector2f()
				CenterPoint?.y = getbfr.floatValue()
			}
			"40" -> {
				Radius = getbfr.floatValue()
			}
			"50" -> {
				StartAngle = getbfr.floatValue()
			}
			"51" -> {
				EndAngle = getbfr.floatValue()
			}
			"73" -> {
				IsCounterclockwise = getbfr.intValue()
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
		if( CenterPoint != null) sb.append( "\n 10\n"+CenterPoint?.x+"\n 20\n"+CenterPoint?.y )
		if( Radius != null) sb.append( "\n 40\n"+Radius )
		if( StartAngle != null) sb.append( "\n 50\n"+StartAngle )
		if( EndAngle != null) sb.append( "\n 51\n"+EndAngle )
		if( IsCounterclockwise != null) sb.append( "\n 73\n"+IsCounterclockwise )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHatchArcEdgeData")
			sbX.append( sb)
		}

		 return sbX
	}}