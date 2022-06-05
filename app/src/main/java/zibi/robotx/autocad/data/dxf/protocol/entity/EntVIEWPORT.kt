package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class EntVIEWPORT(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Center point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of center point (in WCS)
	//	10	20	30	
	var CenterPoint: Vector3f? = null

	//	 Width in paper space units
	//	40	
	var WidthIn: Float? = null

	//	 Height in paper space units
	//	41	
	var HeightIn: Float? = null

	//	 Viewport status field:, -1 = On, but is fully off screen, or is one of the viewports that is not active because the $MAXACTVP count is currently being exceeded., 0 = Off, <positive value > = On and active. The value indicates the order of stacking for the viewports, where 1 is the active viewport, 2 is the next, and so forth
	//	68	
	var ViewportStatus: Int? = null

	//	 Viewport ID
	//	69	
	var ViewportID: Int? = null

	//	 View center point (in DCS), DXF: X value APP: 2D pointDXF: View center point Y value (in DCS)
	//	12	22	
	var ViewCenter: Vector2f? = null

	//	 Snap base point, DXF: X value APP: 2D pointDXF: Snap base point Y value
	//	13	23	
	var SnapBase: Vector2f? = null

	//	 Snap spacing, DXF: X value APP: 2D pointDXF: Snap spacing Y value
	//	14	24	
	var SnapSpacing: Vector2f? = null

	//	 Grid spacing, DXF: X value APP: 2D pointDXF: Grid spacing Y value
	//	15	25	
	var GridSpacing: Vector2f? = null

	//	 View direction vector (in WCS), DXF: X value APP: 3D vectorDXF: Y and Z values of view direction vector (in WCS)
	//	16	26	36	
	var ViewDirection: Vector3f? = null

	//	 View target point (in WCS), DXF: X value APP: 3D vectorDXF: Y and Z values of view target point (in WCS)
	//	17	27	37	
	var ViewTarget: Vector3f? = null

	//	 Perspective lens length
	//	42	
	var PerspectiveLens: Float? = null

	//	 Front clip plane Z value
	//	43	
	var FrontClip: Float? = null

	//	 Back clip plane Z value
	//	44	
	var BackClip: Float? = null

	//	 View height (in model space units)
	//	45	
	var ViewHeight: Float? = null

	//	 Snap angle
	//	50	
	var SnapAngle: Float? = null

	//	 View twist angle
	//	51	
	var ViewTwist: Float? = null

	//	 Circle zoom percent
	//	72	
	var CircleZoom: Int? = null

	//	 Frozen layer object ID/handle (multiple entries may exist) (optional)
	//	331	
	var FrozenLayer: String? = null

	//	 Viewport status bit-coded flags:, 1 (0x1) = Enables perspective mode, 2 (0x2) = Enables front clipping, 4 (0x4) = Enables back clipping, 8 (0x8) = Enables UCS follow, 16 (0x10) = Enables front clip not at eye, 32 (0x20) = Enables UCS icon visibility, 64 (0x40) = Enables UCS icon at origin, 128 (0x80) = Enables fast zoom, 256 (0x100) = Enables snap mode, 512 (0x200) = Enables grid mode, 1024 (0x400) = Enables isometric snap style, 2048 (0x800) = Enables hide plot mode, 4096 (0x1000) = kIsoPairTop. If set and kIsoPairRight is not set, then isopair top is enabled. If both kIsoPairTop and kIsoPairRight are set, then isopair left is enabled, 8192 (0x2000) = kIsoPairRight. If set and kIsoPairTop is not set, then isopair right is enabled, 16384 (0x4000) = Enables viewport zoom locking, 32768 (0x8000) = Currently always enabled, 65536 (0x10000) = Enables non-rectangular clipping, 131072 (0x20000) = Turns the viewport off, 262144 (0x40000) = Enables the display of the grid beyond the drawing limits, 524288 (0x80000) = Enable adaptive grid display, 1048576 (0x100000) = Enables subdivision of the grid below the set grid spacing when the grid display is adaptive, 2097152 (0x200000) = Enables grid follows workplane switching
	//	90	
	var ViewportStatus1: Int? = null

	//	 Hard-pointer ID/handle to entity that serves as the viewport's clipping boundary (only present if viewport is non-rectangular)
	//	340	
	var HardpointerIDhandle: String? = null

	//	 Plot style sheet name assigned to this viewport
	//	1	
	var PlotStyle: String? = null

	//	 Render mode:, 0 = 2D Optimized (classic 2D), 1 = Wireframe, 2 = Hidden line, 3 = Flat shaded, 4 = Gouraud shaded, 5 = Flat shaded with wireframe, 6 = Gouraud shaded with wireframe, All rendering modes other than 2D Optimized engage the new 3D graphics pipeline. These values directly correspond to the SHADEMODE command and the AcDbAbstractViewTableRecord::RenderMode enum
	//	281	
	var RenderMode: Int? = null

	//	 UCS per viewport flag:, 0 = The UCS will not change when this viewport becomes active., 1 = This viewport stores its own UCS which will become the current UCS whenever the viewport is activated
	//	71	
	var UCSPer: Int? = null

	//	 Display UCS icon at UCS origin flag:, Controls whether UCS icon represents viewport UCS or current UCS (these will be different if UCSVP is 1 and viewport is not active). However, this field is currently being ignored and the icon always represents the viewport UCS
	//	74	
	var DisplayUCS: Int? = null

	//	 UCS origin, DXF: X value APP: 3D pointDXF: Y and Z values of UCS origin
	//	110	120	130	
	var UCSOrigin: Vector3f? = null

	//	 UCS X-axis, DXF: X value APP: 3D vectorDXF: Y and Z values of UCS X-axis
	//	111	121	131	
	var UCSXaxis: Vector3f? = null

	//	 UCS Y-axis, DXF: X value APP: 3D vectorDXF: Y and Z values of UCS Y-axis
	//	112	122	132	
	var UCSYaxis: Vector3f? = null

	//	 ID/handle of AcDbUCSTableRecord if UCS is a named UCS. If not present, then UCS is unnamed
	//	345	
	var IDhandleAcDbUCSTableRecord: String? = null

	//	 ID/handle of AcDbUCSTableRecord of base UCS if UCS is orthographic (79 code is non-zero). If not present and 79 code is non-zero, then base UCS is taken to be WORLD
	//	346	
	var IDhandleAcDbUCSTableRecord1: String? = null

	//	 Orthographic type of UCS:, 0 = UCS is not orthographic, 1 = Top 2 = Bottom, 3 = Front 4 = Back, 5 = Left 6 = Right
	//	79	
	var OrthographicType: Int? = null

	//	 Elevation
	//	146	
	var Elevation: Float? = null

	//	 ShadePlot mode:, 0 = As Displayed, 1 = Wireframe, 2 = Hidden, 3 = Rendered
	//	170	
	var ShadePlotMode: Int? = null

	//	 Frequency of major grid lines compared to minor grid lines
	//	61	
	var FrequencyMajor: Int? = null

	//	 Background ID/Handle (optional)
	//	332	
	var BackgroundIDHandle: String? = null

	//	 Shade plot ID/Handle (optional)
	//	333	
	var ShadePlot: String? = null

	//	 Visual style ID/Handle (optional)
	//	348	
	var VisualStyle: String? = null

	//	 Default lighting flag. On when no user lights are specified.
	//	292	
	var DefaultLighting: Boolean? = null

	//	 Default lighting type:, 0 = One distant light, 1 = Two distant lights
	//	282	
	var DefaultLighting1: Int? = null

	//	 View brightness
	//	141	
	var ViewBrightness: Float? = null

	//	 View contrast
	//	142	
	var ViewContrast: Float? = null

	//	 Ambient light color. Write only if not black color.
	//	63	
	var AmbientLight: Int? = null

	//	 Sun ID/Handle (optional)
	//	361	
	var SunIDHandle: String? = null

	//	 Soft pointer reference to viewport object (for layer VP property override)
	//	335	
	var SoftPointer: String? = null

	//	 Soft pointer reference to viewport object (for layer VP property override)
	//	343	
	var SoftPointer1: String? = null

	//	 Soft pointer reference to viewport object (for layer VP property override)
	//	344	
	var SoftPointer2: String? = null

	//	 Soft pointer reference to viewport object (for layer VP property override)
	//	91	
	var SoftPointer3: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( CenterPoint == null ) CenterPoint = Vector3f()
				CenterPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( CenterPoint == null ) CenterPoint = Vector3f()
				CenterPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( CenterPoint == null ) CenterPoint = Vector3f()
				CenterPoint?.z = getbfr.floatValue()
			}
			"40" -> {
				WidthIn = getbfr.floatValue()
			}
			"41" -> {
				HeightIn = getbfr.floatValue()
			}
			"68" -> {
				ViewportStatus = getbfr.intValue()
			}
			"69" -> {
				ViewportID = getbfr.intValue()
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
				PerspectiveLens = getbfr.floatValue()
			}
			"43" -> {
				FrontClip = getbfr.floatValue()
			}
			"44" -> {
				BackClip = getbfr.floatValue()
			}
			"45" -> {
				ViewHeight = getbfr.floatValue()
			}
			"50" -> {
				SnapAngle = getbfr.floatValue()
			}
			"51" -> {
				ViewTwist = getbfr.floatValue()
			}
			"72" -> {
				CircleZoom = getbfr.intValue()
			}
			"331" -> {
				FrozenLayer = getbfr.stringValue()
			}
			"90" -> {
				ViewportStatus1 = getbfr.intValue()
			}
			"340" -> {
				HardpointerIDhandle = getbfr.stringValue()
			}
			"1" -> {
				PlotStyle = getbfr.stringValue()
			}
			"281" -> {
				RenderMode = getbfr.intValue()
			}
			"71" -> {
				UCSPer = getbfr.intValue()
			}
			"74" -> {
				DisplayUCS = getbfr.intValue()
			}
			"110" -> {
				if( UCSOrigin == null ) UCSOrigin = Vector3f()
				UCSOrigin?.x = getbfr.floatValue()
			}
			"120" -> {
				if( UCSOrigin == null ) UCSOrigin = Vector3f()
				UCSOrigin?.y = getbfr.floatValue()
			}
			"130" -> {
				if( UCSOrigin == null ) UCSOrigin = Vector3f()
				UCSOrigin?.z = getbfr.floatValue()
			}
			"111" -> {
				if( UCSXaxis == null ) UCSXaxis = Vector3f()
				UCSXaxis?.x = getbfr.floatValue()
			}
			"121" -> {
				if( UCSXaxis == null ) UCSXaxis = Vector3f()
				UCSXaxis?.y = getbfr.floatValue()
			}
			"131" -> {
				if( UCSXaxis == null ) UCSXaxis = Vector3f()
				UCSXaxis?.z = getbfr.floatValue()
			}
			"112" -> {
				if( UCSYaxis == null ) UCSYaxis = Vector3f()
				UCSYaxis?.x = getbfr.floatValue()
			}
			"122" -> {
				if( UCSYaxis == null ) UCSYaxis = Vector3f()
				UCSYaxis?.y = getbfr.floatValue()
			}
			"132" -> {
				if( UCSYaxis == null ) UCSYaxis = Vector3f()
				UCSYaxis?.z = getbfr.floatValue()
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
				ShadePlotMode = getbfr.intValue()
			}
			"61" -> {
				FrequencyMajor = getbfr.intValue()
			}
			"332" -> {
				BackgroundIDHandle = getbfr.stringValue()
			}
			"333" -> {
				ShadePlot = getbfr.stringValue()
			}
			"348" -> {
				VisualStyle = getbfr.stringValue()
			}
			"292" -> {
				DefaultLighting = getbfr.booleanValue()
			}
			"282" -> {
				DefaultLighting1 = getbfr.intValue()
			}
			"141" -> {
				ViewBrightness = getbfr.floatValue()
			}
			"142" -> {
				ViewContrast = getbfr.floatValue()
			}
			"63" -> {
				AmbientLight = getbfr.intValue()
			}
			"361" -> {
				SunIDHandle = getbfr.stringValue()
			}
			"335" -> {
				SoftPointer = getbfr.stringValue()
			}
			"343" -> {
				SoftPointer1 = getbfr.stringValue()
			}
			"344" -> {
				SoftPointer2 = getbfr.stringValue()
			}
			"91" -> {
				SoftPointer3 = getbfr.intValue()
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


	//	 Subclass marker (AcDbViewport)
	//	100	
	const val AcDbViewport: String = "AcDbViewport"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( CenterPoint != null) sb.append( "\n 10\n"+CenterPoint?.x+"\n 20\n"+CenterPoint?.y+"\n 30\n"+CenterPoint?.z )
		if( WidthIn != null) sb.append( "\n 40\n"+WidthIn )
		if( HeightIn != null) sb.append( "\n 41\n"+HeightIn )
		if( ViewportStatus != null) sb.append( "\n 68\n"+ViewportStatus )
		if( ViewportID != null) sb.append( "\n 69\n"+ViewportID )
		if( ViewCenter != null) sb.append( "\n 12\n"+ViewCenter?.x+"\n 22\n"+ViewCenter?.y )
		if( SnapBase != null) sb.append( "\n 13\n"+SnapBase?.x+"\n 23\n"+SnapBase?.y )
		if( SnapSpacing != null) sb.append( "\n 14\n"+SnapSpacing?.x+"\n 24\n"+SnapSpacing?.y )
		if( GridSpacing != null) sb.append( "\n 15\n"+GridSpacing?.x+"\n 25\n"+GridSpacing?.y )
		if( ViewDirection != null) sb.append( "\n 16\n"+ViewDirection?.x+"\n 26\n"+ViewDirection?.y+"\n 36\n"+ViewDirection?.z )
		if( ViewTarget != null) sb.append( "\n 17\n"+ViewTarget?.x+"\n 27\n"+ViewTarget?.y+"\n 37\n"+ViewTarget?.z )
		if( PerspectiveLens != null) sb.append( "\n 42\n"+PerspectiveLens )
		if( FrontClip != null) sb.append( "\n 43\n"+FrontClip )
		if( BackClip != null) sb.append( "\n 44\n"+BackClip )
		if( ViewHeight != null) sb.append( "\n 45\n"+ViewHeight )
		if( SnapAngle != null) sb.append( "\n 50\n"+SnapAngle )
		if( ViewTwist != null) sb.append( "\n 51\n"+ViewTwist )
		if( CircleZoom != null) sb.append( "\n 72\n"+CircleZoom )
		if( FrozenLayer != null) sb.append( "\n 331\n"+FrozenLayer )
		if( ViewportStatus1 != null) sb.append( "\n 90\n"+ViewportStatus1 )
		if( HardpointerIDhandle != null) sb.append( "\n 340\n"+HardpointerIDhandle )
		if( PlotStyle != null) sb.append( "\n 1\n"+PlotStyle )
		if( RenderMode != null) sb.append( "\n 281\n"+RenderMode )
		if( UCSPer != null) sb.append( "\n 71\n"+UCSPer )
		if( DisplayUCS != null) sb.append( "\n 74\n"+DisplayUCS )
		if( UCSOrigin != null) sb.append( "\n 110\n"+UCSOrigin?.x+"\n 120\n"+UCSOrigin?.y+"\n 130\n"+UCSOrigin?.z )
		if( UCSXaxis != null) sb.append( "\n 111\n"+UCSXaxis?.x+"\n 121\n"+UCSXaxis?.y+"\n 131\n"+UCSXaxis?.z )
		if( UCSYaxis != null) sb.append( "\n 112\n"+UCSYaxis?.x+"\n 122\n"+UCSYaxis?.y+"\n 132\n"+UCSYaxis?.z )
		if( IDhandleAcDbUCSTableRecord != null) sb.append( "\n 345\n"+IDhandleAcDbUCSTableRecord )
		if( IDhandleAcDbUCSTableRecord1 != null) sb.append( "\n 346\n"+IDhandleAcDbUCSTableRecord1 )
		if( OrthographicType != null) sb.append( "\n 79\n"+OrthographicType )
		if( Elevation != null) sb.append( "\n 146\n"+Elevation )
		if( ShadePlotMode != null) sb.append( "\n 170\n"+ShadePlotMode )
		if( FrequencyMajor != null) sb.append( "\n 61\n"+FrequencyMajor )
		if( BackgroundIDHandle != null) sb.append( "\n 332\n"+BackgroundIDHandle )
		if( ShadePlot != null) sb.append( "\n 333\n"+ShadePlot )
		if( VisualStyle != null) sb.append( "\n 348\n"+VisualStyle )
		if( DefaultLighting != null) sb.append( "\n 292\n"+DefaultLighting )
		if( DefaultLighting1 != null) sb.append( "\n 282\n"+DefaultLighting1 )
		if( ViewBrightness != null) sb.append( "\n 141\n"+ViewBrightness )
		if( ViewContrast != null) sb.append( "\n 142\n"+ViewContrast )
		if( AmbientLight != null) sb.append( "\n 63\n"+AmbientLight )
		if( SunIDHandle != null) sb.append( "\n 361\n"+SunIDHandle )
		if( SoftPointer != null) sb.append( "\n 335\n"+SoftPointer )
		if( SoftPointer1 != null) sb.append( "\n 343\n"+SoftPointer1 )
		if( SoftPointer2 != null) sb.append( "\n 344\n"+SoftPointer2 )
		if( SoftPointer3 != null) sb.append( "\n 91\n"+SoftPointer3 )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nVIEWPORT")
			sbX.append( sb)
		}

		 return sbX
	}}