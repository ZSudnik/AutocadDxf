package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfLoaderContext

abstract class TblCommon: TblExtendedData() {


	//	 Handlenot omitted
	//	5	
	override var HandlenotOmitted: Int? = null

	//	 Handle (DIMSTYLE table only)
	//	105
	var HandleDIMSTYLE: String? = null

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





   abstract override fun read(dxfContext: DxfLoaderContext)
 
    fun readX(cod : String): Boolean {
		if (super.readExtend(cod)) {
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
				"105" -> {
					HandleDIMSTYLE = dxfContext.stringValue()
					return false
				}
				else -> return true
			}
		}
		return false
	}


	override fun write( sb: StringBuilder): StringBuilder {

		if( HandlenotOmitted != null) sb.append( "\n 5\n"+Integer.toHexString(HandlenotOmitted!!) )
		if( HandleDIMSTYLE != null) sb.append( "\n 105\n"+HandleDIMSTYLE )
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
		if( AcDbEntitynotOmitted != null) 				 sb.append( "\n 100\n"+AcDbEntitynotOmitted )
		return sb
	}

}