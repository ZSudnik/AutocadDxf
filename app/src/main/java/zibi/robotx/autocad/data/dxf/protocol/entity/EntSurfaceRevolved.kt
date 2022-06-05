package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntSurfaceRevolved(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 ID of revolve entity
	//	90	
	var IDRevolve: Int? = null

	//	 Size of binary data
	//	90	
	var SizeBinary: Int? = null

	//	 Binary data
	//	310	
	var BinaryData: String? = null

	//	 Axis point
	//	10	20	30	
	var AxisPoint: Vector3f? = null

	//	 Axis vector
	//	11	21	31	
	var AxisVector: Vector3f? = null

	//	 Revolve angle (in radians)
	//	40	
	var RevolveAngle: Float? = null

	//	 Start angle (in radians)
	//	41	
	var StartAngle: Float? = null

	//	 Transform matrix of revolved entity (16 reals row major format default = identity matrix)
	//	42	
	var TransformMatrix: Float? = null

	//	 Draft angle (in radians)
	//	43	
	var DraftAngle: Float? = null

	//	 Start draft distance
	//	44	
	var StartDraft: Float? = null

	//	 End draft distance
	//	45	
	var EndDraft: Float? = null

	//	 Twist angle (in radians)
	//	46	
	var TwistAngle: Float? = null

	//	 Solid flag
	//	290	
	var SolidFlag: Boolean? = null

	//	 Close to axis flag
	//	291	
	var CloseTo: Boolean? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				IDRevolve = getbfr.intValue()
			}
			"90" -> {
				SizeBinary = getbfr.intValue()
			}
			"310" -> {
				BinaryData = getbfr.stringValue()
			}
			"10" -> {
				if( AxisPoint == null ) AxisPoint = Vector3f()
				AxisPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( AxisPoint == null ) AxisPoint = Vector3f()
				AxisPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( AxisPoint == null ) AxisPoint = Vector3f()
				AxisPoint?.z = getbfr.floatValue()
			}
			"11" -> {
				if( AxisVector == null ) AxisVector = Vector3f()
				AxisVector?.x = getbfr.floatValue()
			}
			"21" -> {
				if( AxisVector == null ) AxisVector = Vector3f()
				AxisVector?.y = getbfr.floatValue()
			}
			"31" -> {
				if( AxisVector == null ) AxisVector = Vector3f()
				AxisVector?.z = getbfr.floatValue()
			}
			"40" -> {
				RevolveAngle = getbfr.floatValue()
			}
			"41" -> {
				StartAngle = getbfr.floatValue()
			}
			"42" -> {
				TransformMatrix = getbfr.floatValue()
			}
			"43" -> {
				DraftAngle = getbfr.floatValue()
			}
			"44" -> {
				StartDraft = getbfr.floatValue()
			}
			"45" -> {
				EndDraft = getbfr.floatValue()
			}
			"46" -> {
				TwistAngle = getbfr.floatValue()
			}
			"290" -> {
				SolidFlag = getbfr.booleanValue()
			}
			"291" -> {
				CloseTo = getbfr.booleanValue()
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


	//	 Subclass markar (AcDbRevolvedSurface)
	//	100	
	const val Markar: String = "AcDbRevolvedSurface"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( IDRevolve != null) sb.append( "\n 90\n"+IDRevolve )
		if( SizeBinary != null) sb.append( "\n 90\n"+SizeBinary )
		if( BinaryData != null) sb.append( "\n 310\n"+BinaryData )
		if( AxisPoint != null) sb.append( "\n 10\n"+AxisPoint?.x+"\n 20\n"+AxisPoint?.y+"\n 30\n"+AxisPoint?.z )
		if( AxisVector != null) sb.append( "\n 11\n"+AxisVector?.x+"\n 21\n"+AxisVector?.y+"\n 31\n"+AxisVector?.z )
		if( RevolveAngle != null) sb.append( "\n 40\n"+RevolveAngle )
		if( StartAngle != null) sb.append( "\n 41\n"+StartAngle )
		if( TransformMatrix != null) sb.append( "\n 42\n"+TransformMatrix )
		if( DraftAngle != null) sb.append( "\n 43\n"+DraftAngle )
		if( StartDraft != null) sb.append( "\n 44\n"+StartDraft )
		if( EndDraft != null) sb.append( "\n 45\n"+EndDraft )
		if( TwistAngle != null) sb.append( "\n 46\n"+TwistAngle )
		if( SolidFlag != null) sb.append( "\n 290\n"+SolidFlag )
		if( CloseTo != null) sb.append( "\n 291\n"+CloseTo )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSurfaceRevolved")
			sbX.append( sb)
		}

		 return sbX
	}}