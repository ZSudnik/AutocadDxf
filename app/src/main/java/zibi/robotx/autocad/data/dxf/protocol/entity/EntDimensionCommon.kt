package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntDimensionCommon(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Version number:, 0 = 2010
	//	280	
	var VersionNumber: Int? = null

	//	 Name of the block that contains the entities that make up the dimension picture
	//	2	
	var NameThe: String? = null

	//	 Definition point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of definition point (in WCS)
	//	10	20	30	
	var DefinitionPoint: Vector3f? = null

	//	 Middle point of dimension text (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of middle point of dimension text (in OCS)
	//	11	21	31	
	var MiddlePoint: Vector3f? = null

	//	 Dimension type:, Values 0-6 are integer values that represent the dimension type. Values 32, 64, and 128 are bit values, which are added to the integer values (value 32 is always set in R13 and later releases), 0 = Rotated, horizontal, or vertical, 1 = Aligned, 2 = Angular, 3 = Diameter, 4 = Radius, 5 = Angular 3-point, 6 = Ordinate, 32 = Indicates that the block reference (group code 2) is referenced by this dimension only, 64 = Ordinate type. This is a bit value (bit 7) used only with integer value 6. If set, ordinate is X-type if not set, ordinate is Y-type, 128 = This is a bit value (bit 8) added to the other group 70 values if the dimension text has been positioned at a user-defined location rather than at the default location
	//	70	
	var DimensionType: Int? = null

	//	 Attachment point:, 1 = Top left, 2 = Top center, 3 = Top right, 4 = Middle left, 5 = Middle center, 6 = Middle right, 7 = Bottom left, 8 = Bottom center, 9 = Bottom right
	//	71	
	var AttachmentPoint: Int? = null

	//	 Dimension text line-spacing style (optional):, 1 (or missing) = At least (taller characters will override), 2 = Exact (taller characters will not override)
	//	72	
	var DimensionText: Int? = null

	//	 Dimension text-line spacing factor (optional):, Percentage of default (3-on-5) line spacing to be applied. Valid values range from 0.25 to 4.00
	//	41	
	var DimensionTextline: Float? = null

	//	 Actual measurement (optional read-only value)
	//	42	
	var ActualMeasurement: Float? = null

	//	 Dimension text explicitly entered by the user. Optional default is the measurement. If null or <>, the dimension measurement is drawn as the text, if  (one blank space), the text is suppressed. Anything else is drawn as the text
	//	1	
	var DimensionText1: String? = null

	//	 The optional group code 53 is the rotation angle of the dimension text away from its default orientation (the direction of the dimension line) (optional)
	//	53	
	var TheOptional: Float? = null

	//	 All dimension types have an optional 51 group code, which indicates the horizontal direction for the dimension entity. The dimension entity determines the orientation of dimension text and lines for horizontal, vertical, and rotated linear dimensions, This group value is the negative of the angle between the OCS X axis and the UCS X axis. It is always in the XY plane of the OCS
	//	51	
	var AllDimension: Float? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Dimension style name
	//	3	
	var DimensionStyle: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"280" -> {
				VersionNumber = getbfr.intValue()
			}
			"2" -> {
				NameThe = getbfr.stringValue()
			}
			"10" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( DefinitionPoint == null ) DefinitionPoint = Vector3f()
				DefinitionPoint?.z = getbfr.floatValue()
			}
			"11" -> {
				if( MiddlePoint == null ) MiddlePoint = Vector3f()
				MiddlePoint?.x = getbfr.floatValue()
			}
			"21" -> {
				if( MiddlePoint == null ) MiddlePoint = Vector3f()
				MiddlePoint?.y = getbfr.floatValue()
			}
			"31" -> {
				if( MiddlePoint == null ) MiddlePoint = Vector3f()
				MiddlePoint?.z = getbfr.floatValue()
			}
			"70" -> {
				DimensionType = getbfr.intValue()
			}
			"71" -> {
				AttachmentPoint = getbfr.intValue()
			}
			"72" -> {
				DimensionText = getbfr.intValue()
			}
			"41" -> {
				DimensionTextline = getbfr.floatValue()
			}
			"42" -> {
				ActualMeasurement = getbfr.floatValue()
			}
			"1" -> {
				DimensionText1 = getbfr.stringValue()
			}
			"53" -> {
				TheOptional = getbfr.floatValue()
			}
			"51" -> {
				AllDimension = getbfr.floatValue()
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
			"3" -> {
				DimensionStyle = getbfr.stringValue()
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


	//	 Subclass marker (AcDbDimension)
	//	100	
	const val AcDbDimension: String = "AcDbDimension"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( VersionNumber != null) sb.append( "\n 280\n"+VersionNumber )
		if( NameThe != null) sb.append( "\n 2\n"+NameThe )
		if( DefinitionPoint != null) sb.append( "\n 10\n"+DefinitionPoint?.x+"\n 20\n"+DefinitionPoint?.y+"\n 30\n"+DefinitionPoint?.z )
		if( MiddlePoint != null) sb.append( "\n 11\n"+MiddlePoint?.x+"\n 21\n"+MiddlePoint?.y+"\n 31\n"+MiddlePoint?.z )
		if( DimensionType != null) sb.append( "\n 70\n"+DimensionType )
		if( AttachmentPoint != null) sb.append( "\n 71\n"+AttachmentPoint )
		if( DimensionText != null) sb.append( "\n 72\n"+DimensionText )
		if( DimensionTextline != null) sb.append( "\n 41\n"+DimensionTextline )
		if( ActualMeasurement != null) sb.append( "\n 42\n"+ActualMeasurement )
		if( DimensionText1 != null) sb.append( "\n 1\n"+DimensionText1 )
		if( TheOptional != null) sb.append( "\n 53\n"+TheOptional )
		if( AllDimension != null) sb.append( "\n 51\n"+AllDimension )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( DimensionStyle != null) sb.append( "\n 3\n"+DimensionStyle )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nDimensionCommon")
			sbX.append( sb)
		}

		 return sbX
	}}