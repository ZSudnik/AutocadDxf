package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntATTRIB(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Text start point (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of text start point (in OCS)
	//	10	20	30	
	var TextStart: Vector3f? = null

	//	 Text height
	//	40	
	var TextHeight: Float? = null

	//	 Default value (string)
	//	1	
	var DefaultValue: String? = null

	//	 Version number:, 0 = 2010
	//	280	
	var VersionNumber: Int? = null

	//	 Attribute tag (string cannot contain spaces)
	//	2	
	var AttributeTag: String? = null

	//	 Attribute flags:, 1 = Attribute is invisible (does not appear), 2 = This is a constant attribute, 4 = Verification is required on input of this attribute, 8 = Attribute is preset (no prompt during insertion)
	//	70	
	var AttributeFlags: Int? = null

	//	 Field length (optional default = 0) (not currently used)
	//	73	
	var FieldLength: Int? = null

	//	 Text rotation (optional default = 0)
	//	50	
	var TextRotation: Float? = null

	//	 Relative X scale factor (width) (optional default = 1). This value is also adjusted when fit-type text is used
	//	41	
	var RelativeX: Float? = null

	//	 Oblique angle (optional default = 0)
	//	51	
	var ObliqueAngle: Float? = null

	//	 Text style name (optional default = STANDARD)
	//	7	
	var TextStyle: String? = null

	//	 Text generation flags (optional default = 0). See TEXT group codes
	//	71	
	var TextGeneration: Int? = null

	//	 Horizontal text justification type (optional default = 0). See TEXT group codes
	//	72	
	var HorizontalText: Int? = null

	//	 Vertical text justification type (optional default = 0). See group code 73 inTEXT
	//	74	
	var VerticalText: Int? = null

	//	 Alignment point (in OCS) (optional), DXF: X value APP: 3D point, Present only if 72 or 74 group is present and nonzeroDXF: Y and Z values of alignment point (in OCS) (optional)
	//	11	21	31	
	var AlignmentPoint: Vector3f? = null

	//	 Extrusion direction. Present only if the entity's extrusion direction is not parallel to the WCS Z axis (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Lock position flag. Locks the position of the attribute within the block reference
	//	280	
	var LockPosition: Int? = null

	//	 Duplicate record cloning flag (determines how to merge duplicate entries):, 1 = Keep existing
	//	280	
	var DuplicateRecord: Int? = null

	//	 MText flag:, 2 = multiline attribute, 4 = constant multiline attribute definition
	//	70	
	var MTextFlag: Int? = null

	//	 isReallyLocked flag:, 0 = unlocked, 1 = locked
	//	70	
	var IsReallyLockedFlag: Int? = null

	//	 Number of secondary attributes or attribute definitions
	//	70	
	var NumberSecondary: Int? = null

	//	 Hard-pointer id of secondary attribute(s) or attribute definition(s)
	//	340	
	var HardpointerId: String? = null

	//	 Alignment point of attribute or attribute definition, DXF: X value APP: 3D pointDXF: Y and Z values of insertion point
	//	10	20	30	
	var AlignmentPoint1: Vector3f? = null

	//	 current annotation scale
	//	40	
	var CurrentAnnotation: Float? = null

	//	 attribute or attribute definition tag string
	//	2	
	var AttributeAttribute: String? = null

	//	 Entity type (MTEXT)
	//	0	
	var EntityType: String? = null

	//	 Absent or zero indicates entity is in model space. 1 indicates entity is in paper space (optional)
	//	67	
	var AbsentZero: Int? = null

	//	 Layer name
	//	8	
	var LayerName: String? = null

	//	 Insertion point, DXF: X value APP: 3D pointDXF: Y and Z values of insertion point
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 Nominal (initial) text height
	//	40	
	var NominalText: Float? = null

	//	 Reference rectangle width
	//	41	
	var ReferenceRectangle: Float? = null

	//	 Defined annotation height
	//	46	
	var DefinedAnnotation: Float? = null

	//	 Attachment point:, 1 = Top left 2 = Top center 3 = Top right, 4 = Middle left 5 = Middle center 6 = Middle right, 7 = Bottom left 8 = Bottom center 9 = Bottom right
	//	71	
	var AttachmentPoint: Int? = null

	//	 Drawing direction:, 1 = Left to right, 3 = Top to bottom, 5 = By style (the flow direction is inherited from the associated text style)
	//	72	
	var DrawingDirection: Int? = null

	//	 Text string, If the text string is less than 250 characters, all characters appear in group 1. If the text string is greater than 250 characters, the string is divided into 250-character chunks, which appear in one or more group 3 codes. If group 3 codes are used, the last group is a group 1 and has fewer than 250 characters.
	//	1	
	var TextString: String? = null

	//	 Additional text (always in 250-character chunks) (optional)
	//	3	
	var AdditionalText: String? = null

	//	 DXF: X value APP: 3D vectText style name (STANDARD if not provided) (optional)
	//	7	
	var XValue: String? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection1: Vector3f? = null

	//	 X-axis direction vector (in WCS), DXF: X value APP: 3D vectorDXF: Y and Z values of X-axis direction vector (in WCS)
	//	11	21	31	
	var XaxisDirection: Vector3f? = null

	//	 Horizontal width of the characters that make up the mtext entity., This value will always be equal to or less than the value of group code 41 (read-only, ignored if supplied).
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

	//	 Mtext line spacing factor (optional):, Percentage of default (3-on-5) line spacing to be applied., Valid values range from 0.25 to 4.00
	//	44	
	var MtextLine1: Float? = null

	//	 Background fill setting:, 0 = Background fill off, 1 = Use background fill color, 2 = Use drawing window color as background fill color
	//	90	
	var BackgroundFill: Int? = null

	//	 Background color (if color index number)
	//	63	
	var BackgroundColor: Int? = null

	//	 Background color (if RGB color)
	//	420	
	var BackgroundColor1: Int? = null

	//	 Background color (if color name)
	//	430	
	var BackgroundColor2: String? = null

	//	 Fill box scale (optional):, Determines how much border is around the text.
	//	45	
	var FillBox: Float? = null

	//	 Background fill color (optional):, Color to use for background fill when group code 90 is 1.
	//	63	
	var BackgroundFill1: Int? = null

	//	 Transparency of background fill color (not implemented)
	//	441	
	var TransparencyBackground: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
			}
			"10" -> {
				if( TextStart == null ) TextStart = Vector3f()
				TextStart?.x = getbfr.floatValue()
			}
			"20" -> {
				if( TextStart == null ) TextStart = Vector3f()
				TextStart?.y = getbfr.floatValue()
			}
			"30" -> {
				if( TextStart == null ) TextStart = Vector3f()
				TextStart?.z = getbfr.floatValue()
			}
			"40" -> {
				TextHeight = getbfr.floatValue()
			}
			"1" -> {
				DefaultValue = getbfr.stringValue()
			}
			"280" -> {
				VersionNumber = getbfr.intValue()
			}
			"2" -> {
				AttributeTag = getbfr.stringValue()
			}
			"70" -> {
				AttributeFlags = getbfr.intValue()
			}
			"73" -> {
				FieldLength = getbfr.intValue()
			}
			"50" -> {
				TextRotation = getbfr.floatValue()
			}
			"41" -> {
				RelativeX = getbfr.floatValue()
			}
			"51" -> {
				ObliqueAngle = getbfr.floatValue()
			}
			"7" -> {
				TextStyle = getbfr.stringValue()
			}
			"71" -> {
				TextGeneration = getbfr.intValue()
			}
			"72" -> {
				HorizontalText = getbfr.intValue()
			}
			"74" -> {
				VerticalText = getbfr.intValue()
			}
			"11" -> {
				if( AlignmentPoint == null ) AlignmentPoint = Vector3f()
				AlignmentPoint?.x = getbfr.floatValue()
			}
			"21" -> {
				if( AlignmentPoint == null ) AlignmentPoint = Vector3f()
				AlignmentPoint?.y = getbfr.floatValue()
			}
			"31" -> {
				if( AlignmentPoint == null ) AlignmentPoint = Vector3f()
				AlignmentPoint?.z = getbfr.floatValue()
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
			"280" -> {
				LockPosition = getbfr.intValue()
			}
			"280" -> {
				DuplicateRecord = getbfr.intValue()
			}
			"70" -> {
				MTextFlag = getbfr.intValue()
			}
			"70" -> {
				IsReallyLockedFlag = getbfr.intValue()
			}
			"70" -> {
				NumberSecondary = getbfr.intValue()
			}
			"340" -> {
				HardpointerId = getbfr.stringValue()
			}
			"10" -> {
				if( AlignmentPoint1 == null ) AlignmentPoint1 = Vector3f()
				AlignmentPoint1?.x = getbfr.floatValue()
			}
			"20" -> {
				if( AlignmentPoint1 == null ) AlignmentPoint1 = Vector3f()
				AlignmentPoint1?.y = getbfr.floatValue()
			}
			"30" -> {
				if( AlignmentPoint1 == null ) AlignmentPoint1 = Vector3f()
				AlignmentPoint1?.z = getbfr.floatValue()
			}
			"40" -> {
				CurrentAnnotation = getbfr.floatValue()
			}
			"2" -> {
				AttributeAttribute = getbfr.stringValue()
			}
			"0" -> {
				EntityType = getbfr.stringValue()
			}
			"67" -> {
				AbsentZero = getbfr.intValue()
			}
			"8" -> {
				LayerName = getbfr.stringValue()
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
			"40" -> {
				NominalText = getbfr.floatValue()
			}
			"41" -> {
				ReferenceRectangle = getbfr.floatValue()
			}
			"46" -> {
				DefinedAnnotation = getbfr.floatValue()
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
				XValue = getbfr.stringValue()
			}
			"210" -> {
				if( ExtrusionDirection1 == null ) ExtrusionDirection1 = Vector3f()
				ExtrusionDirection1?.x = getbfr.floatValue()
			}
			"220" -> {
				if( ExtrusionDirection1 == null ) ExtrusionDirection1 = Vector3f()
				ExtrusionDirection1?.y = getbfr.floatValue()
			}
			"230" -> {
				if( ExtrusionDirection1 == null ) ExtrusionDirection1 = Vector3f()
				ExtrusionDirection1?.z = getbfr.floatValue()
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
			"63" -> {
				BackgroundColor = getbfr.intValue()
			}
			"420" -> {
				BackgroundColor1 = getbfr.intValue()
			}
			"430" -> {
				BackgroundColor2 = getbfr.stringValue()
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


	//	 Subclass marker (AcDbText)
	//	100	
	const val AcDbText: String = "AcDbText"

	//	 Subclass marker (AcDbAttribute)
	//	100	
	const val AcDbAttribute: String = "AcDbAttribute"

	//	 Subclass marker (AcDbXrecord)
	//	100	
	const val AcDbXrecord: String = "AcDbXrecord"

	//	 Subclass marker (AcDbEntity)
	//	100	
	const val AcDbEntity: String = "AcDbEntity"

	//	 Subclass marker (AcDbMText)
	//	100	
	const val AcDbMText: String = "AcDbMText"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( TextStart != null) sb.append( "\n 10\n"+TextStart?.x+"\n 20\n"+TextStart?.y+"\n 30\n"+TextStart?.z )
		if( TextHeight != null) sb.append( "\n 40\n"+TextHeight )
		if( DefaultValue != null) sb.append( "\n 1\n"+DefaultValue )
		if( VersionNumber != null) sb.append( "\n 280\n"+VersionNumber )
		if( AttributeTag != null) sb.append( "\n 2\n"+AttributeTag )
		if( AttributeFlags != null) sb.append( "\n 70\n"+AttributeFlags )
		if( FieldLength != null) sb.append( "\n 73\n"+FieldLength )
		if( TextRotation != null) sb.append( "\n 50\n"+TextRotation )
		if( RelativeX != null) sb.append( "\n 41\n"+RelativeX )
		if( ObliqueAngle != null) sb.append( "\n 51\n"+ObliqueAngle )
		if( TextStyle != null) sb.append( "\n 7\n"+TextStyle )
		if( TextGeneration != null) sb.append( "\n 71\n"+TextGeneration )
		if( HorizontalText != null) sb.append( "\n 72\n"+HorizontalText )
		if( VerticalText != null) sb.append( "\n 74\n"+VerticalText )
		if( AlignmentPoint != null) sb.append( "\n 11\n"+AlignmentPoint?.x+"\n 21\n"+AlignmentPoint?.y+"\n 31\n"+AlignmentPoint?.z )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( LockPosition != null) sb.append( "\n 280\n"+LockPosition )
		if( DuplicateRecord != null) sb.append( "\n 280\n"+DuplicateRecord )
		if( MTextFlag != null) sb.append( "\n 70\n"+MTextFlag )
		if( IsReallyLockedFlag != null) sb.append( "\n 70\n"+IsReallyLockedFlag )
		if( NumberSecondary != null) sb.append( "\n 70\n"+NumberSecondary )
		if( HardpointerId != null) sb.append( "\n 340\n"+HardpointerId )
		if( AlignmentPoint1 != null) sb.append( "\n 10\n"+AlignmentPoint1?.x+"\n 20\n"+AlignmentPoint1?.y+"\n 30\n"+AlignmentPoint1?.z )
		if( CurrentAnnotation != null) sb.append( "\n 40\n"+CurrentAnnotation )
		if( AttributeAttribute != null) sb.append( "\n 2\n"+AttributeAttribute )
		if( EntityType != null) sb.append( "\n 0\n"+EntityType )
		if( AbsentZero != null) sb.append( "\n 67\n"+AbsentZero )
		if( LayerName != null) sb.append( "\n 8\n"+LayerName )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( NominalText != null) sb.append( "\n 40\n"+NominalText )
		if( ReferenceRectangle != null) sb.append( "\n 41\n"+ReferenceRectangle )
		if( DefinedAnnotation != null) sb.append( "\n 46\n"+DefinedAnnotation )
		if( AttachmentPoint != null) sb.append( "\n 71\n"+AttachmentPoint )
		if( DrawingDirection != null) sb.append( "\n 72\n"+DrawingDirection )
		if( TextString != null) sb.append( "\n 1\n"+TextString )
		if( AdditionalText != null) sb.append( "\n 3\n"+AdditionalText )
		if( XValue != null) sb.append( "\n 7\n"+XValue )
		if( ExtrusionDirection1 != null) sb.append( "\n 210\n"+ExtrusionDirection1?.x+"\n 220\n"+ExtrusionDirection1?.y+"\n 230\n"+ExtrusionDirection1?.z )
		if( XaxisDirection != null) sb.append( "\n 11\n"+XaxisDirection?.x+"\n 21\n"+XaxisDirection?.y+"\n 31\n"+XaxisDirection?.z )
		if( HorizontalWidth != null) sb.append( "\n 42\n"+HorizontalWidth )
		if( VerticalHeight != null) sb.append( "\n 43\n"+VerticalHeight )
		if( RotationAngle != null) sb.append( "\n 50\n"+RotationAngle )
		if( MtextLine != null) sb.append( "\n 73\n"+MtextLine )
		if( MtextLine1 != null) sb.append( "\n 44\n"+MtextLine1 )
		if( BackgroundFill != null) sb.append( "\n 90\n"+BackgroundFill )
		if( BackgroundColor != null) sb.append( "\n 63\n"+BackgroundColor )
		if( BackgroundColor1 != null) sb.append( "\n 420\n"+BackgroundColor1 )
		if( BackgroundColor2 != null) sb.append( "\n 430\n"+BackgroundColor2 )
		if( FillBox != null) sb.append( "\n 45\n"+FillBox )
		if( BackgroundFill1 != null) sb.append( "\n 63\n"+BackgroundFill1 )
		if( TransparencyBackground != null) sb.append( "\n 441\n"+TransparencyBackground )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nATTRIB")
			sbX.append( sb)
		}

		 return sbX
	}}