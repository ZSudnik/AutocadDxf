package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class TblVIEW(lastElem: DxfChain, isRead: Boolean) : TblCommon() {



	//	 Name of view
	//	2	
	var NameView: String = ""
		private set

	//	 Standard flag values (bit-coded values):,1 = If set, this is a paper space view,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and does not need to be set by programs that write DXF files)
	//	70	
	var StandardFlag: Int? = null

	//	 View height (in DCS)
	//	40	
	var ViewHeight: Float? = null

	//	 View center point (in DCS),DXF: X value APP: 2D pointDXF: Y value of view center point (in DCS)
	//	10	20	
	var ViewCenter: Vector2f? = null

	//	 View width (in DCS)
	//	41	
	var ViewWidth: Float? = null

	//	 View direction from target (in WCS),DXF: X value APP: 3D vectorDXF: Y and Z values of view direction from target (in WCS)
	//	11	21	31	
	var ViewDirection: Vector3f? = null

	//	 Target point (in WCS),DXF: X value APP: 3D pointDXF: Y and Z values of target point (in WCS)
	//	12	22	32	
	var TargetPoint: Vector3f? = null

	//	 Lens length
	//	42	
	var LensLength: Float? = null

	//	 Front clipping plane (offset from target point)
	//	43	
	var FrontClipping: Float? = null

	//	 Back clipping plane (offset from target point)
	//	44	
	var BackClipping: Float? = null

	//	 Twist angle
	//	50	
	var TwistAngle: Float? = null

	//	 View mode (see VIEWMODE system variable)
	//	71	
	var ViewMode: Int? = null

	//	 Render mode:,0 = 2D Optimized (classic 2D),1 = Wireframe,2 = Hidden line,3 = Flat shaded,4 = Gouraud shaded,5 = Flat shaded with wireframe,6 = Gouraud shaded with wireframe,All rendering modes other than 2D Optimized engage the new 3D graphics pipeline. These values directly correspond to the SHADEMODE command and the AcDbAbstractViewTableRecord::RenderMode enum
	//	281	
	var RenderMode0: Int? = null

	//	 1 if there is a UCS associated to this view 0 otherwise
	//	72	
	var IfThere: Int? = null

	//	 1 if the camera is plottable
	//	73	
	var IfThe: Int? = null

	//	 Soft-pointer ID/handle to background object (optional)
	//	332	
	var SoftpointerIDhandle: String? = null

	//	 Soft-pointer ID/handle to live section object (optional)
	//	334	
	var SoftpointerIDhandle1: String? = null

	//	 Hard-pointer ID/handle to visual style object (optional)
	//	348	
	var HardpointerIDhandle: String? = null

	//	 Sun hard ownership ID
	//	361	
	var SunHard: String? = null

	//	 UCS origin (appears only if code 72 is set to 1),DXF: X value APP: 3D pointDXF: Y and Z values of UCS origin
	//	110	120	130	
	var UCSOrigin: Vector3f? = null

	//	 UCS X-axis (appears only if code 72 is set to 1),DXF: X value APP: 3D vectorDXF: Y and Z values of UCS X-axis
	//	111	121	131	
	var UCSXaxis: Vector3f? = null

	//	 UCS Y-axis (appears only if code 72 is set to 1),DXF: X value APP: 3D vectorDXF: Y and Z values of UCS Y-axis
	//	112	122	132	
	var UCSYaxis: Vector3f? = null

	//	 Orthographic type of UCS (appears only if code 72 is set to 1):,0 = UCS is not orthographic,1 = Top 2 = Bottom,3 = Front 4 = Back,5 = Left 6 = Right
	//	79	
	var OrthographicType: Int? = null

	//	 UCS elevation (appears only if code 72 is set to 1)
	//	146	
	var UCSElevation: Float? = null

	//	 ID/handle of AcDbUCSTableRecord if UCS is a named UCS. If not present, then UCS is unnamed (appears only if code 72 is set to 1)
	//	345	
	var IDhandleAcDbUCSTableRecord: String? = null

	//	 ID/handle of AcDbUCSTableRecord of base UCS if UCS is orthographic (79 code is non-zero). If not present and 79 code is non-zero, then base UCS is taken to be WORLD (appears only if code 72 is set to 1),
	//	346	
	var IDhandleAcDbUCSTableRecord1: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				NameView = getbfr.stringValue()
			}
			"70" -> {
				StandardFlag = getbfr.intValue()
			}
			"40" -> {
				ViewHeight = getbfr.floatValue()
			}
			"10" -> {
				if( ViewCenter == null ) ViewCenter = Vector2f()
				ViewCenter?.x = getbfr.floatValue()
			}
			"20" -> {
				if( ViewCenter == null ) ViewCenter = Vector2f()
				ViewCenter?.y = getbfr.floatValue()
			}
			"41" -> {
				ViewWidth = getbfr.floatValue()
			}
			"11" -> {
				if( ViewDirection == null ) ViewDirection = Vector3f()
				ViewDirection?.x = getbfr.floatValue()
			}
			"21" -> {
				if( ViewDirection == null ) ViewDirection = Vector3f()
				ViewDirection?.y = getbfr.floatValue()
			}
			"31" -> {
				if( ViewDirection == null ) ViewDirection = Vector3f()
				ViewDirection?.z = getbfr.floatValue()
			}
			"12" -> {
				if( TargetPoint == null ) TargetPoint = Vector3f()
				TargetPoint?.x = getbfr.floatValue()
			}
			"22" -> {
				if( TargetPoint == null ) TargetPoint = Vector3f()
				TargetPoint?.y = getbfr.floatValue()
			}
			"32" -> {
				if( TargetPoint == null ) TargetPoint = Vector3f()
				TargetPoint?.z = getbfr.floatValue()
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
			"50" -> {
				TwistAngle = getbfr.floatValue()
			}
			"71" -> {
				ViewMode = getbfr.intValue()
			}
			"281" -> {
				RenderMode0 = getbfr.intValue()
			}
			"72" -> {
				IfThere = getbfr.intValue()
			}
			"73" -> {
				IfThe = getbfr.intValue()
			}
			"332" -> {
				SoftpointerIDhandle = getbfr.stringValue()
			}
			"334" -> {
				SoftpointerIDhandle1 = getbfr.stringValue()
			}
			"348" -> {
				HardpointerIDhandle = getbfr.stringValue()
			}
			"361" -> {
				SunHard = getbfr.stringValue()
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
			"79" -> {
				OrthographicType = getbfr.intValue()
			}
			"146" -> {
				UCSElevation = getbfr.floatValue()
			}
			"345" -> {
				IDhandleAcDbUCSTableRecord = getbfr.stringValue()
			}
			"346" -> {
				IDhandleAcDbUCSTableRecord1 = getbfr.stringValue()
			}
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
    override fun collectionConnect(collectionPoint: CollectionPoint){}
 	override fun xdef(): Int { return 0 }

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }

	 companion object {
	//	 Subclass marker (AcDbViewTableRecord)
	//	100	
	const val AcDbViewTableRecord: String = "AcDbViewTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( NameView != null) sb.append( "\n 2\n"+NameView )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( ViewHeight != null) sb.append( "\n 40\n"+ViewHeight )
		if( ViewCenter != null) sb.append( "\n 10\n"+ViewCenter?.x+"\n 20\n"+ViewCenter?.y )
		if( ViewWidth != null) sb.append( "\n 41\n"+ViewWidth )
		if( ViewDirection != null) sb.append( "\n 11\n"+ViewDirection?.x+"\n 21\n"+ViewDirection?.y+"\n 31\n"+ViewDirection?.z )
		if( TargetPoint != null) sb.append( "\n 12\n"+TargetPoint?.x+"\n 22\n"+TargetPoint?.y+"\n 32\n"+TargetPoint?.z )
		if( LensLength != null) sb.append( "\n 42\n"+LensLength )
		if( FrontClipping != null) sb.append( "\n 43\n"+FrontClipping )
		if( BackClipping != null) sb.append( "\n 44\n"+BackClipping )
		if( TwistAngle != null) sb.append( "\n 50\n"+TwistAngle )
		if( ViewMode != null) sb.append( "\n 71\n"+ViewMode )
		if( RenderMode0 != null) sb.append( "\n 281\n"+RenderMode0 )
		if( IfThere != null) sb.append( "\n 72\n"+IfThere )
		if( IfThe != null) sb.append( "\n 73\n"+IfThe )
		if( SoftpointerIDhandle != null) sb.append( "\n 332\n"+SoftpointerIDhandle )
		if( SoftpointerIDhandle1 != null) sb.append( "\n 334\n"+SoftpointerIDhandle1 )
		if( HardpointerIDhandle != null) sb.append( "\n 348\n"+HardpointerIDhandle )
		if( SunHard != null) sb.append( "\n 361\n"+SunHard )
		if( UCSOrigin != null) sb.append( "\n 110\n"+UCSOrigin?.x+"\n 120\n"+UCSOrigin?.y+"\n 130\n"+UCSOrigin?.z )
		if( UCSXaxis != null) sb.append( "\n 111\n"+UCSXaxis?.x+"\n 121\n"+UCSXaxis?.y+"\n 131\n"+UCSXaxis?.z )
		if( UCSYaxis != null) sb.append( "\n 112\n"+UCSYaxis?.x+"\n 122\n"+UCSYaxis?.y+"\n 132\n"+UCSYaxis?.z )
		if( OrthographicType != null) sb.append( "\n 79\n"+OrthographicType )
		if( UCSElevation != null) sb.append( "\n 146\n"+UCSElevation )
		if( IDhandleAcDbUCSTableRecord != null) sb.append( "\n 345\n"+IDhandleAcDbUCSTableRecord )
		if( IDhandleAcDbUCSTableRecord1 != null) sb.append( "\n 346\n"+IDhandleAcDbUCSTableRecord1 )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nVIEW")
			sbX.append( sb)
		}

		 return sbX
	}
	constructor(lastElem: DxfChain, NameView: String ): this(lastElem, false){
		this.NameView = NameView
	}
}