package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class TblVPORT(lastElem: DxfChain, isRead: Boolean) : TblCommon() {



	//	 Viewport name
	//	2	
	var ViewportName: String = ""
		private set

	//	 Standard flag values (bit-coded values):,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and does not need to be set by programs that write DXF files)
	//	70	
	var StandardFlag: Int? = null

	//	 Lower-left corner of viewport,DXF: X value APP: 2D pointDXF: Y value of lower-left corner of viewport
	//	10	20	
	var LowerleftCorner: Vector2f? = null

	//	 Upper-right corner of viewport,DXF: X value APP: 2D pointDXF: Y value of upper-right corner of viewport
	//	11	21	
	var UpperrightCorner: Vector2f? = null

	//	 View center point (in DCS),DXF: X value APP: 2D pointDXF: Y value of view center point (in DCS)
	//	12	22	
	var ViewCenter: Vector2f? = null

	//	 Snap base point (in DCS),DXF: X value APP: 2D pointDXF: Y value of snap base point (in DCS)
	//	13	23	
	var SnapBase: Vector2f? = null

	//	 Snap spacing X and Y,DXF: X value APP: 2D pointDXF: Y value of snap spacing X and Y
	//	14	24	
	var SnapSpacing: Vector2f? = null

	//	 Grid spacing X and Y,DXF: X value APP: 2D pointDXF: Y value of grid spacing X and Y
	//	15	25	
	var GridSpacing: Vector2f? = null

	//	 View direction from target point (in WCS),DXF: X value APP: 3D pointDXF: Y and Z values of view direction from target point,(in WCS)
	//	16	26	36	
	var ViewDirection: Vector3f? = null

	//	 View target point (in WCS),DXF: X value APP: 3D pointDXF: Y and Z values of view target point (in WCS)
	//	17	27	37	
	var ViewTarget: Vector3f? = null

	//	 Lens length
	//	42	
	var LensLength: Float? = null

	//	 Front clipping plane (offset from target point)
	//	43	
	var FrontClipping: Float? = null

	//	 Back clipping plane (offset from target point)
	//	44	
	var BackClipping: Float? = null

	//	 View height
	//	45	
	var ViewHeight: Float? = null

	//	 Snap rotation angle
	//	50	
	var SnapRotation: Float? = null

	//	 View twist angle
	//	51	
	var ViewTwist: Float? = null

	//	 Circle sides
	//	72	
	var CircleSides: Int? = null

	//	 Soft or hard-pointer ID/handle to frozen layer objects repeats for each frozen layers
	//	331	
	var SoftHardpointer: String? = null

	//	 Soft1 or hard-pointer ID/handle to frozen layer objects repeats for each frozen layers
	//	441	
	var Soft1Hardpointer: Int? = null

	//	 Plot style sheet
	//	1	
	var PlotStyle: String? = null

	//	 Render mode:,0 = 2D Optimized (classic 2D),1 = Wireframe,2 = Hidden line,3 = Flat shaded,4 = Gouraud shaded,5 = Flat shaded with wireframe,6 = Gouraud shaded with wireframe,All rendering modes other than 2D Optimized engage the new 3D graphics pipeline. These values directly correspond to the SHADEMODE command and the AcDbAbstractViewTableRecord::RenderMode enum
	//	281	
	var RenderMode0: Int? = null

	//	 View mode (see VIEWMODE system variable)
	//	71	
	var ViewMode: Int? = null

	//	 UCSICON setting
	//	74	
	var UCSICONSetting: Int? = null

	//	 UCS origin,DXF: X value APP: 3D pointDXF: Y and Z values of UCS origin
	//	110	120	130	
	var UCSOriginDXF: Vector3f? = null

	//	 UCS X-axis,DXF: X value APP: 3D vectorDXF: Y and Z values of UCS X-axis
	//	111	121	131	
	var UCSXaxisDXF: Vector3f? = null

	//	 UCS Y-axis,DXF: X value APP: 3D vectorDXF: Y and Z values of UCS Y-axis
	//	112	122	132	
	var UCSYaxisDXF: Vector3f? = null

	//	 ID/handle of AcDbUCSTableRecord if UCS is a named UCS. If not present, then UCS is unnamed
	//	345	
	var IDhandleAcDbUCSTableRecord: String? = null

	//	 ID/handle of AcDbUCSTableRecord of base UCS if UCS is orthographic (79 code is non-zero). If not present and 79 code is non-zero, then base UCS is taken to be WORLD
	//	346	
	var IDhandleAcDbUCSTableRecord1: String? = null

	//	 Orthographic type of UCS,0 = UCS is not orthographic,1 = Top,2 = Bottom,3 = Front,4 = Back,5 = Left,6 = Right
	//	79	
	var OrthographicType: Int? = null

	//	 Elevation
	//	146	
	var Elevation: Float? = null

	//	 Shade plot setting
	//	170	
	var ShadePlot: Int? = null

	//	 Major grid lines
	//	61	
	var MajorGrid: Int? = null

	//	 Soft-pointer ID/handle to background object (optional)
	//	332	
	var SoftpointerIDhandle: String? = null

	//	 Soft-pointer ID/handle to shade plot object (optional)
	//	333	
	var SoftpointerIDhandle1: String? = null

	//	 Hard-pointer ID/handle to visual style object (optional)
	//	348	
	var HardpointerIDhandle: String? = null

	//	 Default Lighting On flag
	//	292	
	var DefaultLighting: Int? = null

	//	 Default Lighting type,0 = One distant light,1 = Two distant lights
	//	282	
	var DefaultLighting1: Int? = null

	//	 Brightness
	//	141	
	var Brightness: Float? = null

	//	 Contrast
	//	142	
	var Contrast: Float? = null

	//	 Ambient color (only output when non-black),
	//	63	
	var AmbientColor: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				ViewportName = getbfr.stringValue()
			}
			"70" -> {
				StandardFlag = getbfr.intValue()
			}
			"10" -> {
				if( LowerleftCorner == null ) LowerleftCorner = Vector2f()
				LowerleftCorner?.x = getbfr.floatValue()
			}
			"20" -> {
				if( LowerleftCorner == null ) LowerleftCorner = Vector2f()
				LowerleftCorner?.y = getbfr.floatValue()
			}
			"11" -> {
				if( UpperrightCorner == null ) UpperrightCorner = Vector2f()
				UpperrightCorner?.x = getbfr.floatValue()
			}
			"21" -> {
				if( UpperrightCorner == null ) UpperrightCorner = Vector2f()
				UpperrightCorner?.y = getbfr.floatValue()
			}
			"12" -> {
				if( ViewCenter == null ) ViewCenter = Vector2f()
				ViewCenter?.x = getbfr.floatValue()
			}
			"22" -> {
				if( ViewCenter == null ) ViewCenter = Vector2f()
				ViewCenter?.y = getbfr.floatValue()
			}
			"13" -> {
				if( SnapBase == null ) SnapBase = Vector2f()
				SnapBase?.x = getbfr.floatValue()
			}
			"23" -> {
				if( SnapBase == null ) SnapBase = Vector2f()
				SnapBase?.y = getbfr.floatValue()
			}
			"14" -> {
				if( SnapSpacing == null ) SnapSpacing = Vector2f()
				SnapSpacing?.x = getbfr.floatValue()
			}
			"24" -> {
				if( SnapSpacing == null ) SnapSpacing = Vector2f()
				SnapSpacing?.y = getbfr.floatValue()
			}
			"15" -> {
				if( GridSpacing == null ) GridSpacing = Vector2f()
				GridSpacing?.x = getbfr.floatValue()
			}
			"25" -> {
				if( GridSpacing == null ) GridSpacing = Vector2f()
				GridSpacing?.y = getbfr.floatValue()
			}
			"16" -> {
				if( ViewDirection == null ) ViewDirection = Vector3f()
				ViewDirection?.x = getbfr.floatValue()
			}
			"26" -> {
				if( ViewDirection == null ) ViewDirection = Vector3f()
				ViewDirection?.y = getbfr.floatValue()
			}
			"36" -> {
				if( ViewDirection == null ) ViewDirection = Vector3f()
				ViewDirection?.z = getbfr.floatValue()
			}
			"17" -> {
				if( ViewTarget == null ) ViewTarget = Vector3f()
				ViewTarget?.x = getbfr.floatValue()
			}
			"27" -> {
				if( ViewTarget == null ) ViewTarget = Vector3f()
				ViewTarget?.y = getbfr.floatValue()
			}
			"37" -> {
				if( ViewTarget == null ) ViewTarget = Vector3f()
				ViewTarget?.z = getbfr.floatValue()
			}
			"42" -> {
				LensLength = getbfr.floatValue()
			}
			"43" -> {
				FrontClipping = getbfr.floatValue()
			}
			"44" -> {
				BackClipping = getbfr.floatValue()
			}
			"45" -> {
				ViewHeight = getbfr.floatValue()
			}
			"50" -> {
				SnapRotation = getbfr.floatValue()
			}
			"51" -> {
				ViewTwist = getbfr.floatValue()
			}
			"72" -> {
				CircleSides = getbfr.intValue()
			}
			"331" -> {
				SoftHardpointer = getbfr.stringValue()
			}
			"441" -> {
				Soft1Hardpointer = getbfr.intValue()
			}
			"1" -> {
				PlotStyle = getbfr.stringValue()
			}
			"281" -> {
				RenderMode0 = getbfr.intValue()
			}
			"71" -> {
				ViewMode = getbfr.intValue()
			}
			"74" -> {
				UCSICONSetting = getbfr.intValue()
			}
			"110" -> {
				if( UCSOriginDXF == null ) UCSOriginDXF = Vector3f()
				UCSOriginDXF?.x = getbfr.floatValue()
			}
			"120" -> {
				if( UCSOriginDXF == null ) UCSOriginDXF = Vector3f()
				UCSOriginDXF?.y = getbfr.floatValue()
			}
			"130" -> {
				if( UCSOriginDXF == null ) UCSOriginDXF = Vector3f()
				UCSOriginDXF?.z = getbfr.floatValue()
			}
			"111" -> {
				if( UCSXaxisDXF == null ) UCSXaxisDXF = Vector3f()
				UCSXaxisDXF?.x = getbfr.floatValue()
			}
			"121" -> {
				if( UCSXaxisDXF == null ) UCSXaxisDXF = Vector3f()
				UCSXaxisDXF?.y = getbfr.floatValue()
			}
			"131" -> {
				if( UCSXaxisDXF == null ) UCSXaxisDXF = Vector3f()
				UCSXaxisDXF?.z = getbfr.floatValue()
			}
			"112" -> {
				if( UCSYaxisDXF == null ) UCSYaxisDXF = Vector3f()
				UCSYaxisDXF?.x = getbfr.floatValue()
			}
			"122" -> {
				if( UCSYaxisDXF == null ) UCSYaxisDXF = Vector3f()
				UCSYaxisDXF?.y = getbfr.floatValue()
			}
			"132" -> {
				if( UCSYaxisDXF == null ) UCSYaxisDXF = Vector3f()
				UCSYaxisDXF?.z = getbfr.floatValue()
			}
			"345" -> {
				IDhandleAcDbUCSTableRecord = getbfr.stringValue()
			}
			"346" -> {
				IDhandleAcDbUCSTableRecord1 = getbfr.stringValue()
			}
			"79" -> {
				OrthographicType = getbfr.intValue()
			}
			"146" -> {
				Elevation = getbfr.floatValue()
			}
			"170" -> {
				ShadePlot = getbfr.intValue()
			}
			"61" -> {
				MajorGrid = getbfr.intValue()
			}
			"332" -> {
				SoftpointerIDhandle = getbfr.stringValue()
			}
			"333" -> {
				SoftpointerIDhandle1 = getbfr.stringValue()
			}
			"348" -> {
				HardpointerIDhandle = getbfr.stringValue()
			}
			"292" -> {
				DefaultLighting = getbfr.intValue()
			}
			"282" -> {
				DefaultLighting1 = getbfr.intValue()
			}
			"141" -> {
				Brightness = getbfr.floatValue()
			}
			"142" -> {
				Contrast = getbfr.floatValue()
			}
			"63" -> {
				AmbientColor = getbfr.intValue()
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


	//	 Subclass marker (AcDbViewportTableRecord)
	//	100	
	const val AcDbViewportTableRecord: String = "AcDbViewportTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ViewportName != null) sb.append( "\n 2\n"+ViewportName )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( LowerleftCorner != null) sb.append( "\n 10\n"+LowerleftCorner?.x+"\n 20\n"+LowerleftCorner?.y )
		if( UpperrightCorner != null) sb.append( "\n 11\n"+UpperrightCorner?.x+"\n 21\n"+UpperrightCorner?.y )
		if( ViewCenter != null) sb.append( "\n 12\n"+ViewCenter?.x+"\n 22\n"+ViewCenter?.y )
		if( SnapBase != null) sb.append( "\n 13\n"+SnapBase?.x+"\n 23\n"+SnapBase?.y )
		if( SnapSpacing != null) sb.append( "\n 14\n"+SnapSpacing?.x+"\n 24\n"+SnapSpacing?.y )
		if( GridSpacing != null) sb.append( "\n 15\n"+GridSpacing?.x+"\n 25\n"+GridSpacing?.y )
		if( ViewDirection != null) sb.append( "\n 16\n"+ViewDirection?.x+"\n 26\n"+ViewDirection?.y+"\n 36\n"+ViewDirection?.z )
		if( ViewTarget != null) sb.append( "\n 17\n"+ViewTarget?.x+"\n 27\n"+ViewTarget?.y+"\n 37\n"+ViewTarget?.z )
		if( LensLength != null) sb.append( "\n 42\n"+LensLength )
		if( FrontClipping != null) sb.append( "\n 43\n"+FrontClipping )
		if( BackClipping != null) sb.append( "\n 44\n"+BackClipping )
		if( ViewHeight != null) sb.append( "\n 45\n"+ViewHeight )
		if( SnapRotation != null) sb.append( "\n 50\n"+SnapRotation )
		if( ViewTwist != null) sb.append( "\n 51\n"+ViewTwist )
		if( CircleSides != null) sb.append( "\n 72\n"+CircleSides )
		if( SoftHardpointer != null) sb.append( "\n 331\n"+SoftHardpointer )
		if( Soft1Hardpointer != null) sb.append( "\n 441\n"+Soft1Hardpointer )
		if( PlotStyle != null) sb.append( "\n 1\n"+PlotStyle )
		if( RenderMode0 != null) sb.append( "\n 281\n"+RenderMode0 )
		if( ViewMode != null) sb.append( "\n 71\n"+ViewMode )
		if( UCSICONSetting != null) sb.append( "\n 74\n"+UCSICONSetting )
		if( UCSOriginDXF != null) sb.append( "\n 110\n"+UCSOriginDXF?.x+"\n 120\n"+UCSOriginDXF?.y+"\n 130\n"+UCSOriginDXF?.z )
		if( UCSXaxisDXF != null) sb.append( "\n 111\n"+UCSXaxisDXF?.x+"\n 121\n"+UCSXaxisDXF?.y+"\n 131\n"+UCSXaxisDXF?.z )
		if( UCSYaxisDXF != null) sb.append( "\n 112\n"+UCSYaxisDXF?.x+"\n 122\n"+UCSYaxisDXF?.y+"\n 132\n"+UCSYaxisDXF?.z )
		if( IDhandleAcDbUCSTableRecord != null) sb.append( "\n 345\n"+IDhandleAcDbUCSTableRecord )
		if( IDhandleAcDbUCSTableRecord1 != null) sb.append( "\n 346\n"+IDhandleAcDbUCSTableRecord1 )
		if( OrthographicType != null) sb.append( "\n 79\n"+OrthographicType )
		if( Elevation != null) sb.append( "\n 146\n"+Elevation )
		if( ShadePlot != null) sb.append( "\n 170\n"+ShadePlot )
		if( MajorGrid != null) sb.append( "\n 61\n"+MajorGrid )
		if( SoftpointerIDhandle != null) sb.append( "\n 332\n"+SoftpointerIDhandle )
		if( SoftpointerIDhandle1 != null) sb.append( "\n 333\n"+SoftpointerIDhandle1 )
		if( HardpointerIDhandle != null) sb.append( "\n 348\n"+HardpointerIDhandle )
		if( DefaultLighting != null) sb.append( "\n 292\n"+DefaultLighting )
		if( DefaultLighting1 != null) sb.append( "\n 282\n"+DefaultLighting1 )
		if( Brightness != null) sb.append( "\n 141\n"+Brightness )
		if( Contrast != null) sb.append( "\n 142\n"+Contrast )
		if( AmbientColor != null) sb.append( "\n 63\n"+AmbientColor )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nVPORT")
			sbX.append( sb)
		}

		 return sbX
	}

	constructor(lastElem: DxfChain, ViewportName: String ): this(lastElem, false){
		this.ViewportName = ViewportName
	}

}