package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntHatchPatternData(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Pattern line angle
	//	53	
	var PatternLine: Float? = null

	//	 Pattern line base point, X component
	//	43	
	var PatternLine1: Float? = null

	//	 Pattern line base point, Y component
	//	44	
	var PatternLine2: Float? = null

	//	 Pattern line offset, X component
	//	45	
	var PatternLine3: Float? = null

	//	 Pattern line offset, Y component
	//	46	
	var PatternLine4: Float? = null

	//	 Number of dash length items
	//	79	
	var NumberDash: Int? = null

	//	 Dash length (multiple entries)
	//	49	
	var DashLength: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"53" -> {
				PatternLine = getbfr.floatValue()
			}
			"43" -> {
				PatternLine1 = getbfr.floatValue()
			}
			"44" -> {
				PatternLine2 = getbfr.floatValue()
			}
			"45" -> {
				PatternLine3 = getbfr.floatValue()
			}
			"46" -> {
				PatternLine4 = getbfr.floatValue()
			}
			"79" -> {
				NumberDash = getbfr.intValue()
			}
			"49" -> {
				DashLength = getbfr.floatValue()
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
		if( PatternLine != null) sb.append( "\n 53\n"+PatternLine )
		if( PatternLine1 != null) sb.append( "\n 43\n"+PatternLine1 )
		if( PatternLine2 != null) sb.append( "\n 44\n"+PatternLine2 )
		if( PatternLine3 != null) sb.append( "\n 45\n"+PatternLine3 )
		if( PatternLine4 != null) sb.append( "\n 46\n"+PatternLine4 )
		if( NumberDash != null) sb.append( "\n 79\n"+NumberDash )
		if( DashLength != null) sb.append( "\n 49\n"+DashLength )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHatchPatternData")
			sbX.append( sb)
		}

		 return sbX
	}}