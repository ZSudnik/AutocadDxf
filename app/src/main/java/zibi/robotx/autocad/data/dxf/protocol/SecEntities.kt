   package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.protocol.*
import zibi.robotx.autocad.data.dxf.protocol.entity.*
import zibi.robotx.autocad.data.dxf.util.*

class SecEntities(lastElem: DxfChain) : DxfChain() {
                
    override fun read(dxfContext: DxfLoaderContext) {
        var code: String = dxfContext.get()
        loop@ while (true) {
            if (dxfContext.codEquals("0")) {
				do{ code = dxfContext.get() }while(code == "0") //error from AutoCad 2016
                if (code == "ENDSEC") {
                    return
                }
                when (code) {
					"3DFACE" -> {
						Ent3DFACE( this, true)
						continue@loop
					}
					"3DSOLID" -> {
						Ent3DSOLID( this, true)
						continue@loop
					}
					"ACAD_PROXY_ENTITY" -> {
						EntACAD_PROXY_ENTITY( this, true)
						continue@loop
					}
					"ARC" -> {
						EntARC( this, true)
						continue@loop
					}
					"ATTDEF" -> {
						EntATTDEF( this, true)
						continue@loop
					}
					"ATTRIB" -> {
						EntATTRIB( this, true)
						continue@loop
					}
					"BODY" -> {
						EntBODY( this, true)
						continue@loop
					}
					"CIRCLE" -> {
						EntCIRCLE( this, true)
						continue@loop
					}
					"COORDINATION_MODEL" -> {
						EntCOORDINATION_MODEL( this, true)
						continue@loop
					}
					"DimensionCommon" -> {
						EntDimensionCommon( this, true)
						continue@loop
					}
					"DimensionAligned" -> {
						EntDimensionAligned( this, true)
						continue@loop
					}
					"DimensionAngular" -> {
						EntDimensionAngular( this, true)
						continue@loop
					}
					"DimensionLinearAndRotated" -> {
						EntDimensionLinearAndRotated( this, true)
						continue@loop
					}
					"DimensionOrdinate" -> {
						EntDimensionOrdinate( this, true)
						continue@loop
					}
					"DimensionRadialAndDiameter" -> {
						EntDimensionRadialAndDiameter( this, true)
						continue@loop
					}
					"ELLIPSE" -> {
						EntELLIPSE( this, true)
						continue@loop
					}
					"HATCH" -> {
						EntHATCH( this, true)
						continue@loop
					}
					"HELIX" -> {
						EntHELIX( this, true)
						continue@loop
					}
					"IMAGE" -> {
						EntIMAGE( this, true)
						continue@loop
					}
					"INSERT" -> {
						EntINSERT( this, true)
						continue@loop
					}
					"LEADER" -> {
						EntLEADER( this, true)
						continue@loop
					}
					"LIGHT" -> {
						EntLIGHT( this, true)
						continue@loop
					}
					"LINE" -> {
						EntLINE( this, true)
						continue@loop
					}
					"LWPOLYLINE" -> {
						EntLWPOLYLINE( this, true)
						continue@loop
					}
					"MESH" -> {
						EntMESH( this, true)
						continue@loop
					}
					"MLINE" -> {
						EntMLINE( this, true)
						continue@loop
					}
					"MTEXT" -> {
						EntMTEXT( this, true)
						continue@loop
					}
					"OLEFRAME" -> {
						EntOLEFRAME( this, true)
						continue@loop
					}
					"OLE2FRAME" -> {
						EntOLE2FRAME( this, true)
						continue@loop
					}
					"POINT" -> {
						EntPOINT( this, true)
						continue@loop
					}
					"POLYLINE" -> {
						EntPOLYLINE( this, true)
						continue@loop
					}
					"RAY" -> {
						EntRAY( this, true)
						continue@loop
					}
					"REGION" -> {
						EntREGION( this, true)
						continue@loop
					}
					"SECTION" -> {
						EntSECTION( this, true)
						continue@loop
					}
					"SHAPE" -> {
						EntSHAPE( this, true)
						continue@loop
					}
					"SOLID" -> {
						EntSOLID( this, true)
						continue@loop
					}
					"SPLINE" -> {
						EntSPLINE( this, true)
						continue@loop
					}
					"SUN" -> {
						EntSUN( this, true)
						continue@loop
					}
					"SURFACE" -> {
						EntSURFACE( this, true)
						continue@loop
					}
					"SurfaceExtruded" -> {
						EntSurfaceExtruded( this, true)
						continue@loop
					}
					"SurfaceLofted" -> {
						EntSurfaceLofted( this, true)
						continue@loop
					}
					"SurfaceRevolved" -> {
						EntSurfaceRevolved( this, true)
						continue@loop
					}
					"SurfaceSwept" -> {
						EntSurfaceSwept( this, true)
						continue@loop
					}
					"TABLE" -> {
						EntTABLE( this, true)
						continue@loop
					}
					"TEXT" -> {
						EntTEXT( this, true)
						continue@loop
					}
					"TOLERANCE" -> {
						EntTOLERANCE( this, true)
						continue@loop
					}
					"TRACE" -> {
						EntTRACE( this, true)
						continue@loop
					}
					"UNDERLAY" -> {
						EntUNDERLAY( this, true)
						continue@loop
					}
					"VERTEX" -> {
						EntVERTEX( this, true)
						continue@loop
					}
					"VIEWPORT" -> {
						EntVIEWPORT( this, true)
						continue@loop
					}
					"WIPEOUT" -> {
						EntWIPEOUT( this, true)
						continue@loop
					}
					"XLINE" -> {
						EntXLINE( this, true)
						continue@loop
					}
				}
			}
			dxfContext.get()
        }
	}

	override fun write(sb: StringBuilder): StringBuilder {
		TODO("Not yet implemented")
	}

//	val end = 1000
	override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
        var sizeMM: SizeMinMax? = sizeMMParent
        val iter = this.iterator()
        while( iter.hasNext() ) {
			val element = iter.next()
			sizeMM = element.centerObject(sizeMM)
		}
        return sizeMM
    }


    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		val iter = this.iterator()
		while( iter.hasNext() ){
			val element = iter.next()
			mRadiusSqr = element.scaleObjectToFit( mRadiusSqr )
		}
		return mRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
		val iter = this.iterator()
		while( iter.hasNext() ){
			val element = iter.next()
			element.collectionConnect( collectionPoint )
		}
   }

 	override fun xdef(): Int {
		return 0
	}


	fun get3DFACE(): Ent3DFACE {
		return Ent3DFACE( this, false)
	}
	fun get3DSOLID(): Ent3DSOLID {
		return Ent3DSOLID( this, false)
	}
	fun getACAD_PROXY_ENTITY(): EntACAD_PROXY_ENTITY {
		return EntACAD_PROXY_ENTITY( this, false)
	}
	fun getARC(): EntARC {
		return EntARC( this, false)
	}
	fun getATTDEF(): EntATTDEF {
		return EntATTDEF( this, false)
	}
	fun getATTRIB(): EntATTRIB {
		return EntATTRIB( this, false)
	}
	fun getBODY(): EntBODY {
		return EntBODY( this, false)
	}
	fun getCIRCLE(): EntCIRCLE {
		return EntCIRCLE( this, false)
	}
	fun getCOORDINATION_MODEL(): EntCOORDINATION_MODEL {
		return EntCOORDINATION_MODEL( this, false)
	}
	fun getDimensionCommon(): EntDimensionCommon {
		return EntDimensionCommon( this, false)
	}
	fun getDimensionAligned(): EntDimensionAligned  {
		return EntDimensionAligned( this, false)
	}
	fun getDimensionAngular(): EntDimensionAngular {
		return EntDimensionAngular( this, false)
	}
	fun getDimensionLinearAndRotated(): EntDimensionLinearAndRotated {
		return EntDimensionLinearAndRotated( this, false)
	}
	fun getDimensionOrdinate(): EntDimensionOrdinate {
		return EntDimensionOrdinate( this, false)
	}
	fun getDimensionRadialAndDiameter(): EntDimensionRadialAndDiameter {
		return EntDimensionRadialAndDiameter( this, false)
	}
	fun getELLIPSE(): EntELLIPSE {
		return EntELLIPSE( this, false)
	}
	fun getHATCH(): EntHATCH {
		return EntHATCH( this, false)
	}
	fun getHELIX(): EntHELIX {
		return EntHELIX( this, false)
	}
	fun getIMAGE(): EntIMAGE {
		return EntIMAGE( this, false)
	}
	fun getINSERT(): EntINSERT {
		return EntINSERT( this, false)
	}
	fun getLEADER(): EntLEADER {
		return EntLEADER( this, false)
	}
	fun getLIGHT(): EntLIGHT {
		return EntLIGHT( this, false)
	}
	fun getLINE(): EntLINE {
		return EntLINE( this, false)
	}
	fun getLWPOLYLINE(): EntLWPOLYLINE {
		return EntLWPOLYLINE( this, false)
	}
	fun getMESH(): EntMESH {
		return EntMESH( this, false)
	}
	fun getMLINE(): EntMLINE {
		return EntMLINE( this, false)
	}
	fun getMTEXT(): EntMTEXT {
		return EntMTEXT( this, false)
	}
	fun getOLEFRAME(): EntOLEFRAME {
		return EntOLEFRAME( this, false)
	}
	fun getOLE2FRAME(): EntOLE2FRAME {
		return EntOLE2FRAME( this, false)
	}
	fun getPOINT(): EntPOINT {
		return EntPOINT( this, false)
	}
	fun getPOLYLINE(): EntPOLYLINE {
		return EntPOLYLINE( this, false)
	}
	fun getRAY(): EntRAY {
		return EntRAY( this, false)
	}
	fun getREGION(): EntREGION {
		return EntREGION( this, false)
	}
	fun getSECTION(): EntSECTION {
		return EntSECTION( this, false)
	}
	fun getSHAPE(): EntSHAPE {
		return EntSHAPE( this, false)
	}
	fun getSOLID(): EntSOLID {
		return EntSOLID( this, false)
	}
	fun getSPLINE(): EntSPLINE {
		return EntSPLINE( this, false)
	}
	fun getSUN(): EntSUN {
		return EntSUN( this, false)
	}
	fun getSURFACE(): EntSURFACE {
		return EntSURFACE( this, false)
	}
	fun getSurfaceExtruded(): EntSurfaceExtruded {
		return EntSurfaceExtruded( this, false)
	}
	fun getSurfaceLofted(): EntSurfaceLofted {
		return EntSurfaceLofted( this, false)
	}
	fun getSurfaceRevolved(): EntSurfaceRevolved {
		return EntSurfaceRevolved( this, false)
	}
	fun getSurfaceSwept(): EntSurfaceSwept {
		return EntSurfaceSwept( this, false)
	}
	fun getTABLE(): EntTABLE {
		return EntTABLE( this, false)
	}
	fun getTEXT(): EntTEXT {
		return EntTEXT( this, false)
	}
	fun getTOLERANCE(): EntTOLERANCE {
		return EntTOLERANCE( this, false)
	}
	fun getTRACE(): EntTRACE {
		return EntTRACE( this, false)
	}
	fun getUNDERLAY(): EntUNDERLAY {
		return EntUNDERLAY( this, false)
	}
	fun getVERTEX(): EntVERTEX {
		return EntVERTEX( this, false)
	}
	fun getVIEWPORT(): EntVIEWPORT {
		return EntVIEWPORT( this, false)
	}
	fun getWIPEOUT(): EntWIPEOUT {
		return EntWIPEOUT( this, false)
	}
	fun getXLINE(): EntXLINE {
		return EntXLINE( this, false)
	}

    init {
        last(lastElem)
    }
}