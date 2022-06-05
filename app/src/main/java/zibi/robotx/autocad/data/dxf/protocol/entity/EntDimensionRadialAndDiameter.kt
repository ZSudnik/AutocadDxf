package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntDimensionRadialAndDiameter(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Definition point for diameter, radius, and angular dimensions (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of definition point for diameter, radius, and angular dimensions (in WCS)
	//	15	25	35	
	var DefinitionPoint: Vector3f? = null

	//	 Leader length for radius and diameter dimensions
	//	40	
	var LeaderLength: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"15" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.x = getbfr.floatValue()
			}
			"25" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.y = getbfr.floatValue()
			}
			"35" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.z = getbfr.floatValue()
			}
			"40" -> {
				LeaderLength = getbfr.floatValue()
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


	//	 Subclass marker (AcDbRadialDimension or AcDbDiametricDimension)
	//	100	
	const val AcDbRadialDimensionAcDbDiametricDimension: String = "AcDbRadialDimension or AcDbDiametricDimension"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( DefinitionPoint != null) sb.append( "\n 15\n"+DefinitionPoint?.x+"\n 25\n"+DefinitionPoint?.y+"\n 35\n"+DefinitionPoint?.z )
		if( LeaderLength != null) sb.append( "\n 40\n"+LeaderLength )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nDimensionRadialAndDiameter")
			sbX.append( sb)
		}

		 return sbX
	}}