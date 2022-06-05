package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f

class EntHatchLineEdgeData(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Start point (in OCS), DXF: X value APP: 2D pointDXF: Y value of start point (in OCS)
	//	10	20	
	var StartPoint: Vector2f? = null

	//	 Endpoint (in OCS), DXF: X value APP: 2D pointDXF: Y value of endpoint (in OCS)
	//	11	21	
	var EndpointOCS: Vector2f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( StartPoint == null ) StartPoint = Vector2f()
				StartPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( StartPoint == null ) StartPoint = Vector2f()
				StartPoint?.y = getbfr.floatValue()
			}
			"11" -> {
				if( EndpointOCS == null ) EndpointOCS = Vector2f()
				EndpointOCS?.x = getbfr.floatValue()
			}
			"21" -> {
				if( EndpointOCS == null ) EndpointOCS = Vector2f()
				EndpointOCS?.y = getbfr.floatValue()
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
		if( StartPoint != null) sb.append( "\n 10\n"+StartPoint?.x+"\n 20\n"+StartPoint?.y )
		if( EndpointOCS != null) sb.append( "\n 11\n"+EndpointOCS?.x+"\n 21\n"+EndpointOCS?.y )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHatchLineEdgeData")
			sbX.append( sb)
		}

		 return sbX
	}}