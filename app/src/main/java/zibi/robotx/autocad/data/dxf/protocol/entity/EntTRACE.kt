package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntTRACE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 First corner (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of first corner (in OCS)
	//	10	20	30	
	var FirstCorner: Vector3f? = null

	//	 Second corner (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of second corner (in OCS)
	//	11	21	31	
	var SecondCorner: Vector3f? = null

	//	 Third corner (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of third corner (in OCS)
	//	12	22	32	
	var ThirdCorner: Vector3f? = null

	//	 Fourth corner (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of fourth corner (in OCS)
	//	13	23	33	
	var FourthCorner: Vector3f? = null

	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( FirstCorner == null ) FirstCorner = Vector3f()
				FirstCorner?.x = getbfr.floatValue()
			}
			"20" -> {
				if( FirstCorner == null ) FirstCorner = Vector3f()
				FirstCorner?.y = getbfr.floatValue()
			}
			"30" -> {
				if( FirstCorner == null ) FirstCorner = Vector3f()
				FirstCorner?.z = getbfr.floatValue()
			}
			"11" -> {
				if( SecondCorner == null ) SecondCorner = Vector3f()
				SecondCorner?.x = getbfr.floatValue()
			}
			"21" -> {
				if( SecondCorner == null ) SecondCorner = Vector3f()
				SecondCorner?.y = getbfr.floatValue()
			}
			"31" -> {
				if( SecondCorner == null ) SecondCorner = Vector3f()
				SecondCorner?.z = getbfr.floatValue()
			}
			"12" -> {
				if( ThirdCorner == null ) ThirdCorner = Vector3f()
				ThirdCorner?.x = getbfr.floatValue()
			}
			"22" -> {
				if( ThirdCorner == null ) ThirdCorner = Vector3f()
				ThirdCorner?.y = getbfr.floatValue()
			}
			"32" -> {
				if( ThirdCorner == null ) ThirdCorner = Vector3f()
				ThirdCorner?.z = getbfr.floatValue()
			}
			"13" -> {
				if( FourthCorner == null ) FourthCorner = Vector3f()
				FourthCorner?.x = getbfr.floatValue()
			}
			"23" -> {
				if( FourthCorner == null ) FourthCorner = Vector3f()
				FourthCorner?.y = getbfr.floatValue()
			}
			"33" -> {
				if( FourthCorner == null ) FourthCorner = Vector3f()
				FourthCorner?.z = getbfr.floatValue()
			}
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
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


	//	 Subclass marker (AcDbTrace)
	//	100	
	const val AcDbTrace: String = "AcDbTrace"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( FirstCorner != null) sb.append( "\n 10\n"+FirstCorner?.x+"\n 20\n"+FirstCorner?.y+"\n 30\n"+FirstCorner?.z )
		if( SecondCorner != null) sb.append( "\n 11\n"+SecondCorner?.x+"\n 21\n"+SecondCorner?.y+"\n 31\n"+SecondCorner?.z )
		if( ThirdCorner != null) sb.append( "\n 12\n"+ThirdCorner?.x+"\n 22\n"+ThirdCorner?.y+"\n 32\n"+ThirdCorner?.z )
		if( FourthCorner != null) sb.append( "\n 13\n"+FourthCorner?.x+"\n 23\n"+FourthCorner?.y+"\n 33\n"+FourthCorner?.z )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nTRACE")
			sbX.append( sb)
		}

		 return sbX
	}}