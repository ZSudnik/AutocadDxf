package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntHELIX(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Major release number
	//	90	
	var MajorRelease: Int? = null

	//	 Maintenance release number
	//	91	
	var MaintenanceRelease: Int? = null

	//	 Axis base point
	//	10	20	30	
	var AxisBase: Vector3f? = null

	//	 Start point
	//	11	21	31	
	var StartPoint: Vector3f? = null

	//	 Axis vector
	//	12	22	32	
	var AxisVector: Vector3f? = null

	//	 Radius
	//	40	
	var Radius: Float? = null

	//	 Number of turns
	//	41	
	var NumberTurns: Float? = null

	//	 Turn height
	//	42	
	var TurnHeight: Float? = null

	//	 Handedness:, 0 = Left, 1 = Right
	//	290	
	var HandednessLeft: Boolean? = null

	//	 Constrain type:, 0 = Constrain turn height, 1 = Constrain turns, 2 = Constrain height
	//	280	
	var ConstrainType: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				MajorRelease = getbfr.intValue()
			}
			"91" -> {
				MaintenanceRelease = getbfr.intValue()
			}
			"10" -> {
				if( AxisBase == null ) AxisBase = Vector3f()
				AxisBase?.x = getbfr.floatValue()
			}
			"20" -> {
				if( AxisBase == null ) AxisBase = Vector3f()
				AxisBase?.y = getbfr.floatValue()
			}
			"30" -> {
				if( AxisBase == null ) AxisBase = Vector3f()
				AxisBase?.z = getbfr.floatValue()
			}
			"11" -> {
				if( StartPoint == null ) StartPoint = Vector3f()
				StartPoint?.x = getbfr.floatValue()
			}
			"21" -> {
				if( StartPoint == null ) StartPoint = Vector3f()
				StartPoint?.y = getbfr.floatValue()
			}
			"31" -> {
				if( StartPoint == null ) StartPoint = Vector3f()
				StartPoint?.z = getbfr.floatValue()
			}
			"12" -> {
				if( AxisVector == null ) AxisVector = Vector3f()
				AxisVector?.x = getbfr.floatValue()
			}
			"22" -> {
				if( AxisVector == null ) AxisVector = Vector3f()
				AxisVector?.y = getbfr.floatValue()
			}
			"32" -> {
				if( AxisVector == null ) AxisVector = Vector3f()
				AxisVector?.z = getbfr.floatValue()
			}
			"40" -> {
				Radius = getbfr.floatValue()
			}
			"41" -> {
				NumberTurns = getbfr.floatValue()
			}
			"42" -> {
				TurnHeight = getbfr.floatValue()
			}
			"290" -> {
				HandednessLeft = getbfr.booleanValue()
			}
			"280" -> {
				ConstrainType = getbfr.intValue()
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


	//	 Subclass marker (AcDbHelix)
	//	100	
	const val AcDbHelix: String = "AcDbHelix"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( MajorRelease != null) sb.append( "\n 90\n"+MajorRelease )
		if( MaintenanceRelease != null) sb.append( "\n 91\n"+MaintenanceRelease )
		if( AxisBase != null) sb.append( "\n 10\n"+AxisBase?.x+"\n 20\n"+AxisBase?.y+"\n 30\n"+AxisBase?.z )
		if( StartPoint != null) sb.append( "\n 11\n"+StartPoint?.x+"\n 21\n"+StartPoint?.y+"\n 31\n"+StartPoint?.z )
		if( AxisVector != null) sb.append( "\n 12\n"+AxisVector?.x+"\n 22\n"+AxisVector?.y+"\n 32\n"+AxisVector?.z )
		if( Radius != null) sb.append( "\n 40\n"+Radius )
		if( NumberTurns != null) sb.append( "\n 41\n"+NumberTurns )
		if( TurnHeight != null) sb.append( "\n 42\n"+TurnHeight )
		if( HandednessLeft != null) sb.append( "\n 290\n"+HandednessLeft )
		if( ConstrainType != null) sb.append( "\n 280\n"+ConstrainType )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHELIX")
			sbX.append( sb)
		}

		 return sbX
	}}