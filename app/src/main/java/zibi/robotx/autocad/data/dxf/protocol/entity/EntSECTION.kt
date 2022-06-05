package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntSECTION(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Section state
	//	90	
	var SectionState: Int? = null

	//	 Section flags
	//	91	
	var SectionFlags: Int? = null

	//	 Name
	//	1	
	var Name: String? = null

	//	 Vertical direction
	//	10	20	30	
	var VerticalDirection: Vector3f? = null

	//	 Top height
	//	40	
	var TopHeight: Float? = null

	//	 Bottom height
	//	41	
	var BottomHeight: Float? = null

	//	 Indicator transparency
	//	70	
	var IndicatorTransparency: Int? = null

	//	 Indicator color
	//	63	
	var IndicatorColor: Int? = null

	//	 Number of vertices
	//	92	
	var NumberVertices: Int? = null

	//	 Vertex (repeats for number of vertices)
	//	11	21	31	
	var VertexFor: Vector3f? = null

	//	 Number of back line vertices
	//	93	
	var NumberBack: Int? = null

	//	 Back line vertex (repeats for number of back line vertices)
	//	12	22	32	
	var BackLine: Vector3f? = null

	//	 Hard-pointer ID/handle to geometry settings object
	//	360	
	var HardpointerIDhandle: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				SectionState = getbfr.intValue()
			}
			"91" -> {
				SectionFlags = getbfr.intValue()
			}
			"1" -> {
				Name = getbfr.stringValue()
			}
			"10" -> {
				if( VerticalDirection == null ) VerticalDirection = Vector3f()
				VerticalDirection?.x = getbfr.floatValue()
			}
			"20" -> {
				if( VerticalDirection == null ) VerticalDirection = Vector3f()
				VerticalDirection?.y = getbfr.floatValue()
			}
			"30" -> {
				if( VerticalDirection == null ) VerticalDirection = Vector3f()
				VerticalDirection?.z = getbfr.floatValue()
			}
			"40" -> {
				TopHeight = getbfr.floatValue()
			}
			"41" -> {
				BottomHeight = getbfr.floatValue()
			}
			"70" -> {
				IndicatorTransparency = getbfr.intValue()
			}
			"63" -> {
				IndicatorColor = getbfr.intValue()
			}
			"92" -> {
				NumberVertices = getbfr.intValue()
			}
			"11" -> {
				if( VertexFor == null ) VertexFor = Vector3f()
				VertexFor?.x = getbfr.floatValue()
			}
			"21" -> {
				if( VertexFor == null ) VertexFor = Vector3f()
				VertexFor?.y = getbfr.floatValue()
			}
			"31" -> {
				if( VertexFor == null ) VertexFor = Vector3f()
				VertexFor?.z = getbfr.floatValue()
			}
			"93" -> {
				NumberBack = getbfr.intValue()
			}
			"12" -> {
				if( BackLine == null ) BackLine = Vector3f()
				BackLine?.x = getbfr.floatValue()
			}
			"22" -> {
				if( BackLine == null ) BackLine = Vector3f()
				BackLine?.y = getbfr.floatValue()
			}
			"32" -> {
				if( BackLine == null ) BackLine = Vector3f()
				BackLine?.z = getbfr.floatValue()
			}
			"360" -> {
				HardpointerIDhandle = getbfr.stringValue()
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


	//	 Subclass marker (AcDbSection)
	//	100	
	const val AcDbSection: String = "AcDbSection"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( SectionState != null) sb.append( "\n 90\n"+SectionState )
		if( SectionFlags != null) sb.append( "\n 91\n"+SectionFlags )
		if( Name != null) sb.append( "\n 1\n"+Name )
		if( VerticalDirection != null) sb.append( "\n 10\n"+VerticalDirection?.x+"\n 20\n"+VerticalDirection?.y+"\n 30\n"+VerticalDirection?.z )
		if( TopHeight != null) sb.append( "\n 40\n"+TopHeight )
		if( BottomHeight != null) sb.append( "\n 41\n"+BottomHeight )
		if( IndicatorTransparency != null) sb.append( "\n 70\n"+IndicatorTransparency )
		if( IndicatorColor != null) sb.append( "\n 63\n"+IndicatorColor )
		if( NumberVertices != null) sb.append( "\n 92\n"+NumberVertices )
		if( VertexFor != null) sb.append( "\n 11\n"+VertexFor?.x+"\n 21\n"+VertexFor?.y+"\n 31\n"+VertexFor?.z )
		if( NumberBack != null) sb.append( "\n 93\n"+NumberBack )
		if( BackLine != null) sb.append( "\n 12\n"+BackLine?.x+"\n 22\n"+BackLine?.y+"\n 32\n"+BackLine?.z )
		if( HardpointerIDhandle != null) sb.append( "\n 360\n"+HardpointerIDhandle )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSECTION")
			sbX.append( sb)
		}

		 return sbX
	}}