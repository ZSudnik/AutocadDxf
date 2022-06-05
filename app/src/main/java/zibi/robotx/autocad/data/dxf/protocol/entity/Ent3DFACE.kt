package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class Ent3DFACE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 First corner (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of first corner (in WCS)
	//	10	20	30	
	var FirstCorner: Vector3f? = null

	//	 Second corner (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of second corner (in WCS)
	//	11	21	31	
	var SecondCorner: Vector3f? = null

	//	 Third corner (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of third corner (in WCS)
	//	12	22	32	
	var ThirdCorner: Vector3f? = null

	//	 Fourth corner (in WCS). If only three corners are entered, this is the same as the third corner, DXF: X value APP: 3D pointDXF: Y and Z values of fourth corner (in WCS)
	//	13	23	33	
	var FourthCorner: Vector3f? = null

	//	 Invisible edge flags (optional default = 0):, 1 = First edge is invisible, 2 = Second edge is invisible, 4 = Third edge is invisible, 8 = Fourth edge is invisible
	//	70	
	var InvisibleEdge: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
				"10" -> {
					if (FirstCorner == null) FirstCorner = Vector3f()
					FirstCorner?.x = getbfr.floatValue()
				}
				"20" -> {
					if (FirstCorner == null) FirstCorner = Vector3f()
					FirstCorner?.y = getbfr.floatValue()
				}
				"30" -> {
					if (FirstCorner == null) FirstCorner = Vector3f()
					FirstCorner?.z = getbfr.floatValue()
				}
				"11" -> {
					if (SecondCorner == null) SecondCorner = Vector3f()
					SecondCorner?.x = getbfr.floatValue()
				}
				"21" -> {
					if (SecondCorner == null) SecondCorner = Vector3f()
					SecondCorner?.y = getbfr.floatValue()
				}
				"31" -> {
					if (SecondCorner == null) SecondCorner = Vector3f()
					SecondCorner?.z = getbfr.floatValue()
				}
				"12" -> {
					if (ThirdCorner == null) ThirdCorner = Vector3f()
					ThirdCorner?.x = getbfr.floatValue()
				}
				"22" -> {
					if (ThirdCorner == null) ThirdCorner = Vector3f()
					ThirdCorner?.y = getbfr.floatValue()
				}
				"32" -> {
					if (ThirdCorner == null) ThirdCorner = Vector3f()
					ThirdCorner?.z = getbfr.floatValue()
				}
				"13" -> {
					if (FourthCorner == null) FourthCorner = Vector3f()
					FourthCorner?.x = getbfr.floatValue()
				}
				"23" -> {
					if (FourthCorner == null) FourthCorner = Vector3f()
					FourthCorner?.y = getbfr.floatValue()
				}
				"33" -> {
					if (FourthCorner == null) FourthCorner = Vector3f()
					FourthCorner?.z = getbfr.floatValue()
				}
				"70" -> {
					InvisibleEdge = getbfr.intValue()
				}
				else -> cod = getbfr.get()
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		if( FirstCorner==null || SecondCorner==null || ThirdCorner==null || FourthCorner==null ) return sizeMMParent
		return sizeMMParent?.findMinMax( FirstCorner, SecondCorner, ThirdCorner, FourthCorner ) ?: SizeMinMax( FirstCorner!!, SecondCorner!!, ThirdCorner!!, FourthCorner!! )
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		var rad1 = FirstCorner?.lengthSquared() ?: 0f
		if (rad1 > maxRadiusSqr) mRadiusSqr = rad1
		rad1 = SecondCorner?.lengthSquared() ?: 0f
		if (rad1 > maxRadiusSqr) mRadiusSqr = rad1
		rad1 = ThirdCorner?.lengthSquared() ?: 0f
		if (rad1 > maxRadiusSqr) mRadiusSqr = rad1
		rad1 = FourthCorner?.lengthSquared() ?: 0f
		if (rad1 > maxRadiusSqr) mRadiusSqr = rad1

		return mRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint) {
		if(LayerNamenot==null )
			LayerNamenot = dxfContext.layerValueEmpty(this)
        collectionPoint.collectSurface(HandlenotOmitted, LayerNamenot,
			FirstCorner, SecondCorner, ThirdCorner, FourthCorner)
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


	//	 Subclass marker (AcDbFace)
	//	100	
	const val AcDbFace: String = "AcDbFace"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( FirstCorner != null) sb.append( "\n 10\n"+FirstCorner?.x+"\n 20\n"+FirstCorner?.y+"\n 30\n"+FirstCorner?.z )
		if( SecondCorner != null) sb.append( "\n 11\n"+SecondCorner?.x+"\n 21\n"+SecondCorner?.y+"\n 31\n"+SecondCorner?.z )
		if( ThirdCorner != null) sb.append( "\n 12\n"+ThirdCorner?.x+"\n 22\n"+ThirdCorner?.y+"\n 32\n"+ThirdCorner?.z )
		if( FourthCorner != null) sb.append( "\n 13\n"+FourthCorner?.x+"\n 23\n"+FourthCorner?.y+"\n 33\n"+FourthCorner?.z )
		if( InvisibleEdge != null) sb.append( "\n 70\n"+InvisibleEdge )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\n3DFACE")
			sbX.append( sb)
		}

		 return sbX
	}


	fun getVERTEX(): EntVERTEX {
		return EntVERTEX( this, false)
	}


}