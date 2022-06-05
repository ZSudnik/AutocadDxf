package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntLIGHT(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Version number
	//	90	
	var VersionNumber: Int? = null

	//	 Light name
	//	1	
	var LightName: String? = null

	//	 Light type:, 1 = Distant, 2 = Point, 3 = Spot
	//	70	
	var LightType: Int? = null

	//	 Status
	//	290	
	var Status: Boolean? = null

	//	 Plot glyph
	//	291	
	var PlotGlyph: Boolean? = null

	//	 Intensity
	//	40	
	var Intensity: Float? = null

	//	 Light Position, DXF: X value APP: 3D pointDXF: X, Y, and Z values of the light position
	//	10	20	30	
	var LightPosition: Vector3f? = null

	//	 Target location, DXF: X value APP: 3D pointDXF: X, Y, and Z values of the target location
	//	11	21	31	
	var TargetLocation: Vector3f? = null

	//	 Attenuation type:, 0 = None, 1 = Inverse Linear, 2 = Inverse Square
	//	72	
	var AttenuationType: Int? = null

	//	 Use attenuation limits
	//	292	
	var UseAttenuation: Boolean? = null

	//	 Attenuation start limit
	//	41	
	var AttenuationStart: Float? = null

	//	 Attenuation end limit
	//	42	
	var AttenuationEnd: Float? = null

	//	 Hotspot angle
	//	50	
	var HotspotAngle: Float? = null

	//	 Falloff angle
	//	51	
	var FalloffAngle: Float? = null

	//	 Cast shadows
	//	293	
	var CastShadows: Boolean? = null

	//	 Shadow Type, 0 = Ray traced shadows, 1 = Shadow maps
	//	73	
	var ShadowType: Int? = null

	//	 Shadow map size
	//	91	
	var ShadowMap: Int? = null

	//	 Shadow map softness
	//	280	
	var ShadowMap1: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				VersionNumber = getbfr.intValue()
			}
			"1" -> {
				LightName = getbfr.stringValue()
			}
			"70" -> {
				LightType = getbfr.intValue()
			}
			"290" -> {
				Status = getbfr.booleanValue()
			}
			"291" -> {
				PlotGlyph = getbfr.booleanValue()
			}
			"40" -> {
				Intensity = getbfr.floatValue()
			}
			"10" -> {
				if( LightPosition == null ) LightPosition = Vector3f()
				LightPosition?.x = getbfr.floatValue()
			}
			"20" -> {
				if( LightPosition == null ) LightPosition = Vector3f()
				LightPosition?.y = getbfr.floatValue()
			}
			"30" -> {
				if( LightPosition == null ) LightPosition = Vector3f()
				LightPosition?.z = getbfr.floatValue()
			}
			"11" -> {
				if( TargetLocation == null ) TargetLocation = Vector3f()
				TargetLocation?.x = getbfr.floatValue()
			}
			"21" -> {
				if( TargetLocation == null ) TargetLocation = Vector3f()
				TargetLocation?.y = getbfr.floatValue()
			}
			"31" -> {
				if( TargetLocation == null ) TargetLocation = Vector3f()
				TargetLocation?.z = getbfr.floatValue()
			}
			"72" -> {
				AttenuationType = getbfr.intValue()
			}
			"292" -> {
				UseAttenuation = getbfr.booleanValue()
			}
			"41" -> {
				AttenuationStart = getbfr.floatValue()
			}
			"42" -> {
				AttenuationEnd = getbfr.floatValue()
			}
			"50" -> {
				HotspotAngle = getbfr.floatValue()
			}
			"51" -> {
				FalloffAngle = getbfr.floatValue()
			}
			"293" -> {
				CastShadows = getbfr.booleanValue()
			}
			"73" -> {
				ShadowType = getbfr.intValue()
			}
			"91" -> {
				ShadowMap = getbfr.intValue()
			}
			"280" -> {
				ShadowMap1 = getbfr.intValue()
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


	//	 Subclass marker (AcDbLight)
	//	100	
	const val AcDbLight: String = "AcDbLight"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( VersionNumber != null) sb.append( "\n 90\n"+VersionNumber )
		if( LightName != null) sb.append( "\n 1\n"+LightName )
		if( LightType != null) sb.append( "\n 70\n"+LightType )
		if( Status != null) sb.append( "\n 290\n"+Status )
		if( PlotGlyph != null) sb.append( "\n 291\n"+PlotGlyph )
		if( Intensity != null) sb.append( "\n 40\n"+Intensity )
		if( LightPosition != null) sb.append( "\n 10\n"+LightPosition?.x+"\n 20\n"+LightPosition?.y+"\n 30\n"+LightPosition?.z )
		if( TargetLocation != null) sb.append( "\n 11\n"+TargetLocation?.x+"\n 21\n"+TargetLocation?.y+"\n 31\n"+TargetLocation?.z )
		if( AttenuationType != null) sb.append( "\n 72\n"+AttenuationType )
		if( UseAttenuation != null) sb.append( "\n 292\n"+UseAttenuation )
		if( AttenuationStart != null) sb.append( "\n 41\n"+AttenuationStart )
		if( AttenuationEnd != null) sb.append( "\n 42\n"+AttenuationEnd )
		if( HotspotAngle != null) sb.append( "\n 50\n"+HotspotAngle )
		if( FalloffAngle != null) sb.append( "\n 51\n"+FalloffAngle )
		if( CastShadows != null) sb.append( "\n 293\n"+CastShadows )
		if( ShadowType != null) sb.append( "\n 73\n"+ShadowType )
		if( ShadowMap != null) sb.append( "\n 91\n"+ShadowMap )
		if( ShadowMap1 != null) sb.append( "\n 280\n"+ShadowMap1 )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nLIGHT")
			sbX.append( sb)
		}

		 return sbX
	}}