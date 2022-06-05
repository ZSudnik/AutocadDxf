package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntSurfaceExtruded(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Class ID
	//	90	
	var ClassID: Int? = null

	//	 Size of binary data
	//	90	
	var SizeBinary: Int? = null

	//	 Binary data
	//	310	
	var BinaryData: String? = null

	//	 Sweep vector
	//	10	20	30	
	var SweepVector: Vector3f? = null

	//	 Transform matrix of extruded entity (16 reals row major format default = identity matrix)
	//	40	
	var TransformMatrix: Float? = null

	//	 Draft angle (in radians)
	//	42	
	var DraftAngle: Float? = null

	//	 Draft start distance
	//	43	
	var DraftStart: Float? = null

	//	 Draft end distance
	//	44	
	var DraftEnd: Float? = null

	//	 Twist angle
	//	45	
	var TwistAngle: Float? = null

	//	 Scale factor
	//	48	
	var ScaleFactor: Float? = null

	//	 Align angle (in radians)
	//	49	
	var AlignAngle: Float? = null

	//	 Transform matrix of sweep entity (16 reals row major format default = identity matrix)
	//	46	
	var TransformMatrix1: Float? = null

	//	 Transform matrix of path entity (16 reals row major format default = identity matrix)
	//	47	
	var TransformMatrix2: Float? = null

	//	 Solid flag
	//	290	
	var SolidFlag: Boolean? = null

	//	 Sweep alignment option, 0 = No alignment, 1 = Align sweep entity to path, 2 = Translate sweep entity to path, 3 = Translate path to sweep entity
	//	70	
	var SweepAlignment: Int? = null

	//	 Align start flag
	//	292	
	var AlignStart: Boolean? = null

	//	 Bank flag
	//	293	
	var BankFlag: Boolean? = null

	//	 Base point set flag
	//	294	
	var BasePoint: Boolean? = null

	//	 Sweep entity transform computed flag
	//	295	
	var SweepEntity: Boolean? = null

	//	 Path entity transform computed flag
	//	296	
	var PathEntity: Boolean? = null

	//	 Reference vector for controlling twist
	//	11	21	31	
	var ReferenceVector: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				ClassID = getbfr.intValue()
			}
			"90" -> {
				SizeBinary = getbfr.intValue()
			}
			"310" -> {
				BinaryData = getbfr.stringValue()
			}
			"10" -> {
				if( SweepVector == null ) SweepVector = Vector3f()
				SweepVector?.x = getbfr.floatValue()
			}
			"20" -> {
				if( SweepVector == null ) SweepVector = Vector3f()
				SweepVector?.y = getbfr.floatValue()
			}
			"30" -> {
				if( SweepVector == null ) SweepVector = Vector3f()
				SweepVector?.z = getbfr.floatValue()
			}
			"40" -> {
				TransformMatrix = getbfr.floatValue()
			}
			"42" -> {
				DraftAngle = getbfr.floatValue()
			}
			"43" -> {
				DraftStart = getbfr.floatValue()
			}
			"44" -> {
				DraftEnd = getbfr.floatValue()
			}
			"45" -> {
				TwistAngle = getbfr.floatValue()
			}
			"48" -> {
				ScaleFactor = getbfr.floatValue()
			}
			"49" -> {
				AlignAngle = getbfr.floatValue()
			}
			"46" -> {
				TransformMatrix1 = getbfr.floatValue()
			}
			"47" -> {
				TransformMatrix2 = getbfr.floatValue()
			}
			"290" -> {
				SolidFlag = getbfr.booleanValue()
			}
			"70" -> {
				SweepAlignment = getbfr.intValue()
			}
			"292" -> {
				AlignStart = getbfr.booleanValue()
			}
			"293" -> {
				BankFlag = getbfr.booleanValue()
			}
			"294" -> {
				BasePoint = getbfr.booleanValue()
			}
			"295" -> {
				SweepEntity = getbfr.booleanValue()
			}
			"296" -> {
				PathEntity = getbfr.booleanValue()
			}
			"11" -> {
				if( ReferenceVector == null ) ReferenceVector = Vector3f()
				ReferenceVector?.x = getbfr.floatValue()
			}
			"21" -> {
				if( ReferenceVector == null ) ReferenceVector = Vector3f()
				ReferenceVector?.y = getbfr.floatValue()
			}
			"31" -> {
				if( ReferenceVector == null ) ReferenceVector = Vector3f()
				ReferenceVector?.z = getbfr.floatValue()
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


	//	 Subclass markar (AcDbExtrudedSurface)
	//	100	
	const val Markar: String = "AcDbExtrudedSurface"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ClassID != null) sb.append( "\n 90\n"+ClassID )
		if( SizeBinary != null) sb.append( "\n 90\n"+SizeBinary )
		if( BinaryData != null) sb.append( "\n 310\n"+BinaryData )
		if( SweepVector != null) sb.append( "\n 10\n"+SweepVector?.x+"\n 20\n"+SweepVector?.y+"\n 30\n"+SweepVector?.z )
		if( TransformMatrix != null) sb.append( "\n 40\n"+TransformMatrix )
		if( DraftAngle != null) sb.append( "\n 42\n"+DraftAngle )
		if( DraftStart != null) sb.append( "\n 43\n"+DraftStart )
		if( DraftEnd != null) sb.append( "\n 44\n"+DraftEnd )
		if( TwistAngle != null) sb.append( "\n 45\n"+TwistAngle )
		if( ScaleFactor != null) sb.append( "\n 48\n"+ScaleFactor )
		if( AlignAngle != null) sb.append( "\n 49\n"+AlignAngle )
		if( TransformMatrix1 != null) sb.append( "\n 46\n"+TransformMatrix1 )
		if( TransformMatrix2 != null) sb.append( "\n 47\n"+TransformMatrix2 )
		if( SolidFlag != null) sb.append( "\n 290\n"+SolidFlag )
		if( SweepAlignment != null) sb.append( "\n 70\n"+SweepAlignment )
		if( AlignStart != null) sb.append( "\n 292\n"+AlignStart )
		if( BankFlag != null) sb.append( "\n 293\n"+BankFlag )
		if( BasePoint != null) sb.append( "\n 294\n"+BasePoint )
		if( SweepEntity != null) sb.append( "\n 295\n"+SweepEntity )
		if( PathEntity != null) sb.append( "\n 296\n"+PathEntity )
		if( ReferenceVector != null) sb.append( "\n 11\n"+ReferenceVector?.x+"\n 21\n"+ReferenceVector?.y+"\n 31\n"+ReferenceVector?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSurfaceExtruded")
			sbX.append( sb)
		}

		 return sbX
	}}