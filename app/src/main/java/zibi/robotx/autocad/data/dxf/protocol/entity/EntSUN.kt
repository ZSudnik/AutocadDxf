package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntSUN(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Version number
	//	90	
	var VersionNumber: Int? = null

	//	 Status
	//	290	
	var Status: Boolean? = null

	//	 Color
	//	63	
	var Color: Int? = null

	//	 Intensity
	//	40	
	var Intensity: Float? = null

	//	 Shadows
	//	291	
	var Shadows: Boolean? = null

	//	 Julian day
	//	91	
	var JulianDay: Int? = null

	//	 Time (in seconds past midnight)
	//	92	
	var TimeSeconds: Int? = null

	//	 Daylight savings time
	//	292	
	var DaylightSavings: Boolean? = null

	//	 Shadow type, 0 = Ray traced shadows, 1 = Shadow maps
	//	70	
	var ShadowType: Int? = null

	//	 Shadow map size
	//	71	
	var ShadowMap: Int? = null

	//	 Shadow softness
	//	280	
	var ShadowSoftness: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				VersionNumber = getbfr.intValue()
			}
			"290" -> {
				Status = getbfr.booleanValue()
			}
			"63" -> {
				Color = getbfr.intValue()
			}
			"40" -> {
				Intensity = getbfr.floatValue()
			}
			"291" -> {
				Shadows = getbfr.booleanValue()
			}
			"91" -> {
				JulianDay = getbfr.intValue()
			}
			"92" -> {
				TimeSeconds = getbfr.intValue()
			}
			"292" -> {
				DaylightSavings = getbfr.booleanValue()
			}
			"70" -> {
				ShadowType = getbfr.intValue()
			}
			"71" -> {
				ShadowMap = getbfr.intValue()
			}
			"280" -> {
				ShadowSoftness = getbfr.intValue()
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

    override fun collectionConnect(collectionPoint: CollectionPoint) {
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


	//	 Subclass marker (AcDbSun)
	//	100	
	const val AcDbSun: String = "AcDbSun"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( VersionNumber != null) sb.append( "\n 90\n"+VersionNumber )
		if( Status != null) sb.append( "\n 290\n"+Status )
		if( Color != null) sb.append( "\n 63\n"+Color )
		if( Intensity != null) sb.append( "\n 40\n"+Intensity )
		if( Shadows != null) sb.append( "\n 291\n"+Shadows )
		if( JulianDay != null) sb.append( "\n 91\n"+JulianDay )
		if( TimeSeconds != null) sb.append( "\n 92\n"+TimeSeconds )
		if( DaylightSavings != null) sb.append( "\n 292\n"+DaylightSavings )
		if( ShadowType != null) sb.append( "\n 70\n"+ShadowType )
		if( ShadowMap != null) sb.append( "\n 71\n"+ShadowMap )
		if( ShadowSoftness != null) sb.append( "\n 280\n"+ShadowSoftness )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nSUN")
			sbX.append( sb)
		}

		 return sbX
	}}