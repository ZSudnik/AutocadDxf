package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntXLINE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 First point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of first point (in WCS)
	//	10	20	30	
	var FirstPoint: Vector3f? = null

	//	 Unit direction vector (in WCS), DXF: X value APP: 3D vectorDXF: Y and Z values of unit direction vector (in WCS)
	//	11	21	31	
	var UnitDirection: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( FirstPoint == null ) FirstPoint = Vector3f()
				FirstPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( FirstPoint == null ) FirstPoint = Vector3f()
				FirstPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( FirstPoint == null ) FirstPoint = Vector3f()
				FirstPoint?.z = getbfr.floatValue()
			}
			"11" -> {
				if( UnitDirection == null ) UnitDirection = Vector3f()
				UnitDirection?.x = getbfr.floatValue()
			}
			"21" -> {
				if( UnitDirection == null ) UnitDirection = Vector3f()
				UnitDirection?.y = getbfr.floatValue()
			}
			"31" -> {
				if( UnitDirection == null ) UnitDirection = Vector3f()
				UnitDirection?.z = getbfr.floatValue()
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


	//	 Subclass marker (AcDbXline)
	//	100	
	const val AcDbXline: String = "AcDbXline"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( FirstPoint != null) sb.append( "\n 10\n"+FirstPoint?.x+"\n 20\n"+FirstPoint?.y+"\n 30\n"+FirstPoint?.z )
		if( UnitDirection != null) sb.append( "\n 11\n"+UnitDirection?.x+"\n 21\n"+UnitDirection?.y+"\n 31\n"+UnitDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nXLINE")
			sbX.append( sb)
		}

		 return sbX
	}}