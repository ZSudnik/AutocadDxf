package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntTABLE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Entity name (ACAD_TABLE)
	//	0	
	var EntityName: String? = null

	//	 Entity handle
	//	5	
	var EntityHandle: Int? = null

	//	 Soft-pointer ID to the owner dictionary
	//	330	
	var SoftpointerID: String? = null

	//	 Number of bytes in the proxy entity graphics
	//	92	
	var NumberBytes: Int? = null

	//	 Data for proxy entity graphics (multiple lines 256-character maximum per line)
	//	310	
	var DataFor: String? = null

	//	 Block name an anonymous block begins with a *T value
	//	2	
	var BlockName: String? = null

	//	 Insertion point
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 Table data version number:, 0 = 2010
	//	280	
	var TableData: Int? = null

	//	 Hard pointer ID of the TABLESTYLE object
	//	342	
	var HardPointer: String? = null

	//	 Hard pointer ID of the owning BLOCK record
	//	343	
	var HardPointer1: String? = null

	//	 Horizontal direction vector
	//	11	21	31	
	var HorizontalDirection: Vector3f? = null

	//	 Flag for table value (unsigned integer)
	//	90	
	var FlagFor: Int? = null

	//	 Number of rows
	//	91	
	var NumberRows: Int? = null

	//	 Number of columns
	//	92	
	var NumberColumns: Int? = null

	//	 Flag for an override
	//	93	
	var FlagFor1: Int? = null

	//	 Flag for an override of border color
	//	94	
	var FlagFor2: Int? = null

	//	 Flag for an override of border lineweight
	//	95	
	var FlagFor3: Int? = null

	//	 Flag for an override of border visibility
	//	96	
	var FlagFor4: Int? = null

	//	 Row height this value is repeated, 1 value per row
	//	141	
	var RowHeight: Float? = null

	//	 Column height this value is repeated, 1 value per column
	//	142	
	var ColumnHeight: Float? = null

	//	 Cell type this value is repeated, 1 value per cell:, 1 = text type, 2 = block type
	//	171	
	var CellType: Int? = null

	//	 Cell flag value this value is repeated, 1 value per cell
	//	172	
	var CellFlag: Int? = null

	//	 Cell merged value this value is repeated, 1 value per cell
	//	173	
	var CellMerged: Int? = null

	//	 Boolean flag indicating if the autofit option is set for the cell this value is repeated, 1 value per cell
	//	174	
	var BooleanFlag: Int? = null

	//	 Cell border width (applicable only for merged cells) this value is repeated, 1 value per cell
	//	175	
	var CellBorder: Int? = null

	//	 Cell border height ( applicable for merged cells) this value is repeated, 1 value per cell
	//	176	
	var CellBorder1: Int? = null

	//	 Cell override flag this value is repeated, 1 value per cell (from AutoCAD 2007)
	//	91	
	var CellOverride: Int? = null

	//	 Flag value for a virtual edge
	//	178	
	var FlagValue: Int? = null

	//	 Rotation value (real applicable for a block-type cell and a text-type cell)
	//	145	
	var RotationValue: Float? = null

	//	 Hard pointer ID of the FIELD object. This applies only to a text-type cell. If the text in the cell contains one or more fields, only the ID of the FIELD object is saved. The text string (group codes 1 and 3) is ignored
	//	344	
	var HardPointer2: String? = null

	//	 Text string in a cell. If the string is shorter than 250 characters, all characters appear in code 1. If the string is longer than 250 characters, it is divided into chunks of 250 characters. The chunks are contained in one or more code 2 codes. If code 2 codes are used, the last group is a code 1 and is shorter than 250 characters. This value applies only to text-type cells and is repeated, 1 value per cell
	//	1	
	var TextIn: String? = null

	//	 Text string in a cell, in 250-character chunks optional. This value applies only to text-type cells and is repeated, 1 value per cell
	//	2	
	var TextIn1: String? = null

	//	 Hard-pointer ID of the block table record. This value applies only to block-type cells and is repeated, 1 value per cell
	//	340	
	var HardpointerID: String? = null

	//	 Block scale (real). This value applies only to block-type cells and is repeated, 1 value per cell
	//	144	
	var BlockScale: Float? = null

	//	 Number of attribute definitions in the block table record (applicable only to a block-type cell)
	//	179	
	var NumberAttribute: Int? = null

	//	 Soft pointer ID of the attribute definition in the block table record, referenced by group code 179 (applicable only for a block-type cell). This value is repeated once per attribute definition
	//	331	
	var SoftPointer: String? = null

	//	 Text string value for an attribute definition, repeated once per attribute definition and applicable only for a block-type cell
	//	300	
	var TextValue: String? = null

	//	 Text style name (string) override applied at the cell level
	//	7	
	var TextStyle: String? = null

	//	 Text height value override applied at the cell level
	//	140	
	var TextHeight: Float? = null

	//	 Cell alignment value override applied at the cell level
	//	170	
	var CellAlignment: Int? = null

	//	 Value for the color of cell content override applied at the cell level
	//	64	
	var ValueFor: Int? = null

	//	 Value for the background (fill) color of cell content override applied at the cell level
	//	63	
	var ValueFor1: Int? = null

	//	 True color value for the top border of the cell override applied at the cell level
	//	69	
	var TrueColor: Int? = null

	//	 True color value for the right border of the cell override applied at the cell level
	//	65	
	var TrueColor1: Int? = null

	//	 True color value for the bottom border of the cell override applied at the cell level
	//	66	
	var TrueColor2: Int? = null

	//	 True color value for the left border of the cell override applied at the cell level
	//	68	
	var TrueColor3: Int? = null

	//	 Lineweight for the top border of the cell override applied at the cell level
	//	279	
	var LineweightFor: Int? = null

	//	 Lineweight for the right border of the cell override applied at the cell level
	//	275	
	var LineweightFor1: Int? = null

	//	 Lineweight for the bottom border of the cell override applied at the cell level
	//	276	
	var LineweightFor2: Int? = null

	//	 Lineweight for the left border of the cell override applied at the cell level
	//	278	
	var LineweightFor3: Int? = null

	//	 Boolean flag for whether the fill color is on override applied at the cell level
	//	283	
	var BooleanFlag1: Int? = null

	//	 Boolean flag for the visibility of the top border of the cell override applied at the cell level
	//	289	
	var BooleanFlag2: Int? = null

	//	 Boolean flag for the visibility of the right border of the cell override applied at the cell level
	//	285	
	var BooleanFlag3: Int? = null

	//	 Boolean flag for the visibility of the bottom border of the cell override applied at the cell level
	//	286	
	var BooleanFlag4: Int? = null

	//	 Boolean flag for the visibility of the left border of the cell override applied at the cell level
	//	288	
	var BooleanFlag5: Int? = null

	//	 Flow direction override applied at the table entity level
	//	70	
	var FlowDirection: Int? = null

	//	 Horizontal cell margin override applied at the table entity level
	//	40	
	var HorizontalCell: Float? = null

	//	 Vertical cell margin override applied at the table entity level
	//	41	
	var VerticalCell: Float? = null

	//	 Flag for whether the title is suppressed override applied at the table entity level
	//	280	
	var FlagFor5: Int? = null

	//	 Flag for whether the header row is suppressed override applied at the table entity level
	//	281	
	var FlagFor6: Int? = null

	//	 Text style name (string) override applied at the table entity level. There may be one entry for each cell type
	//	7	
	var TextStyle1: String? = null

	//	 Text height (real) override applied at the table entity level. There may be one entry for each cell type
	//	140	
	var TextHeight1: Float? = null

	//	 Cell alignment (integer) override applied at the table entity level. There may be one entry for each cell type
	//	170	
	var CellAlignment1: Int? = null

	//	 Color value for cell background or for the vertical, left border of the table override applied at the table entity level. There may be one entry for each cell type
	//	63	
	var ColorValue: Int? = null

	//	 Color value for cell content or for the horizontal, top border of the table override applied at the table entity level. There may be one entry for each cell type
	//	64	
	var ColorValue1: Int? = null

	//	 Color value for the horizontal, inside border lines override applied at the table entity level
	//	65	
	var ColorValue2: Int? = null

	//	 Color value for the horizontal, bottom border lines override applied at the table entity level
	//	66	
	var ColorValue3: Int? = null

	//	 Color value for the vertical, inside border lines override applied at the table entity level
	//	68	
	var ColorValue4: Int? = null

	//	 Color value for the vertical, right border lines override applied at the table entity level
	//	69	
	var ColorValue5: Int? = null

	//	 Flag for whether background color is enabled (default = 0) override applied at the table entity level. There may be one entry for each cell type:, 0 = Disabled, 1 = Enabled
	//	283	
	var FlagFor7: Int? = null

	//	 Lineweight for each border type of the cell (default = kLnWtByBlock) override applied at the table entity level. There may be one group for each cell type
	//	274	
	var LineweightFor4: Int? = null

	//	 Flag for visibility of each border type of the cell (default = 1) override applied at the table entity level. There may be one group for each cell type:, 0 = Invisible, 1 = Visible
	//	284	
	var FlagFor8: Int? = null

	//	 Standard/title/header row data type
	//	97	
	var StandardtitleheaderRow: Int? = null

	//	 Standard/title/header row unit type
	//	98	
	var StandardtitleheaderRow1: Int? = null

	//	 Standard/title/header row format string
	//	4	
	var StandardtitleheaderRow2: String? = null

	//	 Cell override flag value (before AutoCAD 2007)
	//	177	
	var CellOverride1: Int? = null

	//	 Extended cell flags (from AutoCAD 2007)
	//	92	
	var ExtendedCell: Int? = null

	//	 Cell value block begin (from AutoCAD 2007)
	//	301	
	var CellValue: String? = null

	//	 Text string in a cell. If the string is shorter than 250 characters, all characters appear in code 302. If the string is longer than 250 characters, it is divided into chunks of 250 characters. The chunks are contained in one or more code 303 codes. If code 393 codes are used, the last group is a code 1 and is shorter than 250 characters. This value applies only to text-type cells and is repeated, 1 value per cell (from AutoCAD 2007)
	//	302	
	var TextIn2: String? = null

	//	 Text string in a cell, in 250-character chunks optional. This value applies only to text-type cells and is repeated, 302 value per cell (from AutoCAD 2007)
	//	303	
	var TextIn3: String? = null

    override fun read(getbfr: DxfLoaderContext) {

    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"0" -> {
				EntityName = getbfr.stringValue()
			}
			"5" -> {
				EntityHandle = getbfr.intHexValue()
			}
			"330" -> {
				SoftpointerID = getbfr.stringValue()
			}
			"92" -> {
				NumberBytes = getbfr.intValue()
			}
			"310" -> {
				DataFor = getbfr.stringValue()
			}
			"2" -> {
				BlockName = getbfr.stringValue()
			}
			"10" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.z = getbfr.floatValue()
			}
			"280" -> {
				TableData = getbfr.intValue()
			}
			"342" -> {
				HardPointer = getbfr.stringValue()
			}
			"343" -> {
				HardPointer1 = getbfr.stringValue()
			}
			"11" -> {
				if( HorizontalDirection == null ) HorizontalDirection = Vector3f()
				HorizontalDirection?.x = getbfr.floatValue()
			}
			"21" -> {
				if( HorizontalDirection == null ) HorizontalDirection = Vector3f()
				HorizontalDirection?.y = getbfr.floatValue()
			}
			"31" -> {
				if( HorizontalDirection == null ) HorizontalDirection = Vector3f()
				HorizontalDirection?.z = getbfr.floatValue()
			}
			"90" -> {
				FlagFor = getbfr.intValue()
			}
			"91" -> {
				NumberRows = getbfr.intValue()
			}
			"92" -> {
				NumberColumns = getbfr.intValue()
			}
			"93" -> {
				FlagFor1 = getbfr.intValue()
			}
			"94" -> {
				FlagFor2 = getbfr.intValue()
			}
			"95" -> {
				FlagFor3 = getbfr.intValue()
			}
			"96" -> {
				FlagFor4 = getbfr.intValue()
			}
			"141" -> {
				RowHeight = getbfr.floatValue()
			}
			"142" -> {
				ColumnHeight = getbfr.floatValue()
			}
			"171" -> {
				CellType = getbfr.intValue()
			}
			"172" -> {
				CellFlag = getbfr.intValue()
			}
			"173" -> {
				CellMerged = getbfr.intValue()
			}
			"174" -> {
				BooleanFlag = getbfr.intValue()
			}
			"175" -> {
				CellBorder = getbfr.intValue()
			}
			"176" -> {
				CellBorder1 = getbfr.intValue()
			}
			"91" -> {
				CellOverride = getbfr.intValue()
			}
			"178" -> {
				FlagValue = getbfr.intValue()
			}
			"145" -> {
				RotationValue = getbfr.floatValue()
			}
			"344" -> {
				HardPointer2 = getbfr.stringValue()
			}
			"1" -> {
				TextIn = getbfr.stringValue()
			}
			"2" -> {
				TextIn1 = getbfr.stringValue()
			}
			"340" -> {
				HardpointerID = getbfr.stringValue()
			}
			"144" -> {
				BlockScale = getbfr.floatValue()
			}
			"179" -> {
				NumberAttribute = getbfr.intValue()
			}
			"331" -> {
				SoftPointer = getbfr.stringValue()
			}
			"300" -> {
				TextValue = getbfr.stringValue()
			}
			"7" -> {
				TextStyle = getbfr.stringValue()
			}
			"140" -> {
				TextHeight = getbfr.floatValue()
			}
			"170" -> {
				CellAlignment = getbfr.intValue()
			}
			"64" -> {
				ValueFor = getbfr.intValue()
			}
			"63" -> {
				ValueFor1 = getbfr.intValue()
			}
			"69" -> {
				TrueColor = getbfr.intValue()
			}
			"65" -> {
				TrueColor1 = getbfr.intValue()
			}
			"66" -> {
				TrueColor2 = getbfr.intValue()
			}
			"68" -> {
				TrueColor3 = getbfr.intValue()
			}
			"279" -> {
				LineweightFor = getbfr.intValue()
			}
			"275" -> {
				LineweightFor1 = getbfr.intValue()
			}
			"276" -> {
				LineweightFor2 = getbfr.intValue()
			}
			"278" -> {
				LineweightFor3 = getbfr.intValue()
			}
			"283" -> {
				BooleanFlag1 = getbfr.intValue()
			}
			"289" -> {
				BooleanFlag2 = getbfr.intValue()
			}
			"285" -> {
				BooleanFlag3 = getbfr.intValue()
			}
			"286" -> {
				BooleanFlag4 = getbfr.intValue()
			}
			"288" -> {
				BooleanFlag5 = getbfr.intValue()
			}
			"70" -> {
				FlowDirection = getbfr.intValue()
			}
			"40" -> {
				HorizontalCell = getbfr.floatValue()
			}
			"41" -> {
				VerticalCell = getbfr.floatValue()
			}
			"280" -> {
				FlagFor5 = getbfr.intValue()
			}
			"281" -> {
				FlagFor6 = getbfr.intValue()
			}
			"7" -> {
				TextStyle1 = getbfr.stringValue()
			}
			"140" -> {
				TextHeight1 = getbfr.floatValue()
			}
			"170" -> {
				CellAlignment1 = getbfr.intValue()
			}
			"63" -> {
				ColorValue = getbfr.intValue()
			}
			"64" -> {
				ColorValue1 = getbfr.intValue()
			}
			"65" -> {
				ColorValue2 = getbfr.intValue()
			}
			"66" -> {
				ColorValue3 = getbfr.intValue()
			}
			"68" -> {
				ColorValue4 = getbfr.intValue()
			}
			"69" -> {
				ColorValue5 = getbfr.intValue()
			}
			"283" -> {
				FlagFor7 = getbfr.intValue()
			}
			"274" -> {
				LineweightFor4 = getbfr.intValue()
			}
			"284" -> {
				FlagFor8 = getbfr.intValue()
			}
			"97" -> {
				StandardtitleheaderRow = getbfr.intValue()
			}
			"98" -> {
				StandardtitleheaderRow1 = getbfr.intValue()
			}
			"4" -> {
				StandardtitleheaderRow2 = getbfr.stringValue()
			}
			"177" -> {
				CellOverride1 = getbfr.intValue()
			}
			"92" -> {
				ExtendedCell = getbfr.intValue()
			}
			"301" -> {
				CellValue = getbfr.stringValue()
			}
			"302" -> {
				TextIn2 = getbfr.stringValue()
			}
			"303" -> {
				TextIn3 = getbfr.stringValue()
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


	//	 Subclass marker. (AcDbEntity)
	//	100	
	const val AcDbEntity: String = "AcDbEntity"

	//	 Subclass marker. (AcDbBlockReference)
	//	100	
	const val AcDbBlockReference: String = "AcDbBlockReference"

	//	 Subclass marker. (AcDbTable)
	//	100	
	const val AcDbTable: String = "AcDbTable"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( EntityName != null) sb.append( "\n 0\n"+EntityName )
		if( EntityHandle != null) sb.append( "\n 5\n"+Integer.toHexString(EntityHandle!! ) )
		if( SoftpointerID != null) sb.append( "\n 330\n"+SoftpointerID )
		if( NumberBytes != null) sb.append( "\n 92\n"+NumberBytes )
		if( DataFor != null) sb.append( "\n 310\n"+DataFor )
		if( BlockName != null) sb.append( "\n 2\n"+BlockName )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( TableData != null) sb.append( "\n 280\n"+TableData )
		if( HardPointer != null) sb.append( "\n 342\n"+HardPointer )
		if( HardPointer1 != null) sb.append( "\n 343\n"+HardPointer1 )
		if( HorizontalDirection != null) sb.append( "\n 11\n"+HorizontalDirection?.x+"\n 21\n"+HorizontalDirection?.y+"\n 31\n"+HorizontalDirection?.z )
		if( FlagFor != null) sb.append( "\n 90\n"+FlagFor )
		if( NumberRows != null) sb.append( "\n 91\n"+NumberRows )
		if( NumberColumns != null) sb.append( "\n 92\n"+NumberColumns )
		if( FlagFor1 != null) sb.append( "\n 93\n"+FlagFor1 )
		if( FlagFor2 != null) sb.append( "\n 94\n"+FlagFor2 )
		if( FlagFor3 != null) sb.append( "\n 95\n"+FlagFor3 )
		if( FlagFor4 != null) sb.append( "\n 96\n"+FlagFor4 )
		if( RowHeight != null) sb.append( "\n 141\n"+RowHeight )
		if( ColumnHeight != null) sb.append( "\n 142\n"+ColumnHeight )
		if( CellType != null) sb.append( "\n 171\n"+CellType )
		if( CellFlag != null) sb.append( "\n 172\n"+CellFlag )
		if( CellMerged != null) sb.append( "\n 173\n"+CellMerged )
		if( BooleanFlag != null) sb.append( "\n 174\n"+BooleanFlag )
		if( CellBorder != null) sb.append( "\n 175\n"+CellBorder )
		if( CellBorder1 != null) sb.append( "\n 176\n"+CellBorder1 )
		if( CellOverride != null) sb.append( "\n 91\n"+CellOverride )
		if( FlagValue != null) sb.append( "\n 178\n"+FlagValue )
		if( RotationValue != null) sb.append( "\n 145\n"+RotationValue )
		if( HardPointer2 != null) sb.append( "\n 344\n"+HardPointer2 )
		if( TextIn != null) sb.append( "\n 1\n"+TextIn )
		if( TextIn1 != null) sb.append( "\n 2\n"+TextIn1 )
		if( HardpointerID != null) sb.append( "\n 340\n"+HardpointerID )
		if( BlockScale != null) sb.append( "\n 144\n"+BlockScale )
		if( NumberAttribute != null) sb.append( "\n 179\n"+NumberAttribute )
		if( SoftPointer != null) sb.append( "\n 331\n"+SoftPointer )
		if( TextValue != null) sb.append( "\n 300\n"+TextValue )
		if( TextStyle != null) sb.append( "\n 7\n"+TextStyle )
		if( TextHeight != null) sb.append( "\n 140\n"+TextHeight )
		if( CellAlignment != null) sb.append( "\n 170\n"+CellAlignment )
		if( ValueFor != null) sb.append( "\n 64\n"+ValueFor )
		if( ValueFor1 != null) sb.append( "\n 63\n"+ValueFor1 )
		if( TrueColor != null) sb.append( "\n 69\n"+TrueColor )
		if( TrueColor1 != null) sb.append( "\n 65\n"+TrueColor1 )
		if( TrueColor2 != null) sb.append( "\n 66\n"+TrueColor2 )
		if( TrueColor3 != null) sb.append( "\n 68\n"+TrueColor3 )
		if( LineweightFor != null) sb.append( "\n 279\n"+LineweightFor )
		if( LineweightFor1 != null) sb.append( "\n 275\n"+LineweightFor1 )
		if( LineweightFor2 != null) sb.append( "\n 276\n"+LineweightFor2 )
		if( LineweightFor3 != null) sb.append( "\n 278\n"+LineweightFor3 )
		if( BooleanFlag1 != null) sb.append( "\n 283\n"+BooleanFlag1 )
		if( BooleanFlag2 != null) sb.append( "\n 289\n"+BooleanFlag2 )
		if( BooleanFlag3 != null) sb.append( "\n 285\n"+BooleanFlag3 )
		if( BooleanFlag4 != null) sb.append( "\n 286\n"+BooleanFlag4 )
		if( BooleanFlag5 != null) sb.append( "\n 288\n"+BooleanFlag5 )
		if( FlowDirection != null) sb.append( "\n 70\n"+FlowDirection )
		if( HorizontalCell != null) sb.append( "\n 40\n"+HorizontalCell )
		if( VerticalCell != null) sb.append( "\n 41\n"+VerticalCell )
		if( FlagFor5 != null) sb.append( "\n 280\n"+FlagFor5 )
		if( FlagFor6 != null) sb.append( "\n 281\n"+FlagFor6 )
		if( TextStyle1 != null) sb.append( "\n 7\n"+TextStyle1 )
		if( TextHeight1 != null) sb.append( "\n 140\n"+TextHeight1 )
		if( CellAlignment1 != null) sb.append( "\n 170\n"+CellAlignment1 )
		if( ColorValue != null) sb.append( "\n 63\n"+ColorValue )
		if( ColorValue1 != null) sb.append( "\n 64\n"+ColorValue1 )
		if( ColorValue2 != null) sb.append( "\n 65\n"+ColorValue2 )
		if( ColorValue3 != null) sb.append( "\n 66\n"+ColorValue3 )
		if( ColorValue4 != null) sb.append( "\n 68\n"+ColorValue4 )
		if( ColorValue5 != null) sb.append( "\n 69\n"+ColorValue5 )
		if( FlagFor7 != null) sb.append( "\n 283\n"+FlagFor7 )
		if( LineweightFor4 != null) sb.append( "\n 274\n"+LineweightFor4 )
		if( FlagFor8 != null) sb.append( "\n 284\n"+FlagFor8 )
		if( StandardtitleheaderRow != null) sb.append( "\n 97\n"+StandardtitleheaderRow )
		if( StandardtitleheaderRow1 != null) sb.append( "\n 98\n"+StandardtitleheaderRow1 )
		if( StandardtitleheaderRow2 != null) sb.append( "\n 4\n"+StandardtitleheaderRow2 )
		if( CellOverride1 != null) sb.append( "\n 177\n"+CellOverride1 )
		if( ExtendedCell != null) sb.append( "\n 92\n"+ExtendedCell )
		if( CellValue != null) sb.append( "\n 301\n"+CellValue )
		if( TextIn2 != null) sb.append( "\n 302\n"+TextIn2 )
		if( TextIn3 != null) sb.append( "\n 303\n"+TextIn3 )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nTABLE")
			sbX.append( sb)
		}

		 return sbX
	}}