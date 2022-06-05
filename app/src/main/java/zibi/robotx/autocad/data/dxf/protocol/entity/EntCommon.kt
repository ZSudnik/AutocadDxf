package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.protocol.table.TblLAYER
import zibi.robotx.autocad.data.dxf.protocol.table.TblLTYPE

abstract class EntCommon: DxfChain() {


	//	 Handle not omitted
	//	5	
	override var HandlenotOmitted: Int? = null

	//	 Start of application-defined group
	//	102	
	//  var StartApplicationdefined: String? = null

	//	 End of group, } (optional)no default
	//	102	
	//  var EndGroup: String? = null

	//	 {ACAD_REACTORS indicates the start of the AutoCAD persistent reactors group. This group exists only if persistent reactors have been attached to this object (optional)no default
	//	102	
	//  var {ACAD_REACTORSIndicates: String? = null

	//	 Soft-pointer ID/handle to owner dictionary (optional)no default
	//	330	
	var IDhandleDictionary: String? = null

	//	 End of group, } (optional)no default
	//	102	
	//  var EndGroup: String? = null

	//	 {ACAD_XDICTIONARY indicates the start of an extension dictionary group. This group exists only if an extension dictionary has been attached to the object (optional)no default
	//	102	
	//  var {ACAD_XDICTIONARYIndicates: String? = null

	//	 Hard-owner ID/handle to owner dictionary (optional)no default
	//	360	
	var HardownerIDhandle: String? = null

	//	 End of group, } (optional)no default
	//	102	
	//  var EndGroup: String? = null

	//	 Soft-pointer ID/handle to owner BLOCK_RECORD objectnot omitted
	//	330	
	var IDhandleBLOCK_RECORD: String? = null

	//	 Subclass marker (AcDbEntity)not omitted
	//	100	
	var AcDbEntitynotOmitted: String? = null

	//	 Absent or zero indicates entity is in model space. 1 indicates entity is in paper space (optional).0
	//	67	
	var AbsentIndicates: Int? = null

	//	 APP: layout tab namenot omitted
	//	410	
	var APPLayout: String? = null

	//	 Layer namenot omitted
	//	8	
	var LayerNamenot: TblLAYER? = null

	//	 Linetype name (present if not BYLAYER). The special name BYBLOCK indicates a floating linetype (optional)BYLAYER
	//	6	
	var LinetypeName: TblLTYPE? = null

	//	 Hard-pointer ID/handle to material object (present if not BYLAYER)BYLAYER
	//	347	
	var IDhandleMaterial: String? = null

	//	 Color number (present if not BYLAYER)
	//	62	
	var ColorNumber: Int? = null

	//	 Lineweight enum value. Stored and moved around as a 16-bit integer.not omitted
	//	370	
	var LineweightEnum: Int? = null

	//	 Linetype scale (optional)1
	//	48	
	var LinetypeScale: Float? = null

	//	 Object visibility (optional):
	//	60	
	var ObjectVisibility: Int? = null

	//	 Number of bytes in the proxy entity graphics represented in the subsequent 310 groups, which are binary chunk records (optional)no default
	//	92	
	var NumberProxy: Int? = null

	//	 Proxy entity graphics data (multiple lines
	//	310	
	var ProxyGraphics: String? = null

	//	 A 24-bit color value that should be dealt with in terms of bytes with values of 0 to 255. The lowest byte is the blue value, the middle byte is the green value, and the third byte is the red value. The top byte is always 0. The group code cannot be used by custom entities for their own data because the group code is reserved for AcDbEntity, class-level color data and AcDbEntity, class-level transparency datano default
	//	420	
	var AColor: Int? = null

	//	 Color name. The group code cannot be used by custom entities for their own data because the group code is reserved for AcDbEntity, class-level color data and AcDbEntity, class-level transparency datano default
	//	430	
	var ColorName: String? = null

	//	 Transparency value. The group code cannot be used by custom entities for their own data because the group code is reserved for AcDbEntity, class-level color data and AcDbEntity, class-level transparency datano default
	//	440	
	var TransparencyValue: Int? = null

	//	 Hard-pointer ID/handle to the plot style objectno default
	//	390	
	var IDhandlePlot: String? = null

	//	 Shadow mode
	//	284	
	var ShadowMode: Int? = null



   abstract override fun read(dxfContext: DxfLoaderContext)
 
    fun readX(cod : String): Boolean {

        when (cod) {
			"5" -> {
				HandlenotOmitted = dxfContext.intHexValue()
				return false
			}
			"330" -> {
				IDhandleDictionary = dxfContext.stringValue()
				return false
			}
			"360" -> {
				HardownerIDhandle = dxfContext.stringValue()
				return false
			}
			"330" -> {
				IDhandleBLOCK_RECORD = dxfContext.stringValue()
				return false
			}
			"100" -> {
				AcDbEntitynotOmitted = dxfContext.stringValue()
				return false
			}
			"67" -> {
				AbsentIndicates = dxfContext.intValue()
				return false
			}
			"410" -> {
				APPLayout = dxfContext.stringValue()
				return false
			}
			"8" -> {
				LayerNamenot = dxfContext.layerValue(this)
				return false
			}
			"6" -> {
				LinetypeName = dxfContext.ltypeValue(this)
				return false
			}
			"347" -> {
				IDhandleMaterial = dxfContext.stringValue()
				return false
			}
			"62" -> {
				ColorNumber = dxfContext.intValue()
				return false
			}
			"370" -> {
				LineweightEnum = dxfContext.intValue()
				return false
			}
			"48" -> {
				LinetypeScale = dxfContext.floatValue()
				return false
			}
			"60" -> {
				ObjectVisibility = dxfContext.intValue()
				return false
			}
			"92" -> {
				NumberProxy = dxfContext.intValue()
				return false
			}
			"310" -> {
				ProxyGraphics = dxfContext.stringValue()
				return false
			}
			"420" -> {
				AColor = dxfContext.intValue()
				return false
			}
			"430" -> {
				ColorName = dxfContext.stringValue()
				return false
			}
			"440" -> {
				TransparencyValue = dxfContext.intValue()
				return false
			}
			"390" -> {
				IDhandlePlot = dxfContext.stringValue()
				return false
			}
			"284" -> {
				ShadowMode = dxfContext.intValue()
				return false
			}
		else -> return true
		}
	}


	override fun write( sb: StringBuilder): StringBuilder {

		if( HandlenotOmitted != null) sb.append( "\n 5\n"+Integer.toHexString(HandlenotOmitted!!) )
		if( IDhandleDictionary != null) {
				sb.append( "\n 102\n{ACAD_REACTORS" )
 				 sb.append( "\n 330\n"+IDhandleDictionary )
				sb.append( "\n 102\n}" ) 
				}
		if( HardownerIDhandle != null) {
				sb.append( "\n 102\n{ACAD_XDICTIONARY" )
 				 sb.append( "\n 360\n"+HardownerIDhandle )
				sb.append( "\n 102\n}" ) 
				}
		if( IDhandleBLOCK_RECORD != null) {
				sb.append( "\n 102\n{ACAD_REACTORS" )
 				 sb.append( "\n 330\n"+IDhandleBLOCK_RECORD )
				sb.append( "\n 102\n}" ) 
				}
		if( AcDbEntitynotOmitted != null) 	 sb.append( "\n 100\n"+AcDbEntitynotOmitted )
		if( AbsentIndicates != null) 	 sb.append( "\n 67\n"+AbsentIndicates )
		if( APPLayout != null) 				 sb.append( "\n 410\n"+APPLayout )
		if( LayerNamenot != null) 		sb.append( "\n 8\n"+LayerNamenot )
		if( LinetypeName != null) 				 sb.append( "\n 6\n"+LinetypeName )
		if( IDhandleMaterial != null) 				 sb.append( "\n 347\n"+IDhandleMaterial )
		if( ColorNumber != null) 				 sb.append( "\n 62\n"+ColorNumber )
		if( LineweightEnum != null) 				 sb.append( "\n 370\n"+LineweightEnum )
		if( LinetypeScale != null) 				 sb.append( "\n 48\n"+LinetypeScale )
		if( ObjectVisibility != null) 				 sb.append( "\n 60\n"+ObjectVisibility )
		if( NumberProxy != null) 				 sb.append( "\n 92\n"+NumberProxy )
		if( ProxyGraphics != null) 				 sb.append( "\n 310\n"+ProxyGraphics )
		if( AColor != null) 				 sb.append( "\n 420\n"+AColor )
		if( ColorName != null) 				 sb.append( "\n 430\n"+ColorName )
		if( TransparencyValue != null) 				 sb.append( "\n 440\n"+TransparencyValue )
		if( IDhandlePlot != null) 				 sb.append( "\n 390\n"+IDhandlePlot )
		if( ShadowMode != null) 				 sb.append( "\n 284\n"+ShadowMode )
		return sb
	}

}