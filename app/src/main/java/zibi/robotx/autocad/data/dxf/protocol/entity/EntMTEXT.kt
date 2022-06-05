package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntMTEXT(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Insertion point, DXF: X value APP: 3D pointDXF: Y and Z values of insertion point
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 Nominal (initial) text height
	//	40	
	var NominalText: Float? = null

	//	 Reference rectangle width
	//	41	
	var ReferenceRectangle: Float? = null

	//	 Attachment point:, 1 = Top left, 2 = Top center, 3 = Top right, 4 = Middle left, 5 = Middle center, 6 = Middle right, 7 = Bottom left, 8 = Bottom center, 9 = Bottom right
	//	71	
	var AttachmentPoint: Int? = null

	//	 Drawing direction:, 1 = Left to right, 3 = Top to bottom, 5 = By style (the flow direction is inherited from the associated text style)
	//	72	
	var DrawingDirection: Int? = null

	//	 Text string. If the text string is less than 250 characters, all characters appear in group 1. If the text string is greater than 250 characters, the string is divided into 250-character chunks, which appear in one or more group 3 codes. If group 3 codes are used, the last group is a group 1 and has fewer than 250 characters
	//	1	
	var TextString: String? = null

	//	 Additional text (always in 250-character chunks) (optional)
	//	3	
	var AdditionalText: String? = null

	//	 Text style name (STANDARD if not provided) (optional)
	//	7	
	var TextStyle: String? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 X-axis direction vector (in WCS), DXF: X value APP: 3D vector, A group code 50 (rotation angle in radians) passed as DXF input is converted to the equivalent direction vector (if both a code 50 and codes 11, 21, 31 are passed, the last one wins). This is provided as a convenience for conversions from text objectsDXF: Y and Z values of X-axis direction vector (in WCS)
	//	11	21	31	
	var XaxisDirection: Vector3f? = null

	//	 Horizontal width of the characters that make up the mtext entity. This value will always be equal to or less than the value of group code 41 (read-only, ignored if supplied)
	//	42	
	var HorizontalWidth: Float? = null

	//	 Vertical height of the mtext entity (read-only, ignored if supplied)
	//	43	
	var VerticalHeight: Float? = null

	//	 Rotation angle in radians
	//	50	
	var RotationAngle: Float? = null

	//	 Mtext line spacing style (optional):, 1 = At least (taller characters will override), 2 = Exact (taller characters will not override)
	//	73	
	var MtextLine: Int? = null

	//	 Mtext line spacing factor (optional):, Percentage of default (3-on-5) line spacing to be applied. Valid values range from 0.25 to 4.00
	//	44	
	var MtextLine1: Float? = null

	//	 Background fill setting:, 0 = Background fill off, 1 = Use background fill color, 2 = Use drawing window color as background fill color
	//	90	
	var BackgroundFill: Int? = null

	//	 Background color (if RGB color)
	//	420	
	var BackgroundColor: Int? = null

	//	 Background color (if color name)
	//	430	
	var BackgroundColor1: String? = null

	//	 Fill box scale (optional):, Determines how much border there is around the text.
	//	45	
	var FillBox: Float? = null

	//	 Background fill color (optional):, Color to use for background fill when group code 90 is 1.
	//	63	
	var BackgroundFill1: Int? = null

	//	 Transparency of background fill color (not implemented)
	//	441	
	var TransparencyBackground: Int? = null

	//	 Column type
	//	75	
	var ColumnType: Int? = null

	//	 Column count
	//	76	
	var ColumnCount: Int? = null

	//	 Column Flow Reversed
	//	78	
	var ColumnFlow: Int? = null

	//	 Column Autoheight
	//	79	
	var ColumnAutoheight: Int? = null

	//	 Column width
	//	48	
	var ColumnWidth: Float? = null

	//	 Column gutter
	//	49	
	var ColumnGutter: Float? = null

	//	 Column heights this code is followed by a column count (Int16), and then the number of column heights
	//	50	
	var ColumnHeights: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
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
			"40" -> {
				NominalText = getbfr.floatValue()
			}
			"41" -> {
				ReferenceRectangle = getbfr.floatValue()
			}
			"71" -> {
				AttachmentPoint = getbfr.intValue()
			}
			"72" -> {
				DrawingDirection = getbfr.intValue()
			}
			"1" -> {
				TextString = getbfr.stringValue()
			}
			"3" -> {
				AdditionalText = getbfr.stringValue()
			}
			"7" -> {
				TextStyle = getbfr.stringValue()
			}
			"210" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.x = getbfr.floatValue()
			}
			"220" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.y = getbfr.floatValue()
			}
			"230" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.z = getbfr.floatValue()
			}
			"11" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.x = getbfr.floatValue()
			}
			"21" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.y = getbfr.floatValue()
			}
			"31" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.z = getbfr.floatValue()
			}
			"42" -> {
				HorizontalWidth = getbfr.floatValue()
			}
			"43" -> {
				VerticalHeight = getbfr.floatValue()
			}
			"50" -> {
				RotationAngle = getbfr.floatValue()
			}
			"73" -> {
				MtextLine = getbfr.intValue()
			}
			"44" -> {
				MtextLine1 = getbfr.floatValue()
			}
			"90" -> {
				BackgroundFill = getbfr.intValue()
			}
			"420" -> {
				BackgroundColor = getbfr.intValue()
			}
			"430" -> {
				BackgroundColor1 = getbfr.stringValue()
			}
			"45" -> {
				FillBox = getbfr.floatValue()
			}
			"63" -> {
				BackgroundFill1 = getbfr.intValue()
			}
			"441" -> {
				TransparencyBackground = getbfr.intValue()
			}
			"75" -> {
				ColumnType = getbfr.intValue()
			}
			"76" -> {
				ColumnCount = getbfr.intValue()
			}
			"78" -> {
				ColumnFlow = getbfr.intValue()
			}
			"79" -> {
				ColumnAutoheight = getbfr.intValue()
			}
			"48" -> {
				ColumnWidth = getbfr.floatValue()
			}
			"49" -> {
				ColumnGutter = getbfr.floatValue()
			}
			"50" -> {
				ColumnHeights = getbfr.floatValue()
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


	//	 Subclass marker (AcDbMText)
	//	100	
	const val AcDbMText: String = "AcDbMText"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( NominalText != null) sb.append( "\n 40\n"+NominalText )
		if( ReferenceRectangle != null) sb.append( "\n 41\n"+ReferenceRectangle )
		if( AttachmentPoint != null) sb.append( "\n 71\n"+AttachmentPoint )
		if( DrawingDirection != null) sb.append( "\n 72\n"+DrawingDirection )
		if( TextString != null) sb.append( "\n 1\n"+TextString )
		if( AdditionalText != null) sb.append( "\n 3\n"+AdditionalText )
		if( TextStyle != null) sb.append( "\n 7\n"+TextStyle )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( XaxisDirection != null) sb.append( "\n 11\n"+XaxisDirection?.x+"\n 21\n"+XaxisDirection?.y+"\n 31\n"+XaxisDirection?.z )
		if( HorizontalWidth != null) sb.append( "\n 42\n"+HorizontalWidth )
		if( VerticalHeight != null) sb.append( "\n 43\n"+VerticalHeight )
		if( RotationAngle != null) sb.append( "\n 50\n"+RotationAngle )
		if( MtextLine != null) sb.append( "\n 73\n"+MtextLine )
		if( MtextLine1 != null) sb.append( "\n 44\n"+MtextLine1 )
		if( BackgroundFill != null) sb.append( "\n 90\n"+BackgroundFill )
		if( BackgroundColor != null) sb.append( "\n 420\n"+BackgroundColor )
		if( BackgroundColor1 != null) sb.append( "\n 430\n"+BackgroundColor1 )
		if( FillBox != null) sb.append( "\n 45\n"+FillBox )
		if( BackgroundFill1 != null) sb.append( "\n 63\n"+BackgroundFill1 )
		if( TransparencyBackground != null) sb.append( "\n 441\n"+TransparencyBackground )
		if( ColumnType != null) sb.append( "\n 75\n"+ColumnType )
		if( ColumnCount != null) sb.append( "\n 76\n"+ColumnCount )
		if( ColumnFlow != null) sb.append( "\n 78\n"+ColumnFlow )
		if( ColumnAutoheight != null) sb.append( "\n 79\n"+ColumnAutoheight )
		if( ColumnWidth != null) sb.append( "\n 48\n"+ColumnWidth )
		if( ColumnGutter != null) sb.append( "\n 49\n"+ColumnGutter )
		if( ColumnHeights != null) sb.append( "\n 50\n"+ColumnHeights )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMTEXT")
			sbX.append( sb)
		}

		 return sbX
	}}