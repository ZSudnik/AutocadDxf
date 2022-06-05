package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntDimensionLinearAndRotated(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Insertion point for clones of a dimension Baseline and Continue (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of insertion point for clones of a dimension Baseline and Continue (in OCS)
	//	12	22	32	
	var InsertionPoint: Vector3f? = null

	//	 Definition point for linear and angular dimensions (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of definition point for linear and angular dimensions (in WCS)
	//	13	23	33	
	var DefinitionPoint: Vector3f? = null

	//	 Definition point for linear and angular dimensions (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of definition point for linear and angular dimensions (in WCS)
	//	14	24	34	
	var DefinitionPoint1: Vector3f? = null

	//	 Angle of rotated, horizontal, or vertical dimensions
	//	50	
	var AngleRotated: Float? = null

	//	 Linear dimension types with an oblique angle have an optional group code 52. When added to the rotation angle of the linear dimension (group code 50), it gives the angle of the extension lines
	//	52	
	var LinearDimension: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"12" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.x = getbfr.floatValue()
			}
			"22" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.y = getbfr.floatValue()
			}
			"32" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.z = getbfr.floatValue()
			}
			"13" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.x = getbfr.floatValue()
			}
			"23" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.y = getbfr.floatValue()
			}
			"33" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.z = getbfr.floatValue()
			}
			"14" -> {
				if( DefinitionPoint1 == null ) DefinitionPoint1 = Vector3f()
				DefinitionPoint1?.x = getbfr.floatValue()
			}
			"24" -> {
				if( DefinitionPoint1 == null ) DefinitionPoint1 = Vector3f()
				DefinitionPoint1?.y = getbfr.floatValue()
			}
			"34" -> {
				if( DefinitionPoint1 == null ) DefinitionPoint1 = Vector3f()
				DefinitionPoint1?.z = getbfr.floatValue()
			}
			"50" -> {
				AngleRotated = getbfr.floatValue()
			}
			"52" -> {
				LinearDimension = getbfr.floatValue()
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


	//	 Subclass marker (AcDbAlignedDimension)
	//	100	
	const val AcDbAlignedDimension: String = "AcDbAlignedDimension"

	//	 Subclass marker (AcDbRotatedDimension)
	//	100	
	const val AcDbRotatedDimension: String = "AcDbRotatedDimension"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( InsertionPoint != null) sb.append( "\n 12\n"+InsertionPoint?.x+"\n 22\n"+InsertionPoint?.y+"\n 32\n"+InsertionPoint?.z )
		if( DefinitionPoint != null) sb.append( "\n 13\n"+DefinitionPoint?.x+"\n 23\n"+DefinitionPoint?.y+"\n 33\n"+DefinitionPoint?.z )
		if( DefinitionPoint1 != null) sb.append( "\n 14\n"+DefinitionPoint1?.x+"\n 24\n"+DefinitionPoint1?.y+"\n 34\n"+DefinitionPoint1?.z )
		if( AngleRotated != null) sb.append( "\n 50\n"+AngleRotated )
		if( LinearDimension != null) sb.append( "\n 52\n"+LinearDimension )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nDimensionLinearAndRotated")
			sbX.append( sb)
		}

		 return sbX
	}}