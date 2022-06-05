package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import javax.vecmath.Vector3f
import kotlin.math.abs

abstract class TblExtendedData: DxfChain() {

	// Strings in extended data
	//	1000
	var StringExtendedData: String? = null

	//Application names can be up to 31 bytes long (the 32nd byte is reserved for the null character).
	// 1001
	var ApplicationName: String? = null

	// An extended data control string can be either “{”or “}”. These braces enable applications to organize their data by subdividing the data into lists. The left brace begins a list, and the right brace terminates the most recent list. Lists can be nested.
	// When the program reads the extended data for a particular application, it checks to ensure that braces are balanced.
	//	1002
	var ControlString: String? = null

	// Name of the layer associated with the extended data.
	// 1003
	var LayerNameExt: String? = null

	// Binary data is organized into variable-length chunks. The maximum length of each chunk is 127 bytes. In ASCII DXF files, binary data is represented as a string of hexadecimal digits, two per binary byte.
	// 1004
	var BinaryData: String? = null

	// 	Handles of entities in the drawing database
	//	Note: When a drawing with handles and extended data handles is imported into another drawing using INSERT, INSERT *, XREF Bind, XBIND, or partial OPEN, the extended data handles are translated in the same manner as their corresponding entity handles, thus maintaining their binding. This is also done in the EXPLODE block operation or for any other operation. When AUDIT detects an extended data handle that doesn't match the handle of an entity in the drawing file, it is considered an error. If AUDIT is fixing entities, it sets the handle to 0.
	// 1005
	var DatabaseHandle: String? = null

	//Three real values, in the order X, Y, Z. They can be used as a point or vector record. The program never alters their value.
	//	1010, 1020, 1030
	var XYZReals: Vector3f? = null

	//	Unlike a simple 3D point, the world space coordinates are moved, scaled, rotated, and mirrored along with the parent entity to which the extended data belongs. The world space position is also stretched when the STRETCH command is applied to the parent entity and this point lies within the select window.
	//	1011, 1021, 1031
	var WorldSpacePosition: Vector3f? = null

	// Also a 3D point that is scaled, rotated, and mirrored along with the parent (but is not moved or stretched).
	// 1012, 1022, 1032
	var WorldSpaceDisplacement: Vector3f? = null

	// Also a 3D point that is rotated and mirrored along with the parent (but is not moved, scaled, or stretched).
	// 1013, 1023, 1033
	var WorldDirection: Vector3f? = null

	// A real value.
	// 1040
	var Real: Float? = null

	// A real value that is scaled along with the parent entity.
	// 1041
	var Distance: Float? = null

	// Also a real value that is scaled along with the parent. The difference between a distance and a scale factor is application-defined.
	// 1042
	var ScaleFactor: Float? = null

	// A 16-bit integer (signed or unsigned).
	// 1070
	var ValueInteger: Int? = null

	// A 16-bit integer (signed or unsigned).
	// 1071
	var ValueLong: Long? = null

	///////////// my value
	var AcCmTransparency: Float? = null

   abstract override fun read(dxfContext: DxfLoaderContext)
 
    fun readExtend(cod : String): Boolean {
        when (cod) {
			"1000" -> {
				StringExtendedData = dxfContext.stringValue()
				return false
			}
			"1001" -> {
				ApplicationName = dxfContext.stringValue()
				return false
			}
			"1002" -> {
				ControlString = dxfContext.stringValue()
				return false
			}
			"1003" -> {
				LayerNameExt = dxfContext.stringValue()
				return false
			}
			"1004" -> {
				BinaryData = dxfContext.stringValue()
				return false
			}
			"1005" -> {
				DatabaseHandle = dxfContext.stringValue()
				return false
			}
			"1010" -> {
				if( XYZReals == null ) XYZReals = Vector3f()
				XYZReals?.x = dxfContext.floatValue()
				return false
			}
			"1020" -> {
				XYZReals?.y = dxfContext.floatValue()
				return false
			}
			"1030" -> {
				XYZReals?.z = dxfContext.floatValue()
				return false
			}
			"1011" -> {
				if( WorldSpacePosition == null ) WorldSpacePosition = Vector3f()
				WorldSpacePosition?.x = dxfContext.floatValue()
				return false
			}
			"1021" -> {
				WorldSpacePosition?.y = dxfContext.floatValue()
				return false
			}
			"1031" -> {
				WorldSpacePosition?.z = dxfContext.floatValue()
				return false
			}
			"1012" -> {
				if( WorldSpaceDisplacement == null ) WorldSpaceDisplacement = Vector3f()
				WorldSpaceDisplacement?.x = dxfContext.floatValue()
				return false
			}
			"1022" -> {
				WorldSpaceDisplacement?.y = dxfContext.floatValue()
				return false
			}
			"1032" -> {
				WorldSpaceDisplacement?.z = dxfContext.floatValue()
				return false
			}
			"1013" -> {
				if( WorldDirection == null ) WorldDirection = Vector3f()
				WorldDirection?.x = dxfContext.floatValue()
				return false
			}
			"1023" -> {
				WorldDirection?.y = dxfContext.floatValue()
				return false
			}
			"1033" -> {
				WorldDirection?.z = dxfContext.floatValue()
				return false
			}
			"1040" -> {
				Real = dxfContext.floatValue()
				return false
			}
			"1041" -> {
				Distance = dxfContext.floatValue()
				return false
			}
			"1042" -> {
				ScaleFactor = dxfContext.floatValue()
				return false
			}
			"1070" -> {
				ValueInteger = dxfContext.intValue()
				return false
			}
			"1071" -> {
				ValueLong = dxfContext.longValue()
				if( ApplicationName.equals( "AcCmTransparency",true))
					AcCmTransparency = transparentDxfToOpenGL( ValueLong!! )
				return false
			}
			else -> return true
        }

	}


	override fun write( sb: StringBuilder): StringBuilder {

//		if( HandlenotOmitted != null) sb.append( "\n 5\n"+Integer.toHexString(HandlenotOmitted!!) )
//		if( HandleDIMSTYLE != null) sb.append( "\n 105\n"+HandleDIMSTYLE )
//		if( IDhandleDictionary != null) {
//				sb.append( "\n 102\n{ACAD_REACTORS" )
// 				 sb.append( "\n 330\n"+IDhandleDictionary )
//				sb.append( "\n 102\n}" )
//				}
//		if( HardownerIDhandle != null) {
//				sb.append( "\n 102\n{ACAD_XDICTIONARY" )
// 				 sb.append( "\n 360\n"+HardownerIDhandle )
//				sb.append( "\n 102\n}" )
//				}
//		if( IDhandleBLOCK_RECORD != null) {
//				sb.append( "\n 102\n{ACAD_REACTORS" )
// 				 sb.append( "\n 330\n"+IDhandleBLOCK_RECORD )
//				sb.append( "\n 102\n}" )
//				}
//		if( AcDbEntitynotOmitted != null) 				 sb.append( "\n 100\n"+AcDbEntitynotOmitted )
		return sb
	}

	private fun transparentDxfToOpenGL(transAutoCAD: Long): Float{
		//	0f - 33554687
		//0.9f - 33554457
		var transOpenGl: Float = abs(33554687L - transAutoCAD ) /23000f
		return transOpenGl
	}
}