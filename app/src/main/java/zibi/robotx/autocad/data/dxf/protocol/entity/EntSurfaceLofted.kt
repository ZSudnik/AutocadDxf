package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntSurfaceLofted(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Transform matrix of loft entity (16 reals row major format default = identity matrix), Entity data for cross sections, Entity data for guide curves, Entity data for path curves
	//	40	
	var TransformMatrix: Float? = null

	//	 Plane normal lofting type
	//	70	
	var PlaneNormal: Int? = null

	//	 Start draft angle (in radians)
	//	41	
	var StartDraft: Float? = null

	//	 End draft angle (in radians)
	//	42	
	var EndDraft: Float? = null

	//	 Start draft magnitude
	//	43	
	var StartDraft1: Float? = null

	//	 End draft magnitude
	//	44	
	var EndDraft1: Float? = null

	//	 Arc length parameterization flag
	//	290	
	var ArcLength: Boolean? = null

	//	 No twist flag
	//	291	
	var NoTwist: Boolean? = null

	//	 Align direction flag
	//	292	
	var AlignDirection: Boolean? = null

	//	 Create simple surfaces flag
	//	293	
	var CreateSimple: Boolean? = null

	//	 Create closed surface flag
	//	294	
	var CreateClosed: Boolean? = null

	//	 Solid flag
	//	295	
	var SolidFlag: Boolean? = null

	//	 Create ruled surface flag
	//	296	
	var CreateRuled: Boolean? = null

	//	 Virtual guide flag
	//	297	
	var VirtualGuide: Boolean? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"40" -> {
				TransformMatrix = getbfr.floatValue()
			}
			"70" -> {
				PlaneNormal = getbfr.intValue()
			}
			"41" -> {
				StartDraft = getbfr.floatValue()
			}
			"42" -> {
				EndDraft = getbfr.floatValue()
			}
			"43" -> {
				StartDraft1 = getbfr.floatValue()
			}
			"44" -> {
				EndDraft1 = getbfr.floatValue()
			}
			"290" -> {
				ArcLength = getbfr.booleanValue()
			}
			"291" -> {
				NoTwist = getbfr.booleanValue()
			}
			"292" -> {
				AlignDirection = getbfr.booleanValue()
			}
			"293" -> {
				CreateSimple = getbfr.booleanValue()
			}
			"294" -> {
				CreateClosed = getbfr.booleanValue()
			}
			"295" -> {
				SolidFlag = getbfr.booleanValue()
			}
			"296" -> {
				CreateRuled = getbfr.booleanValue()
			}
			"297" -> {
				VirtualGuide = getbfr.booleanValue()
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


	//	 Subclass marker (AcDbLoftedSurface)
	//	100	
	const val AcDbLoftedSurface: String = "AcDbLoftedSurface"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( TransformMatrix != null) sb.append( "\n 40\n"+TransformMatrix )
		if( PlaneNormal != null) sb.append( "\n 70\n"+PlaneNormal )
		if( StartDraft != null) sb.append( "\n 41\n"+StartDraft )
		if( EndDraft != null) sb.append( "\n 42\n"+EndDraft )
		if( StartDraft1 != null) sb.append( "\n 43\n"+StartDraft1 )
		if( EndDraft1 != null) sb.append( "\n 44\n"+EndDraft1 )
		if( ArcLength != null) sb.append( "\n 290\n"+ArcLength )
		if( NoTwist != null) sb.append( "\n 291\n"+NoTwist )
		if( AlignDirection != null) sb.append( "\n 292\n"+AlignDirection )
		if( CreateSimple != null) sb.append( "\n 293\n"+CreateSimple )
		if( CreateClosed != null) sb.append( "\n 294\n"+CreateClosed )
		if( SolidFlag != null) sb.append( "\n 295\n"+SolidFlag )
		if( CreateRuled != null) sb.append( "\n 296\n"+CreateRuled )
		if( VirtualGuide != null) sb.append( "\n 297\n"+VirtualGuide )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSurfaceLofted")
			sbX.append( sb)
		}

		 return sbX
	}}