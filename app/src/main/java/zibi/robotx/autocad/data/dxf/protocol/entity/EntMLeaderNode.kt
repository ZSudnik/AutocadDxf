package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntMLeaderNode(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Has Set Last Leader Line Point
	//	290	
	var HasSet: Boolean? = null

	//	 Has Set Dogleg Vector
	//	291	
	var HasSet1: Boolean? = null

	//	 Last Leader Line Point
	//	10	20	30	
	var LastLeader: Vector3f? = null

	//	 Dogleg Vector
	//	11	21	31	
	var DoglegVector: Vector3f? = null

	//	 Break Start Point
	//	12	22	32	
	var BreakStart: Vector3f? = null

	//	 Break End Point
	//	13	23	33	
	var BreakEnd: Vector3f? = null

	//	 Leader Branch Index
	//	90	
	var LeaderBranch: Int? = null

	//	 Dogleg Length
	//	40	
	var DoglegLength: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"290" -> {
				HasSet = getbfr.booleanValue()
			}
			"291" -> {
				HasSet1 = getbfr.booleanValue()
			}
			"10" -> {
				if( LastLeader == null ) LastLeader = Vector3f()
				LastLeader?.x = getbfr.floatValue()
			}
			"20" -> {
				if( LastLeader == null ) LastLeader = Vector3f()
				LastLeader?.y = getbfr.floatValue()
			}
			"30" -> {
				if( LastLeader == null ) LastLeader = Vector3f()
				LastLeader?.z = getbfr.floatValue()
			}
			"11" -> {
				if( DoglegVector == null ) DoglegVector = Vector3f()
				DoglegVector?.x = getbfr.floatValue()
			}
			"21" -> {
				if( DoglegVector == null ) DoglegVector = Vector3f()
				DoglegVector?.y = getbfr.floatValue()
			}
			"31" -> {
				if( DoglegVector == null ) DoglegVector = Vector3f()
				DoglegVector?.z = getbfr.floatValue()
			}
			"12" -> {
				if( BreakStart == null ) BreakStart = Vector3f()
				BreakStart?.x = getbfr.floatValue()
			}
			"22" -> {
				if( BreakStart == null ) BreakStart = Vector3f()
				BreakStart?.y = getbfr.floatValue()
			}
			"32" -> {
				if( BreakStart == null ) BreakStart = Vector3f()
				BreakStart?.z = getbfr.floatValue()
			}
			"13" -> {
				if( BreakEnd == null ) BreakEnd = Vector3f()
				BreakEnd?.x = getbfr.floatValue()
			}
			"23" -> {
				if( BreakEnd == null ) BreakEnd = Vector3f()
				BreakEnd?.y = getbfr.floatValue()
			}
			"33" -> {
				if( BreakEnd == null ) BreakEnd = Vector3f()
				BreakEnd?.z = getbfr.floatValue()
			}
			"90" -> {
				LeaderBranch = getbfr.intValue()
			}
			"40" -> {
				DoglegLength = getbfr.floatValue()
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
		if( HasSet != null) sb.append( "\n 290\n"+HasSet )
		if( HasSet1 != null) sb.append( "\n 291\n"+HasSet1 )
		if( LastLeader != null) sb.append( "\n 10\n"+LastLeader?.x+"\n 20\n"+LastLeader?.y+"\n 30\n"+LastLeader?.z )
		if( DoglegVector != null) sb.append( "\n 11\n"+DoglegVector?.x+"\n 21\n"+DoglegVector?.y+"\n 31\n"+DoglegVector?.z )
		if( BreakStart != null) sb.append( "\n 12\n"+BreakStart?.x+"\n 22\n"+BreakStart?.y+"\n 32\n"+BreakStart?.z )
		if( BreakEnd != null) sb.append( "\n 13\n"+BreakEnd?.x+"\n 23\n"+BreakEnd?.y+"\n 33\n"+BreakEnd?.z )
		if( LeaderBranch != null) sb.append( "\n 90\n"+LeaderBranch )
		if( DoglegLength != null) sb.append( "\n 40\n"+DoglegLength )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMLeaderNode")
			sbX.append( sb)
		}

		 return sbX
	}}